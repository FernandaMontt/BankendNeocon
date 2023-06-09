package cl.forum.neoconportal.services;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.model.BalanceDetalle;
import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.BalanceResponseRest;

@Service
public class BalancesServiceImpl implements IBalancesService{

	Connection cn;
	String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "database=BD_NEOCON;"
                    + "user=sa;"
                    + "password=abril.2023;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
	ResultSet rs;
	
	@Override
	public ResponseEntity<BalanceResponseRest> findAllBalanceSheet() {
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_BALANCE_TEMP }")) {
				
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Balance balancess = new Balance();
					balancess.setBalanceTempId(rs.getInt("BALANCETEMP_ID"));
					balancess.setPeriodo(rs.getInt("PERIODO"));
					balancess.setAcronimo(rs.getString("ACRONIMO"));
					balancess.setFecha_incial(rs.getDate("FECHA_INICIAL"));
					balancess.setFecha_fin(rs.getDate("FECHA_FIN"));
					balancess.setNombreArchivo(rs.getString("NOMBREARCHIVO"));
					balancess.setCantidadRegistros(rs.getInt("CANTIDADREGISTROS"));
					balancess.setEstado(rs.getString("ESTADO"));
					balances.add(balancess);
	            }
	            response.getBalanceResponse().setBalance(balances);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BalanceResponseRest> uploadBalanceSheetFile(MultipartFile file, Integer periodo, String acronimo) {
		
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
	        fileContent = replaceCommasInsideQuotes(fileContent);
	        fileContent = fileContent.replaceAll("\r", ""); // Eliminar los caracteres \r
	        String[] rows = fileContent.split("\n");
	        int numtotal = 0;
	        //String[] headers = rows[0].split(",");
	        cn = DriverManager.getConnection(connectionUrl);
	        //Primero Fecha Inicial
	        CallableStatement cst1 = cn.prepareCall("{CALL SP_INSERT_BALANCE_FECHASINICIAL(?,?,?,?) }");
	        cst1.setInt(1, periodo);
			cst1.setString(2, acronimo);
			cst1.setInt(3, numtotal);
			cst1.setString(4, file.getOriginalFilename());
			rs = cst1.executeQuery();
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_BALANCEDETALLE_CSV(?,?,?,?,?) }");

	        for (int i = 1; i < rows.length; i++) {
	        	//String[] row = rows[i].split(";");
	        	String[] row = rows[i].split(",");
	        	String valor = row[2];
	        	String cuenta = row[0];
	        	cuenta = cuenta.replace(".", "");
	        	numtotal = i;
	        	valor = valor.replace(",", ".");
	        	valor = valor.replace("(", "").trim();
	        	valor = valor.replace(")", "").trim();
	        	valor = valor.replace(".", "");
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				cst.setDouble(3, Double.parseDouble(cuenta));
				cst.setString(4, row[1]);
				cst.setDouble(5, Double.parseDouble(valor));
				rs = cst.executeQuery();	        
	        }
	        
	        //Validar Balance
	        CallableStatement cst3 = cn.prepareCall("{CALL SP_VALIDAR_RUBRO_CUENTA(?,?) }");
	        cst3.setInt(1, periodo);
	        cst3.setString(2, acronimo);
			rs = cst3.executeQuery();
	        //empieza nuevo procedimiento que pone las fecha inicial y fecha fin
	        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_BALANCE_FECHASFIN(?,?,?) }");
	        cst2.setInt(1, periodo);
			cst2.setString(2, acronimo);
			cst2.setInt(3, numtotal);
			rs = cst2.executeQuery();
	        while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
	        	Balance balancess = new Balance();
				balancess.setBalanceTempId(rs.getInt("BALANCETEMP_ID"));
				balancess.setPeriodo(rs.getInt("PERIODO"));
				balancess.setAcronimo(rs.getString("ACRONIMO"));
				balancess.setFecha_incial(rs.getDate("FECHA_INICIAL"));
				balancess.setFecha_fin(rs.getDate("FECHA_FIN"));
				balancess.setNombreArchivo(rs.getString("NOMBREARCHIVO"));
				balancess.setCantidadRegistros(rs.getInt("CANTIDADREGISTROS"));
				balancess.setEstado(rs.getString("ESTADO"));
				balances.add(balancess);
			}
	        
	        if(numtotal != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla HEADERBALANCE_TEMP, BALANCE_TEMP_DETALLE";
		  	      String accion = "Se cargo balance v1";
		  	      String detalle = "Se cargo balance, periodo "+ periodo + " empresa " + 
		  	    		acronimo + " cantidad de lineas cargadas " + numtotal;
		  	      CallableStatement cstaccion = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
			  	    cstaccion.setString(1, detalleTabla);
			  	    cstaccion.setString(2, accion);
			  	    cstaccion.setString(3, username);
			  	    cstaccion.setString(4, detalle);
			  	    cstaccion.execute();
		        }

		} catch(Exception e) {
			
			//response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BalanceResponseRest> findBalanceSheetById(Integer periodo, String acronimo) {
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBALANCE_BYID(?,?) }")) {
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				rs = cst.executeQuery();
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Balance balancess = new Balance();
					balancess.setBalanceTempId(rs.getInt("BALANCETEMP_ID"));
					balancess.setPeriodo(rs.getInt("PERIODO"));
					balancess.setAcronimo(rs.getString("ACRONIMO"));
					balancess.setFecha_incial(rs.getDate("FECHA_INICIAL"));
					balancess.setFecha_fin(rs.getDate("FECHA_FIN"));
					balancess.setNombreArchivo(rs.getString("NOMBREARCHIVO"));
					balancess.setCantidadRegistros(rs.getInt("CANTIDADREGISTROS"));
					balancess.setEstado(rs.getString("ESTADO"));
					balances.add(balancess);
	            }
	            response.getBalanceResponse().setBalance(balances);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BalanceResponseRest> uploadBalanceSheetFile2(MultipartFile file, Integer periodo,
			String acronimo) {
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
	        fileContent = replaceCommasInsideQuotes(fileContent);
	        fileContent = fileContent.replaceAll("\r", ""); // Eliminar los caracteres \r
	        String[] rows = fileContent.split("\n");
	        int numtotal = 0;
	        //String[] headers = rows[0].split(",");
	        cn = DriverManager.getConnection(connectionUrl);
	        //Primero Fecha Inicial
	        CallableStatement cst1 = cn.prepareCall("{CALL SP_INSERT_BALANCE_FECHASINICIAL(?,?,?,?) }");
	        cst1.setInt(1, periodo);
			cst1.setString(2, acronimo);
			cst1.setInt(3, numtotal);
			cst1.setString(4, file.getOriginalFilename());
			rs = cst1.executeQuery();
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_BALANCEDETALLE_CSV(?,?,?,?,?) }");

	        for (int i = 1; i < rows.length; i++) {
	        	String[] row = rows[i].split(",");
	        	String valor = row[12];
	        	String valor2 = row[13];
	        	String cuenta = row[10];
	        	cuenta = cuenta.replace(".", "");
	        	numtotal = i;
	        	valor = valor.replace("(", "").trim();
	            valor2 = valor2.replace("(", "").trim();
	            valor = valor.replace(")", "").trim();
	            valor2 = valor2.replace(")", "").trim();
	            valor = valor.replace(",", "").trim();
	            valor2 = valor2.replace(",", "").trim();
	            valor = valor.replace(".", "");
	            valor2 = valor2.replace(".", "");
	            BigDecimal saldo = new BigDecimal(valor).subtract(new BigDecimal(valor2));
	            // Convertir el saldo a double sin ceros innecesarios
	            Double saldoDouble = saldo.stripTrailingZeros().doubleValue();
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				cst.setDouble(3, Double.parseDouble(cuenta));
				cst.setString(4, row[11]);
				cst.setDouble(5, saldoDouble);
				rs = cst.executeQuery();	        
	        }
	        
	        //Validar Balance
	        CallableStatement cst3 = cn.prepareCall("{CALL SP_VALIDAR_RUBRO_CUENTA(?,?) }");
	        cst3.setInt(1, periodo);
	        cst3.setString(2, acronimo);
			rs = cst3.executeQuery();
	        //empieza nuevo procedimiento que pone las fecha inicial y fecha fin
	        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_BALANCE_FECHASFIN(?,?,?) }");
	        cst2.setInt(1, periodo);
			cst2.setString(2, acronimo);
			cst2.setInt(3, numtotal);
			rs = cst2.executeQuery();
	        while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
	        	Balance balancess = new Balance();
				balancess.setBalanceTempId(rs.getInt("BALANCETEMP_ID"));
				balancess.setPeriodo(rs.getInt("PERIODO"));
				balancess.setAcronimo(rs.getString("ACRONIMO"));
				balancess.setFecha_incial(rs.getDate("FECHA_INICIAL"));
				balancess.setFecha_fin(rs.getDate("FECHA_FIN"));
				balancess.setNombreArchivo(rs.getString("NOMBREARCHIVO"));
				balancess.setCantidadRegistros(rs.getInt("CANTIDADREGISTROS"));
				balancess.setEstado(rs.getString("ESTADO"));
				balances.add(balancess);
			}
	        
	        if(numtotal != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla HEADERBALANCE_TEMP, BALANCE_TEMP_DETALLE";
		  	      String accion = "Se cargo balance v2";
		  	      String detalle = "Se cargo balance, periodo "+ periodo + " empresa " + 
		  	    		acronimo + " cantidad de lineas cargadas " + numtotal;
		  	      CallableStatement cstaccion = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
			  	    cstaccion.setString(1, detalleTabla);
			  	    cstaccion.setString(2, accion);
			  	    cstaccion.setString(3, username);
			  	    cstaccion.setString(4, detalle);
			  	    cstaccion.execute();
		        }

		} catch(Exception e) {
			
			//response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.OK);
	}
	
	private String replaceCommasInsideQuotes(String input) {
		StringBuilder result = new StringBuilder();
	    Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(input);
	    int lastIndex = 0;

	    while (matcher.find()) {
	        result.append(input, lastIndex, matcher.start()); // Agregar el texto antes de la coincidencia
	        String match = matcher.group(1); // Obtener el texto dentro de las comillas
	        match = match.replace(",", " ").replace("\"", " "); // Eliminar comas y comillas dentro del texto
	        // Verificar si el texto contiene paréntesis y si el contenido es numérico
	        if (match.contains("(") && match.contains(")")) {
	        	int startIndex = match.indexOf("(");
	            int endIndex = match.indexOf(")");
	            String content = match.substring(startIndex + 1, endIndex).trim();
	            if (content.matches("-?\\d+(\\.\\d+)?")) {
	                match = content; // Conservar solo el valor numérico dentro de los paréntesis
	            }
	        }
	        result.append(match); // Agregar el texto modificado
	        lastIndex = matcher.end();
	    }

	    if (lastIndex < input.length()) {
	        result.append(input.substring(lastIndex)); // Agregar el texto restante después de la última coincidencia
	    }

	    return result.toString();
	}
	
	@Override
	public ResponseEntity<BalanceDetalleResponseRest> validateAccountBalanceSheet(Integer id_balance) {
		BalanceDetalleResponseRest response = new BalanceDetalleResponseRest();
		List<BalanceDetalle> balanceDetalles = new ArrayList<BalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONCUENTA(?) }")) {
				cst.setString(1, id_balance.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	BalanceDetalle balanceDetalle = new BalanceDetalle();
	            	balanceDetalle.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
	            	balanceDetalle.setTestCuenta(rs.getInt("TESTCUENTA"));
	            	balanceDetalle.setMensaje(rs.getString("MENSAJE"));
	            	balanceDetalles.add(balanceDetalle);
	            }
	            response.getBalanceDetalleResponse().setBalanceDetalle(balanceDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<BalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<BalanceDetalleResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BalanceDetalleResponseRest> validateItemAccountBalanceSheet(Integer id_balance) {
		BalanceDetalleResponseRest response = new BalanceDetalleResponseRest();
		List<BalanceDetalle> balanceDetalles = new ArrayList<BalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONRUBRO(?) }")) {
				cst.setString(1, id_balance.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	BalanceDetalle balanceDetalle = new BalanceDetalle();
	            	balanceDetalle.setRubro(rs.getInt("RUBRO"));
	            	balanceDetalle.setTestRubro(rs.getInt("TESTRUBRO"));
	            	balanceDetalle.setMensaje(rs.getString("MENSAJE"));
	            	balanceDetalles.add(balanceDetalle);
	            }
	            response.getBalanceDetalleResponse().setBalanceDetalle(balanceDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<BalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<BalanceDetalleResponseRest>(response, HttpStatus.OK);
	}
}

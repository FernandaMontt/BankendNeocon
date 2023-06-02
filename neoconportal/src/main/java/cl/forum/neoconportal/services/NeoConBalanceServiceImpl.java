package cl.forum.neoconportal.services;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.NeoConBalanceDetalle;
import cl.forum.neoconportal.model.NeoConHeader;
import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;
import cl.forum.neoconportal.response.NeoConHeaderResponseRest;

@Service
public class NeoConBalanceServiceImpl implements INeoConBalanceService{
	
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
	@Transactional
	public ResponseEntity<NeoConHeaderResponseRest> saveNeoConHeaders(@RequestParam("periodo") Integer periodo,
			 @RequestParam("acronimo") String acronimo) {
		// TODO Auto-generated method stub
				NeoConHeaderResponseRest response = new NeoConHeaderResponseRest();
				List<NeoConHeader> neoConHeaders = new ArrayList<NeoConHeader>();
				
				try (Connection cn = DriverManager.getConnection(connectionUrl);
			            CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_NEOCONHEADER(?,?) }")) {
						cst.setInt(1, periodo);
						cst.setString(2, acronimo);
			        try (ResultSet rs = cst.executeQuery()) {
			            while (rs.next()) {
			            	NeoConHeader NeoConHeaderss = new NeoConHeader();
			            	NeoConHeaderss.setNeoconId(rs.getInt("NEOCONID"));
			            	NeoConHeaderss.setFechaProceso(rs.getDate("FECHAPROCESO"));
			            	NeoConHeaderss.setVersion(rs.getInt("VERSION"));
			            	NeoConHeaderss.setPeriodo(rs.getInt("PERIODO"));
			            	NeoConHeaderss.setAcronimo(rs.getString("ACRONIMO"));
			            	NeoConHeaderss.setHora(rs.getTime("HORA"));
			            	neoConHeaders.add(NeoConHeaderss);
			            }
			            response.getNeoConHeaderResponse().setNeoConHeader(neoConHeaders);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return new ResponseEntity<NeoConHeaderResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			    return new ResponseEntity<NeoConHeaderResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NeoConHeaderResponseRest> findNeoConBalanceId(Integer periodo, String acronimo) {
		// TODO Auto-generated method stub
		NeoConHeaderResponseRest response = new NeoConHeaderResponseRest();
		List<NeoConHeader> neoConHeaders = new ArrayList<NeoConHeader>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCENEOCONBALANCE_BYID(?,?) }")) {
				if(acronimo == "Todas") {
					acronimo = "0";
				}
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConHeader NeoConHeaderss = new NeoConHeader();
	            	NeoConHeaderss.setNeoconId(rs.getInt("NEOCONID"));
	            	NeoConHeaderss.setFechaProceso(rs.getDate("FECHAPROCESO"));
	            	NeoConHeaderss.setVersion(rs.getInt("VERSION"));
	            	NeoConHeaderss.setPeriodo(rs.getInt("PERIODO"));
	            	NeoConHeaderss.setAcronimo(rs.getString("ACRONIMO"));
	            	NeoConHeaderss.setHora(rs.getTime("HORA"));
	            	neoConHeaders.add(NeoConHeaderss);
	            }
	            response.getNeoConHeaderResponse().setNeoConHeader(neoConHeaders);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<NeoConHeaderResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<NeoConHeaderResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<NeoConBalanceDetalleResponseRest> saveNeoConBalanceDetalles(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo) {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();

		try {
	        // Leer y procesar el contenido del archivo
			String fileContent = new String(file.getBytes());
	        fileContent = replaceCommasInsideQuotes(fileContent);
	        fileContent = fileContent.replaceAll("\r", ""); // Eliminar los caracteres \r
	        String[] rows = fileContent.split("\n");
	        //String[] headers = rows[0].split(",");
	        cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_NEOCONBALANCEDETALLE(?,?,?,?,?) }");

	        for (int i = 1; i < rows.length; i++) {
	        	String[] row = rows[i].split(",");
	        	String valor = row[2];
	        	String cuenta = row[0];
	        	cuenta = cuenta.replace(".", "");
	        	valor = valor.replace(",", ".");
	        	valor = valor.replace("(", "").trim();
	        	valor = valor.replace(")", "").trim();
	        	valor = valor.replace(".", "");
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				cst.setDouble(3, Double.parseDouble(valor));
				cst.setDouble(4, Double.parseDouble(cuenta));
				cst.setString(5, row[1]);
				rs = cst.executeQuery();	        
	        }
	        //empieza nuevo procedimiento validacion cuenta y rubro
	        CallableStatement cst3 = cn.prepareCall("{CALL SP_VALDICAR_NATURALEZA_IG(?,?) }");
	        cst3.setInt(1, periodo);
			cst3.setString(2, acronimo);
			rs = cst3.executeQuery();
	        
	        
	        //empieza nuevo procedimiento que pone las fecha inicial y fecha fin
	        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_NEOCONITEM(?,?) }");
	        cst2.setInt(1, periodo);
			cst2.setString(2, acronimo);
			rs = cst2.executeQuery();
	        
	        while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
	        	
			}
	        
		} catch(Exception e) {
			
			//response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleId(Integer id_neocon) {
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONNATURALEZA(?) }")) {
				cst.setString(1, id_neocon.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConBalanceDetalle neoConBalanceDetalle = new NeoConBalanceDetalle();
	            	neoConBalanceDetalle.setPeriodo(rs.getInt("PERIODO"));
	            	neoConBalanceDetalle.setAcronimo(rs.getString("ACRONIMO"));
	            	neoConBalanceDetalle.setNombreEmpresa(rs.getString("NOMBREEMPRESA"));
	            	neoConBalanceDetalle.setCuenta(rs.getDouble("CUENTA"));
	            	neoConBalanceDetalle.setSaldo(rs.getDouble("SALDO"));
	            	neoConBalanceDetalle.setRubro(rs.getInt("RUBRO"));
	            	neoConBalanceDetalle.setDescripcionRubro(rs.getString("DESCRIPCIONRUBRO"));
	            	neoConBalanceDetalle.setTestCuenta(rs.getInt("TESTCUENTA"));
	            	neoConBalanceDetalle.setTestRubro(rs.getInt("TESTRUBRO"));
	            	neoConBalanceDetalle.setMensaje(rs.getString("MENSAJE"));
	            	neoConBalanceDetalles.add(neoConBalanceDetalle);
	            }
	            response.getNeoConBalanceDetalleResponse().setNeoConBalanceDetalle(neoConBalanceDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleRubroId(Integer id_neocon) {
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONINTERGRUPO(?) }")) {
				cst.setString(1, id_neocon.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConBalanceDetalle neoConBalanceDetalle = new NeoConBalanceDetalle();
	            	neoConBalanceDetalle.setPeriodo(rs.getInt("PERIODO"));
	            	neoConBalanceDetalle.setAcronimo(rs.getString("ACRONIMO"));
	            	neoConBalanceDetalle.setNombreEmpresa(rs.getString("NOMBREEMPRESA"));
	            	neoConBalanceDetalle.setCuenta(rs.getDouble("CUENTA"));
	            	neoConBalanceDetalle.setSaldo(rs.getDouble("SALDO"));
	            	neoConBalanceDetalle.setRubro(rs.getInt("RUBRO"));
	            	neoConBalanceDetalle.setDescripcionRubro(rs.getString("DESCRIPCIONRUBRO"));
	            	neoConBalanceDetalle.setTestCuenta(rs.getInt("TESTCUENTA"));
	            	neoConBalanceDetalle.setTestRubro(rs.getInt("TESTRUBRO"));
	            	neoConBalanceDetalle.setMensaje(rs.getString("MENSAJE"));
	            	neoConBalanceDetalles.add(neoConBalanceDetalle);
	            }
	            response.getNeoConBalanceDetalleResponse().setNeoConBalanceDetalle(neoConBalanceDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NeoConBalanceDetalleResponseRest> ReporteProestec(Integer periodo) {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_DESCARGA_PROESPECT(?) }")) {
				cst.setString(1, periodo.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConBalanceDetalle neoConBalanceDetalle = new NeoConBalanceDetalle();
	            	neoConBalanceDetalle.setEmpresa_nif(rs.getInt("COD_EMPRESA"));
	            	neoConBalanceDetalle.setNombreEmpresa(rs.getString("EMPRESA"));
	            	neoConBalanceDetalle.setCuenta(rs.getDouble("CUENTA"));
	            	neoConBalanceDetalle.setCuentaDescripcion(rs.getString("NOMBRE"));
	            	neoConBalanceDetalle.setRubro(rs.getInt("RUBRO"));
	            	neoConBalanceDetalle.setDescripcionRubro(rs.getString("NOMBRE_CUENTA_ESPAÑA"));
	            	neoConBalanceDetalle.setSaldo(rs.getDouble("SALDO_BALANCE"));
	            	neoConBalanceDetalles.add(neoConBalanceDetalle);
	            }
	            response.getNeoConBalanceDetalleResponse().setNeoConBalanceDetalle(neoConBalanceDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NeoConBalanceDetalleResponseRest> DescargaCodGInterfaz(@RequestParam("periodo") Integer periodo, @RequestParam("acronimo") String acronimo) {
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_DESCARGA_GINTERFAZ(?,?) }")) {
				if(acronimo == "Todas") {
					acronimo = "0";
				}
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConBalanceDetalle neoConBalanceDetalle = new NeoConBalanceDetalle();
	            	neoConBalanceDetalle.setPeriodo(rs.getInt("PERIODO"));
	            	neoConBalanceDetalle.setNumero_ig(rs.getInt("NUMERO_IG"));
	            	neoConBalanceDetalle.setRubro(rs.getInt("RUBRO"));
	            	neoConBalanceDetalle.setEmpresa_nif(rs.getInt("EMPRESA_NIF"));
	            	neoConBalanceDetalle.setSaldo2(rs.getDouble("SALDO2"));
	            	neoConBalanceDetalle.setSignodelSaldo(rs.getString("SIGNODELSALDO"));
	            	neoConBalanceDetalle.setNaturalezaRubro(rs.getString("NATURALEZARUBRO"));
	            	neoConBalanceDetalle.setSignoRubro(rs.getString("SIGNODELRUBRO"));
	            	neoConBalanceDetalle.setEmpresa2_nif(rs.getString("EMPRESA2_NIF"));
	            	neoConBalanceDetalle.setFiller1(rs.getString("FILLER1"));
	            	neoConBalanceDetalle.setFiller2(rs.getString("FILLER2"));
	            	neoConBalanceDetalle.setFiller3(rs.getString("FILLER3"));
	            	neoConBalanceDetalles.add(neoConBalanceDetalle);
	            }
	            response.getNeoConBalanceDetalleResponse().setNeoConBalanceDetalle(neoConBalanceDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<NeoConBalanceDetalleResponseRest>(response, HttpStatus.OK);
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

}

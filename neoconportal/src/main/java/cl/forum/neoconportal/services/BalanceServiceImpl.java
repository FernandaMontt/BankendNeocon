package cl.forum.neoconportal.services;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.model.CuentaEmpresa;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;

@Service
public class BalanceServiceImpl implements IBalanceService{

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
	public ResponseEntity<BalanceResponseRest> findAllBalances() {
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
	public ResponseEntity<BalanceResponseRest> uploadFile(MultipartFile file, Integer periodo, String acronimo) {
		
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
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
	        	String[] row = rows[i].split(";");
	        	String valor = row[2];
	        	numtotal = i;
	        	valor = valor.replace(",", ".");
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				cst.setInt(3, Integer.parseInt(row[0]));
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
	public ResponseEntity<BalanceResponseRest> findBalanceId(Integer periodo, String acronimo) {
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
	public ResponseEntity<BalanceResponseRest> uploadFileBalance2(MultipartFile file, Integer periodo,
			String acronimo) {
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
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
	        	numtotal = i;
	        	valor = valor.replace(",", ".");
	        	valor2 = valor2.replace(",", ".");
	            valor = valor.replace(",", "").trim();
	            valor2 = valor2.replace(",", "").trim();
	         // Verificar si hay más de un punto decimal y eliminarlos excepto el último
	            if (valor.indexOf('.') != valor.lastIndexOf('.')) {
	                valor = valor.substring(0, valor.lastIndexOf('.')).replace(".", "") + valor.substring(valor.lastIndexOf('.'));
	            }
	            if (valor2.indexOf('.') != valor2.lastIndexOf('.')) {
	                valor2 = valor2.substring(0, valor2.lastIndexOf('.')).replace(".", "") + valor2.substring(valor2.lastIndexOf('.'));
	            }
	        	double saldo = (Double.parseDouble(valor)-Double.parseDouble(valor2));
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				cst.setInt(3, Integer.parseInt(row[10]));
				cst.setString(4, row[11]);
				cst.setDouble(5, saldo);
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

	
	
	

}

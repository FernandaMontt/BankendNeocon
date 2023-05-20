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
	@Transactional
	public ResponseEntity<BalanceResponseRest> saveBalance(Balance balance) {
		// TODO Auto-generated method stub
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_BALANCE_CSV(?,?,?,?,?) }")) {
				cst.setInt(1, balance.getPeriodo());
				cst.setString(2, balance.getAcronimo());
				cst.setInt(3, balance.getCuentaCodigo());
				cst.setString(4, balance.getDescripcion());
				cst.setDouble(5, balance.getSaldo());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Balance balancess = new Balance();
					balancess.setBalanceTempId(rs.getInt("BALANCETEMP_ID"));
					balancess.setPeriodo(rs.getInt("PERIODO"));
					balancess.setAcronimo(rs.getString("ACRONIMO"));
					balancess.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
					balancess.setDescripcion(rs.getString("DESCRIPCION"));
					balancess.setSaldo(rs.getDouble("SALDO"));
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
	public ResponseEntity<BalanceResponseRest> findAll() {
		BalanceResponseRest response = new BalanceResponseRest();
		List<Balance> balances = new ArrayList<Balance>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_BALANCE_TEMP }")) {
				
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Balance balancess = new Balance();
					balancess.setPeriodo(rs.getInt("PERIODO"));
					balancess.setAcronimo(rs.getString("ACRONIMO"));
					balancess.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
					balancess.setDescripcion(rs.getString("DESCRIPCION"));
					balancess.setSaldo(rs.getDouble("SALDO"));
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
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_BALANCE_CSV(?,?,?,?,?) }");

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
	        //empieza nuevo procedimiento que pone las fecha inicial y fecha fin
	        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_BALANCE_FECHAS(?,?,?) }");
	        cst2.setInt(1, periodo);
			cst2.setString(2, acronimo);
			cst2.setInt(3, numtotal);
			rs = cst2.executeQuery();
	        while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
				Balance balancess = new Balance();
				balancess.setPeriodo(rs.getInt("PERIODO"));
				balancess.setAcronimo(rs.getString("ACRONIMO"));
				balancess.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
				balancess.setDescripcion(rs.getString("DESCRIPCION"));
				balancess.setSaldo(rs.getDouble("SALDO"));
				balances.add(balancess);
			}

	        response.getBalanceResponse().setBalance(balances);

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

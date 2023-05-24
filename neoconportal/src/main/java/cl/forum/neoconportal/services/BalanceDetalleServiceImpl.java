package cl.forum.neoconportal.services;

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

import cl.forum.neoconportal.model.BalanceDetalle;
import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.RubroResponseRest;

@Service
public class BalanceDetalleServiceImpl implements IBalanceDetalleService{
	
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
	public ResponseEntity<BalanceDetalleResponseRest> findBalanceDetalleId(Integer id_balance) {
		BalanceDetalleResponseRest response = new BalanceDetalleResponseRest();
		List<BalanceDetalle> balanceDetalles = new ArrayList<BalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONCUENTA(?) }")) {
				cst.setString(1, id_balance.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	BalanceDetalle balanceDetalle = new BalanceDetalle();
	            	balanceDetalle.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
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
	public ResponseEntity<BalanceDetalleResponseRest> findBalanceDetalleRubroId(Integer id_balance) {
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

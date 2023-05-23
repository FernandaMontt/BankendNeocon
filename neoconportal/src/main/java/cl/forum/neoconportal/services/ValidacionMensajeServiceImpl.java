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
import org.springframework.transaction.annotation.Transactional;

import cl.forum.neoconportal.model.ValidacionMensaje;
import cl.forum.neoconportal.response.ValidacionMensajeResponseRest;

@Service
public class ValidacionMensajeServiceImpl implements IValidacionMensajeService{
	
	
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
	@Transactional(readOnly=true)
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeCuentaId(Integer Id) {
		ValidacionMensajeResponseRest response = new ValidacionMensajeResponseRest();
		List<ValidacionMensaje> validacionMensajes = new ArrayList<ValidacionMensaje>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONCUENTA(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	ValidacionMensaje validacionMensaje = new ValidacionMensaje();
	            	validacionMensaje.setBalancetemp_id(rs.getInt("BALANCETEMP_ID"));
	            	validacionMensaje.setCuenta_codigo(rs.getInt("CUENTA_CODIGO"));
	            	validacionMensaje.setMensajeC(rs.getString("MENSAJEC"));
	            	validacionMensaje.setNombreArchivo(rs.getString("NOMBREARCHIVO"));
	            	validacionMensaje.setAcronimo(rs.getString("ACRONIMO"));
	    			validacionMensajes.add(validacionMensaje);
	            }
	            response.getValidacionMensajeResponse().setValidacionMensaje(validacionMensajes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeRubroId(Integer Id) {
		ValidacionMensajeResponseRest response = new ValidacionMensajeResponseRest();
		List<ValidacionMensaje> validacionMensajes = new ArrayList<ValidacionMensaje>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONRUBRO(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	ValidacionMensaje validacionMensaje = new ValidacionMensaje();
	            	validacionMensaje.setBalancetemp_id(rs.getInt("BALANCETEMP_ID"));
	            	validacionMensaje.setRubro(rs.getInt("RUBRO"));
	            	validacionMensaje.setMensajeR(rs.getString("MENSAJER"));
	            	validacionMensaje.setNombreArchivo(rs.getString("NOMBREARCHIVO"));
	            	validacionMensaje.setAcronimo(rs.getString("ACRONIMO"));
	    			validacionMensajes.add(validacionMensaje);
	            }
	            response.getValidacionMensajeResponse().setValidacionMensaje(validacionMensajes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeNaturalezaId(Integer Id) {
		ValidacionMensajeResponseRest response = new ValidacionMensajeResponseRest();
		List<ValidacionMensaje> validacionMensajes = new ArrayList<ValidacionMensaje>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONNATURALEZA(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	ValidacionMensaje validacionMensaje = new ValidacionMensaje();
	            	validacionMensaje.setNeoconId(rs.getInt("NEOCONID"));
	            	validacionMensaje.setRubro(rs.getInt("RUBRO"));
	            	validacionMensaje.setCuenta_codigo(rs.getInt("CUENTA"));
	            	validacionMensaje.setRubroNaturaleza(rs.getString("RUBRO_NATURALEZA"));
	            	validacionMensaje.setSignoSaldo(rs.getString("SIGNOSALDO"));
	            	validacionMensaje.setMensajeR(rs.getString("MENSAJE"));
	            	validacionMensaje.setAcronimo(rs.getString("ACRONIMO"));
	    			validacionMensajes.add(validacionMensaje);
	            }
	            response.getValidacionMensajeResponse().setValidacionMensaje(validacionMensajes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ValidacionMensajeResponseRest> findByValidacionMensajeIntergrupoId(Integer Id) {
		ValidacionMensajeResponseRest response = new ValidacionMensajeResponseRest();
		List<ValidacionMensaje> validacionMensajes = new ArrayList<ValidacionMensaje>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_VALIDACIONINTERGRUPO(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	ValidacionMensaje validacionMensaje = new ValidacionMensaje();
	            	validacionMensaje.setNeoconId(rs.getInt("NEOCONID"));
	            	validacionMensaje.setRubro(rs.getInt("RUBRO"));
	            	validacionMensaje.setCuenta_codigo(rs.getInt("CUENTA"));
	            	validacionMensaje.setIG(rs.getInt("IG"));
	            	validacionMensaje.setEmpresa1(rs.getString("EMPRESA1"));
	            	validacionMensaje.setEmpresa2(rs.getString("EMPRESA2"));
	            	validacionMensaje.setMensajeI(rs.getString("MENSAJEI"));
	            	validacionMensaje.setAcronimo(rs.getString("ACRONIMO"));
	    			validacionMensajes.add(validacionMensaje);
	            }
	            response.getValidacionMensajeResponse().setValidacionMensaje(validacionMensajes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<ValidacionMensajeResponseRest>(response, HttpStatus.OK);
	}

}

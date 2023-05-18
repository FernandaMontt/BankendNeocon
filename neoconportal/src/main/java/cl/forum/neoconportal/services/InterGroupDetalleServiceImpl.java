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

import cl.forum.neoconportal.model.InterGroupDetalle;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;

@Service
public class InterGroupDetalleServiceImpl implements IInterGroupDetalleService{
	
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
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumeroIG(Integer Id) {
	    InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
	    List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();

	    try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_INTERGROUP(?) }")) {
	        cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	                InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
	                interGroupDetalle.setNumeroIg(rs.getInt("NUMERO_IG"));
	                interGroupDetalle.setEmpresa(rs.getString("EMPRESA"));
	                interGroupDetalle.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	                interGroupDetalle.setEmpresa1(rs.getString("EMPRESA1"));
	                interGroupDetalle.setEmpresa2(rs.getString("EMPRESA2"));
	                interGroupDetalle.setRubro1(rs.getInt("RUBRO1"));
	                interGroupDetalle.setRubro2(rs.getInt("RUBRO2"));
	                interGroupDetalle.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
	                interGroupDetalle.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
	                InterGroupDetalles.add(interGroupDetalle);
	            }
	            response.getInterGroupDetalleResponse().setInterGroupDetalle(InterGroupDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupDetalleResponseRest> saveInterGruposD(InterGroupDetalle interGroupDetalle) {
		// TODO Auto-generated method stub
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_INTERGRUPODETALLE(?,?,?) }")) {
				cst.setInt(1, interGroupDetalle.getNumeroIg());
				cst.setString(2, interGroupDetalle.getEmpresa());
				cst.setInt(3, interGroupDetalle.getCuentaCodigo());
	        try (ResultSet rs = cst.executeQuery()) {
	            
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
						
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupDetalleResponseRest> deleteIntergrupodetalle(Integer numeroig, String acronimo,
			int codigocuenta) {
		// TODO Auto-generated method stub
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ELIMINAR_CUENTA_INTERGRUPO(?,?,?) }")) {
				cst.setInt(1, numeroig);
				cst.setString(6, acronimo);
				cst.setInt(3, codigocuenta);
	        try (ResultSet rs = cst.executeQuery()) {
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
						
	}

	@Override
	public ResponseEntity<InterGroupDetalleResponseRest> findById(Integer IdInter) {
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYIDINTER_INTERGROUP(?) }")) {
				cst.setInt(1, IdInter);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
	    			interGroupDetalle.setNumeroIg(rs.getInt("NUMERO_IG"));;
	    			interGroupDetalle.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	    			interGroupDetalle.setEmpresa1(rs.getString("EMPRESA1"));
	    			interGroupDetalle.setEmpresa2(rs.getString("EMPRESA2"));
	    			interGroupDetalle.setRubro1(rs.getInt("RUBRO1"));
	    			interGroupDetalle.setRubro2(rs.getInt("RUBRO2"));
	    			InterGroupDetalles.add(interGroupDetalle);
	            }
	            response.getInterGroupDetalleResponse().setInterGroupDetalle(InterGroupDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa(Integer codigorubro,
			String acronimo) {
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYRUBRO_EMPRESA_CUENTA_EMPRESAEMPRESA1(?,?) }")) {
				cst.setInt(1, codigorubro);
				cst.setString(2, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
					interGroupDetalle.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupDetalle.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
					interGroupDetalle.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					InterGroupDetalles.add(interGroupDetalle);
	            }
	            response.getInterGroupDetalleResponse().setInterGroupDetalle(InterGroupDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<InterGroupDetalleResponseRest> searchInterCuentaEmpresa2(Integer codigorubro,
			String acronimo) {
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYRUBRO_EMPRESA_CUENTA_EMPRESAEMPRESA2(?,?) }")) {
				cst.setInt(1, codigorubro);
				cst.setString(2, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
					interGroupDetalle.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupDetalle.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
					interGroupDetalle.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					InterGroupDetalles.add(interGroupDetalle);
	            }
	            response.getInterGroupDetalleResponse().setInterGroupDetalle(InterGroupDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
		
	}

}

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

import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.model.Rubro;
import cl.forum.neoconportal.response.InterGroupResponseRest;
import cl.forum.neoconportal.response.RubroResponseRest;

@Service
public class InterGroupHServiceImpl implements IInterGroupHService{
	
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
	public ResponseEntity<InterGroupResponseRest> findAll() {
		InterGroupResponseRest response = new InterGroupResponseRest();
		List<InterGroupHeader> interGroupHeaders = new ArrayList<InterGroupHeader>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_INTERGROUPHEADER }")) {
				
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupHeader interGroupHeaderss = new InterGroupHeader();
	            	interGroupHeaderss.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupHeaderss.setNumeroIg(rs.getInt("NUMERO_IG"));
	            	interGroupHeaderss.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	            	interGroupHeaderss.setEmpresa1(rs.getString("EMPRESA1"));
	            	interGroupHeaderss.setEmpresa2(rs.getString("EMPRESA2"));
	            	interGroupHeaderss.setRubro1(rs.getInt("RUBRO1"));
	            	interGroupHeaderss.setRubro2(rs.getInt("RUBRO2"));
	            	interGroupHeaderss.setEstado(rs.getString("ESTADO"));
	            	interGroupHeaders.add(interGroupHeaderss);
	            }
	            response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupResponseRest> saveInterGruposH(InterGroupHeader interGroupHeader) {
		// TODO Auto-generated method stub
		InterGroupResponseRest response = new InterGroupResponseRest();
		List<InterGroupHeader> interGroupHeaders = new ArrayList<InterGroupHeader>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_INTERGRUPO(?,?,?,?,?,?) }")) {
				cst.setInt(1, interGroupHeader.getNumeroIg());
				cst.setString(2, interGroupHeader.getDescripcionIg());
				cst.setString(3, interGroupHeader.getEmpresa1());
				cst.setString(4, interGroupHeader.getEmpresa2());
				cst.setInt(5, interGroupHeader.getRubro1());
				cst.setInt(6, interGroupHeader.getRubro2());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupHeader interGroupHeaderss = new InterGroupHeader();
	            	interGroupHeaderss.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupHeaderss.setNumeroIg(rs.getInt("NUMERO_IG"));
	            	interGroupHeaderss.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	            	interGroupHeaderss.setEmpresa1(rs.getString("EMPRESA1"));
	            	interGroupHeaderss.setEmpresa2(rs.getString("EMPRESA2"));
	            	interGroupHeaderss.setRubro1(rs.getInt("RUBRO1"));
	            	interGroupHeaderss.setRubro2(rs.getInt("RUBRO2"));
	            	interGroupHeaderss.setEstado(rs.getString("ESTADO"));
	            	interGroupHeaders.add(interGroupHeaderss);
	            }
	            response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
				
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupResponseRest> updatestateIntergrupo(Integer Id) {
		// TODO Auto-generated method stub
		InterGroupResponseRest response = new InterGroupResponseRest();
		List<InterGroupHeader> interGroupHeaders = new ArrayList<InterGroupHeader>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_DESHABILITAR_INTERGRUPO(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupHeader interGroupHeaderss = new InterGroupHeader();
	            	interGroupHeaderss.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupHeaderss.setNumeroIg(rs.getInt("NUMERO_IG"));
	            	interGroupHeaderss.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	            	interGroupHeaderss.setEmpresa1(rs.getString("EMPRESA1"));
	            	interGroupHeaderss.setEmpresa2(rs.getString("EMPRESA2"));
	            	interGroupHeaderss.setRubro1(rs.getInt("RUBRO1"));
	            	interGroupHeaderss.setRubro2(rs.getInt("RUBRO2"));
	            	interGroupHeaderss.setEstado(rs.getString("ESTADO"));
	            	interGroupHeaders.add(interGroupHeaderss);
	            }
	            response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
				
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupResponseRest> updateInterGruposH(InterGroupHeader interGroupHeader) {
		// TODO Auto-generated method stub
				InterGroupResponseRest response = new InterGroupResponseRest();
				List<InterGroupHeader> interGroupHeaders = new ArrayList<InterGroupHeader>();
				
				try (Connection cn = DriverManager.getConnection(connectionUrl);
			            CallableStatement cst = cn.prepareCall("{CALL SP_UPDATE_INTERGRUPO(?,?,?,?,?,?) }")) {
						cst.setInt(1, interGroupHeader.getNumeroIg());
						cst.setString(2, interGroupHeader.getDescripcionIg());
						cst.setString(3, interGroupHeader.getEmpresa1());
						cst.setString(4, interGroupHeader.getEmpresa2());
						cst.setInt(5, interGroupHeader.getRubro1());
						cst.setInt(6, interGroupHeader.getRubro2());
			        try (ResultSet rs = cst.executeQuery()) {
			            while (rs.next()) {
			            	InterGroupHeader interGroupHeaderss = new InterGroupHeader();
			            	interGroupHeaderss.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
			            	interGroupHeaderss.setNumeroIg(rs.getInt("NUMERO_IG"));
			            	interGroupHeaderss.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
			            	interGroupHeaderss.setEmpresa1(rs.getString("EMPRESA1"));
			            	interGroupHeaderss.setEmpresa2(rs.getString("EMPRESA2"));
			            	interGroupHeaderss.setRubro1(rs.getInt("RUBRO1"));
			            	interGroupHeaderss.setRubro2(rs.getInt("RUBRO2"));
			            	interGroupHeaderss.setEstado(rs.getString("ESTADO"));
			            	interGroupHeaders.add(interGroupHeaderss);
			            }
			            response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);

	}


}

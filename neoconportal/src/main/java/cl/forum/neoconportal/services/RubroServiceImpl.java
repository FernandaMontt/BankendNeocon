package cl.forum.neoconportal.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.forum.neoconportal.model.InterGroupDetalle;
import cl.forum.neoconportal.model.Rubro;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;
import cl.forum.neoconportal.response.RubroResponseRest;

import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;

@Service
public class RubroServiceImpl implements IRubroService{
	
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
	public ResponseEntity<RubroResponseRest> findAllRubro(String estado) {
		
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_RUBROS(?) }")) {
				cst.setString(1, estado);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubro = new Rubro();
	                rubro.setRubroId(rs.getInt("RUBRO_ID"));
	                rubro.setRubroCodigo(rs.getString("Rubro"));
	                rubro.setRubroDescripcion(rs.getString("Descripción"));
	                rubro.setRubroNaturaleza(rs.getString("Naturaleza"));
	                rubro.setRubroEstado(rs.getString("Estado"));
	                rubros.add(rubro);
	            }
	            response.getRubroResponse().setRubro(rubros);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<RubroResponseRest> findByRubroId(Integer Id) {
		
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_RUBROS(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubro = new Rubro();
	    			rubro.setRubroId(rs.getInt("RUBRO_ID"));
	    			rubro.setRubroCodigo(rs.getString("Rubro"));
	    			rubro.setRubroDescripcion(rs.getString("Descripción"));
	    			rubro.setRubroNaturaleza(rs.getString("Naturaleza"));
	    			rubro.setRubroEstado(rs.getString("Estado"));
	    			rubros.add(rubro);
	            }
	            response.getRubroResponse().setRubro(rubros);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<RubroResponseRest> updatestate(Integer Id) {
		// TODO Auto-generated method stub
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		String CodigoRubro = "";
		try (Connection cn = DriverManager.getConnection(connectionUrl);
				
	            CallableStatement cst = cn.prepareCall("{CALL SP_CHANGESTATE_RUBROS(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubro = new Rubro();
					rubro.setRubroId(rs.getInt("RUBRO_ID"));
					rubro.setRubroCodigo(rs.getString("RUBRO_CODIGO"));
					if(Id != 0) {
						CodigoRubro = rubro.getRubroCodigo();
					}
					rubro.setRubroDescripcion(rs.getString("RUBRO_DESCRIPCION"));
					rubro.setRubroNaturaleza(rs.getString("RUBRO_NATURALEZA"));
					rubro.setRubroEstado(rs.getString("RUBRO_ESTADO"));
	    			rubros.add(rubro);
	            }
	            
	        }
	        if(Id != 0) {
	          String username = System.getProperty("user.name");
	  	      String detalleTabla = "Tabla RUBRO";
	  	      String accion = "Cambio de estado";
	  	      String detalle = "Se deshabilito el Rubro " + CodigoRubro ;
	  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
	  	      cst2.setString(1, detalleTabla);
	  	      cst2.setString(2, accion);
	  	      cst2.setString(3, username);
	  	      cst2.setString(4, detalle);
	  	      rs = cst2.executeQuery();
	        }
	        response.getRubroResponse().setRubro(rubros);
	      
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<RubroResponseRest> findByRubroCodigo(String Codigo) {
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYCODIGO_RUBROS(?) }")) {
				cst.setString(1, Codigo.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubro = new Rubro();
	    			rubro.setRubroId(rs.getInt("RUBRO_ID"));
	    			rubro.setRubroCodigo(rs.getString("Rubro"));
	    			rubro.setRubroDescripcion(rs.getString("Descripción"));
	    			rubro.setRubroNaturaleza(rs.getString("Naturaleza"));
	    			rubro.setRubroEstado(rs.getString("Estado"));
	    			rubros.add(rubro);
	            }
	            response.getRubroResponse().setRubro(rubros);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<RubroResponseRest> createRubros(Rubro rubro) {
		// TODO Auto-generated method stub
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_RUBROS(?,?,?,?) }")) {
				cst.setString(1, rubro.getRubroCodigo());
				cst.setString(2, rubro.getRubroDescripcion());
				cst.setString(3, rubro.getRubroNaturaleza());
				cst.setString(4, rubro.getRubroEstado());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubross = new Rubro();
					rubross.setRubroId(rs.getInt("RUBRO_ID"));
					rubross.setRubroCodigo(rs.getString("RUBRO_CODIGO"));
					rubross.setRubroDescripcion(rs.getString("RUBRO_DESCRIPCION"));
					rubross.setRubroNaturaleza(rs.getString("RUBRO_NATURALEZA"));
					rubross.setRubroEstado(rs.getString("RUBRO_ESTADO"));
					rubros.add(rubross);
	            }
	        }
	        if(rubro.getRubroCodigo().toString() != "0") {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla RUBRO";
		  	      String accion = "Se agrego nuevo Rubro";
		  	      String detalle = "Nuevo Rubro agregado, código " + rubro.getRubroCodigo() + " nombre: " +  rubro.getRubroDescripcion()
		  	      + " naturaleza " + rubro.getRubroNaturaleza() + " estado " + rubro.getRubroEstado();
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getRubroResponse().setRubro(rubros);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<RubroResponseRest> updateRubros(Rubro rubro) {
		// TODO Auto-generated method stub
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_UPDATE_RUBROS(?,?,?,?,?) }")) {
				cst.setString(1, rubro.getRubroId().toString());
				cst.setString(2, rubro.getRubroCodigo());
				cst.setString(3, rubro.getRubroDescripcion());
				cst.setString(4, rubro.getRubroNaturaleza());
				cst.setString(5, rubro.getRubroEstado());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubross = new Rubro();
					rubross.setRubroId(rs.getInt("RUBRO_ID"));
					rubross.setRubroCodigo(rs.getString("RUBRO_CODIGO"));
					rubross.setRubroDescripcion(rs.getString("RUBRO_DESCRIPCION"));
					rubross.setRubroNaturaleza(rs.getString("RUBRO_NATURALEZA"));
					rubross.setRubroEstado(rs.getString("RUBRO_ESTADO"));
					rubros.add(rubross);
	            }
	            
	        }
	        if(rubro.getRubroCodigo().toString() != "0") {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla RUBRO";
		  	      String accion = "Se modificó Rubro";
		  	      String detalle = "Se modifica el Rubro código " + rubro.getRubroCodigo() + " nombre: " +  rubro.getRubroDescripcion()
		  	      + " naturaleza " + rubro.getRubroNaturaleza() + " estado " + rubro.getRubroEstado();
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getRubroResponse().setRubro(rubros);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<RubroResponseRest> findAllActivos() {
		RubroResponseRest response = new RubroResponseRest();
		List<Rubro> rubros = new ArrayList<Rubro>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_RUBROSACTIVOS }")) {
				
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	Rubro rubro = new Rubro();
	                rubro.setRubroId(rs.getInt("RUBRO_ID"));
	                rubro.setRubroCodigo(rs.getString("Rubro"));
	                rubro.setRubroDescripcion(rs.getString("Descripción"));
	                rubro.setRubroNaturaleza(rs.getString("Naturaleza"));
	                rubro.setRubroEstado(rs.getString("Estado"));
	                rubros.add(rubro);
	            }
	            response.getRubroResponse().setRubro(rubros);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<RubroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<RubroResponseRest>(response, HttpStatus.OK);
		
	}

}

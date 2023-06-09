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
import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.InterGroupDetalleResponseRest;
import cl.forum.neoconportal.response.InterGroupResponseRest;

@Service
public class InterGrupoServiceImpl implements IInterGrupoService{
	
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
	public ResponseEntity<InterGroupResponseRest> createInterGroup(InterGroupHeader interGroupHeader) {
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
	            
	        }
	        if(interGroupHeader.getNumeroIg() != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla INTERGRUPOHEADER";
		  	      String accion = "Se agrego nuevo INTERGRUPOHEADER";
		  	      String detalle = "Nuevo INTERGRUPOHEADER agregado, número ig " + interGroupHeader.getNumeroIg() +
		  	    		  " nombre intergrupo " + interGroupHeader.getDescripcionIg() + " empresa 1 " + interGroupHeader.getEmpresa1()
		  	    		  + " empresa 2 " + interGroupHeader.getEmpresa2() + " rubro 1 " + interGroupHeader.getRubro1() + 
		  	    		  " rubro 2 " + interGroupHeader.getRubro2();
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
				
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupResponseRest> updateInterGroupState(Integer Id) {
		// TODO Auto-generated method stub
		InterGroupResponseRest response = new InterGroupResponseRest();
		List<InterGroupHeader> interGroupHeaders = new ArrayList<InterGroupHeader>();
		int CodigoIntergrupo = 0;
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_DESHABILITAR_INTERGRUPO(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupHeader interGroupHeaderss = new InterGroupHeader();
	            	interGroupHeaderss.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupHeaderss.setNumeroIg(rs.getInt("NUMERO_IG"));
	            	if(Id != 0) {
	            		CodigoIntergrupo = interGroupHeaderss.getNumeroIg();
					}
	            	interGroupHeaderss.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	            	interGroupHeaderss.setEmpresa1(rs.getString("EMPRESA1"));
	            	interGroupHeaderss.setEmpresa2(rs.getString("EMPRESA2"));
	            	interGroupHeaderss.setRubro1(rs.getInt("RUBRO1"));
	            	interGroupHeaderss.setRubro2(rs.getInt("RUBRO2"));
	            	interGroupHeaderss.setEstado(rs.getString("ESTADO"));
	            	interGroupHeaders.add(interGroupHeaderss);
	            }
	            
	        }
	        if(Id != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla INTERGRUPOHEADER";
		  	      String accion = "Cambio de estado";
		  	      String detalle = "Se deshabilito el intergrupo, número ig " + CodigoIntergrupo ;
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
				
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupResponseRest> updateInterGroup(InterGroupHeader interGroupHeader) {
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
			            
			        }
			        if(interGroupHeader.getNumeroIg() != 0) {
				          String username = System.getProperty("user.name");
				  	      String detalleTabla = "Tabla INTERGRUPOHEADER";
				  	      String accion = "Se modificó INTERGRUPOHEADER";
				  	      String detalle = "Se modifica la tabla INTERGRUPOHEADER, número ig " + interGroupHeader.getNumeroIg() +
				  	    		  " nombre intergrupo " + interGroupHeader.getDescripcionIg() + " empresa 1 " + interGroupHeader.getEmpresa1()
				  	    		  + " empresa 2 " + interGroupHeader.getEmpresa2() + " rubro 1 " + interGroupHeader.getRubro1() + 
				  	    		  " rubro 2 " + interGroupHeader.getRubro2();
				  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
				  	      cst2.setString(1, detalleTabla);
				  	      cst2.setString(2, accion);
				  	      cst2.setString(3, username);
				  	      cst2.setString(4, detalle);
				  	      rs = cst2.executeQuery();
				        }
			        response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);

	}
	
	@Override
	public ResponseEntity<InterGroupDetalleResponseRest> findByNumberIG(Integer Id) {
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
	                interGroupDetalle.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
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
	public ResponseEntity<InterGroupDetalleResponseRest> createInterGroupDetail(InterGroupDetalle interGroupDetalle) {
		// TODO Auto-generated method stub
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_INTERGRUPODETALLE(?,?,?) }")) {
				cst.setInt(1, interGroupDetalle.getNumeroIg());
				cst.setString(2, interGroupDetalle.getEmpresa());
				cst.setDouble(3, interGroupDetalle.getCuentaCodigo());
	        try (ResultSet rs = cst.executeQuery()) {
	        	InterGroupDetalle interGroupDetaller = new InterGroupDetalle();
                interGroupDetaller.setNumeroIg(rs.getInt("NUMERO_IG"));
                interGroupDetaller.setEmpresa(rs.getString("EMPRESA"));
                interGroupDetaller.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
                interGroupDetaller.setEmpresa1(rs.getString("EMPRESA1"));
                interGroupDetaller.setEmpresa2(rs.getString("EMPRESA2"));
                interGroupDetaller.setRubro1(rs.getInt("RUBRO1"));
                interGroupDetaller.setRubro2(rs.getInt("RUBRO2"));
                interGroupDetaller.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
                interGroupDetaller.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
                InterGroupDetalles.add(interGroupDetaller);
	        }
	        if(interGroupDetalle.getNumeroIg() != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla INETERGRUPODETALLE";
		  	      String accion = "Se agrego nuevo INETERGRUPODETALLE";
		  	      String detalle = "Nuevo INETERGRUPODETALLE agregado, número ig " + interGroupDetalle.getNumeroIg() + 
		  	    		  " empresa " + interGroupDetalle.getEmpresa() + " cuenta " + interGroupDetalle.getCuentaCodigo();
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getInterGroupDetalleResponse().setInterGroupDetalle(InterGroupDetalles);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
						
	}

	@Override
	@Transactional
	public ResponseEntity<InterGroupDetalleResponseRest> deleteInterGroupDetail(Integer numeroig, String acronimo,
			int codigocuenta) {
		// TODO Auto-generated method stub
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> interGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_ELIMINAR_CUENTA_INTERGRUPO(?,?,?) }")) {
				cst.setInt(1, numeroig);
				cst.setString(2, acronimo);
				cst.setInt(3, codigocuenta);
	        try (ResultSet rs = cst.executeQuery()) {
	        	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
                interGroupDetalle.setNumeroIg(rs.getInt("NUMERO_IG"));
                interGroupDetalle.setEmpresa(rs.getString("EMPRESA"));
                interGroupDetalle.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
                interGroupDetalle.setEmpresa1(rs.getString("EMPRESA1"));
                interGroupDetalle.setEmpresa2(rs.getString("EMPRESA2"));
                interGroupDetalle.setRubro1(rs.getInt("RUBRO1"));
                interGroupDetalle.setRubro2(rs.getInt("RUBRO2"));
                interGroupDetalle.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
                interGroupDetalle.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
                interGroupDetalles.add(interGroupDetalle);
	        }
	        if(numeroig != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla INETERGRUPODETALLE";
		  	      String accion = "Se borró la cuenta en la tabla INETERGRUPODETALLE";
		  	      String detalle = "Se borro la cuenta en la tabla INETERGRUPODETALLE, número ig " + numeroig +
		  	    		  " cuenta " + codigocuenta + " empresa " + acronimo;
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getInterGroupDetalleResponse().setInterGroupDetalle(interGroupDetalles);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupDetalleResponseRest>(response, HttpStatus.OK);
						
	}

	@Override
	public ResponseEntity<InterGroupResponseRest> findInterGroupById(Integer IdInter) {
		InterGroupResponseRest response = new InterGroupResponseRest();
		List<InterGroupHeader> InterGroupDetalles = new ArrayList<InterGroupHeader>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYIDINTER_INTERGROUP(?) }")) {
				cst.setInt(1, IdInter);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupHeader interGroupDetalle = new InterGroupHeader();
	            	interGroupDetalle.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	    			interGroupDetalle.setNumeroIg(rs.getInt("NUMERO_IG"));;
	    			interGroupDetalle.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
	    			interGroupDetalle.setEmpresa1(rs.getString("EMPRESA1"));
	    			interGroupDetalle.setEmpresa2(rs.getString("EMPRESA2"));
	    			interGroupDetalle.setRubro1(rs.getInt("RUBRO1"));
	    			interGroupDetalle.setRubro2(rs.getInt("RUBRO2"));
	    			interGroupDetalle.setEstado(rs.getString("ESTADO"));
	    			InterGroupDetalles.add(interGroupDetalle);
	            }
	            response.getInterGroupHResponse().setInterGroupHeader(InterGroupDetalles);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<InterGroupDetalleResponseRest> searchIntergroupCompanyAccounts(Integer codigorubro,
			String acronimo, Integer numeroig) {
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYRUBRO_EMPRESA_CUENTA_EMPRESAEMPRESA1(?,?,?) }")) {
				cst.setInt(1, numeroig);
				cst.setInt(2, codigorubro);
				cst.setString(3, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
					interGroupDetalle.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupDetalle.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
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
	public ResponseEntity<InterGroupDetalleResponseRest> searchIntergroupCompanyAccounts2(Integer codigorubro,
			String acronimo, Integer numeroig) {
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYRUBRO_EMPRESA_CUENTA_EMPRESAEMPRESA2(?,?,?) }")) {
				cst.setInt(1, numeroig);
				cst.setInt(2, codigorubro);
				cst.setString(3, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
					interGroupDetalle.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
	            	interGroupDetalle.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
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
	public ResponseEntity<InterGroupDetalleResponseRest> reportInterGroup() {
		InterGroupDetalleResponseRest response = new InterGroupDetalleResponseRest();
		List<InterGroupDetalle> InterGroupDetalles = new ArrayList<InterGroupDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_REPORT_INTERGRUPO }")) {
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	InterGroupDetalle interGroupDetalle = new InterGroupDetalle();
					interGroupDetalle.setNumeroIg(rs.getInt("NUMERO_IG"));
	            	interGroupDetalle.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
					interGroupDetalle.setEmpresaNif1(rs.getInt("EMPRESA_ORIGEN"));
					interGroupDetalle.setRubro(rs.getInt("RUBRO_CODIGO"));
					interGroupDetalle.setDescripcionRubro(rs.getString("RUBRO_DESCRIPCION"));
					interGroupDetalle.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
					interGroupDetalle.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					interGroupDetalle.setEmpresaNif2(rs.getInt("EMPRESA_DESTINO"));
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

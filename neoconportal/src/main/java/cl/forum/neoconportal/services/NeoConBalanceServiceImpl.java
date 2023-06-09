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
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
	public ResponseEntity<NeoConHeaderResponseRest> findNeoConBalanceSheetId(Integer periodo, String acronimo) {
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> createNeoConBalanceSheet(
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo) {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();

		try {
	        cn = DriverManager.getConnection(connectionUrl);
	        if (acronimo.equals("0")) {
	        	// Obtener todas las empresas disponibles
				Statement stmt = cn.createStatement();
				String query = "SELECT DISTINCT EMPRESA.ACRONIMO FROM EMPRESA INNER JOIN HEADERBALANCE_TEMP ON HEADERBALANCE_TEMP.ACRONIMO = EMPRESA.ACRONIMO ";
				ResultSet rsEmpresas = stmt.executeQuery(query);
				List<String> empresas = new ArrayList<>();
				while (rsEmpresas.next()) {
					empresas.add(rsEmpresas.getString("ACRONIMO"));
				}
				rsEmpresas.close();
				// Iterar sobre las empresas y ejecutar el procedimiento almacenado para cada una
				for (String empresa : empresas) {
					CallableStatement cst4 = cn.prepareCall("{CALL SP_INSERT_NEOCONBALANCESHEET(?,?) }");
					cst4.setInt(1, periodo);
					cst4.setString(2, empresa);
					ResultSet rsNeoConBalanceSheet = cst4.executeQuery();
					// Realizar el procesamiento de los resultados como sea necesario
					rsNeoConBalanceSheet.close();
					//empieza nuevo procedimiento validacion cuenta y rubro
			        CallableStatement cst3 = cn.prepareCall("{CALL SP_VALDICAR_NATURALEZA_IG(?,?) }");
			        cst3.setInt(1, periodo);
					cst3.setString(2, empresa);
					ResultSet rsNeoConBalanceSheet2 = cst3.executeQuery();
					rsNeoConBalanceSheet2.close();
					//empieza nuevo procedimiento que pone las fecha inicial y fecha fin
			        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_NEOCONITEM(?,?) }");
			        cst2.setInt(1, periodo);
					cst2.setString(2, empresa);
					ResultSet rsNeoConBalanceSheet3 = cst2.executeQuery();
					rsNeoConBalanceSheet3.close();
				}
	        }else {
	        	// Acá puedes mantener el código existente para el caso en que el acrónimo no sea "Todas"
				CallableStatement cst4 = cn.prepareCall("{CALL SP_INSERT_NEOCONBALANCESHEET(?,?) }");
				cst4.setInt(1, periodo);
				cst4.setString(2, acronimo);
				ResultSet rsNeoConBalanceSheet = cst4.executeQuery();
				// Realizar el procesamiento de los resultados como sea necesario
				rsNeoConBalanceSheet.close();
				//empieza nuevo procedimiento validacion cuenta y rubro
		        CallableStatement cst3 = cn.prepareCall("{CALL SP_VALDICAR_NATURALEZA_IG(?,?) }");
		        cst3.setInt(1, periodo);
				cst3.setString(2, acronimo);
				ResultSet rsNeoConBalanceSheet2 = cst3.executeQuery();
				rsNeoConBalanceSheet2.close();
				//empieza nuevo procedimiento que pone las fecha inicial y fecha fin
		        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_NEOCONITEM(?,?) }");
		        cst2.setInt(1, periodo);
				cst2.setString(2, acronimo);
				ResultSet rsNeoConBalanceSheet3 = cst2.executeQuery();
				rsNeoConBalanceSheet3.close();
	        }
	        if(periodo != 0) {
	        		if(acronimo.equals(0)) {
	        			acronimo = "Todas las empresas";
	        		}
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla NEOCONHEADER, NEOCONBALANCE, NEOCONITEM";
		  	      String accion = "Se cargo la interfaz";
		  	      String detalle = "Se cargo la interfaz, periodo "+ periodo + " empresa " + 
		  	    		acronimo;
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateAccountNeoconBalanceSheet(Integer id_neocon) {
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> validateItemAccountNeoconBalanceSheet(Integer id_neocon) {
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportNeoconBalanceSheetProespect(Integer periodo) {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_REPORT_PROESPECT(?) }")) {
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> reportNeoconBalanceSheet(@RequestParam("periodo") Integer periodo, @RequestParam("acronimo") String acronimo) {
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_REPORT_GINTERFAZ(?,?) }")) {
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
	            	neoConBalanceDetalle.setTestNaturaleza(rs.getInt("TESTNATURALEZA"));
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

	@Override
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoConItemById() {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
			try {
				cn = DriverManager.getConnection(connectionUrl);
				CallableStatement cstaccion;
				cstaccion = cn.prepareCall("{CALL SP_REFRESH_NEOCONTABLES }");
				cstaccion.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	
}

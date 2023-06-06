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
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.model.CuentaEmpresa;
import cl.forum.neoconportal.model.Empresa;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.CuentaEmpresaResponseRest;
import cl.forum.neoconportal.response.EmpresaResponseRest;

@Service
public class CuentaEmpresaServiceImpl implements ICuentaEmpresaService{

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
	public ResponseEntity<CuentaEmpresaResponseRest> findAllCuentas() {

			CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
			List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
			
			try (Connection cn = DriverManager.getConnection(connectionUrl);
		            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_CUENTA_EMPRESA }")) {

		        try (ResultSet rs = cst.executeQuery()) {
		            while (rs.next()) {
		            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
		            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
		            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
		            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
		            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
		            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("Cuenta"));
		            	cuentaEmpresa.setRubroCodigo(rs.getString("Rubro"));
		            	cuentaEmpresa.setRubroDescripcion(rs.getString("Descripcion_Rubro"));
		            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
		            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
		            	cuentaEmpresa.setEstado(rs.getString("Estado"));
		            	cuentaEmpresas.add(cuentaEmpresa);
		            }
		            response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
			
	}



	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaId(Integer Id) {

		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYIDCUENTA_EMPRESA(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("Cuenta"));
	            	cuentaEmpresa.setRubroCodigo(rs.getString("Rubro"));
	            	cuentaEmpresa.setRubroDescripcion(rs.getString("Descripcion_Rubro"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
	            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
	            	cuentaEmpresa.setEstado(rs.getString("Estado"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	            }
	            response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(Integer IdEmpresa) {

		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYEMPRESACUENTA_EMPRESA(?) }")) {
				cst.setString(1, IdEmpresa.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("Cuenta"));
	            	cuentaEmpresa.setRubroCodigo(rs.getString("Rubro"));
	            	cuentaEmpresa.setRubroDescripcion(rs.getString("Descripcion_Rubro"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
	            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
	            	cuentaEmpresa.setEstado(rs.getString("Estado"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	            }
	            response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
		
	}



	@Override
	@Transactional
	public ResponseEntity<CuentaEmpresaResponseRest> updatestateCuentaEmpresa(Integer Id) {
		
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		String CodigoCuenta = "";
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CHANGESTATE_CUENTA_EMPRESA(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
	            	if(Id != 0) {
	            		CodigoCuenta = cuentaEmpresa.getCuentaCodigo().toString();
					}
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
	            	cuentaEmpresa.setCuentaTipo(rs.getString("CUENTA_TIPO"));
	            	cuentaEmpresa.setEstado(rs.getString("ESTADO"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	            }
	            
	        }
	        if(Id != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla CUENTA_EMPRESA";
		  	      String accion = "Cambio de estado";
		  	      String detalle = "Se deshabilito la cuenta_empresa " + CodigoCuenta ;
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
	}



	@Override
	@Transactional
	public ResponseEntity<CuentaEmpresaResponseRest> createCuentaEmpresa(CuentaEmpresa cuentaEmpresa) {
		// TODO Auto-generated method stub
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_CUENTA_EMPRESA(?,?,?,?,?) }")) {
				cst.setInt(1, cuentaEmpresa.getEmpresaId());
				cst.setInt(2, cuentaEmpresa.getRubroId());
				cst.setDouble(3, cuentaEmpresa.getCuentaCodigo());
				cst.setString(4, cuentaEmpresa.getCuentaDescripcion());
				cst.setString(5, cuentaEmpresa.getEstado());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresass = new CuentaEmpresa();
					cuentaEmpresass.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
					cuentaEmpresass.setEmpresaId(rs.getInt("EMPRESA_ID"));
					cuentaEmpresass.setRubroId(rs.getInt("RUBRO_ID"));
					cuentaEmpresass.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
					cuentaEmpresass.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					cuentaEmpresass.setCuentaTipo(rs.getString("CUENTA_TIPO"));
					cuentaEmpresass.setEstado(rs.getString("ESTADO"));
	            	cuentaEmpresas.add(cuentaEmpresass);
	            }
	            
	        }
	        if(cuentaEmpresa.getCuentaCodigo().toString() != "0") {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla CUENTA_EMPRESA";
		  	      String accion = "Se agrego nueva cuenta_empresa";
		  	      String detalle = "Nueva cuenta_empresa agregada, cuenta " + cuentaEmpresa.getCuentaCodigo() + 
		  	    		  " empresa " + cuentaEmpresa.getEmpresaId() + " rubro " + cuentaEmpresa.getRubroId() +
		  	    		  " nombre cuenta " + cuentaEmpresa.getCuentaDescripcion() + " estado " + 
		  	    		cuentaEmpresa.getEstado();
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
				
	}



	@Override
	@Transactional
	public ResponseEntity<CuentaEmpresaResponseRest> updateCuentaEmpresa(CuentaEmpresa cuentaEmpresa) {
		// TODO Auto-generated method stub
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_UPDATE_CUENTA_EMPRESA(?,?,?,?,?,?) }")) {
				cst.setInt(1, cuentaEmpresa.getCuentaEmpresaId());
				cst.setInt(2, cuentaEmpresa.getEmpresaId());
				cst.setInt(3, cuentaEmpresa.getRubroId());
				cst.setDouble(4, cuentaEmpresa.getCuentaCodigo());
				cst.setString(5, cuentaEmpresa.getCuentaDescripcion());
				cst.setString(6, cuentaEmpresa.getEstado());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresass = new CuentaEmpresa();
					cuentaEmpresass.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
					cuentaEmpresass.setEmpresaId(rs.getInt("EMPRESA_ID"));
					cuentaEmpresass.setRubroId(rs.getInt("RUBRO_ID"));
					cuentaEmpresass.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
					cuentaEmpresass.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					cuentaEmpresass.setCuentaTipo(rs.getString("CUENTA_TIPO"));
					cuentaEmpresass.setEstado(rs.getString("ESTADO"));
	            	cuentaEmpresas.add(cuentaEmpresass);
	            }
	            
	        }
	        if(cuentaEmpresa.getCuentaCodigo().toString() != "0") {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla CUENTA_EMPRESA";
		  	      String accion = "Se modificó la cuenta_empresa";
		  	      String detalle = "Se modifica la cuenta_empresa, cuenta " + cuentaEmpresa.getCuentaCodigo() + 
		  	    		  " empresa " + cuentaEmpresa.getEmpresaId() + " rubro " + cuentaEmpresa.getRubroId() +
		  	    		  " nombre cuenta " + cuentaEmpresa.getCuentaDescripcion() + " estado " + 
		  	    		cuentaEmpresa.getEstado();
		  	      CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_ACCION(?,?,?,?) }");
		  	      cst2.setString(1, detalleTabla);
		  	      cst2.setString(2, accion);
		  	      cst2.setString(3, username);
		  	      cst2.setString(4, detalle);
		  	      rs = cst2.executeQuery();
		        }
	        response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
	}



	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaCodigo(String Codigo) {
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYCODIGOCUENTA_EMPRESA(?) }")) {
				cst.setString(1, Codigo.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("Cuenta"));
	            	cuentaEmpresa.setRubroCodigo(rs.getString("Rubro"));
	            	cuentaEmpresa.setRubroDescripcion(rs.getString("Descripcion_Rubro"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
	            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
	            	cuentaEmpresa.setEstado(rs.getString("Estado"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	            }
	            response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
		
	}



	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaEmpresaResponseRest> searchInterCuentaEmpresa(Integer codigorubro, String acronimo) {
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYRUBRO_EMPRESA_CUENTA_EMPRESA(?,?) }")) {
				cst.setInt(1, codigorubro);
				cst.setString(2, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	            }
	            response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<CuentaEmpresaResponseRest> reportePlanCuentasHomologo(String acronimo) {
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_DESCARGA_PLANCUENTASHOMOLOGADO(?) }")) {
				cst.setString(1, acronimo);
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCod_empresa(rs.getInt("COD_EMPRESA"));
	            	cuentaEmpresa.setAcronimo(rs.getString("ACRONIMO"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getDouble("CUENTA"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("NOMBRE"));
	            	cuentaEmpresa.setRubroCodigo(rs.getString("CODIGO_RUBRO"));
	            	cuentaEmpresa.setRubroDescripcion(rs.getString("NOMBRE_RUBRO"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	            }
	            response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<CuentaEmpresaResponseRest> cargarFile(MultipartFile file, String acronimo) {

		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<Balance> balances = new ArrayList<Balance>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
	        fileContent = replaceCommasInsideQuotes(fileContent);
	        fileContent = fileContent.replaceAll("\r", ""); // Eliminar los caracteres \r
	        String[] rows = fileContent.split("\n");
	        int numtotal = 0;
	        //String[] headers = rows[0].split(",");
	        cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_CUENTA_EMPRESA_CSV(?,?,?,?) }");

	        for (int i = 1; i < rows.length; i++) {
	        	String[] row = rows[i].split(",");
	        	String valor = row[2];
	        	numtotal = i;
	        	valor = valor.replaceAll("\r", "");
				cst.setString(1, acronimo);
				cst.setInt(2, Integer.parseInt(row[0]));
				cst.setString(3, row[1]);
				cst.setInt(4, Integer.parseInt(valor));
				rs = cst.executeQuery();	        
	        }
	        
	        
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
	        
	        if(numtotal != 0) {
		          String username = System.getProperty("user.name");
		  	      String detalleTabla = "Tabla CUENTA_EMPRESA";
		  	      String accion = "Se carga el archivo cuenta";
		  	      String detalle = "Se cargo la cuenta, empresa " + 
		  	    		acronimo + " cantidad de lineas cargadas " + numtotal;
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
			return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CuentaEmpresaResponseRest> cargarFilev2(MultipartFile file, String acronimo) {

		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentas = new ArrayList<CuentaEmpresa>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
	        fileContent = replaceCommasInsideQuotes(fileContent);
	        fileContent = fileContent.replaceAll("\r", ""); // Eliminar los caracteres \r
	        String[] rows = fileContent.split("\n");
	        int numtotal = 0;
	        //String[] headers = rows[0].split(",");
	        cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_CUENTA_EMPRESA_CSV_V2(?,?,?,?,?) }");

	        for (int i = 1; i < rows.length; i++) {
	        	String[] row = rows[i].split(",");
	        	String valor = "";
	        	String tipocuenta = "";
	        	String cuenta = row[0];
	        	cuenta = cuenta.replace(".", "");
	        	//Validaciones
	        	if(row.length == 2) {
	        		tipocuenta = "Activo";
	        		valor = "0";
	        	}
	        	else {
	        		tipocuenta = row[2];
	        	}
	        	if(row.length == 3) {
	        		tipocuenta = row[2];
	        		valor = "0";
	        	}
	        	else {
	        		tipocuenta = row[3];
	        	}
	        	if(row.length == 4) {
	        		tipocuenta = row[2]+"-"+row[3];
	        		if(row[2].isEmpty() && row[3].isEmpty()) {
	        			tipocuenta = "Activo";
	        		}
	        		valor = "0";	
	        	}if(row.length == 5) {
	        		tipocuenta = row[2]+"-"+row[3];
	        		if(row[2].isEmpty() && row[3].isEmpty()) {
	        			tipocuenta = "Activo";
	        		}
	        		valor = row[4];
	        	}
	        	numtotal = i;
	        	valor = valor.replaceAll("\r", "");
				cst.setString(1, acronimo);
				cst.setDouble(2, Double.parseDouble(cuenta));
				cst.setString(3, row[1]);
				cst.setInt(4, Integer.parseInt(valor));
				cst.setString(5, tipocuenta);
				rs = cst.executeQuery();	        
	        }
	        
	        
	        while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
	        	CuentaEmpresa cuentass = new CuentaEmpresa();
	        	cuentass.setCuentaEmpresaId(rs.getInt("CUENTA_EMPRESA_ID"));
	        	cuentass.setEmpresaId(rs.getInt("EMPRESA_ID"));
	        	cuentass.setRubroId(rs.getInt("RUBRO_ID"));
	        	cuentass.setCuentaCodigo(rs.getDouble("CUENTA_CODIGO"));
	        	cuentass.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
	        	cuentass.setCuentaTipo(rs.getString("CUENTA_TIPO"));
				cuentass.setEstado(rs.getString("ESTADO"));
				cuentas.add(cuentass);
			}
	        response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentas);

		} catch(Exception e) {
			
			//response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<CuentaEmpresaResponseRest>(response, HttpStatus.OK);
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

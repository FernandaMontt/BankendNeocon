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
	public ResponseEntity<CuentaEmpresaResponseRest> findAll() {

			CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
			List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
			
			try (Connection cn = DriverManager.getConnection(connectionUrl);
		            CallableStatement cst = cn.prepareCall("{CALL SP_ALL_CUENTA_EMPRESA }")) {

		        try (ResultSet rs = cst.executeQuery()) {
		            while (rs.next()) {
		            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
		            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
		            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
		            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
		            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
		            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
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
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
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
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
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
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CHANGESTATE_CUENTA_EMPRESA(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
	            	cuentaEmpresa.setCuentaTipo(rs.getString("CUENTA_TIPO"));
	            	cuentaEmpresa.setEstado(rs.getString("ESTADO"));
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
	public ResponseEntity<CuentaEmpresaResponseRest> saveCuentaEmpresa(CuentaEmpresa cuentaEmpresa) {
		// TODO Auto-generated method stub
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_CREATE_CUENTA_EMPRESA(?,?,?,?,?) }")) {
				cst.setInt(1, cuentaEmpresa.getEmpresaId());
				cst.setInt(2, cuentaEmpresa.getRubroId());
				cst.setInt(3, cuentaEmpresa.getCuentaCodigo());
				cst.setString(4, cuentaEmpresa.getCuentaDescripcion());
				cst.setString(5, cuentaEmpresa.getEstado());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresass = new CuentaEmpresa();
					cuentaEmpresass.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
					cuentaEmpresass.setEmpresaId(rs.getInt("EMPRESA_ID"));
					cuentaEmpresass.setRubroId(rs.getInt("RUBRO_ID"));
					cuentaEmpresass.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
					cuentaEmpresass.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					cuentaEmpresass.setCuentaTipo(rs.getString("CUENTA_TIPO"));
					cuentaEmpresass.setEstado(rs.getString("ESTADO"));
	            	cuentaEmpresas.add(cuentaEmpresass);
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
	public ResponseEntity<CuentaEmpresaResponseRest> updateCuentaEmpresa(CuentaEmpresa cuentaEmpresa) {
		// TODO Auto-generated method stub
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_UPDATE_CUENTA_EMPRESA(?,?,?,?,?,?) }")) {
				cst.setInt(1, cuentaEmpresa.getCuentaEmpresaId());
				cst.setInt(2, cuentaEmpresa.getEmpresaId());
				cst.setInt(3, cuentaEmpresa.getRubroId());
				cst.setInt(4, cuentaEmpresa.getCuentaCodigo());
				cst.setString(5, cuentaEmpresa.getCuentaDescripcion());
				cst.setString(6, cuentaEmpresa.getEstado());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresass = new CuentaEmpresa();
					cuentaEmpresass.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
					cuentaEmpresass.setEmpresaId(rs.getInt("EMPRESA_ID"));
					cuentaEmpresass.setRubroId(rs.getInt("RUBRO_ID"));
					cuentaEmpresass.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
					cuentaEmpresass.setCuentaDescripcion(rs.getString("CUENTA_DESCRIPCION"));
					cuentaEmpresass.setCuentaTipo(rs.getString("CUENTA_TIPO"));
					cuentaEmpresass.setEstado(rs.getString("ESTADO"));
	            	cuentaEmpresas.add(cuentaEmpresass);
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
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentaCodigo(String Codigo) {
		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYCODIGOCUENTA_EMPRESA(?) }")) {
				cst.setString(1, Codigo.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setNombreempresa(rs.getString("Empresa"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
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
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
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
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("CUENTA"));
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
	        	valor = valor.replace(",", ".");
				cst.setString(1, acronimo);
				cst.setInt(2, Integer.parseInt(row[0]));
				cst.setString(3, row[1]);
				cst.setInt(4, Integer.parseInt(row[2]));
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

}

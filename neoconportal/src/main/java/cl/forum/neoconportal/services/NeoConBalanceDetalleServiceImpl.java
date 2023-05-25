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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.model.BalanceDetalle;
import cl.forum.neoconportal.model.NeoConBalanceDetalle;
import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.NeoConBalanceDetalleResponseRest;

@Service
public class NeoConBalanceDetalleServiceImpl implements INeoConBalanceDetalleService{
	
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> saveNeoConBalanceDetalles(@RequestParam("file") MultipartFile file,
			@RequestParam("periodo") Integer periodo,@RequestParam("acronimo") String acronimo) {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();

		try {
	        // Leer y procesar el contenido del archivo
	        String fileContent = new String(file.getBytes());
	        String[] rows = fileContent.split("\n");
	        //String[] headers = rows[0].split(",");
	        cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_NEOCONBALANCEDETALLE(?,?,?,?,?) }");

	        for (int i = 1; i < rows.length; i++) {
	        	String[] row = rows[i].split(";");
	        	String valor = row[2];
	        	valor = valor.replace(",", ".");
				cst.setInt(1, periodo);
				cst.setString(2, acronimo);
				cst.setDouble(3, Double.parseDouble(valor));
				cst.setInt(4, Integer.parseInt(row[0]));
				cst.setString(5, row[1]);
				rs = cst.executeQuery();	        
	        }
	        //empieza nuevo procedimiento validacion cuenta y rubro
	        CallableStatement cst3 = cn.prepareCall("{CALL SP_VALDICAR_NATURALEZA_IG(?,?) }");
	        cst3.setInt(1, periodo);
			cst3.setString(2, acronimo);
			rs = cst3.executeQuery();
	        
	        
	        //empieza nuevo procedimiento que pone las fecha inicial y fecha fin
	        CallableStatement cst2 = cn.prepareCall("{CALL SP_INSERT_NEOCONITEM(?,?) }");
	        cst2.setInt(1, periodo);
			cst2.setString(2, acronimo);
			rs = cst2.executeQuery();
	        
	        while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
	        	
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleId(Integer id_neocon) {
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
	            	neoConBalanceDetalle.setCuenta(rs.getInt("CUENTA"));
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> findNeoBalanceDetalleRubroId(Integer id_neocon) {
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
	            	neoConBalanceDetalle.setCuenta(rs.getInt("CUENTA"));
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> ReporteProestec(Integer periodo) {
		// TODO Auto-generated method stub
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_DESCARGA_PROESPECT(?) }")) {
				cst.setString(1, periodo.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConBalanceDetalle neoConBalanceDetalle = new NeoConBalanceDetalle();
	            	neoConBalanceDetalle.setEmpresa_nif(rs.getInt("COD_EMPRESA"));
	            	neoConBalanceDetalle.setNombreEmpresa(rs.getString("EMPRESA"));
	            	neoConBalanceDetalle.setCuenta(rs.getInt("CUENTA"));
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
	public ResponseEntity<NeoConBalanceDetalleResponseRest> DescargaCodGInterfaz(Integer id_neocon) {
		NeoConBalanceDetalleResponseRest response = new NeoConBalanceDetalleResponseRest();
		List<NeoConBalanceDetalle> neoConBalanceDetalles = new ArrayList<NeoConBalanceDetalle>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_DESCARGA_GINTERFAZ(?) }")) {
				cst.setString(1, id_neocon.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	            	NeoConBalanceDetalle neoConBalanceDetalle = new NeoConBalanceDetalle();
	            	neoConBalanceDetalle.setPeriodo(rs.getInt("PERIODO"));
	            	neoConBalanceDetalle.setNumero_ig(rs.getInt("NUMERO_IG"));
	            	neoConBalanceDetalle.setRubro(rs.getInt("RUBRO"));
	            	neoConBalanceDetalle.setEmpresa_nif(rs.getInt("EMPRESA_NIF"));
	            	neoConBalanceDetalle.setSaldo2(rs.getDouble("SALDO2"));
	            	neoConBalanceDetalle.setSignodelSaldo(rs.getString("SIGNODELSALDO"));
	            	neoConBalanceDetalle.setNaturalezaRubro(rs.getInt("NATURALEZARUBRO"));
	            	neoConBalanceDetalle.setSignoRubro(rs.getString("SIGNODELRUBRO"));
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
	
	

}

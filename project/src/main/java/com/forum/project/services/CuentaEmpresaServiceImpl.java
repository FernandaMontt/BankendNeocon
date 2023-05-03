package com.forum.project.services;

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

import com.forum.project.model.CuentaEmpresa;
import com.forum.project.response.CuentaEmpresaResponseRest;

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
			
		try {
				cn = DriverManager.getConnection(connectionUrl);
				// Llamada al procedimiento almacenado
				CallableStatement cst = cn.prepareCall("{CALL SP_ALL_CUENTA_EMPRESA }");
	            // Ejecuta el procedimiento almacenado
	            rs = cst.executeQuery();
	            while(rs.next()) {
	            	// Se obtienen la salida del procedimineto almacenado
	            	CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
	            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
	            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
	            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
	            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
	            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
	            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
	            	cuentaEmpresa.setEstado(rs.getString("Estado"));
	            	cuentaEmpresas.add(cuentaEmpresa);
	         }
				response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
				//response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
				
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
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaId(Integer Id) {

		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
	try {
			cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYIDCUENTA_EMPRESA(?) }");
			//Obtener Id
			cst.setString(1, Id.toString());
			// Ejecuta el procedimiento almacenado
			rs = cst.executeQuery();
			while(rs.next()) {
			// Se obtienen la salida del procedimineto almacenado
				CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
            	cuentaEmpresa.setEstado(rs.getString("Estado"));
            	cuentaEmpresas.add(cuentaEmpresa);
		}
			response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
		} catch (SQLException e) {
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
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaEmpresaResponseRest> findByCuentasEmpresaEmpresaId(Integer IdEmpresa) {

		CuentaEmpresaResponseRest response = new CuentaEmpresaResponseRest();
		List<CuentaEmpresa> cuentaEmpresas = new ArrayList<CuentaEmpresa>();
		
	try {
			cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYEMPRESACUENTA_EMPRESA(?) }");
			//Obtener Id
			cst.setString(1, IdEmpresa.toString());
			// Ejecuta el procedimiento almacenado
			rs = cst.executeQuery();
			while(rs.next()) {
			// Se obtienen la salida del procedimineto almacenado
				CuentaEmpresa cuentaEmpresa = new CuentaEmpresa();
            	cuentaEmpresa.setCuentaEmpresaId(rs.getInt("ID_CUENTA_EMPRESA"));
            	cuentaEmpresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
            	cuentaEmpresa.setRubroId(rs.getInt("RUBRO_ID"));
            	cuentaEmpresa.setCuentaCodigo(rs.getInt("Cuenta"));
            	cuentaEmpresa.setCuentaDescripcion(rs.getString("Descripcion"));
            	cuentaEmpresa.setCuentaTipo(rs.getString("TipoCuenta"));
            	cuentaEmpresa.setEstado(rs.getString("Estado"));
            	cuentaEmpresas.add(cuentaEmpresa);
		}
			response.getCuentaEmpresaResponse().setCuentaEmpresa(cuentaEmpresas);
		} catch (SQLException e) {
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

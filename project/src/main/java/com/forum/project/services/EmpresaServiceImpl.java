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
import org.springframework.transaction.annotation.Transactional;

import com.forum.project.model.Empresa;
import com.forum.project.response.EmpresaResponseRest;

public class EmpresaServiceImpl implements IEmpresaService{

	
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
	public ResponseEntity<EmpresaResponseRest> findAll() {

		EmpresaResponseRest response = new EmpresaResponseRest();
		List<Empresa> empresas = new ArrayList<Empresa>();
		
	try {
			cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_ALL_EMPRESAS }");
            // Ejecuta el procedimiento almacenado
            rs = cst.executeQuery();
            while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
            	Empresa empresa = new Empresa();
            	empresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
            	empresa.setEmpresaNombre(rs.getString("EMPRESA_NOMBRE"));
            	empresa.setEmpresaNif(rs.getInt("EMPRESA_NIF"));
            	empresa.setAbreviatura(rs.getString("ABREVIATURA"));
            	empresa.setAcronimo(rs.getString("ACRONIMO"));
            	empresa.setEstado(rs.getString("ESTADO"));
            	empresas.add(empresa);
         }
			response.getEmpresaResponse().setEmpresa(empresas);
			//response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch(Exception e) {
			
			//response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}



	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<EmpresaResponseRest> findByEmpresaId(Integer Id) {

		EmpresaResponseRest response = new EmpresaResponseRest();
		List<Empresa> empresas = new ArrayList<Empresa>();
		
	try {
			cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_EMPRESA(?) }");
			//Obtener Id
			cst.setString(1, Id.toString());
			// Ejecuta el procedimiento almacenado
			rs = cst.executeQuery();
			while(rs.next()) {
			// Se obtienen la salida del procedimineto almacenado
				Empresa empresa = new Empresa();
            	empresa.setEmpresaId(rs.getInt("EMPRESA_ID"));
            	empresa.setEmpresaNombre(rs.getString("EMPRESA_NOMBRE"));
            	empresa.setEmpresaNif(rs.getInt("EMPRESA_NIF"));
            	empresa.setAbreviatura(rs.getString("ABREVIATURA"));
            	empresa.setAcronimo(rs.getString("ACRONIMO"));
            	empresa.setEstado(rs.getString("ESTADO"));
            	empresas.add(empresa);
		}
			response.getEmpresaResponse().setEmpresa(empresas);
		} catch (SQLException e) {
			e.getStackTrace();
			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

}

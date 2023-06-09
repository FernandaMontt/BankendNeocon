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

import cl.forum.neoconportal.model.Empresa;
import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.EmpresaResponseRest;
import cl.forum.neoconportal.response.InterGroupResponseRest;

@Service
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
	@Transactional(readOnly=true)
	public ResponseEntity<EmpresaResponseRest> findCompanyById(Integer Id) {

		EmpresaResponseRest response = new EmpresaResponseRest();
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		try (Connection cn = DriverManager.getConnection(connectionUrl);
	            CallableStatement cst = cn.prepareCall("{CALL SP_GET_SOURCEBYID_EMPRESA(?) }")) {
				cst.setString(1, Id.toString());
	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
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
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
		
	}

}

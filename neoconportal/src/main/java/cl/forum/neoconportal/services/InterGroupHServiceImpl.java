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

import cl.forum.neoconportal.model.InterGroupHeader;
import cl.forum.neoconportal.response.BalanceResponseRest;
import cl.forum.neoconportal.response.InterGroupResponseRest;

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
		
	try {
			cn = DriverManager.getConnection(connectionUrl);
			// Llamada al procedimiento almacenado
			CallableStatement cst = cn.prepareCall("{CALL SP_ALL_INTERGROUPHEADER }");
            // Ejecuta el procedimiento almacenado
            rs = cst.executeQuery();
            while(rs.next()) {
            	// Se obtienen la salida del procedimineto almacenado
            	InterGroupHeader interGroupHeaderss = new InterGroupHeader();
            	interGroupHeaderss.setInterGrupoId(rs.getInt("INTERGRUPO_ID"));
            	interGroupHeaderss.setNumeroIg(rs.getInt("NUMERO_IG"));
            	interGroupHeaderss.setDescripcionIg(rs.getString("DESCRIPCION_IG"));
            	interGroupHeaderss.setEmpresa1(rs.getString("EMPRESA1"));
            	interGroupHeaderss.setEmpresa2(rs.getString("EMPRESA2"));
            	interGroupHeaders.add(interGroupHeaderss);
         }
            response.getInterGroupHResponse().setInterGroupHeader(interGroupHeaders);
			//response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch(Exception e) {
			
			//response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally{
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<InterGroupResponseRest>(response, HttpStatus.OK);
	}


}

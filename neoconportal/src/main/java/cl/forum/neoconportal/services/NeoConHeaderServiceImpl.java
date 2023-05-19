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

import cl.forum.neoconportal.model.NeoConHeader;
import cl.forum.neoconportal.response.NeoConHeaderResponseRest;

@Service
public class NeoConHeaderServiceImpl implements INeoConHeaderService{

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
	public ResponseEntity<NeoConHeaderResponseRest> saveNeoConHeaders(@RequestParam("periodo") Integer periodo,
			 @RequestParam("acronimo") String acronimo) {
		// TODO Auto-generated method stub
				NeoConHeaderResponseRest response = new NeoConHeaderResponseRest();
				List<NeoConHeader> neoConHeaders = new ArrayList<NeoConHeader>();
				
				try (Connection cn = DriverManager.getConnection(connectionUrl);
			            CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_NEOCONHEADER(?,?) }")) {
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

}

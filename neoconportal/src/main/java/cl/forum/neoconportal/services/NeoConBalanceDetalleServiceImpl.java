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
import cl.forum.neoconportal.model.NeoConBalanceDetalle;
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

}

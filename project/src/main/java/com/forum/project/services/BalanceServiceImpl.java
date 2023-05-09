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

import com.forum.project.model.Balance;
import com.forum.project.response.BalanceResponseRest;

@Service
public class BalanceServiceImpl implements IBalanceService{

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
	public ResponseEntity<BalanceResponseRest> saveBalance(Balance balance) {
		// TODO Auto-generated method stub
		BalanceResponseRest response = new BalanceResponseRest();
				List<Balance> balances = new ArrayList<Balance>();
						
					try {
							cn = DriverManager.getConnection(connectionUrl);
							// Llamada al procedimiento almacenado
							CallableStatement cst = cn.prepareCall("{CALL SP_INSERT_BALANCE_CSV(?,?,?,?,?) }");
							//Obtener Id
							cst.setInt(1, balance.getPeriodo());
							cst.setString(2, balance.getAcronimo());
							cst.setInt(3, balance.getCuentaCodigo());
							cst.setString(4, balance.getDescripcion());
							cst.setInt(5, balance.getSaldo());
							// Ejecuta el procedimiento almacenado
							rs = cst.executeQuery();
							while(rs.next()) {
								// Se obtienen la salida del procedimineto almacenado
								Balance balancess = new Balance();
								balancess.setBalanceTempId(rs.getInt("BALANCETEMP_ID"));
								balancess.setPeriodo(rs.getInt("PERIODO"));
								balancess.setAcronimo(rs.getString("ACRONIMO"));
								balancess.setCuentaCodigo(rs.getInt("CUENTA_CODIGO"));
								balancess.setDescripcion(rs.getString("DESCRIPCION"));
								balancess.setSaldo(rs.getInt("SALDO"));
								balances.add(balancess);
							}
							response.getBalanceResponse().setBalance(balances);
						} catch (SQLException e) {
							e.getStackTrace();
							return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
						} finally{
							try {
								cn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}	
						return new ResponseEntity<BalanceResponseRest>(response, HttpStatus.OK);
	}
	
	
	

}

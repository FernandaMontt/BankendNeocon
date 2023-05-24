package cl.forum.neoconportal.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.response.BalanceDetalleResponseRest;
import cl.forum.neoconportal.response.BalanceResponseRest;

public interface IBalanceDetalleService {
	
	public ResponseEntity<BalanceDetalleResponseRest> findBalanceDetalleId(Integer id_balance);
	public ResponseEntity<BalanceDetalleResponseRest> findBalanceDetalleRubroId(Integer id_balance);
}

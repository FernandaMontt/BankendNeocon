package cl.forum.neoconportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cl.forum.neoconportal.model.Balance;
import cl.forum.neoconportal.model.BalanceDetalle;

public interface IBalanceDetalleDao extends JpaRepository<BalanceDetalle,Integer>{

}

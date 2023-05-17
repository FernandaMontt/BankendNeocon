package cl.forum.neoconportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cl.forum.neoconportal.model.Balance;

public interface IBalanceDao extends JpaRepository<Balance,Integer>{

}

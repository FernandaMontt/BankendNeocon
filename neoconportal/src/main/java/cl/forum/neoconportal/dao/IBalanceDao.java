package cl.forum.neoconportal.dao;

import org.springframework.data.repository.CrudRepository;

import cl.forum.neoconportal.model.Balance;

public interface IBalanceDao extends CrudRepository<Balance,Integer>{

}

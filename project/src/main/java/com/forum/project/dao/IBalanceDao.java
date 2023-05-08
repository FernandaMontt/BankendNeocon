package com.forum.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.forum.project.model.Balance;

public interface IBalanceDao extends CrudRepository<Balance,Integer>{

}

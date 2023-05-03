package com.forum.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.forum.project.model.Empresa;

public interface IEmpresaDao extends CrudRepository<Empresa,Integer>{

}

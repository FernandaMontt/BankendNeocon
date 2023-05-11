package cl.forum.neoconportal.dao;

import org.springframework.data.repository.CrudRepository;

import cl.forum.neoconportal.model.Empresa;

public interface IEmpresaDao extends CrudRepository<Empresa,Integer>{

}

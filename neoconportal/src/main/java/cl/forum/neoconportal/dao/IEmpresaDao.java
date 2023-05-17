package cl.forum.neoconportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cl.forum.neoconportal.model.Empresa;

public interface IEmpresaDao extends JpaRepository<Empresa,Integer>{

}

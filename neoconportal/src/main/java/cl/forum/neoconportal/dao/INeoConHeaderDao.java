package cl.forum.neoconportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.forum.neoconportal.model.Rubro;

public interface INeoConHeaderDao extends JpaRepository<Rubro,Integer>{

}

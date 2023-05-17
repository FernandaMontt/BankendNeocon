package cl.forum.neoconportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cl.forum.neoconportal.model.InterGroupHeader;

public interface IIntergGroupHDao extends JpaRepository<InterGroupHeader,Integer>{

}

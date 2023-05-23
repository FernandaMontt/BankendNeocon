package cl.forum.neoconportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.forum.neoconportal.model.ValidacionMensaje;

public interface IValidacionMensajeDao extends JpaRepository<ValidacionMensaje,Integer>{

}

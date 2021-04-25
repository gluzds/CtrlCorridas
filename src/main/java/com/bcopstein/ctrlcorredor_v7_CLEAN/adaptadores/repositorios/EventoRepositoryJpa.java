package com.bcopstein.ctrlcorredor_v7_CLEAN.adaptadores.repositorios;

import com.bcopstein.ctrlcorredor_v7_CLEAN.negocio.entidades.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EventoRepositoryJpa extends CrudRepository<Evento, Long>{

}

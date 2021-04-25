package com.bcopstein.ctrlcorredor_v7_CLEAN.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.ctrlcorredor_v7_CLEAN.negocio.entidades.Evento;
import com.bcopstein.ctrlcorredor_v7_CLEAN.negocio.repositorios.IEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoRepository implements IEventoRepository{
    EventoRepositoryJpa jpa;

    @Autowired
    public EventoRepository(EventoRepositoryJpa jpa) {
        this.jpa = jpa;
    }  

    public List<Evento> todos() {
        return (List<Evento>) jpa.findAll();
    }

    public boolean cadastra(Evento evento){
        jpa.save(evento);
        return true;
    }
}

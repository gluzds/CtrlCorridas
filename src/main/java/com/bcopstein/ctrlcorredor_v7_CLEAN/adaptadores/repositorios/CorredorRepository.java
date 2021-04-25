package com.bcopstein.ctrlcorredor_v7_CLEAN.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.ctrlcorredor_v7_CLEAN.negocio.entidades.Corredor;
import com.bcopstein.ctrlcorredor_v7_CLEAN.negocio.repositorios.ICorredorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorredorRepository implements ICorredorRepository {

    CorredorRepositoryJpa jpa;

    @Autowired
    public CorredorRepository(CorredorRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    public void removeTodos(){
        jpa.deleteAll();
    }

    public boolean cadastra(Corredor corredor){
        jpa.save(corredor);
        return true;
    }

    public List<Corredor> todos(){
        return (List<Corredor>) jpa.findAll();
    }
}

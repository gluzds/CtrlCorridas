package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import lombok.Getter;

@Getter
public class PerformanceDTO {
    private String nomeCorridaUm;
    private String nomeCorridaDois;

    public String getNomeCorridaUm(){
        return nomeCorridaUm;
    }

    public String getNomeCorridaDois(){
        return nomeCorridaDois;
    }

    public void setNomeCorrida(List<Evento> eventos){
        int index = 0;
        int diferenca = 0;
        for(int i = 0; i+1 < eventos.size(); i++){
            if (totalTimeMinutes(eventos.get(i)) - totalTimeMinutes(eventos.get(i+1)) > diferenca){
                index = i;
            }
        }
        this.nomeCorridaUm = eventos.get(index).getNome();
        this.nomeCorridaDois = eventos.get(index+1).getNome();
    }

    private int totalTimeMinutes(Evento evento){
        int minutos = 0;
        minutos += evento.getHoras()*60;
        minutos += evento.getMinutos();
        minutos += evento.getSegundos()/60;
        return minutos;
    }
}

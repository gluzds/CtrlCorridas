package com.bcopstein.CtrlCorredorV1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class EstatisticasDTO {

    private double media;
    private double mediana;
    private double desvioPadrao;

    public double getMedia(){
        return media;
    }
    public double getMediana(){
        return mediana;
    }
    public double getDesvioPadrao(){
        return desvioPadrao;
    }
    
    public void setMedia(List<Evento> eventos){
        List<Double> minutos = getListTempo(eventos);
        DoubleSummaryStatistics stats = minutos.stream()
                                        .mapToDouble((x) -> x)
                                        .summaryStatistics();
        this.media = stats.getAverage(); 
    }

    public void setMediana(List<Evento> eventos){
        List<Double> minutos = getListTempo(eventos);
        Collections.sort(minutos);
        double mediana = 0.0;
        if (minutos.size() % 2 == 0){
            mediana = ((double)minutos.get(minutos.size()/2) + (double)minutos.get(minutos.size()/2 - 1))/2;
        }
        else{
            mediana = (double) minutos.get(minutos.size()/2);
        }
        this.mediana = mediana;
    }

    public void setDesvioPadrao(List<Evento> eventos){
        List<Double> minutos = getListTempo(eventos);
        DoubleSummaryStatistics stats = minutos.stream()
                                        .mapToDouble((x) -> x)
                                        .summaryStatistics();
        double media = stats.getAverage();
        double desvioPadrao = 0.0;
        for (double vlr : minutos) {
            Double aux = vlr - media;
            desvioPadrao += aux * aux;
        }
        this.desvioPadrao =  Math.sqrt(desvioPadrao / (minutos.size() - 1));;
    }

    private List<Double> getListTempo(List<Evento> eventos){
        List<Double> minutosEventos = new ArrayList<>();
        for (Evento evento : eventos){
            double minutos = 0.0;
            minutos += evento.getHoras()*60.0;
            minutos += evento.getMinutos();
            minutos += evento.getSegundos()/60.0;
            minutosEventos.add(minutos);
        }
        return minutosEventos;
    }
}

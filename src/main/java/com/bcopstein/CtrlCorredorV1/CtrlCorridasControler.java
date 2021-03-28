package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {
    private CorredorRepository corredorRepo;
    private EventoRepository eventoRepo;

    @Autowired
    public CtrlCorridasControler(CorredorRepository corredorRepo, EventoRepository eventoRepo){
        this.corredorRepo = corredorRepo;
        this.eventoRepo = eventoRepo;
    }
        
    
    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
        return corredorRepo.consultaCorredor();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        corredorRepo.removeCorredores();
        corredorRepo.cadastraCorredor(corredor);
        return true;
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return eventoRepo.consultaEventos();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        eventoRepo.cadastraEvento(evento);
        return true;
    }

     @GetMapping("/estatisticas")
     @CrossOrigin(origins = "*") 
     public EstatisticasDTO estatisticas(@RequestParam final int distancia){
        return toEstatisticaDTO(eventoRepo.consultaEventosDistacia(distancia));
     }

     @GetMapping("/aumentoPerformance")
     @CrossOrigin(origins = "*")
     public PerformanceDTO aumentoPerformance(@RequestParam final int distancia, @RequestParam final int ano){ 
        var PerformanceDTO = new PerformanceDTO();
        PerformanceDTO.setNomeCorrida(eventoRepo.consultaEventosDistaciaAno(distancia, ano));
        return PerformanceDTO;
     }

     private EstatisticasDTO toEstatisticaDTO(List<Evento> eventos){
        var EstatisticasDTO = new EstatisticasDTO();
        EstatisticasDTO.setDesvioPadrao(eventos);
        EstatisticasDTO.setMedia(eventos);
        EstatisticasDTO.setMediana(eventos);
        return EstatisticasDTO;
     }
}

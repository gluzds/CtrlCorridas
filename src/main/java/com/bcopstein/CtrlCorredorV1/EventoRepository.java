package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

        this.jdbcTemplate.execute("DROP TABLE eventos IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE eventos("
                + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");

                
        cadastraEvento(new Evento(1,"Poa Day Run",22,5,2019,5,0,35,32));
        cadastraEvento(new Evento(2,"Daily run",23,5,2019,10,1,0,32));
        cadastraEvento(new Evento(3,"Daily run",24,5,2019,7,0,45,35));
        cadastraEvento(new Evento(4,"Maratona1",15,1,2020,42,5,35,25));
        cadastraEvento(new Evento(5,"Maratona2",27,3,2020,42,6,1,0));
        cadastraEvento(new Evento(6,"Maratona3",1,5,2020,42,5,45,10));
        cadastraEvento(new Evento(7,"Maratona4",5,7,2020,42,4,59,25));
        cadastraEvento(new Evento(8,"Maratona5",21,9,2020,42,4,55,0));
        cadastraEvento(new Evento(9,"Maratona6",17,12,2020,42,4,35,10));
    }

    public List<Evento> consultaEventos() {
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos",
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
        return resp;
    }

    public boolean cadastraEvento(Evento evento) {
        this.jdbcTemplate.update(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
                evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(),
                evento.getDistancia(), evento.getHoras(), evento.getMinutos(), evento.getSegundos());
        return true;
    }

    public List<Evento> consultaEventosDistacia(int distancia) {
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos where distancia = " + distancia,
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
        return resp;
    }

    public List<Evento> consultaEventosDistaciaAno(int distancia, int ano) {
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos where distancia = " + distancia + " AND ano = " + ano,
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
        return resp;
    }
}

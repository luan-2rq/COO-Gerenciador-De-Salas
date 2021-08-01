import java.time.*;
import java.util.*;

public class Reuniao {

    private Collection<String> participantes;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private HashMap<String, IntervaloDeData> disponibilidades;
    
    public Reuniao(LocalDate dataInicio, LocalDate dataFim, Collection<String> participantes){

        this.participantes = participantes;
        this.dataInicial = dataInicio;
        this.dataFinal = dataFim;
        this.disponibilidades = new HashMap<String, IntervaloDeData>();
    }

    public Collection<String> getParticipantes() {

        return participantes;
    }

    public LocalDate getDataFinal() {

        return dataFinal;
    }

    public LocalDate getDataInicial() {

        return dataInicial;
    }

    public HashMap<String, IntervaloDeData> getDisponibilidades() {

        return disponibilidades;
    }

    public void addDisponibilidade(String participante, IntervaloDeData disponibilidade) {

        IntervaloDeData resposta = this.disponibilidades.putIfAbsent(participante, disponibilidade);
        
        if(resposta != null){

            disponibilidades.replace(participante, disponibilidade);
        }
    }

}

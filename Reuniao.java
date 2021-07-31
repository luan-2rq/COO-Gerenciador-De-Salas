import java.time.*;
import java.util.*;

public class Reuniao {

    private Collection<String> participantes;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private HashMap<String, Disponibilidade> disponibilidades;
    
    public Reuniao(LocalDate dataInicio, LocalDate dataFim, Collection<String> participantes){
        this.participantes = participantes;
        this.dataInicial = dataInicio;
        this.dataFinal = dataFim;
        this.disponibilidades = new HashMap<String, Disponibilidade>();
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

    public HashMap<String, Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void addDisponibilidade(String participante, Disponibilidade disponibilidade) {
        Disponibilidade resposta = this.disponibilidades.putIfAbsent(participante, disponibilidade);
        if(resposta != null){
            disponibilidades.replace(participante, disponibilidade);
        }
    }

}

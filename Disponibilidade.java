import java.time.*;

public class Disponibilidade{

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private int participantesDisponiveis;

    public Disponibilidade(LocalDateTime inicio, LocalDateTime fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDateTime getInicio(){
        return inicio;
    }

    public LocalDateTime getFim(){
        return fim;
    }

    public int getParticipantesComDisponibilidade(){
        return participantesDisponiveis;
    }

    public void setParticipantesComDisponibilidade(int numParticipantes){
        participantesDisponiveis = numParticipantes;
    }
}
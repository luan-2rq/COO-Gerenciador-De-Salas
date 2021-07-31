import java.time.*;

public class Reserva{
    
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Reserva(Sala nomeDaSala, LocalDateTime inicio, LocalDateTime fim){
        this.sala = nomeDaSala;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Sala getSala(){
        return sala;
    }

    public LocalDateTime getInicio(){
        return inicio;
    }

    public LocalDateTime getFim(){
        return fim;
    }

}

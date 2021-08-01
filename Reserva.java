import java.time.*;

public class Reserva{
    
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private int id;

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim, int id){

        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
        this.id = id;
    }

    public Sala sala(){

        return sala;
    }

    public LocalDateTime inicio(){

        return inicio;
    }

    public LocalDateTime fim(){

        return fim;
    }
    
    public int getId(){
        
        return id;
    }

}

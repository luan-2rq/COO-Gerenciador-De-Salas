import java.time.*;

public class Reserva{
    
    private Sala sala;
    private IntervaloDeData<LocalDateTime> data;
    private int id;

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim, int id){

        this.sala = sala;
        this.data = new IntervaloDeData<LocalDateTime>(inicio, fim);
        this.id = id;
    }

    public Sala sala(){

        return sala;
    }

    public LocalDateTime inicio(){

        return data.getInicio();
    }

    public LocalDateTime fim(){

        return data.getFim();
    }
    
    public int getId(){
        
        return id;
    }

}

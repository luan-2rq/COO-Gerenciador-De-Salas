import java.time.*;

public class IntervaloDeData{

    private LocalDateTime inicio;
    private LocalDateTime fim;

    public IntervaloDeData(LocalDateTime inicio, LocalDateTime fim){

        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDateTime getInicio(){

        return inicio;
    }

    public LocalDateTime getFim(){
        
        return fim;
    }

}
import java.util.*;
import java.time.*;
import java.io.*;

public class MarcadorDeReuniao{

    Collection<String> participantes;
    LocalDate dataInicial;
    LocalDate dataFinal;

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        this.participantes = listaDeParticipantes;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public void mostraSobreposicao(){
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim){
        
    }

    public static void main(String args[]){
        
    }

}
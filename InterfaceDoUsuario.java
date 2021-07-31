import java.io.Console;  
import java.time.*;
import java.util.*;
public class InterfaceDoUsuario {

    private static Console console = System.console();
    
    public static LocalDateTime askDateTime(){

        LocalDateTime data = null;

        return data;
    }   

    public static LocalDate askDate(){

        LocalDate data = null;

        return data;
    }
    public static Collection<String> askParticipantes(){

        List<String> participantes = new ArrayList<String>();
        String participante = " ";
        while(!participante.equals("")){
            participante = askName();
            if(!participante.equals("")){
                participantes.add(participante);
            }else{
                break;
            }
        }
        return participantes;
    }

    public static void askDisponibilidades(MarcadorDeReuniao marcadorDeReuniao){
        String participante = " ";
        while(!participante.equals("")){
            participante = askName();
            if(!participante.equals("")){
                LocalDateTime dataInicio = askDateTime();
                LocalDateTime dataFim = askDateTime();
                marcadorDeReuniao.indicaDisponibilidadeDe(participante, dataInicio, dataFim);
            }else{
                break;
            }
        }
    }

    public static String askName(){
        String name;
        System.out.println("Insira seu nome:");
        name = console.readLine();
        return name;
    }

    public static void main(String args[]){
        MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao();

        System.out.println("|| A seguir coloque a data inicial da reunião:\n");
        LocalDate dataInicial = askDate();

        System.out.println("|| A seguir coloque a data final da reunião:\n");
        LocalDate dataFinal = askDate();

        System.out.println("|| A seguir coloque os participantes da reunião: (caso já tenha colocado todos, pressione ENTER\n");
        Collection<String> participantes = askParticipantes();

        marcadorDeReuniao.marcarReuniaoEntre(dataInicial, dataFinal, participantes);

        System.out.println("|| Insira disponibilidades para a reunião:\n");
        askDisponibilidades(marcadorDeReuniao);

    }
    
}

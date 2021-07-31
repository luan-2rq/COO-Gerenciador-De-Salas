import java.io.Console;  
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class InterfaceDoUsuario {

    private static Console console = System.console();
    
    public static LocalDateTime askDateTime(){

        System.out.println("|| Insira a data no seguinte formato dd-MM-yyyy HH:mm :");
        String DataTxt = console.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(DataTxt, formatter);
        System.out.println("The date was saved as: " + dateTime);
        return dateTime;
    }   

    public static LocalDate askDate(){

        System.out.println("|| Insira a data no seguinte formato dd-MM-yyyy :");
        String dataTxt = console.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataTxt, formatter);
        System.out.println("The date was saved as: " + data);
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

        System.out.println("|| A seguir coloque os participantes da reunião: (caso já tenha colocado todos, pressione ENTER)\n");
        Collection<String> participantes = askParticipantes();

        marcadorDeReuniao.marcarReuniaoEntre(dataInicial, dataFinal, participantes);

        System.out.println("|| Insira disponibilidades para a reunião:\n");
        askDisponibilidades(marcadorDeReuniao);

        marcadorDeReuniao.mostraSobreposicao();

    }
    
}

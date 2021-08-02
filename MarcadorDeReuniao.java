import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MarcadorDeReuniao{

    private ArrayList<Reuniao> reunioes;

    public MarcadorDeReuniao(Reuniao reuniao){

        reunioes = new ArrayList<Reuniao>();
        this.reunioes.add(reuniao);
    }

    public MarcadorDeReuniao(){

        reunioes = new ArrayList<Reuniao>();
    }

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        
        ArrayList<String> nomesParticipantes = (ArrayList<String>)listaDeParticipantes;
        ArrayList<Participante> participantes = new ArrayList<Participante>();

        Reuniao reuniao = new Reuniao(dataInicial, dataFinal);

        for (int i = 0; i < listaDeParticipantes.size(); i++) {

            participantes.add(new Participante(nomesParticipantes.get(i)));
        }

        reuniao.setParticipantes(participantes);
        reunioes.add(reuniao);
    }

    public void agendaReuniao(LocalDateTime dataInicial, LocalDateTime dataFinal){
        Reuniao ultimaReuniao = getUltimaReuniao();
        ultimaReuniao.setAgendada(true);
        IntervaloDeData<LocalDateTime> dataDefinitiva = new IntervaloDeData<LocalDateTime>(dataInicial, dataFinal);
        ultimaReuniao.setDataDefinitiva(dataDefinitiva);
    }

    public Reuniao getUltimaReuniao(){

        return reunioes.get(reunioes.size() - 1);
    }

    public ArrayList<Reuniao> listaDeReunioes(){

        return reunioes;
    }

    public void imprimeReunioes(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for(int i = 0; i < reunioes.size(); i++){
            if(reunioes.get(i).getAgendada()){
                System.out.println("\nReuniao " + (i+1) + ":\n");
                System.out.println("  Data Inicial: " + reunioes.get(i).getDataDefinitiva().getInicio().format(formatter));
                System.out.println("  Data Final: " + reunioes.get(i).getDataPrevista().getFim().format(formatter) + "\n");

                ArrayList<Participante> participantes = reunioes.get(i).getParticipantes();
                System.out.println("  Participantes:");

                for(int j = 0; j < participantes.size(); j++){

                    System.out.println("  Participante " + (j+1) + ": " + participantes.get(j).getNome()); 
                }
            }
        }
    }

}
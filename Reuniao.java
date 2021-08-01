import java.time.*;
import java.util.*;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Reuniao {
    
    private ArrayList<Participante> participantes;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private HashMap<String, IntervaloDeData> disponibilidades;
    
    public Reuniao(LocalDate dataInicio, LocalDate dataFim){

        this.dataInicial = dataInicio;
        this.dataFinal = dataFim;
        this.disponibilidades = new HashMap<String, IntervaloDeData>();
    }

    public void mostraSobreposicao(){

        System.out.println("-------------------------------------------------------------");

        IntervaloDeData disponibilidadeEmComum = encontreSobreposicao(this.getDisponibilidades());

        if(disponibilidadeEmComum != null){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dataInicio = disponibilidadeEmComum.getInicio();
            LocalDateTime dataFim = disponibilidadeEmComum.getFim();
            String strDataInicio = dataInicio.format(formatter);
            String strDataFim = dataFim.format(formatter);

            Long periodo = dataInicio.until(dataFim, ChronoUnit.HOURS);

            System.out.println("Todos podem no dia " + strDataInicio + 
            " ate " + strDataFim + " || " + "Periodo para a reuniao: " + periodo + "horas" );
        }
        else{

            System.out.println("||---Nao houve sobreposicao de horario para todos os participantes---||");
        }
        
        
        
        System.out.println("-------------------------------------------------------------");
    }

    private IntervaloDeData encontreSobreposicao(Map<String, IntervaloDeData> disponibilidades){
        
        IntervaloDeData sobreposicao = null;
        Iterator<Map.Entry<String, IntervaloDeData>> sobreposicoes = disponibilidades.entrySet().iterator();
        int numSobreposicoes = numeroDeSobreposicoes(disponibilidades);

        if(numSobreposicoes == disponibilidades.size()){
            
            sobreposicoes = disponibilidades.entrySet().iterator();
            Map.Entry<String, IntervaloDeData> disponibilidade = sobreposicoes.next();
            LocalDateTime finalDateInicio = disponibilidade.getValue().getInicio();
            LocalDateTime finalDateFim = disponibilidade.getValue().getFim();

            for(int i = 0; i < disponibilidades.size() - 1; i++){

                Map.Entry<String, IntervaloDeData> nextDisponibilidade = sobreposicoes.next();
                LocalDateTime nextDateInicio = nextDisponibilidade.getValue().getInicio();
                LocalDateTime nextDateFim = nextDisponibilidade.getValue().getFim();

                if(finalDateInicio.isBefore(nextDateInicio)){

                    finalDateInicio = nextDateInicio;
                }
                if(finalDateFim.isAfter(nextDateFim)){

                    finalDateFim = nextDateFim;
                }
            }
                
            IntervaloDeData disponibilidadeEmComum = new IntervaloDeData(finalDateInicio, finalDateFim);
            sobreposicao = disponibilidadeEmComum;
        }

        return sobreposicao;
    }

    private int numeroDeSobreposicoes(Map<String, IntervaloDeData> disponibilidades){

        int count = 0;
        Iterator<Map.Entry<String, IntervaloDeData>> sobreposicoes = disponibilidades.entrySet().iterator();
        Map.Entry<String, IntervaloDeData> disponibilidade = sobreposicoes.next();
        LocalDateTime auxInicio = disponibilidade.getValue().getInicio();
        LocalDateTime auxFim = disponibilidade.getValue().getFim();

        for(int i = 0; i < disponibilidades.size() - 1; i++){

            disponibilidade = sobreposicoes.next();
            LocalDateTime nextInicio = disponibilidade.getValue().getInicio();
            LocalDateTime nextFim = disponibilidade.getValue().getFim();

            if(auxInicio.compareTo(nextFim) < 0 && auxFim.compareTo(nextInicio) > 0){

                //Sobreposição com X depois de Y
                if(auxInicio.isAfter(nextInicio) && auxFim.isAfter(nextFim)){

                    count++;
                //Sobreposição com X antes de Y
                }else if(auxInicio.isBefore(nextInicio) && auxFim.isBefore(nextFim)){

                    count++;

                //Sobreposição com X dentro de Y
                }else if(auxInicio.isBefore(nextInicio) && auxFim.isAfter(nextFim)){

                    count++;
                //Sobreposição com Y dentro de X
                }else if(auxInicio.isAfter(nextInicio) && auxFim.isBefore(nextFim)){

                    count++;
                //Datas iguais
                }else if(auxInicio.isEqual(nextInicio) && auxFim.isEqual(nextFim)){

                    count++;
                }
            }
        }

        return count + 1;
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim){

        addDisponibilidade(participante, new IntervaloDeData(inicio, fim));
    }

    
    public ArrayList<Participante> getParticipantes() {

        return participantes;
    }

    public LocalDate getDataFinal() {

        return dataFinal;
    }

    public LocalDate getDataInicial() {

        return dataInicial;
    }

    public HashMap<String, IntervaloDeData> getDisponibilidades() {

        return disponibilidades;
    }

    private void addDisponibilidade(String participante, IntervaloDeData disponibilidade) {

        IntervaloDeData resposta = this.disponibilidades.putIfAbsent(participante, disponibilidade);
        
        if(resposta != null){

            disponibilidades.replace(participante, disponibilidade);
        }
    }


    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

}

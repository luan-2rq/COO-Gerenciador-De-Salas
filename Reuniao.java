import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Reuniao {
    
    private ArrayList<Participante> participantes;
    private IntervaloDeData<LocalDate> dataPrevista;
    private IntervaloDeData<LocalDateTime> dataDefinitiva;
    private HashMap<String, IntervaloDeData<LocalDateTime>> disponibilidades;
    private boolean agendada;
    
    public Reuniao(LocalDate dataInicio, LocalDate dataFim){

        this.dataPrevista = new IntervaloDeData<LocalDate>(dataInicio, dataFim);
        this.disponibilidades = new HashMap<String, IntervaloDeData<LocalDateTime>>();
    }

    public void mostraSobreposicao(){

        System.out.println("-------------------------------------------------------------");

        IntervaloDeData<LocalDateTime> disponibilidadeEmComum = encontreSobreposicao(this.getDisponibilidades());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(int j = 0; j < participantes.size(); j++){

            String strDataInicial = disponibilidades.get(participantes.get(j).getNome()).getInicio().format(formatter);
            String strDataFinal = disponibilidades.get(participantes.get(j).getNome()).getFim().format(formatter);
            System.out.println("\nDisponibilidade do participante " + participantes.get(j).getNome() + " - Inicio: " + strDataInicial + " / Fim: " + strDataFinal);
        }

        if(disponibilidadeEmComum != null){

            LocalDateTime dataInicio = disponibilidadeEmComum.getInicio();
            LocalDateTime dataFim = disponibilidadeEmComum.getFim();
            String strDataInicio = dataInicio.format(formatter);
            String strDataFim = dataFim.format(formatter);

            Long periodo = dataInicio.until(dataFim, ChronoUnit.HOURS);
            
            System.out.println("\n| Todos podem no dia " + strDataInicio + 
            " ate " + strDataFim + " || " + "Periodo para a reuniao: " + periodo + " horas" );
        }else{

            System.out.println("\n||---Nao houve sobreposicao de horario para todos os participantes---||");
        }
        
        System.out.println("-------------------------------------------------------------");
    }

    private IntervaloDeData<LocalDateTime> encontreSobreposicao(Map<String, IntervaloDeData<LocalDateTime>> disponibilidades){
        
        IntervaloDeData<LocalDateTime> sobreposicao = null;
        Iterator<Map.Entry<String, IntervaloDeData<LocalDateTime>>> sobreposicoes = disponibilidades.entrySet().iterator();
        int numSobreposicoes = numeroDeSobreposicoes(disponibilidades);

        if(numSobreposicoes == disponibilidades.size()){
            
            sobreposicoes = disponibilidades.entrySet().iterator();
            Map.Entry<String, IntervaloDeData<LocalDateTime>> disponibilidade = sobreposicoes.next();
            LocalDateTime finalDateInicio = disponibilidade.getValue().getInicio();
            LocalDateTime finalDateFim = disponibilidade.getValue().getFim();

            for(int i = 0; i < disponibilidades.size() - 1; i++){

                Map.Entry<String, IntervaloDeData<LocalDateTime>> nextDisponibilidade = sobreposicoes.next();
                LocalDateTime nextDateInicio = nextDisponibilidade.getValue().getInicio();
                LocalDateTime nextDateFim = nextDisponibilidade.getValue().getFim();

                if(finalDateInicio.isBefore(nextDateInicio)){

                    finalDateInicio = nextDateInicio;
                }
                if(finalDateFim.isAfter(nextDateFim)){

                    finalDateFim = nextDateFim;
                }
            }
                
            IntervaloDeData<LocalDateTime> disponibilidadeEmComum = new IntervaloDeData<LocalDateTime>(finalDateInicio, finalDateFim);
            sobreposicao = disponibilidadeEmComum;
        }
        return sobreposicao;
    }

    private int numeroDeSobreposicoes(Map<String, IntervaloDeData<LocalDateTime>> disponibilidades){

        int count = 0;
        Iterator<Map.Entry<String, IntervaloDeData<LocalDateTime>>> sobreposicoes = disponibilidades.entrySet().iterator();
        Map.Entry<String, IntervaloDeData<LocalDateTime>> disponibilidade = sobreposicoes.next();
        LocalDateTime inicio = disponibilidade.getValue().getInicio();
        LocalDateTime fim = disponibilidade.getValue().getFim();

        for(int i = 0; i < disponibilidades.size() - 1; i++){

            disponibilidade = sobreposicoes.next();
            LocalDateTime nextInicio = disponibilidade.getValue().getInicio();
            LocalDateTime nextFim = disponibilidade.getValue().getFim();

            if(inicio.isBefore(nextFim) && fim.isAfter(nextInicio)){

                //Sobreposição com X depois de Y
                if(inicio.isAfter(nextInicio) && fim.isAfter(nextFim)){

                    count++;
                //Sobreposição com X antes de Y
                }else if(inicio.isBefore(nextInicio) && fim.isBefore(nextFim)){

                    count++;

                //Sobreposição com X dentro de Y
                }else if(inicio.isBefore(nextInicio) && fim.isAfter(nextFim)){

                    count++;
                //Sobreposição com Y dentro de X
                }else if(inicio.isAfter(nextInicio) && fim.isBefore(nextFim)){

                    count++;
                //Datas iguais
                }else if(inicio.isEqual(nextInicio) && fim.isEqual(nextFim)){

                    count++;
                }
            }
        }

        return count + 1;
    }


    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim){

        addDisponibilidade(participante, new IntervaloDeData<LocalDateTime>(inicio, fim));
    }

    
    public ArrayList<Participante> getParticipantes() {

        return participantes;
    }

    public IntervaloDeData<LocalDate> getDataPrevista() {

        return dataPrevista;
    }

    public IntervaloDeData<LocalDateTime> getDataDefinitiva() {

        return dataDefinitiva;
    }

    public void setDataPrevista(IntervaloDeData<LocalDate> dataPrevista) {

        this.dataPrevista = dataPrevista;
    }

    public void setDataDefinitiva(IntervaloDeData<LocalDateTime> dataDefinitiva) {

        this.dataDefinitiva = dataDefinitiva;
    }

    public boolean getAgendada(){

        return agendada;
    }

    public void setAgendada(boolean agendada){

        this.agendada = agendada;
    }

    public HashMap<String, IntervaloDeData<LocalDateTime>> getDisponibilidades() {

        return disponibilidades;
    }

    private void addDisponibilidade(String participante, IntervaloDeData<LocalDateTime> disponibilidade) {

        IntervaloDeData<LocalDateTime> resposta = this.disponibilidades.putIfAbsent(participante, disponibilidade);
        
        if(resposta != null){

            disponibilidades.replace(participante, disponibilidade);
        }
    }


    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

}

import java.util.*;
import javax.naming.spi.InitialContextFactory;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class MarcadorDeReuniao{

    private Reuniao reuniao;

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        reuniao = new Reuniao(dataInicial, dataFinal, listaDeParticipantes);
        System.out.println("|| Reunião marcada!");
    }

    public void mostraSobreposicao(){
        System.out.println("-------------------------------------------------------------");

        List<Disponibilidade> datasComSobreposicao = encontreDisponibilidades(reuniao.getDisponibilidades());
        if(datasComSobreposicao.size() > 1){
            for(int i = 0; i < datasComSobreposicao.size(); i++){
                LocalDateTime dataInicio = datasComSobreposicao.get(i).getInicio();
                LocalDateTime dataFim = datasComSobreposicao.get(i).getFim();
                int qntDeParticipantesDisponiveis = datasComSobreposicao.get(i).getParticipantesComDisponibilidade();
                String dataInicioFormatada = dataInicio.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
                String dataFimFormatada = dataFim.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));

                Long periodo = dataInicio.until(dataFim, ChronoUnit.HOURS);

                System.out.println(qntDeParticipantesDisponiveis + " participantes podem no dia " + dataInicioFormatada + 
                " até " + dataFimFormatada + " || " + "Periodo para a reunião: " + periodo + "hours\n" );
            }
        }else if(datasComSobreposicao.size() == 1){
            for(int i = 0; i < datasComSobreposicao.size(); i++){
                LocalDateTime dataInicio = datasComSobreposicao.get(i).getInicio();
                LocalDateTime dataFim = datasComSobreposicao.get(i).getFim();

                String dataInicioFormatada = dataInicio.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
                String dataFimFormatada = dataFim.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));

                Long periodo = dataInicio.until(dataFim, ChronoUnit.HOURS);

                System.out.println("Todos podem no dia " + dataInicioFormatada + 
                " até " + dataFimFormatada + " || " + "Periodo para a reunião: " + periodo + "hours" );
            }
        }
        else{
            System.out.println("||---Não houveram sobreposições de horário---||");
        }
        
        
        
        System.out.println("-------------------------------------------------------------");
    }

    public List<Disponibilidade> encontreDisponibilidades(Map<String, Disponibilidade> disponibilidades){

        List<Disponibilidade> datasComSobreposicao = new ArrayList<Disponibilidade>();
        List<Disponibilidade> sobreposicoes = new ArrayList<Disponibilidade>();

        for(Map.Entry<String, Disponibilidade> disponibilidadeX : disponibilidades.entrySet()){
            sobreposicoes.clear();
            int count = 0;
            for(Map.Entry<String, Disponibilidade> disponibilidadeY : disponibilidades.entrySet()){

                LocalDateTime inicioX = disponibilidadeX.getValue().getInicio();
                LocalDateTime fimX = disponibilidadeX.getValue().getFim();
                LocalDateTime inicioY = disponibilidadeY.getValue().getInicio();
                LocalDateTime fimY = disponibilidadeY.getValue().getFim();

                if(inicioX.isBefore(fimY) && fimX.isAfter(inicioY)){
                    //Sobreposição com X depois de Y
                    if(inicioX.isAfter(inicioY) && fimX.isAfter(fimY)){
                        sobreposicoes.add(disponibilidadeY.getValue());
                        count++;
                    //Sobreposição com X antes de Y
                    }else if(inicioX.isBefore(inicioY) && fimX.isBefore(fimY)){
                        sobreposicoes.add(disponibilidadeY.getValue());
                        count++;
                    }else{
                        break;
                    }
                }else{
                    break;
                }

            }

            if(count == disponibilidades.size()){
                LocalDateTime finalDateInicio = sobreposicoes.get(0).getInicio();
                LocalDateTime finalDateFim = sobreposicoes.get(0).getFim();
                for(int i = 1; i < sobreposicoes.size(); i++){

                    LocalDateTime tmpInicio = sobreposicoes.get(i).getInicio();
                    LocalDateTime tmpFim = sobreposicoes.get(i).getFim();

                    if(finalDateInicio.isBefore(tmpInicio)){
                        finalDateInicio = tmpInicio;
                    }
                    if(finalDateFim.isAfter(tmpFim)){
                        finalDateFim = tmpFim;
                    }
                }
                Disponibilidade disponibilidade = new Disponibilidade(finalDateInicio, finalDateFim);
                disponibilidade.setParticipantesComDisponibilidade(count);
                datasComSobreposicao.add(disponibilidade);
                return datasComSobreposicao;
            }else{
                if(sobreposicoes.size() > 0){
                    LocalDateTime finalDateInicio = sobreposicoes.get(0).getInicio();
                    LocalDateTime finalDateFim = sobreposicoes.get(0).getFim();
                    for(int i = 1; i < sobreposicoes.size(); i++){

                        LocalDateTime tmpInicio = sobreposicoes.get(i).getInicio();
                        LocalDateTime tmpFim = sobreposicoes.get(i).getFim();

                        if(finalDateInicio.isBefore(tmpInicio)){
                            finalDateInicio = tmpInicio;
                        }
                        if(finalDateFim.isAfter(tmpFim)){
                            finalDateFim = tmpFim;
                        }
                    }
                    Disponibilidade disponibilidade = new Disponibilidade(finalDateInicio, finalDateFim);
                    disponibilidade.setParticipantesComDisponibilidade(count);
                    datasComSobreposicao.add(disponibilidade);
                }
            }
        }

        return datasComSobreposicao;
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim){
        reuniao.addDisponibilidade(participante, new Disponibilidade(inicio, fim));
    }

}
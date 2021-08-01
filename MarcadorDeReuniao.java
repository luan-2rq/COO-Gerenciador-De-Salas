import java.util.*;
import javax.naming.spi.InitialContextFactory;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class MarcadorDeReuniao{

    private Reuniao reuniao;

    public MarcadorDeReuniao(Reuniao reuniao){
        this.reuniao = reuniao;
    }
    public MarcadorDeReuniao(){

    }

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){

        reuniao = new Reuniao(dataInicial, dataFinal, listaDeParticipantes);
        System.out.println("|| Reunião marcada!");
    }

    public void mostraSobreposicao(){

        System.out.println("-------------------------------------------------------------");

        List<IntervaloDeData> datasComSobreposicao = encontreDisponibilidades(reuniao.getDisponibilidades());

        if(datasComSobreposicao.size() > 1){

            for(int i = 0; i < datasComSobreposicao.size(); i++){

                LocalDateTime dataInicio = datasComSobreposicao.get(i).getInicio();
                LocalDateTime dataFim = datasComSobreposicao.get(i).getFim();
                int qntDeParticipantesDisponiveis = numeroDeSobreposicoes(reuniao.getDisponibilidades(), datasComSobreposicao.get(i));

                Long periodo = dataInicio.until(dataFim, ChronoUnit.HOURS);

                System.out.println(qntDeParticipantesDisponiveis + " participantes podem no dia " + dataInicio + 
                " até " + dataFim + " || " + "Periodo para a reunião: " + periodo + "hours\n" );
            }

        }else if(datasComSobreposicao.size() == 1){

            for(int i = 0; i < datasComSobreposicao.size(); i++){

                LocalDateTime dataInicio = datasComSobreposicao.get(i).getInicio();
                LocalDateTime dataFim = datasComSobreposicao.get(i).getFim();

                Long periodo = dataInicio.until(dataFim, ChronoUnit.HOURS);

                System.out.println("Todos podem no dia " + dataInicio + 
                " até " + dataFim + " || " + "Periodo para a reunião: " + periodo + "hours" );
            }
        }
        else{

            System.out.println("||---Não houveram sobreposições de horário---||");
        }
        
        
        
        System.out.println("-------------------------------------------------------------");
    }

    public List<String> getParticipantes(){

        return (List<String>)reuniao.getParticipantes();
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim){

        reuniao.addDisponibilidade(participante, new IntervaloDeData(inicio, fim));
    }

    private List<IntervaloDeData> encontreDisponibilidades(Map<String, IntervaloDeData> disponibilidades){
        
        List<IntervaloDeData> datasComSobreposicao = new ArrayList<IntervaloDeData>();
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
            datasComSobreposicao.add(disponibilidadeEmComum);

            return datasComSobreposicao;

        }else{
            //Achando sobreposicoes de horario, mesmo que elas se repitam
            ArrayList<ArrayList<IntervaloDeData>> intervaloDeDatas = new ArrayList<ArrayList<IntervaloDeData>>();
            int i = 0;
            for (Map.Entry<String, IntervaloDeData> disponibilidadeX : disponibilidades.entrySet()) {

                intervaloDeDatas.add(new ArrayList<IntervaloDeData>());
                LocalDateTime inicioX = disponibilidadeX.getValue().getInicio();
                LocalDateTime fimX = disponibilidadeX.getValue().getFim();
                for (Map.Entry<String, IntervaloDeData> disponibilidadeY : disponibilidades.entrySet()) {

                    LocalDateTime inicioY = disponibilidadeY.getValue().getInicio();
                    LocalDateTime fimY = disponibilidadeY.getValue().getFim();
                    if(inicioX.isBefore(fimY) && fimX.isAfter(inicioY)){

                        //Sobreposição com X depois de Y
                        if(inicioX.isAfter(inicioY) && fimX.isAfter(fimY)){
                            IntervaloDeData sobreposicao = new IntervaloDeData(inicioY, fimY);
                            intervaloDeDatas.get(i).add(sobreposicao);
                        //Sobreposição com X antes de Y
                        }else if(inicioX.isBefore(inicioY) && fimX.isBefore(fimY)){
                            IntervaloDeData sobreposicao = new IntervaloDeData(inicioY, fimY);
                            intervaloDeDatas.get(i).add(sobreposicao);
                        }else if(inicioX.equals(inicioY) && fimX.equals(fimY)){
                            IntervaloDeData sobreposicao = new IntervaloDeData(inicioY, fimY);
                            intervaloDeDatas.get(i).add(sobreposicao);
                        }
                    
                    }
                }
                i++;
            }

            //Achando intervalo adequado da sobreposicao
            for(i = 0; i < intervaloDeDatas.size(); i++){

                LocalDateTime finalDateInicio = intervaloDeDatas.get(i).get(0).getInicio();
                LocalDateTime finalDateFim = intervaloDeDatas.get(i).get(0).getFim();

                for(int j = 0; j < intervaloDeDatas.get(i).size(); j++){

                    IntervaloDeData nextDisp = intervaloDeDatas.get(i).get(j);
                    LocalDateTime nextDateInicio = nextDisp.getInicio();
                    LocalDateTime nextDateFim = nextDisp.getFim();

                    if(finalDateInicio.isBefore(nextDateInicio)){

                        finalDateInicio = nextDateInicio;
                    }
                    if(finalDateFim.isAfter(nextDateFim)){

                        finalDateFim = nextDateFim;
                    } 
                }
                IntervaloDeData disponibilidade = new IntervaloDeData(finalDateInicio, finalDateFim);
                datasComSobreposicao.add(disponibilidade);
            }

            //Verificando se nao ha intervalos de data iguais e caso houver removendo
            // int size = datasComSobreposicao.size();
            // i = 0;
            // while(i < datasComSobreposicao.size()){
            //     IntervaloDeData intervaloDeDataX = datasComSobreposicao.get(i);
            //     int j = 0;
            //     while(j < datasComSobreposicao.size()){
            //         IntervaloDeData intervaloDeDataY = datasComSobreposicao.get(j);
            //         if(intervaloDeDataX.getInicio().equals(intervaloDeDataY.getInicio())){
            //             if(intervaloDeDataX.getFim().equals(intervaloDeDataY.getFim())){
            //                 datasComSobreposicao.remove(intervaloDeDataY);
            //                 j--;
            //             }
            //         }
            //         j++;
            //     }
            //     i++;
            // }

            return datasComSobreposicao;


            // Map<Integer, IntervaloDeData> hashMapDisponibilidades = new HashMap<Integer, IntervaloDeData>();
            // ArrayList<Integer> keys = new ArrayList<Integer>();

            // int i = 0;
            // for (Map.Entry<String, IntervaloDeData> disponibilidade: disponibilidades.entrySet()) {

            //     hashMapDisponibilidades.put(i, disponibilidade.getValue());
            //     keys.add(i);
            //     i++;
            // }

            // ArrayList<ArrayList<IntervaloDeData>> disponibilidadesEmComum = new ArrayList<ArrayList<IntervaloDeData>>();
            // Iterator<Integer> keyIterator = keys.iterator();
            // if(numSobreposicoes > 0){ 

            //     i = 0;
            //     while(keys.size() > 0){
            //         keyIterator = keys.iterator();
            //         int nextKey = (int)keyIterator.next();
            //         LocalDateTime auxInicio = hashMapDisponibilidades.get(nextKey).getInicio();
            //         LocalDateTime auxFim = hashMapDisponibilidades.get(nextKey).getFim();

            //         disponibilidadesEmComum.add(new ArrayList<IntervaloDeData>());
            //         disponibilidadesEmComum.get(disponibilidadesEmComum.size() - 1).add(hashMapDisponibilidades.get(nextKey));
            //         hashMapDisponibilidades.remove(nextKey);
            //         keys.remove(Integer.valueOf(nextKey));

            //         int j = 0;
            //         for (Map.Entry<String, IntervaloDeData> disponibilidade: disponibilidades.entrySet()) {
            //             LocalDateTime inicio = disponibilidade.getValue().getInicio();
            //             LocalDateTime fim = disponibilidade.getValue().getFim();
            //             if(auxInicio.isBefore(fim) && auxFim.isAfter(inicio)){

            //                 //Sobreposição com X depois de Y
            //                 if(auxInicio.isAfter(inicio) && auxFim.isAfter(fim)){

            //                     disponibilidadesEmComum.get(disponibilidadesEmComum.size() - 1).add(hashMapDisponibilidades.get(j));
            //                     hashMapDisponibilidades.remove(j);
            //                     keys.remove(Integer.valueOf(j));
            //                 //Sobreposição com X antes de Y
            //                 }else if(auxInicio.isBefore(inicio) && auxFim.isBefore(fim)){

            //                     disponibilidadesEmComum.get(disponibilidadesEmComum.size() - 1).add(hashMapDisponibilidades.get(j));
            //                     hashMapDisponibilidades.remove(j);
            //                     keys.remove(Integer.valueOf(j));
            //                 }
            //             }
            //             j++;
            //         }
            //     }

            //     for(i = 0; i < disponibilidadesEmComum.size(); i++){
            //         System.out.println("Entrei aqui1");
            //         LocalDateTime finalDateInicio = disponibilidadesEmComum.get(i).get(0).getInicio();
            //         LocalDateTime finalDateFim = disponibilidadesEmComum.get(i).get(0).getFim();

            //         for(int j = 1; j < disponibilidadesEmComum.get(i).size(); j++){
                        
            //             System.out.println("Entrei aqui2");
            //             IntervaloDeData nextDisp = disponibilidadesEmComum.get(i).get(j);
            //             if(nextDisp != null){
                            
            //                 LocalDateTime nextDateInicio = nextDisp.getInicio();
            //                 LocalDateTime nextDateFim = nextDisp.getFim();
            //                 if(finalDateInicio.isBefore(nextDateInicio)){

            //                     finalDateInicio = nextDateInicio;
            //                 }
            //                 if(finalDateFim.isAfter(nextDateFim)){

            //                     finalDateFim = nextDateFim;
            //                 }
            //             }
            //         }

            //         IntervaloDeData disponibilidade = new IntervaloDeData(finalDateInicio, finalDateFim);
            //         datasComSobreposicao.add(disponibilidade);
            //     }
            // } 
        }
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

            if(auxInicio.isBefore(nextFim) && auxFim.isAfter(nextInicio)){

                //Sobreposição com X depois de Y
                if(auxInicio.isAfter(nextInicio) && auxFim.isAfter(nextFim)){

                    count++;
                //Sobreposição com X antes de Y
                }else if(auxInicio.isBefore(nextInicio) && auxFim.isBefore(nextFim)){

                    count++;
                }
            }
        }

        return count + 1;
    }

    private int numeroDeSobreposicoes(Map<String, IntervaloDeData> disponibilidades, IntervaloDeData disponibilidade){

        int count = 0;
        Iterator<Map.Entry<String, IntervaloDeData>> sobreposicoes = disponibilidades.entrySet().iterator();
        LocalDateTime inicio = disponibilidade.getInicio();
        LocalDateTime fim = disponibilidade.getFim();

        for(int i = 0; i < disponibilidades.size() - 1; i++){

            Map.Entry<String, IntervaloDeData> nextDisponibilidade = sobreposicoes.next();
            LocalDateTime nextInicio = nextDisponibilidade.getValue().getInicio();
            LocalDateTime nextFim = nextDisponibilidade.getValue().getFim();

            if(inicio.isBefore(nextFim) && fim.isAfter(nextInicio)){

                //Sobreposição com X depois de Y
                if(inicio.isAfter(nextInicio) && fim.isAfter(nextFim)){
                    count++;
                //Sobreposição com X antes de Y
                }else if(inicio.isBefore(nextInicio) && fim.isBefore(nextFim)){
                    count++;
                }

            }
        }

        return count + 1;
    }

}
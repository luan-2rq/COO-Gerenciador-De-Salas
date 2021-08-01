import java.io.Console;  
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class InterfaceDoUsuario {
    
    private static Console console = System.console();
    
    public static LocalDateTime askDateTime(){
        
        LocalDateTime dateTime;
        
        while(true){
           
            try {
                
                System.out.println("| Insira a data no seguinte formato dd-MM-aaaa HH:mm :");
                String DataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                dateTime = LocalDateTime.parse(DataTxt, formatter);
                break;
            } catch (Exception e) {
               
                System.out.println("\n| A data nao estava no formato correto, insira novamente.");
                continue;
            }
        }
        return dateTime;
    }   

    public static LocalDate askDate(){
       
        LocalDate data;
        
        while(true){
            
            try {
               
                System.out.println("\n| Insira a data no seguinte formato dd-MM-aaaa :");
                String dataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataTxt, formatter);
                break;
            } catch (Exception e) {
                
                System.out.println("\n| A data nao estava no formato correto, insira novamente.");
                continue;
            }
        }
        return data;
    }

    public static Collection<String> askParticipantes(){
        System.out.println("-------------------------------------------------------------");
        
        List<String> participantes = new ArrayList<String>();
        String participante = " ";

        while(true){
            
            try {
               
                while(!participante.equals("")){
                    
                    participante = askName();

                    if(!participante.equals("")){
                        
                        participantes.add(participante);
                    }
                    else{
                        
                        break;
                    }
                }
                break;
            } catch (Exception e) {
                
                System.out.println("\n| O nome digitado nao pode ser inserido, tente digitar um nome que conenha letras basicas do alfabeto");
                continue;
            }
            
        }

        System.out.println("-------------------------------------------------------------\n");
        return participantes;
    }

    public static void askDisponibilidades(MarcadorDeReuniao marcadorDeReuniao){
        
        List<String> participantes = marcadorDeReuniao.getParticipantes();

        for(int i = 0; i < marcadorDeReuniao.getParticipantes().size(); i++){
            
            while(true){
               
                try {
                    
                    System.out.println("\nInsira a disponibilidade do participante " + participantes.get(i) + ":");
                    LocalDateTime dataInicio = askDateTime();
                    LocalDateTime dataFim = askDateTime();
                    marcadorDeReuniao.indicaDisponibilidadeDe(participantes.get(i), dataInicio, dataFim);
                    break;
                } catch (Exception e) {
                    
                    System.out.println("\n| A data nao foi digitada da forma correta, digite novamente as datas.");
                    continue;
                }  
            }
        }
    }

    public static String askName(){
        
        String name;
        System.out.println("\n| Insira o nome do participante: ");
        name = console.readLine();
        return name;
    }

    public static String askNomeSala(GerenciadorDeSalas gerenciadorDeSalas){
        
        String nomeDaSala;        
        
        while(true){
            
            try{          
                
                System.out.println("Insira o nome da sala a ser criada: ");
                nomeDaSala = console.readLine();
                gerenciadorDeSalas.verificaNomeSalaChamada(nomeDaSala);
                break;
            } catch (Exception e) {
                
                System.out.println("\n| Ja existe uma sala com o mesmo nome, digite novamente: ");
                continue;
            }  
        }
        return nomeDaSala;
    }

    public static String askLocal(){
       
        String local;
        System.out.println("\n| Insira o local: ");
        local = console.readLine();
        return local;
    }
    
    public static int askCapacidade(){  
        
        int capacidadeMaxima;
        
        while(true){
            try{
                
                System.out.println("\n| Insira a capacidade maxima da sala: ");
                capacidadeMaxima = Integer.parseInt(console.readLine());
                break;
            }catch (Exception e) {
               
                System.out.println("\n| A capacidade maxima nao foi digitada da forma correta, digite novamente: ");
                continue;
            }  
        }
        return capacidadeMaxima;
    }

    public static String askDescricao(){
        String descricao;

        System.out.println("| Insira uma descricao: ");
        descricao = console.readLine();
        
        return descricao;
    }    

    public static void mostraSalas(GerenciadorDeSalas gerenciadorDeSalas){

        System.out.println("|| As salas existentes sao:\n");

        List<Sala> salas = gerenciadorDeSalas.listaDeSalas();
        
        for(int i = 0; i < salas.size(); i++){

            System.out.println("\nSala - " + salas.get(i).getNome());
            System.out.println(" Local: " + salas.get(i).getLocal());
            System.out.println(" Descricao: " + salas.get(i).getDescricao());
        }
        if(salas.size() == 0){
            System.out.println("Nao existem salas criadas");
        }
    }
    
    public static void criaSala(GerenciadorDeSalas gerenciadorDeSalas){
    
        boolean resp;
        int respConsole;
        String nome;
        String local;
        int capacidade;
        String descricao;
        
        System.out.println("\n| Deseja incluir uma nova sala? Digite 1 para SIM ou 2 para NAO");
        respConsole = Integer.parseInt(console.readLine());
        if(respConsole == 1) resp = true;
        else resp = false;
        
        if(resp){
            while(true){
                try{
                    nome = askNomeSala(gerenciadorDeSalas);
                    local = askLocal();
                    capacidade = askCapacidade();
                    descricao = askDescricao();
                    gerenciadorDeSalas.adicionaSalaChamada(nome, capacidade, descricao, local);
                    break;
                }catch (Exception e) {
                    continue;
                }
            }
        }
        else{
            return;
        }  
    }

    public static void removeSala(GerenciadorDeSalas gerenciadorDeSalas){
        
        String nomeDaSala;

        while(true){

            try{

                System.out.println("\n| Insira o nome da sala a ser removida: ");
                nomeDaSala = console.readLine();
                gerenciadorDeSalas.removeSalaChamada(nomeDaSala);
                break;
            }catch (Exception e) {
                
                System.out.println("| A sala nao pode ser removida");
                continue;
            }  
        }
    }

    public static void criaReuniao(MarcadorDeReuniao marcadorDeReuniao){
        
        System.out.println("\n|| Coloque a data inicial da reuniao:\n");
        LocalDate dataInicial = askDate();

        System.out.println("\n|| Coloque a data final da reuniao:\n");
        LocalDate dataFinal = askDate();

        System.out.println("\n|| Coloque os participantes da reuniao:" +
        "\n(Quando tiver inserido o nome de todos os participantes, pressione apenas ENTER)\n");
        Collection<String> participantes = askParticipantes();

        marcadorDeReuniao.marcarReuniaoEntre(dataInicial, dataFinal, participantes);

        System.out.println("\n|| Insira as disponibilidades para a reunião:\n");
        askDisponibilidades(marcadorDeReuniao);
    }

    public static void criaReserva(GerenciadorDeSalas gerenciadorDeSalas){
        
        while(true){

            try{

                String nomeSala = askNomeSala(gerenciadorDeSalas);
                LocalDateTime dataInicial = askDateTime();
                LocalDateTime dataFinal = askDateTime();
                gerenciadorDeSalas.reservaSalaChamada(nomeSala, dataInicial, dataFinal);
                System.out.println("\n|| Reserva concluida");
                break;
            }catch (Exception e) {
                
                System.out.println("\n| Houve algo de errado ao reservar sala, tente novamente");
                continue;
            }  
        }
    }

    public static void removerSala(GerenciadorDeSalas gerenciadorDeSalas){
        
        while(true){

            try{

                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n| Nao ha salas para serem removidas");
                    break;
                }
                System.out.println("\n|| Coloque o nome da sala a ser removida:");
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.removeSalaChamada(nomeSala);
                System.out.println("\n|| Remocao concluida");
                break;
            }catch (Exception e) {
                
                System.out.println("\n| Houve algo de errado ao remover sala, tente novamente");
                continue;
            }  
        }
    }

    public static void imprimeReservasDeSala(GerenciadorDeSalas gerenciadorDeSalas){
               
        while(true){

            try{

                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n| Nao ha salas para serem removidas");
                    break;
                }
                System.out.println("\n|| Coloque o nome da sala a ser removida:");
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.imprimeReservasDaSala(nomeSala);
                System.out.println("\n|| Remocao concluida");
                break;
            }catch (Exception e) {
                
                System.out.println("\n| Houve algo de errado ao impimir reservas da sala, tente novamente");
                continue;
            }  
        }
    }

    public static int askOpcoes(){
        int resp;
        while(true){
            try{
                System.out.println("\n|| Escolha uma das opcoes:");
                System.out.println("\n(1) - Marcar Reuniao");
                System.out.println("(2) - Reservar Sala");
                System.out.println("(3) - Criar Sala");
                System.out.println("(4) - Remover Sala");
                System.out.println("(5) - Imprime Reservas de Sala");
                resp = (int)Integer.parseInt(console.readLine());
                if(resp > 5 || resp < 1){
                    throw new Exception("\n| As opcoes vao de 1 a 5, selecione novamente:");
                }
                break;
            }catch (Exception e) {
                System.out.println("\n| Algo errado ocorreu durante a selecao de opcoes, insira a opcao novamente");
                continue;
            }
        }
        return resp;
       
    }

}

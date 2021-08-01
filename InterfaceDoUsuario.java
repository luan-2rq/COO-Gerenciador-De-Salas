import java.io.Console;  
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InterfaceDoUsuario {
    
    private static Console console = System.console();
    
    public static LocalDateTime askDateTimeFinal(){
        
        LocalDateTime dateTime;
        
        while(true){
           
            try {
                
                System.out.println("| Insira a Data Final no seguinte formato dd-MM-aaaa HH:mm :");
                String DataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                dateTime = LocalDateTime.parse(DataTxt, formatter);
                break;
            } catch (Exception e) {
               
                System.out.println("\n| A Data Final nao estava no formato correto, insira novamente.");
                continue;
            }
        }
        return dateTime;
    }   

    public static LocalDateTime askDateTimeInicial(){
        
        LocalDateTime dateTime;
        
        while(true){
           
            try {
                
                System.out.println("| Insira a Data Inicial no seguinte formato dd-MM-aaaa HH:mm :");
                String DataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                dateTime = LocalDateTime.parse(DataTxt, formatter);
                break;
            } catch (Exception e) {
               
                System.out.println("\n| A DataInicial nao estava no formato correto, insira novamente.");
                continue;
            }
        }
        return dateTime;
    } 

    public static LocalDate askDateInicial(){
       
        LocalDate data;
        
        while(true){
            
            try {
               
                System.out.println("\n| Insira a Data Inicial no seguinte formato dd-MM-aaaa :");
                String dataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataTxt, formatter);
                break;
            } catch (Exception e) {
                
                System.out.println("\n| A Data Inicial nao estava no formato correto, insira novamente.");
                continue;
            }
        }
        return data;
    }

    public static LocalDate askDateFinal(){
       
        LocalDate data;
        
        while(true){
            
            try {
               
                System.out.println("\n| Insira a Data Final no seguinte formato dd-MM-aaaa :");
                String dataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataTxt, formatter);
                break;
            } catch (Exception e) {
                
                System.out.println("\n| A Data Final nao estava no formato correto, insira novamente.");
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
        
        ArrayList<Participante> participantes = marcadorDeReuniao.getUltimaReuniao().getParticipantes();
        int numParticipantes = marcadorDeReuniao.getUltimaReuniao().getParticipantes().size();

        for(int i = 0; i < numParticipantes; i++){
            
            while(true){
               
                try {
                    
                    System.out.println("\nInsira a disponibilidade do participante " + participantes.get(i).getNome() + ":");
                    LocalDateTime dataInicio = askDateTimeInicial();
                    LocalDateTime dataFim = askDateTimeFinal();
                    participantes.get(i).indicaDisponibilidadeParaReuniao(marcadorDeReuniao.getUltimaReuniao(), dataInicio, dataFim);
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
                
                System.out.println("Insira o nome da sala: ");
                nomeDaSala = console.readLine();
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

        System.out.println("\n| Insira uma descricao: ");
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
                    gerenciadorDeSalas.verificaSeSalaExiste(nome);
                    gerenciadorDeSalas.adicionaSalaChamada(nome, capacidade, descricao, local);
                    System.out.println("\n| sala criada com sucesso");
                    break;
                }catch (Exception e) {
                    System.out.println("\n| Ocorreu um erro ao criar sala, tente novamente");
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
                gerenciadorDeSalas.verificaSeNaoExistemSalas();
                gerenciadorDeSalas.removeSalaChamada(nomeDaSala);
                break;
            }catch (Exception e) {
                
                System.out.println("\n| A sala nao pode ser removida");
                continue;
            }  
        }
    }

    public static void criaReuniao(MarcadorDeReuniao marcadorDeReuniao){

        while(true){

            try{

                System.out.println("\n|| Coloque a data inicial da reuniao:\n");
                LocalDate dataInicial = askDateInicial();
        
                System.out.println("\n|| Coloque a data final da reuniao:\n");
                LocalDate dataFinal = askDateFinal();
        
                verificaSeDataFinalMaiorQueDataInicial(dataInicial, dataFinal);
                verificaSeDataInicialEstaNoFuturo(dataInicial);

                System.out.println("\n|| Coloque os participantes da reuniao: " +
                "\n(Quando tiver inserido o nome de todos os participantes, pressione apenas ENTER)\n");
                Collection<String> participantes = askParticipantes();
        
                marcadorDeReuniao.marcarReuniaoEntre(dataInicial, dataFinal, participantes);
        
                System.out.println("\n|| Insira as disponibilidades para a reunião:\n");
                askDisponibilidades(marcadorDeReuniao);
        
                System.out.println("\n|| Reunião marcada!");
                break;
            }catch (Exception e) {
                
                System.out.println("\n| A reuniao nao pode ser marcada, tente novamente");
                continue;
            }  
        }
        
    }
    
    public static void criaReserva(GerenciadorDeSalas gerenciadorDeSalas){
        
        while(true){

            try{
                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n| Nao ha salas para serem reservadas");
                    break;
                }
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                LocalDateTime dataInicial = askDateTimeInicial();
                LocalDateTime dataFinal = askDateTimeFinal();
                verificaSeDataFinalMaiorQueDataInicial(dataInicial, dataFinal);
                verificaSeDataInicialEstaNoFuturo(dataInicial);
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
                System.out.println("\n|| Coloque o nome da sala a ser removida: ");
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeSala);
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

                    System.out.println("\n| Nao ha salas para serem imprimidas");
                    break;
                }
                System.out.println("\n|| Coloque o nome da sala a ser imprimida: ");
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeSala);
                gerenciadorDeSalas.imprimeReservasDaSala(nomeSala);
                break;
            }catch (Exception e) {
                
                System.out.println("\n| Houve algo de errado ao impimir reservas da sala, tente novamente");
                continue;
            }  
        }
    }

    public static void imprimeReunioes(MarcadorDeReuniao marcadorDeReuniao){
        
        while(true){

            try{

                if(marcadorDeReuniao.listaDeReunioes().size() == 0){

                    System.out.println("\n| Nao ha reunioes para serem imprimidas");
                    break;
                }
                marcadorDeReuniao.imprimeReunioes();
                break;
            }catch (Exception e) {
                
                System.out.println("\n| Houve algo de errado ao impimir reunioes, tente novamente");
                continue;
            }  
        }
    }

    public static void cancelarReserva(GerenciadorDeSalas gerenciadorDeSalas){

        String nomeDaSala;

        while(true){

            try{
                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n| Nao existem salas, portanto não existem reservas para serem removidas: ");
                    break;
                }

                System.out.println("\n| Insira o nome da sala da qual a reserva sera cancelada: ");
                nomeDaSala = console.readLine();
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeDaSala);

                if(gerenciadorDeSalas.reservasParaSala(nomeDaSala).size() == 0){

                    System.out.println("\n| Nao existem reservas para a sala citada, portanto não foi possivel cancelar a reserva ");
                    break;
                }
                LocalDateTime dataInicial = askDateTimeInicial();
                LocalDateTime dataFinal = askDateTimeFinal();

                verificaSeDataFinalMaiorQueDataInicial(dataInicial, dataFinal);
                verificaSeDataInicialEstaNoFuturo(dataInicial);
                
                Reserva cancelada = gerenciadorDeSalas.getReserva(nomeDaSala, dataInicial, dataFinal);

                gerenciadorDeSalas.cancelaReserva(cancelada);
                break;
            }catch (Exception e) {
                
                System.out.println("\n| A reserva nao pode ser cancelada, tente novamente");
                continue;
            }  
        }
    }

    public static void verificaSeDataFinalMaiorQueDataInicial(LocalDate dataInicial, LocalDate dataFinal) throws Exception{
        if(dataInicial.isAfter(dataFinal)){

            //A data inicial da reuniao eh depois da data final
            throw new Exception();
        }
    }

    public static void verificaSeDataFinalMaiorQueDataInicial(LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception{
        if(dataInicial.isAfter(dataFinal)){

            //A data inicial da reuniao eh depois da data final
            throw new Exception();
        }
    }

    public static void verificaSeDataInicialEstaNoFuturo(LocalDate dataInicial) throws Exception{
        if(LocalDate.now().isAfter(dataInicial)){

            //A reuniao sendo marcada para um dia no passado
            throw new Exception();
        }
    }

    public static void verificaSeDataInicialEstaNoFuturo(LocalDateTime dataInicial) throws Exception{
        if(LocalDateTime.now().isAfter(dataInicial)){

            //A reuniao sendo marcada para um dia no passado
            throw new Exception();
        }
    }

    public static int askOpcoes(){
        int resp;

        while(true){

            try{

                System.out.println("\n|| Escolha uma das opcoes:");
                System.out.println("\n(1) - Marcar Reuniao");
                System.out.println("(2) - Reservar Sala");
                System.out.println("(3) - Cancelar Reservas");
                System.out.println("(4) - Criar Sala");
                System.out.println("(5) - Remover Sala");
                System.out.println("(6) - Imprimir Reservas de Sala");
                System.out.println("(7) - Imprimir Reunioes");
                resp = (int)Integer.parseInt(console.readLine());

                if(resp > 7 || resp < 1){

                    throw new Exception("\n| As opcoes vao de 1 a 7, selecione novamente: ");
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

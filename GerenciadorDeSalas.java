import java.util.*;
import java.time.*;

public class GerenciadorDeSalas{

    private List<Sala> salas = new ArrayList<Sala>();
    private Collection<Reserva> reservas = new ArrayList<Reserva>();

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao){
        
        if(salas.size() == 0){ //primeira sala sendo criada
            Sala novaSala = new Sala(nome, capacidadeMaxima, descricao);
            adicionaSala(novaSala);
        }
        else{
            for(int i = 0; i < salas.size(); i++){ //verifica se uma sala com o mesmo nome ja existe
                if(salas.get(i).getNome().equals(nome)){
                    System.out.println("Ja existe uma sala com o mesmo nome");
                    return;
                }    
            }
            Sala novaSala = new Sala(nome, capacidadeMaxima, descricao);
            adicionaSala(novaSala);
        }
           
    }

    public void removeSalaChamada(String nomeDaSala){
        
        if(salas.size() == 0){
            System.out.println("Não existem slas para serem removidas");
            return;
        }
        else{
            for(int i = 0; i < salas.size(); i++){
                if(salas.get(i).getNome().equals(nomeDaSala)){
                    salas.remove(i);
                    System.out.println("A sala foi removida com sucesso");
                    return;
                }  
            }
            System.out.println("Esta sala nao existe");
        } 
    }

    public List<Sala> listaDeSalas(){
        return salas;
    }

    public void adicionaSala(Sala novaSala){
        salas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal){
        Reserva reservas = null;
        //FALTA ESTE
        return reservas;
    }

    public void cancelaReserva(Reserva cancelada){
        //FALTA ESTE
    }

    public Collection<Reserva> reservasParaSala(String nomeSala){
        //FALTA ESTE
        return null;
    }

    /*
    public void imprimeReservasDaSala(String nomeSala){
        
    }
    */
}
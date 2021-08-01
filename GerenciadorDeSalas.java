import java.util.*;
import java.time.*;

public class GerenciadorDeSalas{

    private List<Sala> salas = new ArrayList<Sala>();

    public void verificaNomeSalaChamada(String nomeDaSala) throws Exception{    
        if(salas.size() != 0){

            for(int i = 0; i < salas.size(); i++){ 

                if(salas.get(i).getNome().equals(nomeDaSala)){
                    //Sala com mesmo nome ja existe
                    throw new Exception();
                } 
            }
            return;
        }
    }
    
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao, String local) throws Exception{

        for(int i = 0; i < salas.size(); i++){
            if(salas.get(i).getNome().equals(nome)){
                //A sala jah existe
                throw new Exception();
            }
        }
        Sala novaSala = new Sala(nome, capacidadeMaxima, descricao, local);
        adicionaSala(novaSala);
    }

    public void removeSalaChamada(String nomeDaSala) throws Exception{

        if(salas.size() == 0){
            //Nao existem salas
            throw new Exception();
        }
        else{
            
            for(int i = 0; i < salas.size(); i++){
               
               if(salas.get(i).getNome().equals(nomeDaSala)){
                    
                    salas.remove(i);
                    return;
                }  
            }
            //Nao existe salas com este nome
            throw new Exception();
        } 
    }

    public List<Sala> listaDeSalas(){
       
        return salas;
    }

    public void adicionaSala(Sala novaSala)throws Exception{
        
        for(int i = 0; i < salas.size(); i++){
               
            if(salas.get(i).getNome().equals(novaSala.getNome())){
                 
                 salas.remove(i);
                 //A sala jah existe
                 throw new Exception();
             }  
         }
        salas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception{

        Reserva reserva = null;
        for (int i = 0; i < salas.size(); i++) {
            
            if (salas.get(i).getNome().equals(nomeDaSala)) {
                
                ArrayList<Reserva> reservas = (ArrayList<Reserva>)salas.get(i).getReservas();
                
                for (int j = 0; j < reservas.size(); j++) {

                    Reserva currentReserva = reservas.get(i);

                    if ((dataInicial.isBefore(currentReserva.fim()) && dataInicial.isAfter(currentReserva.inicio()))
                            || (dataFinal.isBefore(currentReserva.fim()) && dataFinal.isAfter(currentReserva.inicio()))
                            || (dataInicial.isBefore(currentReserva.inicio()) && dataFinal.isAfter(currentReserva.fim()))) {
                        //Ja existe uma reserva conflitando com esta
                        throw new Exception();
                    }
                }
                reserva = new Reserva(salas.get(i), dataInicial, dataFinal, reservas.size());
                salas.get(i).addReserva(reserva);
                return reserva;
            }
        }
        //Sala nao foi encontrada
        throw new Exception();
    }

    public void cancelaReserva(Reserva cancelada) throws Exception{

        Sala sala = cancelada.sala();
        ArrayList<Reserva> reservas = sala.getReservas();

        if(reservas.size() == 0){

            //nao existem reunioes para serem canceladas
            throw new Exception();
        } 
        for (int i = 0; i < reservas.size(); i++){

            if(reservas.get(i).getId() == cancelada.getId()){

                reservas.remove(i);
                return;
            }
            else{

                //A reserva a desejada para cancelamento nao foi encontrada
                throw new Exception();
            }

        }        
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) throws Exception{

        Collection<Reserva> reservas = null;

        if(salas.size() == 0){

            //Ainda não existem salas, portanto nao existem reservas
            throw new Exception();
        }
        for (int i = 0; i < salas.size(); i++) {

            if (salas.get(i).getNome().equals(nomeSala)){
                
                reservas = salas.get(i).getReservas();
                return reservas;
            }else{

                //A sala desejada nao foi encontrada
                throw new Exception();
            }
        }
        return reservas;
    }
    
    public void imprimeReservasDaSala(String nomeSala) throws Exception{

        if(salas.size() == 0){

            //Ainda nao existem salas, portanto nao existem reservas
            throw new Exception();
        }
        for (int i = 0; i < salas.size(); i++) {

            if (salas.get(i).getNome().equals(nomeSala)){

                ArrayList<Reserva> reservasDaSala = (ArrayList<Reserva>)reservasParaSala(nomeSala);
                System.out.println("Todas as reservas para a sala " + nomeSala + ":");
               
                for(int j = 0; j < reservasDaSala.size(); j++){

                    System.out.println("inicio: " + reservasDaSala.get(i).inicio() + " / fim: " + reservasDaSala.get(i).fim());
                }
            }else{
                //A sala desejada nao foi encontrada
                throw new Exception();
            }
        }
    }
}
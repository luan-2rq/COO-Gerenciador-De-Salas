import java.util.*;
public class Sala{

    private String nome;
    private String local;
    private int capacidadeMaxima;
    private String descricao;
    private ArrayList<Reserva> reservas;

    public Sala(String nome, int capacidadeMaxima, String descricao, String local){

        this.capacidadeMaxima = capacidadeMaxima;
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.reservas = new ArrayList<Reserva>();
    }

    public void addReserva(Reserva reserva){
       
        reservas.add(reserva);
    }
    
    public ArrayList<Reserva> getReservas(){
        
        return reservas;
    }
    
    public String getNome(){

        return nome;
    }

    public int getCapacidadeMaxima(){

        return capacidadeMaxima;
    }

    public String getLocal(){

        return local;
    }
    
    public String getDescricao(){

        return descricao;
    }

}

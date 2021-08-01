import java.time.LocalDateTime;
import java.util.*;

public class Participante {

    private ArrayList<Reuniao> reunioes; 

    private String nome;

    public Participante(String nome){

        this.nome = nome;
    }

    public void addReuniao(Reuniao reuniao){

        reunioes.add(reuniao);
    }

    public void indicaDisponibilidadeParaReuniao(Reuniao reuniao, LocalDateTime inicio, LocalDateTime fim){

        reuniao.indicaDisponibilidadeDe(this.getNome(), inicio, fim);
    }

    public String getNome() {

        return nome;
    }

}

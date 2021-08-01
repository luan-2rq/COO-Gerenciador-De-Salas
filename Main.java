import java.io.Console;  
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
   
    public static void main(String args[]){        
        
        GerenciadorDeSalas gerenciadorDeSalas = new GerenciadorDeSalas();
        MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao();

        // //Criando Reserva
        while(true){
            int opcao = InterfaceDoUsuario.askOpcoes();
            switch (opcao) {
                case 1:
                    //Criando Reuniao
                    InterfaceDoUsuario.criaReuniao(marcadorDeReuniao);
                    Reuniao reuniao = marcadorDeReuniao.getUltimaReuniao();
                    reuniao.mostraSobreposicao();
                    break;
                case 2:
                    //Criando Reserva
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.criaReserva(gerenciadorDeSalas);
                    break;
                case 3:
                    //Cancelar Reserva
                    InterfaceDoUsuario.imprimeReservasDeSala(gerenciadorDeSalas);
                    InterfaceDoUsuario.cancelarReserva(gerenciadorDeSalas);
                    break;
                case 4:
                    //Criando Sala
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.criaSala(gerenciadorDeSalas);
                    break;
                case 5:
                    //Removendo sala
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.removerSala(gerenciadorDeSalas);
                    break;
                case 6:
                    //Mostra Reservas de salas
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.imprimeReservasDeSala(gerenciadorDeSalas);
                    break;
                case 7:
                    //Mostra Reunioes
                    InterfaceDoUsuario.imprimeReunioes(marcadorDeReuniao);
                    break;
            }
        }
        
    }
    
}

import java.io.Console;  
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
   
    public static void main(String args[]){

        //Reuniao mock//
        // Collection<String> participantes = new ArrayList<String>();
        // participantes.add("Matheus");
        // participantes.add("Luan");
        // participantes.add("Felipe");
        // participantes.add("Gustavo");
        // participantes.add("Willyan");
        // String str = "2007-11-03 23:00";
        // String str1 = "2009-11-03 23:00";
        // String str2 = "2008-11-03 23:00";
        // String str3 = "2009-11-03 23:00";
        // String str4 = "2006-11-03 23:00";
        // String str5 = "2008-11-03 23:00";
        // String str6 = "2005-11-03 23:00";
        // String str7 = "2007-11-03 23:00";
        // String str8 = "2009-11-03 23:00";
        // String str9 = "2010-11-03 23:00";
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // LocalDateTime data1 = LocalDateTime.parse(str, formatter);
        // LocalDateTime data2 = LocalDateTime.parse(str1, formatter);
        // LocalDateTime data3 = LocalDateTime.parse(str2, formatter);
        // LocalDateTime data4 = LocalDateTime.parse(str3, formatter);
        // LocalDateTime data5 = LocalDateTime.parse(str4, formatter);
        // LocalDateTime data6 = LocalDateTime.parse(str5, formatter);
        // LocalDateTime data7 = LocalDateTime.parse(str6, formatter);
        // LocalDateTime data8 = LocalDateTime.parse(str7, formatter);
        // LocalDateTime data9 = LocalDateTime.parse(str8, formatter);
        // LocalDateTime data10 = LocalDateTime.parse(str9, formatter);
        // // System.out.println(data1.isAfter(data1));
        // // System.out.println(data1.isBefore(data1));
        // Reuniao reuniao = new Reuniao(LocalDate.parse("2008-07-03"), LocalDate.parse("2010-07-03"), participantes);
        // reuniao.addDisponibilidade("Matheus", new IntervaloDeData(data1, data2));
        // reuniao.addDisponibilidade("Luan", new IntervaloDeData(data3, data4));
        // reuniao.addDisponibilidade("Gustavo", new IntervaloDeData(data5, data6));
        // reuniao.addDisponibilidade("Felipe", new IntervaloDeData(data7, data8));
        // reuniao.addDisponibilidade("Willyan", new IntervaloDeData(data9, data10));

        // MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao(reuniao);
        // marcadorDeReuniao.mostraSobreposicao();
        //-----------//

        
        
        GerenciadorDeSalas gerenciadorDeSalas = new GerenciadorDeSalas();
        MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao();

        // //Criando Reserva
        while(true){
            int opcao = InterfaceDoUsuario.askOpcoes();
            switch (opcao) {
                case 1:
                    //Criando Reuniao
                    InterfaceDoUsuario.criaReuniao(marcadorDeReuniao);
                    marcadorDeReuniao.mostraSobreposicao();
                    break;
                case 2:
                    //Criando Reserva
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.criaReserva(gerenciadorDeSalas);
                    break;
                case 3:
                    //Criando Sala
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.criaSala(gerenciadorDeSalas);
                    break;
                case 4:
                    //Removendo sala
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.removerSala(gerenciadorDeSalas);
                    break;
                case 5:
                    //Mostra Reservas de salas
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.imprimeReservasDeSala(gerenciadorDeSalas);
                    break;
            }
        }
        
    }
    
}

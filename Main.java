public class Main {

    private static GerenciadorDeSalas gerenciadorDeSalas = new GerenciadorDeSalas();
    private static MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao();

    public static void main(String args[]){        
        
        gerenciadorDeSalas = new GerenciadorDeSalas();
        marcadorDeReuniao = new MarcadorDeReuniao();
        
        while(true){
            int opcao = InterfaceDoUsuario.askOpcoes();
            switch (opcao) {
                case 1:
                    //Criando Reuniao
                    InterfaceDoUsuario.criaReuniao(marcadorDeReuniao);
                    break;
                case 2:
                    //Criando Reserva
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
                    InterfaceDoUsuario.criaReserva(gerenciadorDeSalas);
                    break;
                case 3:
                    //Cancelando Reserva
                    InterfaceDoUsuario.mostraSalas(gerenciadorDeSalas);
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

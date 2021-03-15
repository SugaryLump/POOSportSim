package Classes;

import java.util.Scanner;
public class Interpreter {
    private boolean unsavedChanges;
    private Scanner input;

    public Interpreter() {
        this.unsavedChanges = false;
        input = new Scanner(System.in);
    }

    public void welcomeMessage(){
        System.out.println("Bem vindo ao simulador de desportos.");
    }

    public void mainMenu(/*List<Team> teams, List<Player> players, Simulator sim*/) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Selecione uma das opções abaixo com o seu índice.\n\n" +
                    "1- Executar simulação\n" +
                    "2- Equipas\n" +
                    "3- Jogadores\n" +
                    "4- Importar...\n" +
                    "5- Exportar...\n" +
                    "6- Créditos\n" +
                    "7- Saír\n");

            switch (input.nextLine().charAt(0)) {
                case '1':
                    //simulationMenu();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '2':
                    //teamMenu();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '3':
                    //playerMenu();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '4':
                    //importMenu();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '5':
                    //exportMenu();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '6':
                    //printCredits();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '7':
                    if (unsavedChanges)
                        exit = exitConfirm();
                    else
                        exit = true;
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
            }
        }
    }

    public boolean exitConfirm () {
        System.out.println("Foram realizadas alterações que ainda não foram guardadas.\n" +
                "Tem a certeza que quer fechar o programar? (Y/N)");
        return (input.nextLine().toUpperCase().charAt(0) == 'Y');
    }


}

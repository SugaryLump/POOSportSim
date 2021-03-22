package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Interpreter {
    private List<Team> teams;
    private List<Player> players;
    //private Simulator currentSimulator;

    private boolean unsavedChanges;
    private Scanner input;

    public Interpreter() {
        this.teams = new ArrayList<Team>();
        this.players = new ArrayList<Player>();
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

    //EQUIPAS
    private void teamMenu() {
        boolean exit = false;
        while (!exit) {
            printTeams();
            System.out.println("(1)Criar   (2)Editar/Visualizar (3)Remover (Q)Regressar");

            switch (input.nextLine().charAt(0)) {
                case '1':
                    //createTeam();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '2':
                    //editTeam();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '3':
                    //removeTeam();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case 'Q':
                    exit = true;
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
            }
        }
    }

    private void createTeam() {
        System.out.println("Nome da equipa?");
        String name = input.nextLine();

        while (name.length() > 20) {
            System.out.println("Nome demasiado grande (MÁX 20 CHAR.)\nNome da equipa?");
            name = input.nextLine();
        }

        Team team = new Team(name);
        this.teams.add(team);
    }

    private void printTeams() {
        int i = 0;
        for (Team team : teams) {
            System.out.printf("%d- %-20s  %-3d %-3d %-3d%n\n",
                    i,
                    team.getTeamName(),
                    team.getWins(),
                    team.getLosses(),
                    team.getTies());
        }
    }

    //SAIR
    private boolean exitConfirm () {
        System.out.println("Foram realizadas alterações que ainda não foram guardadas.\n" +
                "Tem a certeza que quer fechar o programar? (Y/N)");
        return (input.nextLine().toUpperCase().charAt(0) == 'Y');
    }
}

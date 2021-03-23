package Classes;

import java.util.TreeSet;
import java.util.Scanner;

enum TeamSortMode {
    NAME, WINS, LOSSES, TIES;
}

public class Interpreter {
    private TreeSet<Team> teams;
    private TreeSet<Player> players;
    //private Simulator currentSimulator;

    private boolean unsavedChanges;
    private Scanner input;
    private TeamSortMode teamSortMode;

    public Interpreter() {
        this.teams = new TreeSet<>(new ComparatorTeamName());
        //this.players = new TreeSet<>();
        this.unsavedChanges = false;
        this.teamSortMode = TeamSortMode.NAME;
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
                    "Q- Saír\n");

            switch (input.nextLine().charAt(0)) {
                case '1':
                    //simulationMenu();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '2':
                    teamMenu();
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
                case 'Q':
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
            System.out.println("(1)Criar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");

            switch (input.nextLine().charAt(0)) {
                case '1':
                    createTeam();
                    break;
                case '2':
                    //editTeam(teamsselectIndex(teams.size()));
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '3':
                    //removeTeam();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '4':
                    //changeTeamSorting();
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case '5':
                    //filterTeam();
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
        int i = 1;
        for (Team team : teams) {
            System.out.printf("%d- %-20s  %-3d %-3d %-3d%n",
                    i++,
                    team.getTeamName(),
                    team.getWins(),
                    team.getLosses(),
                    team.getTies());
        }
    }

    private void editTeam(Team team){

    }
    //SAIR
    private boolean exitConfirm () {
        System.out.println("Foram realizadas alterações que ainda não foram guardadas.\n" +
                "Tem a certeza que quer fechar o programar? (Y/N)");
        return (input.nextLine().toUpperCase().charAt(0) == 'Y');
    }

    //GERAL
    private int selectIndex(int maxIndex) {
        //ASSUME QUE O ÍNDICE COMEÇA EM 1 PARA SER EM TERMOS COMUNS!!
        System.out.println("Por favor indique o seu índice.");
        int index;
        try {
            index = Integer.parseInt(input.nextLine());
        }
        catch (NumberFormatException e) {
            index = 0;
        }
        while (index == 0 || index > maxIndex) {
            System.out.println("Índice inválido.\nPor favor indique o seu índice.");
            try {
                index = Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e) {
                index = 0;
            }
        }

        return index;
    }
}

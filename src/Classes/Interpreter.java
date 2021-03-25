package Classes;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Scanner;

enum TeamSortMode {
    NAME, WINS, LOSSES, TIES;
}

enum PlayerSortMode {
    SPORT, NAME, SCORE;
}

public class Interpreter {
    private TreeSet<Team> teams;
    private TeamSortMode teamSortMode;
    private TreeSet<Player> players;
    private PlayerSortMode playerSortMode;
    //private Simulator currentSimulator;

    private boolean unsavedChanges;
    private Scanner input;

    public Interpreter() {
        this.teams = new TreeSet<>(new ComparatorTeamName());
        //this.teamSortMode = TeamSortMode.NAME;
        //this.players = new TreeSet<>();
        //this.playerSortMode = PlayerSortMode.SPORT;
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
                    editTeam(grabTeam(selectIndex(teams.size())));
                    break;
                case '3':
                    this.teams.remove(grabTeam(selectIndex(teams.size())));
                    break;
                case '4':
                    changeTeamsOrder();
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
        Team newTeam = new Team(newTeamName());
        this.teams.add(newTeam);
        unsavedChanges = true;
    }

    private String newTeamName() {
        System.out.println("Nome da equipa?");
        String name = input.nextLine();

        while (name.length() > 20) {
            System.out.println("Nome demasiado grande (MÁX 20 CHAR.)\nNome da equipa?");
            name = input.nextLine();
        }
        return name;
    }

    private void printTeams() {
        int i = 1;
        System.out.printf("#  Nome                  V   D   E\n");
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
        Team tmp = team.clone();
        boolean exit = false;
        boolean changed = false;
        while (!exit) {
            System.out.println("ID: " + tmp.getTeamID() +
                    "\nHabilidade Global: " + tmp.getTeamGlobalAbillity() +
                    "\n(1)Nome: " + tmp.getTeamName() +
                    "\n(2)Vitórias: " + tmp.getWins() +
                    "\n(3)Derrotas: " + tmp.getLosses() +
                    "\n(4)Empates: " + tmp.getTies() +
                    "\n(5)Jogadores..." +
                    "\n\n(q)Cancelar\n(Q)Guardar e saír");
            switch (input.nextLine().charAt(0)) {
                case '1':
                    tmp.setTeamName(newTeamName());
                    changed = true;
                    break;
                case '2':
                    tmp.setWins(getInt(0, 999));
                    changed = true;
                    break;
                case '3':
                    tmp.setLosses(getInt(0,999));
                    changed = true;
                    break;
                case '4':
                    tmp.setTies(getInt(0,999));
                    changed = true;
                    break;
                case '5':
                    //playerMenu(tmp.getPlayerList());
                    System.out.println("Funcionalidade ainda não implementada!");
                    break;
                case 'q':
                    exit = true;
                    break;
                case 'Q':
                    if (changed) {
                        this.unsavedChanges = true;
                        tmp.copyTo(team);
                    }
                    exit = true;
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
            }
        }
    }

    private void changeTeamsOrder() {
        System.out.println("Que ordem?\n(1)Nome\n(2)Vitórias\n(3)Derrotas\n(4)Empates");
        TreeSet<Team> newTree;
        TeamSortMode newSortMode = TeamSortMode.values()[getInt(1,4)-1];
        if (this.teamSortMode != newSortMode) {
            this.teamSortMode = newSortMode;
            newTree = switch (newSortMode) {
                case NAME -> new TreeSet<Team>(new ComparatorTeamName());
                case WINS -> new TreeSet<Team>(new ComparatorTeamWinsFirst());
                case LOSSES -> new TreeSet<Team>(new ComparatorTeamLossesFirst());
                case TIES -> new TreeSet<Team>(new ComparatorTeamTiesFirst());
            };
            for (Team team : this.teams){
                newTree.add(team);
                //addAll não usado porque o enunciado pede a risco de comprometer encapsulamento
            }
            this.teams = newTree;
        }
    }

    //JOGADORES
    private void playerMenu(TreeSet<Player> players) {
        boolean exit = false;
        while (!exit) {
            printPlayers(players);
            System.out.println("(1)Criar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");

            switch (input.nextLine().charAt(0)) {
                case '1':
                    createTeam();
                    break;
                case '2':
                    editTeam(grabTeam(selectIndex(teams.size())));
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

    private void printPlayers (TreeSet<Player> players) {
        int i = 1;
        for (Player player : players) {
            System.out.printf("%d- %-15s %-20s  %-3d\n",
                    i++,
                    "PLAYERSPORTHERE",
                    player.getName(),
                    player.getGlobalAbillity());
        }
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

    private int getInt(int min, int max){
        System.out.println("Por favor indique o número.");
        int r;
        boolean except = false;
        try {
            r = Integer.parseInt(input.nextLine());
        }
        catch (NumberFormatException e) {
            r = min;
            except = true;
        }
        while (except || r > max || r < min) {
            System.out.println("Número inválido.\nPor favor indique o número.");
            except = false;
            try {
                r = Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e) {
                r = min;
                except = true;
            }
        }

        return r;
    }

    private Team grabTeam(int index) {
        //INDEX STARTING AT 1!!
        Iterator<Team> it = this.teams.iterator();
        Team r = null;
        while (it.hasNext() && index > 0){
            r = it.next();
            index--;
        }
        return r;
    }
}

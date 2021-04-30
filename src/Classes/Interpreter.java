package Classes;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.stream.Collectors;

enum TeamSortMode {
    NAME, WINS, LOSSES, TIES
}

enum PlayerSortMode {
    SPORT, NAME, SCORE
}

public class Interpreter {
    private TreeSet<Team> teams;
    private TeamSortMode teamSortMode;
    private TreeSet<Player> players;
    private PlayerSortMode playerSortMode;
    //private Simulator currentSimulator;

    private boolean unsavedChanges;
    private final Scanner input;

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
                    teamMenu(this.teams);
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
    private void teamMenu(TreeSet<Team> teams) {
        TreeSet<Team> workingTeams = teams;
        TeamFilter filter = new TeamFilter();
        boolean exit = false;
        while (!exit) {
            printTeamsTable(workingTeams);
            System.out.println("(1)Criar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");

            switch (input.nextLine().charAt(0)) {
                case '1':
                    createTeam();
                    break;
                case '2':
                    editTeam(grabTeam(workingTeams, askForInt("Selecione o índice da equipa.", 1, workingTeams.size())));
                    break;
                case '3':
                    Team removeTeam = grabTeam(workingTeams, askForInt("Selecione o índice da equipa.", 1, workingTeams.size()));
                    this.teams.remove(removeTeam);
                    workingTeams.remove(removeTeam);
                    break;
                case '4':
                    workingTeams = changeTeamsOrder(workingTeams);
                    break;
                case '5':
                    workingTeams = filterTeams(workingTeams, filter);
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
        Team newTeam = new Team(askForString("Qual o nome da nova equipa?", 1, 20));
        this.teams.add(newTeam);
        unsavedChanges = true;
    }

    private void printTeamsTable(TreeSet<Team> teams) {
        int i = 1;
        System.out.println("----------------------------------\n" +
                "#  Nome                  G     V   D   E");
        for (Team team : teams) {
            System.out.printf("%d- %-20s  %-5d %-3d %-3d %-3d%n",
                    i++,
                    team.getTeamName(),
                    team.getTeamGlobalAbillity(),
                    team.getWins(),
                    team.getLosses(),
                    team.getTies());
        }
        System.out.println("----------------------------------");
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
                    tmp.setTeamName(askForString("Qual o novo nome da equipa?", 1, 20));
                    changed = true;
                    break;
                case '2':
                    tmp.setWins(askForInt("Indique o número de vitórias.",0, 999));
                    changed = true;
                    break;
                case '3':
                    tmp.setLosses(askForInt("Indique o número de derrotas.",0,999));
                    changed = true;
                    break;
                case '4':
                    tmp.setTies(askForInt("Indique o número de empates.",0,999));
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

    private TreeSet<Team> changeTeamsOrder(TreeSet<Team> workingTeams) {
        System.out.println("Que ordem?\n(1)Nome\n(2)Vitórias\n(3)Derrotas\n(4)Empates");
        TreeSet<Team> newMainTeams;
        TreeSet<Team> newWorkingTeams = workingTeams;
        TeamSortMode newSortMode = TeamSortMode.values()[askForInt("Selecione o índice da ordenação.", 1, 4)-1];
        if (this.teamSortMode != newSortMode) {
            this.teamSortMode = newSortMode;
            newMainTeams = switch (newSortMode) {
                case NAME -> new TreeSet<>(new ComparatorTeamName());
                case WINS -> new TreeSet<>(new ComparatorTeamWinsFirst());
                case LOSSES -> new TreeSet<>(new ComparatorTeamLossesFirst());
                case TIES -> new TreeSet<>(new ComparatorTeamTiesFirst());
            };
            newWorkingTeams = new TreeSet<>(newMainTeams.comparator());
            for (Team team : this.teams){
                newMainTeams.add(team);
                //addAll não usado porque o enunciado pede a risco de comprometer encapsulamento
            }
            for (Team team : workingTeams){
                newWorkingTeams.add(team);
                //addAll não usado porque o enunciado pede a risco de comprometer encapsulamento
            }
            this.teams = newMainTeams;
        }
        return newWorkingTeams;
    }

    private TreeSet<Team> filterTeams (TreeSet<Team> filteredTeams, TeamFilter filter) {
        TeamFilter newFilter = filter.clone();
        boolean exit = false;
        boolean changed = false;
        while (!exit) {
            System.out.printf("""
                            Opções de filtro:
                            (1)Nome: %s
                            (2)Habilidade Global: %s
                            (3)Rácio de vitórias: %s
                            (4)Vitórias: %s
                            (5)Derrotas: %s
                            (6)Empates: %s
                            (7)Jogador: %s
                            (R)Remover filtro
                            (q)Cancelar
                            (Q)Confirmar
                            """
                , newFilter.getTeamName()
                , newFilter.getScoreBounds().toString()
                , newFilter.getWinRatio().toString()
                , newFilter.getWinBounds().toString()
                , newFilter.getLoseBounds().toString()
                , newFilter.getTieBounds().toString()
                , newFilter.getPlayerName());

            switch (input.nextLine().charAt(0)) {
                case '1':
                    newFilter.setTeamName(askForString("Nome da equipa a pesquisar?", 0, 20));
                    changed = true;
                    break;
                case '2':
                    newFilter.setScoreBounds(new Pair<>(askForInt("Habilidade global mínima?", 0, 99)
                                                        , askForInt("Habilidade global máxima?", 0, 99)));
                case '3':
                    newFilter.setWinRatio(new Pair<>(
                            askForInt("Rácio de vitórias mínimo (0%-100%)?", 0, 100)
                            , askForInt("Rácio de vitórias máximo (0%-100%)?", 0, 100)));
                    changed = true;
                    break;
                case '4':
                    newFilter.setWinBounds(new Pair<>(
                            askForInt("Número mínimo de vitórias (0-999)?", 0, 999)
                            , askForInt("Número máximo de vitórias (0-999)?", 0, 999)));
                    changed = true;
                    break;
                case '5':
                    newFilter.setLoseBounds(new Pair<>(
                            askForInt("Número mínimo de derrotas (0-999)?", 0, 999)
                            , askForInt("Número mínimo de derrotas (0-999)?", 0, 999)));
                    changed = true;
                    break;
                case '6':
                    newFilter.setTieBounds(new Pair<>(
                            askForInt("Número mínimo de empates (0-999)?", 0, 999)
                            , askForInt("Número mácimo de empates (0-999)?", 0, 999)));
                    changed = true;
                    break;
                case '7':
                    newFilter.setPlayerName(askForString("Nome do jogador a pesquisar?", 0, 20));
                    changed = true;
                case 'R':
                    filter.reset();
                    filteredTeams = this.teams;
                    exit = true;
                case 'q':
                    exit = true;
                    break;
                case 'Q':
                    if (changed) {
                        newFilter.copyTo(filter);
                        filteredTeams = this.teams.stream()
                            .filter(t-> t.passesTeamFilter(newFilter))
                            .collect(Collectors.toCollection(
                                    () -> new TreeSet<>(this.teams.comparator())));
                        /*for (Team team : this.teams){
                            if (team.passesTeamFilter(filter))
                                filteredTeams.add(team);
                        }*/
                    }
                    exit = true;
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
            }
        }
        return filteredTeams;
    }

    private void teamPlayersMenu (TreeSet<Player> players) {}

    //JOGADORES
    private void playerMenu() {
//        boolean exit = false;
//        while (!exit) {
//            printPlayers(players);
//            System.out.println("(1)Criar   (2)Editar/Visualizar   (3)Remover   (4)Ordenar   (5)Filtrar   (Q)Regressar");
//
//            switch (input.nextLine().charAt(0)) {
//                case '1':
//                    //createTeam();
//                    break;
//                case '2':
//                    //editTeam(grabTeam(selectIndex(teams.size())));
//                    break;
//                case '3':
//                    //removeTeam();
//                    System.out.println("Funcionalidade ainda não implementada!");
//                    break;
//                case '4':
//                    //changeTeamSorting();
//                    System.out.println("Funcionalidade ainda não implementada!");
//                    break;
//                case '5':
//                    //filterTeam();
//                    System.out.println("Funcionalidade ainda não implementada!");
//                    break;
//                case 'Q':
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Comando não reconhecido.");
//            }
//        }
    }

    private void createPlayer () {}

    private void editPlayer() {}

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
    /*private int selectIndex(int maxIndex) {
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
    }*/

    private int askForInt(String query, int min, int max){
        System.out.println(query);
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
            System.out.printf("Número inválido.\n%s\n", query);
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

    private String askForString(String query, int min, int max) {
        System.out.println(query);
        String name = input.nextLine();

        while (name.length() > max || name.length() < min) {
            System.out.printf("O comprimento tem de ser entre %d e %d caractéres.\n%s\n", min, max, query);
            name = input.nextLine();
        }
        return name;
    }

    private Team grabTeam(TreeSet<Team> teams, int index) {
        //INDEX STARTING AT 1!!
        Iterator<Team> it = teams.iterator();
        Team r = null;
        while (it.hasNext() && index > 0){
            r = it.next();
            index--;
        }
        return r;
    }
}

package Interpreter;

import Team.*;
import Players.*;
import Auxiliar.*;
import Auxiliar.Comparators.*;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Collectors;

enum TeamSortMode {
    NAME, WINS, LOSSES, TIES
}

enum PlayerSortMode {
    SPORT, NAME, SCORE
}

public class SportSimControl {
    private TreeSet<Team> teams;
    private TeamSortMode teamSortMode;
    private TreeSet<Player> players;
    private PlayerSortMode playerSortMode;
    //private Simulator currentSimulator;

    private boolean unsavedChanges;
    private SportSimView view;

    public SportSimControl() {
        this.teams = new TreeSet<>(new ComparatorTeamName());
        //this.teamSortMode = TeamSortMode.NAME;
        //this.players = new TreeSet<>();
        //this.playerSortMode = PlayerSortMode.SPORT;

        this.unsavedChanges = false;
        this.view = new SportSimView();
        view.welcomeMessage();
    }

    public void mainMenu() {
        boolean exit = false;
        while (!exit) {
            switch (view.viewMainMenu()) {
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
                        exit = view.exitConfirm();
                    else
                        exit = true;
                    break;
                default:
                    view.printUnrecognizedCommandError();
            }
        }
    }

    //EQUIPAS
    public void teamMenu(TreeSet<Team> teams) {
        TreeSet<Team> workingTeams = teams;
        TeamFilter filter = new TeamFilter();
        boolean exit = false;
        while (!exit) {
            view.printTeamsTable(workingTeams);

            switch (view.viewTeamMenu()) {
                case '1' -> {
                    Team newTeam = createTeam();
                    if (addTeam(newTeam) && newTeam.passesTeamFilter(filter))
                        workingTeams.add(newTeam);
                }
                case '2' -> editTeam(getTeamAtIndex(workingTeams, view.askForInt("Selecione o índice da equipa.", 1, workingTeams.size())));
                case '3' -> {
                    Team removeTeam = getTeamAtIndex(workingTeams, view.askForInt("Selecione o índice da equipa.", 1, workingTeams.size()));
                    this.teams.remove(removeTeam);
                    workingTeams.remove(removeTeam);
                }
                case '4' -> workingTeams = changeTeamsOrder(workingTeams);
                case '5' -> workingTeams = filterTeams(workingTeams, filter);
                case 'Q' -> exit = true;
                default -> view.printUnrecognizedCommandError();
            }
        }
    }

    public Team createTeam() {
        Team newTeam = new Team(view.askForString("Qual o nome da nova equipa?", 1, 20));
        return newTeam;
    }

    public void editTeam(Team team){
        Team tmp = team.clone();
        boolean exit = false;
        boolean changed = false;
        while (!exit) {
            switch (view.viewEditTeam(tmp)) {
                case '1' -> {
                    String newName = view.askForString("Qual o novo nome da equipa?", 1, 20);

                    while (teamNameExists(newName)) {
                        view.teamNameExistsError();
                        newName = view.askForString("Qual o novo nome da equipa?", 1, 20);
                    }

                    tmp.setTeamName(newName);
                    changed = true;
                }
                case '2' -> {
                    tmp.setWins(view.askForInt("Indique o número de vitórias.", 0, 999));
                    changed = true;
                }
                case '3' -> {
                    tmp.setLosses(view.askForInt("Indique o número de derrotas.", 0, 999));
                    changed = true;
                }
                case '4' -> {
                    tmp.setTies(view.askForInt("Indique o número de empates.", 0, 999));
                    changed = true;
                }
                case '5' ->
                        //playerMenu(tmp.getPlayerList());
                        System.out.println("Funcionalidade ainda não implementada!");
                case 'q' -> exit = true;
                case 'Q' -> {
                    if (changed) {
                        this.unsavedChanges = true;
                        tmp.copyTo(team);
                    }
                    exit = true;
                }
                default -> view.printUnrecognizedCommandError();
            }
        }
    }

    public TreeSet<Team> changeTeamsOrder(TreeSet<Team> workingTeams) {
        view.printTeamOrders();
        TreeSet<Team> newMainTeams;
        TreeSet<Team> newWorkingTeams = workingTeams;
        TeamSortMode newSortMode = TeamSortMode.values()[view.askForInt("Selecione o índice da ordenação.", 1, 4)-1];
        if (this.teamSortMode != newSortMode) {
            this.teamSortMode = newSortMode;
            newMainTeams = switch (newSortMode) {
                case NAME -> new TreeSet<>(new ComparatorTeamName());
                case WINS -> new TreeSet<>(new ComparatorTeamWinsFirst());
                case LOSSES -> new TreeSet<>(new ComparatorTeamLossesFirst());
                case TIES -> new TreeSet<>(new ComparatorTeamTiesFirst());
            };
            newWorkingTeams = new TreeSet<>(newMainTeams.comparator());
            for (Team team : this.teams)
                newMainTeams.add(team);
            for (Team team : workingTeams)
                newWorkingTeams.add(team);
            this.teams = newMainTeams;
        }
        return newWorkingTeams;
    }

    public TreeSet<Team> filterTeams (TreeSet<Team> filteredTeams, TeamFilter filter) {
        TeamFilter newFilter = filter.clone();
        boolean exit = false;
        boolean changed = false;
        while (!exit) {
            switch (view.viewFilterTeams(newFilter)) {
                case '1':
                    newFilter.setTeamName(view.askForString("Nome da equipa a pesquisar?", 0, 20));
                    changed = true;
                    break;
                case '2':
                    newFilter.setScoreBounds(new Pair<>(view.askForInt("Habilidade global mínima?", 0, 99)
                                                        , view.askForInt("Habilidade global máxima?", 0, 99)));
                case '3':
                    newFilter.setWinRatio(new Pair<>(
                            view.askForInt("Rácio de vitórias mínimo (0%-100%)?", 0, 100)
                            , view.askForInt("Rácio de vitórias máximo (0%-100%)?", 0, 100)));
                    changed = true;
                    break;
                case '4':
                    newFilter.setWinBounds(new Pair<>(
                            view.askForInt("Número mínimo de vitórias (0-999)?", 0, 999)
                            , view.askForInt("Número máximo de vitórias (0-999)?", 0, 999)));
                    changed = true;
                    break;
                case '5':
                    newFilter.setLoseBounds(new Pair<>(
                            view.askForInt("Número mínimo de derrotas (0-999)?", 0, 999)
                            , view.askForInt("Número mínimo de derrotas (0-999)?", 0, 999)));
                    changed = true;
                    break;
                case '6':
                    newFilter.setTieBounds(new Pair<>(
                            view.askForInt("Número mínimo de empates (0-999)?", 0, 999)
                            , view.askForInt("Número mácimo de empates (0-999)?", 0, 999)));
                    changed = true;
                    break;
                case '7':
                    newFilter.setPlayerName(view.askForString("Nome do jogador a pesquisar?", 0, 30));
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
                    }
                    exit = true;
                    break;
                default:
                    view.printUnrecognizedCommandError();
            }
        }
        return filteredTeams;
    }

    public void teamPlayersMenu (TreeSet<Player> players) {}

    //JOGADORES
    public void playerMenu() {
    }

    public void createPlayer () {}

    public void editPlayer() {}

    //GERAL
    public boolean teamNameExists(String name) {
        for (Team t : this.teams) {
            if (t.getTeamName().equals(name))
                return true;
        }
        return false;
    }

    public boolean addTeam (Team t) {
        boolean added = this.teams.add(t);
        if (added)
            this.unsavedChanges = true;
        return added;
    }

    public Team getTeamAtIndex(TreeSet<Team> teams, int index) {
        //INDEX ARGUMENT IS FROM USER, STARTS AT 1!!
        Iterator<Team> it = teams.iterator();
        Team r = null;
        while (it.hasNext() && index > 0){
            r = it.next();
            index--;
        }
        return r;
    }
}

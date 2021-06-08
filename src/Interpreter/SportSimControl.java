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
    NAME, SPORT, TEAM, AGE, SKILL
}

public class SportSimControl {
    private TreeSet<Team> teams;
    private TeamSortMode teamSortMode;
    private TreeSet<Player> players;
    private PlayerSortMode playerSortMode;
    private final String[] sports;
    //private Simulator currentSimulator;

    private boolean unsavedChanges;
    private SportSimView view;

    public SportSimControl() {
        this.teams = new TreeSet<>(new ComparatorTeamName());
        this.teamSortMode = TeamSortMode.NAME;
        this.players = new TreeSet<>(new ComparatorPlayerSport());
        this.playerSortMode = PlayerSortMode.SPORT;

        this.unsavedChanges = false;
        this.view = new SportSimView();
        this.sports = new String[]{"Futebol"};
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
                    playerMenu(this.players);
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
                case '2' -> editTeam((Team) getFromTreeAtIndex(workingTeams, view.askForInt("Selecione o índice da equipa.", 1, workingTeams.size())));
                case '3' -> {
                    Team removeTeam = (Team) getFromTreeAtIndex(workingTeams, view.askForInt("Selecione o índice da equipa.", 1, workingTeams.size()));
                    this.teams.remove(removeTeam);
                    workingTeams.remove(removeTeam);
                    this.unsavedChanges = true;
                }
                case '4' -> workingTeams = changeTeamsOrder(workingTeams);
                case '5' -> workingTeams = filterTeams(workingTeams, filter);
                case 'Q' -> exit = true;
                default -> view.printUnrecognizedCommandError();
            }
        }
    }

    public Team createTeam() {
        return new Team(view.askForString("Qual o nome da nova equipa?", 1, 20));
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
                        view.nameExistsError();
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
                case '5' -> teamPlayerMenu(team);
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

    //PLAYERS
    public void playerMenu(TreeSet<Player> workingPlayers) {
        PlayerFilter filter = new PlayerFilter();
        boolean exit = false;
        while (!exit) {
            view.printPlayersTable(workingPlayers);

            switch (view.viewPlayerMenu()) {
                case '1' -> {
                    Player newPlayer = createPlayer();
                    if (newPlayer != null) {
                        if (addPlayer(newPlayer) && newPlayer.passesPlayerFilter(filter))
                            workingPlayers.add(newPlayer);
                    }
                }
                case '2' -> editPlayer((Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size())));
                case '3' -> {
                    Player playerToRemove = (Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size()));
                    removePlayer(playerToRemove);
                }
                case '4' -> workingPlayers = changePlayersOrder(workingPlayers, this.players);

                case '5' -> workingPlayers = filterPlayers(workingPlayers, this.players, filter);
                case 'Q' -> exit = true;
                default -> view.printUnrecognizedCommandError();
            }
        }
    }

    public void teamPlayerMenu(Team team) {
        TreeSet<Player> workingPlayers = team.getPlayerTree();
        PlayerFilter filter = new PlayerFilter();
        boolean exit = false;
        while (!exit) {
            view.printPlayersTable(workingPlayers);

            switch (view.viewTeamPlayerMenu()) {
                case '1' -> {
                    String sport = team.sport();
                    if (team.getPlayerTree().size() == 0)
                        sport = this.sports[view.viewSportSelection()-1];
                    String finalSport = sport;
                    TreeSet<Player> validPlayers = this.players.stream()
                            .filter(p -> p.getSport().equals(finalSport) && !team.getPlayerTree().contains(p))
                            .collect(Collectors.toCollection(
                                    () -> new TreeSet<Player>(players.comparator())));
                    if (validPlayers.size() > 0) {
                        view.printPlayersTable(validPlayers);
                        team.addPlayer((Player)getFromTreeAtIndex(validPlayers, view.askForInt("Selecione o jogador a adicionar", 1, validPlayers.size())));
                    }
                    else
                        view.noValidPlayersError();
                }
                case '2' -> editPlayer((Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size())));
                case '3' -> {
                    Player playerToRemove = (Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size()));
                    team.removePlayer(playerToRemove);
                    workingPlayers.remove(playerToRemove);
                }
                case '4' -> workingPlayers = changePlayersOrder(workingPlayers, team.getPlayerTree());

                case '5' -> workingPlayers = filterPlayers(workingPlayers, team.getPlayerTree(), filter);
                case 'Q' -> exit = true;
                default -> view.printUnrecognizedCommandError();
            }
        }
    }

    public Player createPlayer() {
        Player newPlayer = null;
        switch(view.viewSportSelection()) {
            case 1 -> {
                String name = view.askForString("Qual o nome do jogador?", 1, 20);
                String position = view.viewFootballPositions();
                newPlayer = new FootballPlayer(name, position);
            }
        }

        return newPlayer;
    }

    public void editPlayer(Player p) {
        switch(p.getSport()) {
            case "Futebol" -> editFootballPlayer((FootballPlayer) p);
        }
    }

    public void editFootballPlayer(FootballPlayer p) {
        FootballPlayer tmp = p.clone();
        boolean exit = false;
        boolean changed = false;
        while (!exit) {
            switch (view.viewEditFootballPlayer(tmp)) {
                case "1" -> {
                    String newName = view.askForString("Qual o novo nome do jogador?", 1, 20);

                    while (playerNameExists(newName)) {
                        view.nameExistsError();
                        newName = view.askForString("Qual o novo nome da equipa?", 1, 20);
                    }

                    tmp.setName(newName);
                    changed = true;
                }
                case "2" -> {
                    tmp.setAge(view.askForInt("Indique a idade do jogador.", 0, 150));
                    changed = true;
                }
                case "3" -> {
                    tmp.setPosition(view.viewFootballPositions());
                    changed = true;
                }
                case "4" -> {
                    tmp.setVelocity(view.askForInt("Indique a nova velocidade.", 0, 999));
                    changed = true;
                }
                case "5" -> {
                    tmp.setResistance(view.askForInt("Indique a nova resistência.", 0, 999));
                    changed = true;
                }
                case "6" -> {
                    tmp.setDexterity(view.askForInt("Indique a nova destreza.", 0, 999));
                    changed = true;
                }
                case "7" -> {
                    tmp.setImpulse(view.askForInt("Indique o novo impulso.", 0, 999));
                    changed = true;
                }
                case "8" -> {
                    tmp.setHeadGame(view.askForInt("Indique a nova habilidade com cabeça.", 0, 999));
                    changed = true;
                }
                case "9" -> {
                    tmp.setShootingSkill(view.askForInt("Indique a nova habilidade de remate.", 0, 999));
                    changed = true;
                }
                case "10" -> {
                    tmp.setPassingSkill(view.askForInt("Indique a nova habilidade de passe.", 0, 999));
                    changed = true;
                }
                case "11" -> {
                    tmp.setCrossingSkill(view.askForInt("Indique a nova habilidade de cruzamento.", 0, 999));
                    changed = true;
                }
                case "12" -> {
                    tmp.setElasticity(view.askForInt("Indique a nova elasticidade.", 0, 999));
                    changed = true;
                }
                case "13" -> {
                    tmp.setBallRecuperation(view.askForInt("Indique a nova habilidade de recuperação de bola.", 0, 999));
                    changed = true;
                }
                case "14" -> {
                    tmp.setStrength(view.askForInt("Indique a nova força.", 0, 999));
                    changed = true;
                }
                case "15" -> {
                    tmp.setReceptionSkill(view.askForInt("Indique a nova habilidade de receção.", 0, 999));
                    changed = true;
                }
                case "q" -> exit = true;
                case "Q" -> {
                    if (changed) {
                        this.unsavedChanges = true;
                        tmp.copyTo(p);
                    }
                    exit = true;
                }
                default -> view.printUnrecognizedCommandError();
            }
        }
    }

    public TreeSet<Player> changePlayersOrder(TreeSet<Player> workingPlayers, TreeSet<Player> players) {
        view.printPlayerOrders();
        TreeSet<Player> newMainPlayers;
        TreeSet<Player> newWorkingPlayers = workingPlayers;
        PlayerSortMode newSortMode = PlayerSortMode.values()[view.askForInt("Selecione o índice da ordenação.", 1, 5)-1];
        if (this.playerSortMode != newSortMode) {
            this.playerSortMode = newSortMode;
            newMainPlayers = switch (newSortMode) {
                case NAME -> new TreeSet<>(new ComparatorPlayerName());
                case SPORT -> new TreeSet<>(new ComparatorPlayerSport());
                case TEAM -> new TreeSet<>(new ComparatorPlayerTeam());
                case AGE -> new TreeSet<>(new ComparatorPlayerAge());
                case SKILL -> new TreeSet<>(new ComparatorPlayerSkill());
            };
            newWorkingPlayers = new TreeSet<>(newMainPlayers.comparator());
            for (Player player : players)
                newMainPlayers.add(player);
            for (Player player : workingPlayers)
                newWorkingPlayers.add(player);
            players = newMainPlayers;
        }
        return newWorkingPlayers;
    }

    public TreeSet<Player> filterPlayers (TreeSet<Player> workingPlayers, TreeSet<Player> players, PlayerFilter filter) {
        PlayerFilter newFilter = filter.clone();
        boolean exit = false;
        boolean changed = false;
        while (!exit) {
            switch (view.viewFilterPlayers(newFilter)) {
                case '1':
                    newFilter.setPlayerName(view.askForString("Nome do jogador a pesquisar?", 0, 20));
                    changed = true;
                    break;
                case '2':
                    newFilter.setSport(view.askForString("Nome do desporto a pesquisar?", 0, 20));
                case '3':
                    newFilter.setTeamName(view.askForString("Nome da equipa a pesquisar?", 0, 20));
                    changed = true;
                    break;
                case '4':
                    newFilter.setAgeBounds(new Pair<>(
                            view.askForInt("Número mínimo da idade (0-150)?", 0, 150)
                            , view.askForInt("Número máximo da idade (0-150)?", 0, 150)));
                    changed = true;
                    break;
                case 'R':
                    filter.reset();
                    workingPlayers = players;
                    exit = true;
                case 'q':
                    exit = true;
                    break;
                case 'Q':
                    if (changed) {
                        newFilter.copyTo(filter);
                        workingPlayers = players.stream()
                                .filter(p-> p.passesPlayerFilter(newFilter))
                                .collect(Collectors.toCollection(
                                        () -> new TreeSet<>(players.comparator())));
                    }
                    exit = true;
                    break;
                default:
                    view.printUnrecognizedCommandError();
            }
        }
        return workingPlayers;
    }

    //GERAL
    public boolean teamNameExists(String name) {
        for (Team t : this.teams) {
            if (t.getTeamName().equals(name))
                return true;
        }
        return false;
    }

    public boolean playerNameExists(String name) {
        for (Player p : this.players) {
            if (p.getName().equals(name))
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

    public void removePlayer (Player p) {
        for(Team t : this.teams)
            t.removePlayer(p);
        this.players.remove(p);
        this.unsavedChanges = true;
    }

    public boolean addPlayer (Player p) {
        boolean added = this.players.add(p);
        if (added)
            this.unsavedChanges = true;
        return added;
    }

    public Object getFromTreeAtIndex(TreeSet<?> tree, int index) {
        //INDEX ARGUMENT IS FROM USER, STARTS AT 1!!
        Iterator<?> it = tree.iterator();
        Object r = null;
        while (it.hasNext() && index > 0){
            r = it.next();
            index--;
        }
        return r;
    }
}

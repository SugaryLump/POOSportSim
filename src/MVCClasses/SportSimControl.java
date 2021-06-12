package MVCClasses;

import Team.*;
import Players.*;
import Auxiliar.*;
import Game.*;
import FullParser.*;

import java.io.IOException;
import java.util.Collection;
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
    private final String[] sports;
    private Simulator currentSimulator;
    private boolean unsavedChanges;
    private SportSimModel model;
    private SportSimView view;

    public SportSimControl() {
        this.unsavedChanges = false;
	    this.currentSimulator = new Simulator();
        this.view = new SportSimView();
        try {
            this.model = Parser.parse("logs.txt");
        }
        catch (LinhaIncorretaException e) {
            SportSimView.showException(e);
            this.model = new SportSimModel();
        }
        this.sports = new String[]{"Futebol"};
        view.welcomeMessage();
    }

    public void mainMenu() {
        boolean exit = false;
        while (!exit) {
            switch (view.viewMainMenu()) {
                case '1':
                    simulationMenu();
                    break;
                case '2':
                    teamMenu();
                    break;
                case '3':
                    playerMenu();
                    break;
                case '4':
                    importMenu();
                    break;
                case '5':
                    exportMenu();
                    break;
                case '6':
                    view.printCredits();
                    break;
                case 'Q':
                    if (unsavedChanges)
                        exit = view.exitConfirm();
                    else
                        exit = true;
                    break;
                default:
                    SportSimView.unrecognizedCommandError();
            }
        }
    }


    //SIMULATOR

    public void simulationMenu(){
	    boolean exit = false, loaded = false;
	    while(!exit){
		    switch(view.viewSim()){
			    case '1':
			        TreeSet<Team> displayTeams = model.getTeams().stream()
                            .collect(Collectors.toCollection(() -> new TreeSet<>(model.getNewTeamComparator())));
			        view.printTeamsTable(displayTeams);
			        Team team1 = (Team)getFromTreeAtIndex(displayTeams, view.askForInt("Selecione uma equipa", 1, displayTeams.size()));
                    view.printTeamsTable(displayTeams.stream()
                            .filter(t -> t.sport().equals(team1.sport()) && !t.equals(team1))
                            .collect(Collectors.toCollection(() -> new TreeSet<>(model.getNewTeamComparator()))));
                    Team team2 = (Team)getFromTreeAtIndex(displayTeams, view.askForInt("Selecione uma equipa", 1, displayTeams.size()));
                    loaded = true;
                    currentSimulator.teamSelection(team1, team2);
			    case '2':
				    view.game_stats(currentSimulator);

			    case '3':
				    if (loaded)
					    runGameMenu();
				    else
					    SportSimView.gameNotLoadedError();
			    case 'Q':
					      exit = true;
			    default:
				    SportSimView.unrecognizedCommandError();
		    }
	    }
    }

    public void runGameMenu(){
	    boolean done = false;
	    int x;
	    while(!done){
		    view.printGame(currentSimulator.get_game().getTeams());
		    switch(view.getPause()){
			    case '1':
				    currentSimulator.l(false);
			    default:
				    x = currentSimulator.l(true);
				    
		    }
		    if (x == 100)
			    done = true;
	    }
	    view.printResults(currentSimulator.get_game());
	    model.updateInfoFromGame(currentSimulator.get_game());
    }

    //EQUIPAS
    public void teamMenu() {
        TreeSet<Team> workingTeams = model.getTeams().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<Team>(this.model.getNewTeamComparator())));
        TeamFilter filter = new TeamFilter();
        boolean exit = false;
        while (!exit) {
            view.printTeamsTable(workingTeams);

            switch (view.viewTeamMenu()) {
                case '1' -> {
                    Team newTeam = createTeam();
                    if (model.addTeam(newTeam) && newTeam.passesTeamFilter(filter)) {
                        workingTeams.add(newTeam);
                        this.unsavedChanges = true;
                    }
                }
                case '2' -> editTeam((Team) getFromTreeAtIndex(workingTeams, view.askForInt("Selecione o índice da equipa.", 1, workingTeams.size())));
                case '3' -> {
                    Team removeTeam = (Team) getFromTreeAtIndex(workingTeams, view.askForInt("Selecione o índice da equipa.", 1, workingTeams.size()));
                    model.removeTeam(removeTeam);
                    workingTeams.remove(removeTeam);
                    this.unsavedChanges = true;
                }
                case '4' -> workingTeams = changeTeamsOrder(workingTeams);
                case '5' -> workingTeams = filterTeams(workingTeams, filter);
                case 'Q' -> exit = true;
                default -> SportSimView.unrecognizedCommandError();
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

                    while (model.teamNameExists(newName)) {
                        SportSimView.nameExistsError();
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
                default -> SportSimView.unrecognizedCommandError();
            }
        }
    }

    public TreeSet<Team> changeTeamsOrder(TreeSet<Team> workingTeams) {
        view.printTeamOrders();
        TreeSet<Team> newWorkingTeams = workingTeams;
        if(model.changeTeamsOrder(TeamSortMode.values()[view.askForInt("Selecione o índice da ordenação.", 1, 4)-1])) {
            newWorkingTeams = new TreeSet<Team>(model.getNewTeamComparator());
            for (Team team : workingTeams)
                newWorkingTeams.add(team);
        }
        return newWorkingTeams;
    }

    public TreeSet<Team> filterTeams (TreeSet<Team> workingTeams, TeamFilter filter) {
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
                    workingTeams = model.getTeams();
                    exit = true;
                    break;
                case 'q':
                    exit = true;
                    break;
                case 'Q':
                    if (changed) {
                        newFilter.copyTo(filter);
                        workingTeams = model.getFilteredTeams(filter);
                    }
                    exit = true;
                    break;
                default:
                    SportSimView.unrecognizedCommandError();
            }
        }
        return workingTeams;
    }

    //PLAYERS
    public void playerMenu() {
        PlayerFilter filter = new PlayerFilter();
        TreeSet<Player> workingPlayers = model.getPlayers().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<Player>(model.getNewPlayerComparator())));
        boolean exit = false;
        while (!exit) {
            view.printPlayersTable(workingPlayers);

            switch (view.viewPlayerMenu()) {
                case '1' -> {
                    Player newPlayer = createPlayer();
                    if (newPlayer != null) {
                        if (model.addPlayer(newPlayer) && newPlayer.passesPlayerFilter(filter)) {
                            workingPlayers.add(newPlayer);
                            this.unsavedChanges = true;
                        }
                    }
                }
                case '2' -> editPlayer((Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size())));
                case '3' -> {
                    Player playerToRemove = (Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size()));
                    workingPlayers.remove(playerToRemove);
                    model.removePlayer(playerToRemove);
                    this.unsavedChanges = true;
                }
                case '4' -> workingPlayers = changePlayersOrder(workingPlayers);
                case '5' -> workingPlayers = filterPlayers(workingPlayers, model.getPlayers(), filter);
                case 'Q' -> exit = true;
                default -> SportSimView.unrecognizedCommandError();
            }
        }
    }

    public void teamPlayerMenu(Team team) {
        TreeSet<Player> workingPlayers = team.getPlayers().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(model.getNewPlayerComparator())));
        PlayerFilter filter = new PlayerFilter();
        boolean exit = false;
        while (!exit) {
            view.printPlayersTable(workingPlayers);

            switch (view.viewTeamPlayerMenu()) {
                case '1' -> {
                    String sport = team.sport();
                    if (sport.equals(""))
                        sport = this.sports[view.viewSportSelection()-1];
                    String finalSport = sport;
                    TreeSet<Player> validPlayers = model.getPlayers().stream()
                            .filter(p -> p.getSport().equals(finalSport) && !p.isInTeam())
                            .collect(Collectors.toCollection(
                                    () -> new TreeSet<>(model.getNewPlayerComparator())));
                    if (validPlayers.size() > 0) {
                        view.printPlayersTable(validPlayers);
                        Player playerToAdd = (Player)getFromTreeAtIndex(validPlayers, view.askForInt("Selecione o jogador a adicionar", 1, validPlayers.size()));
                        team.addPlayer(playerToAdd);
                        if (playerToAdd.passesPlayerFilter(filter))
                            workingPlayers.add(playerToAdd);
                    }
                    else
                        SportSimView.noValidPlayersError();
                }
                case '2' -> editPlayer((Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size())));
                case '3' -> {
                    Player playerToRemove = (Player)getFromTreeAtIndex(workingPlayers, view.askForInt("Selecione o índice do jogador.", 1, workingPlayers.size()));
                    team.removePlayer(playerToRemove);
                    workingPlayers.remove(playerToRemove);
                }
                case '4' -> workingPlayers = changePlayersOrder(workingPlayers);
                case '5' -> workingPlayers = filterPlayers(workingPlayers, team.getPlayers(), filter);
                case 'Q' -> exit = true;
                default -> SportSimView.unrecognizedCommandError();
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

                    while (model.playerNameExists(newName)) {
                        SportSimView.nameExistsError();
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
                default -> SportSimView.unrecognizedCommandError();
            }
        }
    }

    public TreeSet<Player> changePlayersOrder(TreeSet<Player> workingPlayers) {
        view.printPlayerOrders();
        TreeSet<Player> newWorkingPlayers = workingPlayers;

        if(model.changePlayersOrder(PlayerSortMode.values()[view.askForInt("Selecione o índice da ordenação.", 1, 5)-1])) {
            newWorkingPlayers = new TreeSet<Player>(model.getNewPlayerComparator());
            for (Player player : workingPlayers)
                newWorkingPlayers.add(player);
        }
        return newWorkingPlayers;
    }

    public TreeSet<Player> filterPlayers (TreeSet<Player> workingPlayers, Collection<Player> players, PlayerFilter filter) {
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
                    workingPlayers = players.stream().collect(Collectors.toCollection(() -> new TreeSet<>(model.getNewPlayerComparator())));
                    exit = true;
                    break;
                case 'q':
                    exit = true;
                    break;
                case 'Q':
                    if (changed) {
                        newFilter.copyTo(filter);
                        workingPlayers = players.stream()
                                .filter(p-> p.passesPlayerFilter(newFilter))
                                .collect(Collectors.toCollection(
                                        () -> new TreeSet<>(model.getNewPlayerComparator())));
                    }
                    exit = true;
                    break;
                default:
                    SportSimView.unrecognizedCommandError();
            }
        }
        return workingPlayers;
    }

    //EXPORT
    public void exportMenu() {
        try {
            FileHandler.exportModelToFile(this.model, view.askForString("Qual o nome do ficheiro?", 1, 30));
            this.unsavedChanges = false;
        }
        catch (IOException e) {
            SportSimView.showException(e);
        }
    }

    //IMPORT
    public void importMenu() {
        try {
            this.model = FileHandler.importModelFromFile(view.askForString("Qual o nome do ficheiro?", 1, 30));
        }
        catch (IOException | ClassNotFoundException e) {
            SportSimView.showException(e);
        }
    }
    //GERAL
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

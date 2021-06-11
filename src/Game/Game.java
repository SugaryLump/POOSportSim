package Game;

import java.util.List;

import Auxiliar.*;
import Team.Team;


public class Game {
    private int time; // Time Elapsed (0-45/45-90);
    private Pair<Integer,Integer> gameScore; // Pair with the game score
    private Pair<Team,Team> teams;
    private int day; // Day of game daymonth(00×00×)
    private int gameID; // Game ID
    private Events events; // Numero de Eventos num jogo (para time thinghys)

	public Game() {
		this.time = 0000;
		this.gameScore = new Pair<Integer, Integer>(0,0);
		this.teams = new Pair<Team, Team>(null,null);
		this.day = 00000;
		this.gameID = 00000;
		this.events = new Events();
	}
	public Game(int time, Pair<Integer, Integer> gameScore, Pair<Team,Team> teams, int day, int gameID, Events events) {
		this.time = time;
		this.gameScore = gameScore;
		this.teams = teams;
		this.day = day;
		this.gameID = gameID;
		this.events = events;
	}

	public int getTime() {
        return time;
    }

    public Pair<Integer, Integer> getGameScore() {
        return gameScore;
    }

    public Pair<Team,Team> getTeams() {
        return teams;
    }

    public int getDay() {
        return day;
    }

    public int getGameID() {
        return gameID;
    }

    public Events get_events(){
	    return events;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setGameScore(Pair<Integer, Integer> gameScore) {
        this.gameScore = gameScore;
    }

    public void setTeams(Pair<Team,Team> teams) {
        this.teams = teams;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void set_events(Events event){
	    this.events = event;
    }

    public int event_new(){
	    events.rand_stuf();
	    int outcome = events.random();
	    Things ting = new Things();
	    if(events.getCur_events() >= events.getEvents_game() && this.time > 90)
		    return 100;
	    switch(outcome){
		    case 23:
			    events.setCur_events(events.getCur_events()+1);
			    ting.set_player("Striker");
			    ting.set_event("Semi_Miss");
			    ting.set_timestamp(events.getLow());
			    ting.set_effect_on_game("Nothing");
		    case 16:
			    events.setCur_events(events.getCur_events()+1);
			    ting.set_player("Striker");
			    ting.set_event("Full Miss");
			    ting.set_timestamp(events.getLow());
			    ting.set_effect_on_game("Nothing");

		    case 8:
			    events.setCur_events(events.getCur_events()+1);
			    ting.set_player("______");
			    ting.set_event("______");
			    ting.set_timestamp(events.getLow());
			    ting.set_effect_on_game("Nothing");

		    case 0: 
			    events.setCur_events(events.getCur_events()+1);
			    ting.set_player("______");
			    ting.set_event("Scores");
			    ting.set_timestamp(events.getLow());
			    ting.set_effect_on_game("Goal");
			    double l =Math.random()+ getTeams().getL().getTeamGlobalAbillity();
			    double r =Math.random()+ getTeams().getR().getTeamGlobalAbillity();
			    if (l>r) getGameScore().setL(getGameScore().getL()+1);
			    else getGameScore().setR(getGameScore().getR()+1);

		    case 2:
			    events.setCur_events(events.getCur_events()+1);
			    ting.set_player("______");
			    ting.set_event("Misses");
			    ting.set_timestamp(events.getLow());
			    ting.set_effect_on_game("Nothing");
		    default:
			    break;
	    }
	    List<Things> lt = events.getGame_events_list();
	    lt.add(ting);
	    return 0;
    }
}

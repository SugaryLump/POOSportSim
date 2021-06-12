package Game;

import java.util.Date;
import java.util.List;

import Auxiliar.*;
import Team.Team;


public class Game {
    private int time; // Time Elapsed (0-45/45-90);
    private Pair<Integer,Integer> gameScore; // Pair with the game score
    private Pair<Team,Team> teams;
    private Date day; // Day of game daymonth(00×00×)
    private Events events; // Numero de Eventos num jogo (para time thinghys)

	public Game() {
		this.time = 0;
		this.gameScore = new Pair<Integer, Integer>(0,0);
		this.teams = new Pair<Team, Team>(null,null);
		this.day = new Date();
		this.events = new Events();
	}
	public Game(int time, Pair<Integer, Integer> gameScore, Pair<Team,Team> teams, Date day, Events events) {
		this.time = time;
		this.gameScore = gameScore;
		this.teams = teams;
		this.day = day;
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

    public Date getDay() {
        return day;
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

    public void setDay(Date day) {
        this.day = day;
    }

    public void setEvents(Events event){
	    this.events = event;
    }

    public int newEvent(){
	    events.rand_stuf();
	    int outcome = events.random();
	    Things ting = new Things();
	    if(this.time >= 90)
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
			    double l =Math.random()+ getTeams().getL().overallAbilityTeam();
			    double r =Math.random()+ getTeams().getR().overallAbilityTeam();
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
	    this.time = events.getLow();
	    List<Things> lt = events.getGameEventsList();
	    lt.add(ting);
	    return 0;
    }
}

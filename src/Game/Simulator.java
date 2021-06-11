package Game;
import Auxiliar.Pair;
import Team.Team;

public class Simulator{
	private Game game;

	public Simulator(){
		game = new Game();
	}
	
	public Simulator(Game g){
		game=g;
	}
	
	public Game get_game(){
		return game;
	}

	public void set_game(Game game){
		this.game = game;
	}

	public void teamSelection(Team team1,Team team2){
		Pair<Team,Team> teams = new Pair<>(team1,team2);
		game.setTeams(teams);
	}

	public int l(boolean cnt){
		if (cnt){
			return game.newEvent();
		}
		return -1;
	}
}

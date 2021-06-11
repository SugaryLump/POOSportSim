package Game;

public class Things{
	private String player;
	private String event;
	private int timestamp;
	private String effect_on_game;

	public Things(){
		player = "";
		event = "";
		timestamp = 0;
		effect_on_game  = "";
	}

	public Things(String player, String event, int timestamp, String effect_on_game){
		this.player=player;
		this.event = event;
		this.timestamp = timestamp;
		this.effect_on_game = effect_on_game;
	}

	public Things(Things e){
		this.player = e.get_player();
		this.event = e.get_event();
		this.timestamp = e.get_timestamp();
		this.effect_on_game = e.get_effect_on_game();

	}

	public String get_player() {
		return player;
	}

	public void set_player(String player) {
		this.player = player;
	}

	public String get_event() {
		return event;
	}

	public void set_event(String event) {
		this.event = event;
	}

	public int get_timestamp() {
		return timestamp;
	}

	public void set_timestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String get_effect_on_game() {
		return effect_on_game;
	}

	public void set_effect_on_game(String effect_on_game) {
		this.effect_on_game = effect_on_game;
	}
}

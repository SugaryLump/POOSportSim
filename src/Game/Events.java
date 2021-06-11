package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Events{
	
	private int low;
	private List<Things> game_events_list;
	private int events_game;
	private int cur_events;


	public Events(){
		List <Things> game_events_list = new ArrayList<>();
		events_game = 0;
		cur_events = 0;
	}

	public Events(int max){
		events_game=max;
		cur_events=0;
	}

	public List<Things> getGame_events_list() {
		return game_events_list;
	}

	public void setGame_events_list(List<Things> game_events_list) {
		List<Things> cat = new ArrayList<>();
                 for(Things c : game_events_list){
                        cat.add(c);
                 }
                 this.game_events_list = cat;
         }
	
	public int getEvents_game() {
		return events_game;
	}

	public void setEvents_game(int events_game) {
		this.events_game = events_game;
	}

	public int getCur_events() {
		return cur_events;
	}

	public void setCur_events(int cur_events) {
		this.cur_events = cur_events;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public Things rand_stuf(){
	
		Random r = new Random();
		Things t = new Things();
		int high = 90;
		int result = r.nextInt(high-this.low) + low;
		this.low = result;
		t.set_timestamp(result);
		return t;
	}
	public int random(){
		Random r = new Random();
		int low = 0;
		int high = 32;
		return r.nextInt(high-low) + low;
	}
}

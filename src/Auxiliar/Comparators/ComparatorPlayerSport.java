package Auxiliar.Comparators;

import Players.Player;

import java.util.Comparator;

public class ComparatorPlayerSport implements Comparator<Player>{
    public int compare (Player p1, Player p2) {
        int cmp = p1.getSport().compareTo(p2.getSport());
        if (cmp == 0) {
            cmp = p1.getName().compareTo(p2.getName());
        }
        return cmp;
    }
}

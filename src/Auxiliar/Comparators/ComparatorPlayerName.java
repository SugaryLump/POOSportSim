package Auxiliar.Comparators;

import java.util.Comparator;

import Players.*;

public class ComparatorPlayerName implements Comparator<Player>{
    public int compare (Player p1, Player p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

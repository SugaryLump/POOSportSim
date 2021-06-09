package Auxiliar.Comparators;

import Players.Player;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorPlayerSkill implements Comparator<Player>, Serializable {
    public int compare (Player p1, Player p2) {
        int cmp = p2.overallAbility() - p1.overallAbility();
        if (cmp == 0) {
            cmp = p1.getName().compareTo(p2.getName());
        }
        return cmp;
    }
}

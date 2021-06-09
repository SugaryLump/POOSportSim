package Auxiliar.Comparators;

import java.io.Serializable;
import java.util.Comparator;

import Team.*;


public class ComparatorTeamLossesFirst implements Comparator<Team>, Serializable {
    public int compare (Team t1, Team t2) {
        int cmp = t2.getLosses()-t1.getLosses();
        if (cmp == 0) {
            cmp = t1.getTeamName().compareTo(t2.getTeamName());
        }
        return cmp;
    }
}

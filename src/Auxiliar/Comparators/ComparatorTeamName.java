package Auxiliar.Comparators;

import java.io.Serializable;
import java.util.Comparator;

import Team.*;

public class ComparatorTeamName implements Comparator<Team>, Serializable {
    public int compare (Team t1, Team t2) {
        return t1.getTeamName().compareTo(t2.getTeamName());
    }
}

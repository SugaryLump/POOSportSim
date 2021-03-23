package Classes;
import java.util.Comparator;

public class ComparatorTeamLosses implements Comparator<Team>{
    public int compare (Team t1, Team t2) {
        return t1.getTeamName().compareTo(t2.getTeamName());
    }
}

package Classes;
import java.util.Comparator;

public class ComparatorTeamLossesFirst implements Comparator<Team>{
    public int compare (Team t1, Team t2) {
        int cmp = t2.getLosses()-t1.getLosses();
        if (cmp == 0) {
            cmp = t1.getTeamName().compareTo(t2.getTeamName());
        }
        return cmp;
    }
}

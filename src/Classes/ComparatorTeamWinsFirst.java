package Classes;
import java.util.Comparator;

public class ComparatorTeamWinsFirst implements Comparator<Team>{
    public int compare (Team t1, Team t2) {
        int cmp = t2.getWins() - t1.getWins();
        if (cmp == 0) {
            cmp = t1.getTeamName().compareTo(t2.getTeamName());
        }
        return cmp;
    }
}

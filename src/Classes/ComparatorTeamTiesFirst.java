package Classes;
import java.util.Comparator;

public class ComparatorTeamTiesFirst implements Comparator<Team>{
    public int compare (Team t1, Team t2) {
        int cmp = t1.getTies()-t2.getTies();
        if (cmp == 0) {
            cmp = t1.getTeamName().compareTo(t2.getTeamName());
        }
        return cmp;
    }
}
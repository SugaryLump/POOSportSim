package Classes;
import java.util.Comparator;

public class ComparatorTeamWins implements Comparator<Team>{
    public int compare (Team t1, Team t2) {
        return (t1.getLosses()-t2.getLosses());
    }
}

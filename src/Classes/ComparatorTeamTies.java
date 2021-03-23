package Classes;
import java.util.Comparator;

public class ComparatorTeamTies implements Comparator<Team>{
    public int compare (Team t1, Team t2) {
        return (t1.getTies() - t2.getTies());
    }
}

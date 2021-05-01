package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CSVReader {

    //Team: name;score;wins;losses;ties;teamID;teamGlobalAbility;[playerNames]
    public static ArrayList<Team> importTeamsFromFile(String filename, Set<Player> players) throws FileNotFoundException {
        ArrayList<Team> teams = new ArrayList<Team>();
        Scanner file = new Scanner(new File(filename));
        while (file.hasNextLine()) {
            String[] line = file.nextLine().split("[;\n]");
            if (line.length == 8) {
                String[] playerNames = line[7].split("[\\[,]");

                TreeSet<Player> ps = new TreeSet<>(new ComparatorPlayerName());
                for (String name : playerNames) {
                    players.stream()
                            .filter(p -> p.getName().equals(name))
                            .findFirst()
                            .ifPresent(ps::add);
                }
                Team team = new Team(line[0]
                        , Integer.parseInt(line[1])
                        , Integer.parseInt(line[2])
                        , Integer.parseInt(line[3])
                        , Integer.parseInt(line[4])
                        , Integer.parseInt(line[5])
                        , Integer.parseInt(line[6])
                        , ps);
                teams.add(team);
            }
        }

        return teams;
    }

    public static ArrayList<Player> importPlayersFromFile(String filename) throws FileNotFoundException{return null;}

    public static void exportTeamToFile(Set<Team> teams, String filename) {}

    public static void exportPlayerToFile(Set<Player> players, String filename) {}
}

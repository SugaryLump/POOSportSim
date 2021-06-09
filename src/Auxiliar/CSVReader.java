package Auxiliar;

import MVCClasses.SportSimModel;
import MVCClasses.SportSimView;
import Players.*;
import Team.*;
import Auxiliar.Comparators.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CSVReader {

    //Team: name;score;wins;losses;ties;teamID;teamGlobalAbility;[playerNames]\n
    public static ArrayList<Team> importTeamsFromFile(String filename, Set<Player> players) throws FileNotFoundException {
        ArrayList<Team> teams = new ArrayList<Team>();
        Scanner file = new Scanner(new File(filename));
        while (file.hasNextLine()) {
            String[] line = file.nextLine().split("[;\n]");
            if (line.length == 8) {
                String[] playerNames = line[7].split("[\\[,\\]]");

                ArrayList<Player> ps = new ArrayList<>();
                Team team = new Team(line[0]
                        , Integer.parseInt(line[1])
                        , Integer.parseInt(line[2])
                        , Integer.parseInt(line[3])
                        , Integer.parseInt(line[4])
                        , Integer.parseInt(line[5])
                        , Integer.parseInt(line[6])
                        , ps);
                for (String name : playerNames) {
                    Player addingPlayer = players.stream()
                            .filter(p -> p.getName().equals(name))
                            .findFirst().orElse(null);

                    if (addingPlayer == null)
                        SportSimView.playerNotFoundError(name);
                    else if (addingPlayer.isInTeam())
                        SportSimView.playerAlreadyInTeamError(name);
                    else
                        team.addPlayer(addingPlayer);
                }
                teams.add(team);
            }
        }

        return teams;
    }


    public static ArrayList<Player> importPlayersFromFile(String filename) throws FileNotFoundException{return null;}

    public static void exportModelToFile(SportSimModel model, String filename) throws IOException {
        File obj = new File(filename);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(obj));
        oos.writeObject(model);
        oos.close();
    }

    public static SportSimModel importModelFromFile(String filename) throws IOException, ClassNotFoundException {
        File obj = new File(filename);
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(obj));
        return (SportSimModel)oos.readObject();
    }

    public static void exportTeamToFile(Set<Team> teams, String filename) throws IOException {
        File obj = new File(filename);
        ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(obj));
        objout.writeObject(teams);
        objout.close();
    }

    public static void exportPlayersToFile(Set<Player> players, String filename) throws IOException {
        File obj = new File(filename);
        ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(obj));
        objout.writeObject(players);
        objout.close();
    }
}

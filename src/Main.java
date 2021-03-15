import Classes.*;

public class Main {
    //private List<Team> teams;
    //private List<Player> players;
    //private Simulator currentSimulator;

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        interpreter.welcomeMessage();
        interpreter.mainMenu();
    }
}

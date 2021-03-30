import Classes.*;

public class Main {

    public static void main(String[] args) {
        //Interpreter interpreter = new Interpreter();
        //interpreter.welcomeMessage();
        //interpreter.mainMenu();
        int [] teste = {1,4,21};
        FootballPlayer ola = new FootballPlayer("ola", 69, teste, teste, 47,"a tua tia");
        System.out.println(ola.toString());
        ola.addTeam(79);
        System.out.println(ola.toString());
        ola.setPosition("o teu tio");
        System.out.println(ola.toString());
    }
}

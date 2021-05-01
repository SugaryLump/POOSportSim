package Classes;

public class Goalkeeper extends FootballPlayer {
    public int elasticity; //Custom abillity for goalkeepers

    public Goalkeeper(String name, int age, int[] history, int[] ability, int globalAbility, String position, int elasticity) {
        super(name, age, history, ability, globalAbility, position);
        this.elasticity = elasticity;
    }

    public int getElasticity() {
        return elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }
}

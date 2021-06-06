package Classes;

public class Goalkeeper extends FootballPlayer {
    public int elasticity; //Custom abillity for goalkeepers

    public Goalkeeper(){
        super();
        this.elasticity = 0;
    }

    public Goalkeeper(String name, int age, int[] history, int vel, int res,
                   int dex, int imp, int hg, int ss, int ps, int str){
        super(name, age, history, vel, res, dex, imp, hg, ss, ps);
        this.elasticity= str;
    }

    public Goalkeeper(Goalkeeper d){
        super(d);
        this.elasticity = getElasticity();
    }

    public int getElasticity() {
        return elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    @Override
    public int overallAbility() {
        int a = this.velocity + this.resistance + this.dexterity + this.impulse + this.headGame + this.shootingSkill + this.shootingSkill + this.elasticity;
        int b = a / 8;
        return b;
    }
}

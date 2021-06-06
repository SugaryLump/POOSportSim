package Players;

public class Midfield extends FootballPlayer {
    public int ballRecuperation;

    public Midfield(){
        super();
        this.ballRecuperation = 0;
    }

    public Midfield(String name, int age, int[] history, int vel, int res,
                      int dex, int imp, int hg, int ss, int ps, int str){
        super(name, age, history, vel, res, dex, imp, hg, ss, ps);
        this.ballRecuperation = str;
    }

    public Midfield(Midfield d){
        super(d);
        this.ballRecuperation = getBallRecuperation();
    }

    public int getBallRecuperation() {
        return ballRecuperation;
    }

    public void setBallRecuperation(int ballRecuperation) {
        this.ballRecuperation = ballRecuperation;
    }

    @Override
    public int overallAbility() {
        int a = this.velocity + this.resistance + this.dexterity + this.impulse
                + this.headGame + this.shootingSkill + this.shootingSkill + this.ballRecuperation;
        int b = a / 8;
        return b;
    }
}

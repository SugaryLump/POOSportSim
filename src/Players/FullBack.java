package Players;

public class FullBack extends FootballPlayer{
    public int crossingSkill;

    public FullBack(){
        super();
        this.crossingSkill = 0;
    }

    public FullBack(String name, int age, int[] history, int vel, int res,
                      int dex, int imp, int hg, int ss, int ps, int str){
        super(name, age, history, vel, res, dex, imp, hg, ss, ps);
        this.crossingSkill = str;
    }

    public FullBack(Goalkeeper d){
        super(d);
        this.crossingSkill = getCrossingSkill();
    }

    public int getCrossingSkill() {
        return crossingSkill;
    }

    public void setCrossingSkill(int crossingSkill) {
        this.crossingSkill = crossingSkill;
    }

    @Override
    public int overallAbility() {
        int a = this.velocity + this.resistance + this.dexterity + this.impulse + this.headGame + this.shootingSkill + this.shootingSkill + this.crossingSkill;
        int b = a / 8;
        return b;
    }
}

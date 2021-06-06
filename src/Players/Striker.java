package Players;

public class Striker extends FootballPlayer{
    public int receptionSkill;

    public Striker(){
        super();
        this.receptionSkill = 0;
    }

    public Striker(String name, int age, int[] history, int vel, int res,
                    int dex, int imp, int hg, int ss, int ps, int str){
        super(name, age, history, vel, res, dex, imp, hg, ss, ps);
        this.receptionSkill = str;
    }

    public Striker(Midfield d){
        super(d);
        this.receptionSkill = getReceptionSkill();
    }


    public int getReceptionSkill() {
        return receptionSkill;
    }

    public void setReceptionSkill(int receptionSkill) {
        this.receptionSkill = receptionSkill;
    }

    @Override
    public int overallAbility() {
        int a = this.velocity + this.resistance + this.dexterity + this.impulse + this.headGame + this.shootingSkill + this.shootingSkill + this.receptionSkill;
        int b = a / 8;
        return b;
    }
}

package Classes;

public class Defense extends FootballPlayer{
    public int strength;

    public Defense(){
        super();
        this. strength = 0;
    }

    public Defense(String name, int age, int[] history, int vel, int res,
                   int dex, int imp, int hg, int ss, int ps, int str){
        super(name, age, history, vel, res, dex, imp, hg, ss, ps);
        this.strength = str;
    }

    public Defense(Defense d){
        super(d);
        this.strength = getStrength();
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int overallAbility() {
        return 0;
    }
}

package Players;

public abstract class FootballPlayer extends Player{
    public int velocity;
    public int resistance;
    public int dexterity;
    public int impulse;
    public int headGame;
    public int shootingSkill;
    public int passingSkill;

    public FootballPlayer(){
        this.name = "";
        this.age = 0;
        this.history = null;
        this.velocity = 0;
        this.resistance = 0;
        this.dexterity = 0;
        this.impulse = 0;
        this.headGame = 0;
        this.shootingSkill = 0;
        this.passingSkill = 0;
    }

    public FootballPlayer(String name, int age, int[] history, int vel,
                          int res, int dex, int imp, int hg, int ss, int pass) {
        this.name = name;
        this.age = age;
        this.history = history;
        this.velocity = vel;
        this.resistance = res;
        this.dexterity = dex;
        this.impulse = imp;
        this.headGame = hg;
        this.shootingSkill = ss;
        this.passingSkill = pass;
    }

    public FootballPlayer(FootballPlayer p){
        this.name = getName();
        this.age = getAge();
        this.history = getHistory();
        this.velocity = getVelocity();
        this.resistance = getResistance();
        this.impulse = getImpulse();
        this.headGame = getHeadGame();
        this.shootingSkill = getShootingSkill();
        this.passingSkill = getPassingSkill();
    }

    public int getVelocity() {
        return velocity;
    }

    public int getResistance() {
        return resistance;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getImpulse() {
        return impulse;
    }

    public int getHeadGame() {
        return headGame;
    }

    public int getShootingSkill() {
        return shootingSkill;
    }

    public int getPassingSkill() {
        return passingSkill;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setImpulse(int impulse) {
        this.impulse = impulse;
    }

    public void setHeadGame(int headGame) {
        this.headGame = headGame;
    }

    public void setShootingSkill(int shootingSkill) {
        this.shootingSkill = shootingSkill;
    }

    public void setPassingSkill(int passingSkill) {
        this.passingSkill = passingSkill;
    }

    public abstract int overallAbility();
}

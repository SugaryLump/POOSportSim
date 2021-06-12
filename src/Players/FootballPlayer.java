package Players;

import java.util.ArrayList;
public class FootballPlayer extends Player{
    private int velocity;
    private int resistance;
    private int dexterity;
    private int impulse;
    private int headGame;
    private int shootingSkill;
    private int passingSkill;
    //Positions
    private String position;
    //FullBack
    private int crossingSkill;
    //Goalkeeper
    private int elasticity;
    //Midfield
    private int ballRecuperation;
    //Defense
    private int strength;
    //Striker
    private int receptionSkill;

    public FootballPlayer(){
        super();
        this.setSport("Futebol");
        this.velocity = 0;
        this.resistance = 0;
        this.dexterity = 0;
        this.impulse = 0;
        this.headGame = 0;
        this.shootingSkill = 0;
        this.passingSkill = 0;
        this.position = "";
        this.crossingSkill = 0;
        this.elasticity = 0;
        this.ballRecuperation = 0;
        this.strength = 0;
        this.receptionSkill = 0;
    }

    public FootballPlayer(String name, String position){
        super();
        this.setName(name);
        this.setSport("Futebol");
        this.setPosition(position);
        this.velocity = 0;
        this.resistance = 0;
        this.dexterity = 0;
        this.impulse = 0;
        this.headGame = 0;
        this.shootingSkill = 0;
        this.passingSkill = 0;
        this.crossingSkill = 0;
        this.elasticity = 0;
        this.ballRecuperation = 0;
        this.strength = 0;
        this.receptionSkill = 0;
    }

    public FootballPlayer(String name, int age, ArrayList<String> history, int vel,
                          int res, int dex, int imp, int hg, int ss, int pass, String pos,
                          int crs, int el, int blr, int str, int rcs) {
        super(name, age, history, "Futebol");
        this.velocity = vel;
        this.resistance = res;
        this.dexterity = dex;
        this.impulse = imp;
        this.headGame = hg;
        this.shootingSkill = ss;
        this.passingSkill = pass;
        this.crossingSkill = crs;
        this.elasticity = el;
        this.ballRecuperation = blr;
        this.strength = str;
        this.receptionSkill = rcs;
        this.position = pos;
    }

    public FootballPlayer(FootballPlayer p){
        super(p.getName(), p.getAge(), new ArrayList<String>(p.getHistory()), p.getSport());
        this.velocity = p.getVelocity();
        this.resistance = p.getResistance();
        this.impulse = p.getImpulse();
        this.dexterity = p.getDexterity();
        this.headGame = p.getHeadGame();
        this.shootingSkill = p.getShootingSkill();
        this.passingSkill = p.getPassingSkill();
        this.position = p.getPosition();
        this.crossingSkill = p.getCrossingSkill();
        this.elasticity = p.getElasticity();
        this.ballRecuperation = p.getBallRecuperation();
        this.strength = p.getStrength();
        this.receptionSkill = p.getReceptionSkill();
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCrossingSkill() {
        return crossingSkill;
    }

    public void setCrossingSkill(int crossingSkill) {
        this.crossingSkill = crossingSkill;
    }

    public int getElasticity() {
        return elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    public int getBallRecuperation() {
        return ballRecuperation;
    }

    public void setBallRecuperation(int ballRecuperation) {
        this.ballRecuperation = ballRecuperation;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getReceptionSkill() {
        return receptionSkill;
    }

    public void setReceptionSkill(int receptionSkill) {
        this.receptionSkill = receptionSkill;
    }

    public int overallAbility() {
        switch (this.position) {
            case ("Defesa"):
                int a = this.velocity + this.resistance + this.dexterity + this.impulse
                        + this.headGame + this.shootingSkill + this.shootingSkill + this.strength;
                int b = a / 8;
                return b;
            case ("Lateral"):
                int c = this.velocity + this.resistance + this.dexterity + this.impulse
                        + this.headGame + this.shootingSkill + this.shootingSkill + this.crossingSkill;
                int d = c / 8;
                return d;
            case ("Guarda-Redes"):
                int e = this.velocity + this.resistance + this.dexterity + this.impulse
                        + this.headGame + this.shootingSkill + this.shootingSkill + this.elasticity;
                int f = e / 8;
                return f;
            case ("Medio"):
                int h = this.velocity + this.resistance + this.dexterity + this.impulse
                        + this.headGame + this.shootingSkill + this.shootingSkill + this.ballRecuperation;
                int i = h / 8;
                return i;
            case ("Avancado"):
                int j = this.velocity + this.resistance + this.dexterity + this.impulse
                        + this.headGame + this.shootingSkill + this.shootingSkill + this.receptionSkill;
                int k = j / 8;
                return k;
            default:
                return 0;
        }
    }

    public FootballPlayer clone() {
        return new FootballPlayer(this);
    }

    public void copyTo(FootballPlayer p) {
        super.copyTo(p);
        p.setVelocity(velocity);
        p.setDexterity(dexterity);
        p.setResistance(resistance);
        p.setImpulse(impulse);
        p.setHeadGame(headGame);
        p.setShootingSkill(shootingSkill);
        p.setPassingSkill(passingSkill);
        p.setCrossingSkill(crossingSkill);
        p.setElasticity(elasticity);
        p.setBallRecuperation(ballRecuperation);
        p.setStrength(strength);
        p.setReceptionSkill(receptionSkill);
        p.setPosition(position);
    }

    //--Jogadores (pertencentes Ã  ultima equipa listada antes deles )
    //--Os valores de Velocidade, ResistÃªncia, Destreza, ImpulsÃ£o, CabeÃ§a, Remate, Passe, Elasticidade, Cruzamento e RecuperaÃ§Ã£o sÃ£o valores entre 0 a 100.
    //Guarda-Redes:<Nome>,<NumeroCamisola>,<Velocidade>,<ResistÃªncia>,<Destreza>,<ImpulsÃ£o>,<CabeÃ§a>,<Remate>,<Passe>,<Elasticidade>
    //Defesa:<Nome>,<NumeroCamisola>,<Velocidade>,<ResistÃªncia>,<Destreza>,<ImpulsÃ£o>,<CabeÃ§a>,<Remate>,<Passe>
    //Lateral:<Nome>,<NumeroCamisola>,<Velocidade>,<ResistÃªncia>,<Destreza>,<ImpulsÃ£o>,<CabeÃ§a>,<Remate>,<Passe>,<Cruzamento>
    //Medio:<Nome>,<NumeroCamisola>,<Velocidade>,<ResistÃªncia>,<Destreza>,<ImpulsÃ£o>,<CabeÃ§a>,<Remate>,<Passe>,<Recuperacao>
    //Avancado:<Nome>,<NumeroCamisola>,<Velocidade>,<ResistÃªncia>,<Destreza>,<ImpulsÃ£o>,<CabeÃ§a>,<Remate>,<Passe>
    public static Player parse (String position, String line) {
        String[] splitLine = line.split(",",10);
        FootballPlayer p = new FootballPlayer(splitLine[0]
                ,  25
                , new ArrayList<String>()
                , Integer.parseInt(splitLine[2])
                , Integer.parseInt(splitLine[3])
                , Integer.parseInt(splitLine[4])
                , Integer.parseInt(splitLine[5])
                , Integer.parseInt(splitLine[6])
                , Integer.parseInt(splitLine[7])
                , Integer.parseInt(splitLine[8])
                , position
                , 0, 0, 0, 0, 0);

        switch (position) {
            case "Guarda-Redes" -> p.setElasticity(Integer.parseInt(splitLine[9]));
            case "Lateral" -> p.setCrossingSkill(Integer.parseInt(splitLine[9]));
            case "Medio" -> p.setBallRecuperation(Integer.parseInt(splitLine[9]));
        }

        return p;
    }
}

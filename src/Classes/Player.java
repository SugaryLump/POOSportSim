package Classes;

public class Player {
    // nome idade posiçao array de ints e teamids
    public String name;
    public int age;
    public int position;
    public int [] history;
    public int [] abiliity;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int[] getAbiliity() {
        return abiliity;
    }

    public int[] getHistory() {
        return history;
    }

    public int getPosition() {
        return position;
    }

    public void setAbiliity(int[] abiliity) {
        this.abiliity = abiliity;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHistory(int[] history) {
        this.history = history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}



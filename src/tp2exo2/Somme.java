package tp2exo2;

public class Somme extends Message {
    private int s;

    public Somme(int s) {
        this.s = s;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public synchronized void add(int s) { this.s += s; }
}

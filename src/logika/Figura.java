package logika;

public class Figura {

    private int x, y;

    public Figura(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void prestavi(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Figura na polju (" + x + "," + y + ")";
    }


}


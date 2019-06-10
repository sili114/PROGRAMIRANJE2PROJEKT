package logika;

public class Poteza { // Poteza je doloèena s koordinatami na plošèi
    private int x;
    private int y;

    public Poteza(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { // Vrne koordinato X
        return x;
    }

    public int getY() { // Vrne koordinato Y
        return y;
    }

    @Override
    public String toString() { // Izpiše potezo
        return "Poteza [x=" + x + ", y=" + y + "]";
    }
}


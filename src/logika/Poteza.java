package logika;

public class Poteza { // Poteza je dolo�ena s koordinatami na plo��i
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
    public String toString() { // Izpi�e potezo
        return "Poteza [x=" + x + ", y=" + y + "]";
    }
}


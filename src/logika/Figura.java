package logika;

public class Figura { // Pozicija figure je doloèena s koordinatami na plošèi

    private int x, y;

    public Figura(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void prestavi(int x, int y){ // Premakne figuro na novo polje
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
    public String toString() { // Izpiše, kje se figura nahaja
        return "Figura na polju (" + x + "," + y + ")";
    }


}


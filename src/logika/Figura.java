package logika;
import java.awt.*;

public class Figura {
	
	public Color barva; //Dodal barvo, da lahko program sam določi barvo figure
	
	private Igralec igralec; // Igralec.BELI ali Igralec.CRNI

    private int x, y;

    public Figura(int x, int y, Igralec lastnik){ //figuro določata lokacija na plošči in lastnik
        this.x = x;
        this.y = y;
        this.igralec = lastnik;
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
    
    public void Barva () { //določi barvo, glede na lastnika
    	if (igralec == Igralec.BELI) {
    		this.barva = Color.WHITE;
    	}
    	else {
    		this.barva = Color.BLACK;
    	}
    }

    @Override
    public String toString() {
        return "Figura na polju (" + x + "," + y + ")";
    }


}


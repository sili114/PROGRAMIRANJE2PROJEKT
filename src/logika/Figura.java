package logika;
import java.awt.*;

public class Figura {
	
	public Color barva;
	
	private final Igralec igralec;

    private int x, y;

    public Figura(int x, int y, Igralec lastnik){
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
    
    public void Barva (Igralec lastnik) {
    	if (lastnik == Igralec.BELI) {
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

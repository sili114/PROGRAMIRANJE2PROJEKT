package logika;


import sun.invoke.empty.Empty; //Za kaj je to uporabno?

import java.util.*;

public class Igra {

    private Plosca plosca;

    private Figura beli;

    private Figura crni;

    public Igralec naPotezi;

    private boolean premikFigure;
    
    private LinkedList<Poteza> odstranjena_polja;
    
    public Igra(){

        plosca = new Plosca();

        beli = new Figura(3, 0);

        crni = new Figura(3, 6);

        premikFigure = true;

        naPotezi = Igralec.BELI;

        plosca.polja[0][3] = Polje.BELO;
        plosca.polja[6][3] = Polje.CRNO;
    }

    public List<Poteza> poteze(){
        LinkedList<Poteza> ps = new LinkedList<Poteza>();
        int x = (naPotezi == Igralec.BELI) ? beli.getX() : crni.getX();
        int y = (naPotezi == Igralec.BELI) ? beli.getY() : crni.getY();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (0 <= x + i && x + i < 7 && 0 <= y + j && y + j < 7) {
                    if (plosca.polja[y + j][x + i] == Polje.PRAZNO) {
                        ps.add(new Poteza(x + i, y + j));
                    }
                }
            }
        }
        return ps;
    }

    public Stanje stanje(){
        if (poteze().isEmpty()){
            return(naPotezi == Igralec.BELI) ? Stanje.ZMAGA_BELI : Stanje.ZMAGA_CRNI;
        }
        else{
            return (naPotezi == Igralec.BELI) ? Stanje.NA_POTEZI_BELI: Stanje.NA_POTEZI_CRNI;
        }
    }

    public boolean odigraj(Poteza p){
        if (premikFigure){
            if (poteze().contains(p)){
                if (naPotezi == Igralec.BELI){
                    plosca.polja[p.getY()][p.getX()] = Polje.BELO;
                    plosca.polja[beli.getY()][beli.getX()] = Polje.PRAZNO;
                    beli.prestavi(p.getX(),p.getY());
                }
                else if (naPotezi == Igralec.CRNI){
                    plosca.polja[p.getY()][p.getX()] = Polje.CRNO;
                    plosca.polja[crni.getY()][crni.getX()] = Polje.PRAZNO;
                    crni.prestavi(p.getX(),p.getY());
                }
                // Po premiku se premikFigure spremeni na false, po končani odstranitvi polja pa nazaj na true
                premikFigure = false;
                return true;
            }
            else return false;

        }
        //ker je premikFigure zdaj false, program razume, da je dana poteza odstranitev polj. Morda bi pomagalo, če se implementira nek seznam
        // odstranjenih polj.
        else {
        	if (!odstranjena_polja.contains(p)) { //Če polje še ni odstranjeno
        		if (plosca.polja[p.getY()][p.getX()] != Polje.CRNO && plosca.polja[p.getY()][p.getX()] != Polje.BELO) { //in če polje ne vsebuje figure
        			plosca.odstrani(p.getX(), p.getY()); //potem polje odstranimo
        			odstranjena_polja.add(p); //dodamo potezo v seznam odstranjenih potez
        			premikFigure = true; //ker smo polje odstranili nastavimo premikFigure nazaj na true
        			return true;
        		}
        		else { //če polje vsebuje figuro
        			return false;
        		}
        	}
        	else {//polje je že odstranjeno
        		return false;
        	}
        }
   }

}

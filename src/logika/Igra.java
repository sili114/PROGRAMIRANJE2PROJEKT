package logika;


import sun.invoke.empty.Empty;

import java.util.*;

public class Igra {

    private Plosca plosca;

    private Figura beli;

    private Figura crni;

    public Igralec naPotezi;

    private boolean premikFigure;

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
                return true;
            }
            else return false;

        }
        else 

}

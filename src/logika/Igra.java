package logika;


import java.awt.BasicStroke;
import java.awt.Color;

// import sun.invoke.empty.Empty;

import java.util.*;

import static logika.Plosca.N;

public class Igra {

    private Plosca plosca;

    public Figura beli;

    public Figura crni;

    public Igralec naPotezi; // Igralec, ki je "trenutno" na potezi

    public int stevecPotez;

    public boolean premikFigure; // Ali je Igralec (èlovek) že premaknil figuro, ali še ne?

    public Igra() {

        plosca = new Plosca(); // Postavimo plošèo in na njo obe figuri

        beli = new Figura(3, 0); 
        plosca.polja[0][3] = Polje.BELO;
        
        crni = new Figura(3, 6);
        plosca.polja[6][3] = Polje.CRNO;
        
        premikFigure = true; // Premik figure še ni bil izveden

        stevecPotez = 0;

        naPotezi = Igralec.BELI; // Zaène beli igralec

        
        
    }

    public List<Poteza> potezeDef(Igralec  igr){
        LinkedList<Poteza> ps = new LinkedList<Poteza>();
            int x = (igr == Igralec.BELI) ? beli.getX() : crni.getX();
            int y = (igr == Igralec.BELI) ? beli.getY() : crni.getY();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (0 <= x + i && x + i < N && 0 <= y + j && y + j < N) {
                        if (plosca.polja[y + j][x + i] == Polje.PRAZNO) {
                            ps.add(new Poteza(x + i, y + j));
                        }
                    }
                }
            }
            return ps; }

    public List<Poteza> poteze() { // Vrne vse možne poteze za tega, ki je trenutno na vrsti.
        LinkedList<Poteza> ps = new LinkedList<Poteza>();
        if (premikFigure) { // Èe je v fazi premikanja, vrnemo vse možne premike
            int x = (naPotezi == Igralec.BELI) ? beli.getX() : crni.getX();
            int y = (naPotezi == Igralec.BELI) ? beli.getY() : crni.getY();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (0 <= x + i && x + i < N && 0 <= y + j && y + j < N) {
                        if (plosca.polja[y + j][x + i] == Polje.PRAZNO) {
                            ps.add(new Poteza(x + i, y + j));
                        }
                    }
                }
            }
        }
        else { // Sicer vrnemo vsa polja, ki so primerna za odstranitev
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (plosca.polja[j][i] == Polje.PRAZNO) ps.add(new Poteza(i, j));
            }
        }

        }
        return ps;
    }


    public Stanje stanje() {
        if (poteze().isEmpty()) { // Èe Igralec ne more veè premakniti figure, imamo zmagovalca
            return (naPotezi == Igralec.CRNI) ? Stanje.ZMAGA_BELI : Stanje.ZMAGA_CRNI;
        } else {
            return (naPotezi == Igralec.BELI) ? Stanje.NA_POTEZI_BELI : Stanje.NA_POTEZI_CRNI;
        }
    }

    public Plosca getPlosca() { // Vrne plošèo.
        return plosca;
    }

    public boolean vsebuje(Poteza p){ // Preveri, ali je dana poteza med dovoljenimi.
        for (Poteza h : poteze()){
            if (h.getX() == p.getX() && h.getY() == p.getY()) return true;
        }
        return false;
    }
    
    public boolean odigraj(Poteza p){
        if (premikFigure){ // Smo v fazi premikanja
        	
            if (vsebuje(p)){ // Premik je dovoljen, zato ga izvedemo
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
                // Po premiku se premikFigure spremeni na false, po konèani odstranitvi polja pa nazaj na true
                premikFigure = false; // Sedaj smo v fazi odstranjevanja
                return true;
            }
            else return false;

        }
        //ker je premikFigure zdaj false, program razume, da je dana poteza odstranitev polj. 
        else {
            if (plosca.polja[p.getY()][p.getX()] == Polje.PRAZNO) { //polje je prazno in ga lahko odstranimo
                plosca.odstrani(p.getX(),p.getY());
                premikFigure = true;
                naPotezi = naPotezi.nasprotnik();
                stevecPotez += 1;
                return true;
            }
            else return false; //polje je bodisi zasedeno bodisi odstranjeno
            }
        }

    public Igra(Igra igra) { // Postavimo igralno plošèo in nastavimo vse potrebne parametre
        plosca = new Plosca();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                plosca.polja[i][j] = igra.plosca.polja[i][j];
            }
        }
        this.naPotezi = igra.naPotezi;
        this.premikFigure = igra.premikFigure;
        int crnix = igra.crni.getX();
        int crniy = igra.crni.getY();
        int belix = igra.beli.getX();
        int beliy = igra.beli.getY();
        this.crni = new Figura(crnix, crniy);
        this.beli = new Figura(belix, beliy);
    }



}

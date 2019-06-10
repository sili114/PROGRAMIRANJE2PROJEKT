package logika;


import sun.invoke.empty.Empty;

import java.util.*;

import static logika.Plosca.N;

public class Igra {

    private Plosca plosca;

    public Figura beli;

    public Figura crni;

    public Igralec naPotezi;

    public int stevecPotez;

    public boolean premikFigure;

    public Igra() {

        plosca = new Plosca();

        beli = new Figura(3, 0);

        crni = new Figura(3, 6);

        premikFigure = true;

        stevecPotez = 0;

        naPotezi = Igralec.BELI;

        plosca.polja[0][3] = Polje.BELO;
        plosca.polja[6][3] = Polje.CRNO;
    }




    public int steviloOkoliskihPolj(Igralec igr, int okolica) {
        int vsota = 0;
        int x = (igr == Igralec.BELI) ? beli.getX() : crni.getX();
        int y = (igr == Igralec.BELI) ? beli.getY() : crni.getY();
        for (int i = -okolica; i < okolica + 1; i++) {
            for (int j = -okolica; j < okolica + 1; j++) {
                if (0 <= x + i && x + i < N && 0 <= y + j && y + j < N) {
                    if (plosca.polja[y + j][x + i] == Polje.PRAZNO) vsota++;
                }
            }
        }
    return vsota;}

    public List<Poteza> poteze() {
        LinkedList<Poteza> ps = new LinkedList<Poteza>();
        if (premikFigure) {
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
        else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (plosca.polja[j][i] == Polje.PRAZNO) ps.add(new Poteza(i, j));
            }
        }

        }
        return ps;
    }




    public Stanje stanje() {
        if (poteze().isEmpty()) {
            return (naPotezi == Igralec.CRNI) ? Stanje.ZMAGA_BELI : Stanje.ZMAGA_CRNI;
        } else {
            return (naPotezi == Igralec.BELI) ? Stanje.NA_POTEZI_BELI : Stanje.NA_POTEZI_CRNI;
        }
    }

    public Plosca getPlosca() {
        return plosca;
    }

    private boolean vsebuje(Poteza p){
        for (Poteza h : poteze()){
            if (h.getX() == p.getX() && h.getY() == p.getY()) return true;
        }
        return false;
    }

    public boolean odigraj(Poteza p){
        if (premikFigure){
            if (vsebuje(p)){
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

    public Igra(Igra igra) {
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

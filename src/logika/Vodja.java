package logika;

import gui.GlavnoOkno;
import inteligenca.AlphaBeta;

import javax.swing.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Vodja {

    // Glavno okno
    private GlavnoOkno okno;

    // Igra, ki jo trenutno igramo.
    public Igra igra;


    // Ali je clovek beli ali crni?
    private Igralec clovek; //rabmo ko bo implementiran clovek vs racunalnik

    public boolean clovekNaVrsti;

    public Random random;

    public Vodja(GlavnoOkno okno) {
        random = new Random();
        this.okno = okno;
        clovekNaVrsti = true;
    }

    public void novaIgra(Igralec clovek) {
        // Ustvarimo novo igro
        this.igra = new Igra();
        // beli ali crni igralec
        this.clovek = clovek;
        igramo();
    }

    public void igramo () {
        okno.osveziGUI();
        switch (igra.stanje()) {
            case ZMAGA_BELI:
            case ZMAGA_CRNI:
                break;
            case NA_POTEZI_BELI:
            case NA_POTEZI_CRNI:
                if (igra.naPotezi == clovek) {
                    clovekNaVrsti = true;
                } else {
                    racunalnikovaPoteza();
                }
        }
    }

    public void racunalnikovaPoteza() {
            SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
                private Igra zacetnaIgra = igra;
                @Override
                protected Poteza doInBackground() {
                    return AlphaBeta.alphabetaVrzi (igra, clovek.nasprotnik());
                }
                @Override
                protected void done () {
                    Poteza poteza;
                    try {
                        poteza = get();
                        if (poteza != null && zacetnaIgra == igra)
                        {igra.odigraj(poteza);
                            igramo();
                        }
                    } catch (Exception e) {};
                }
            };
            worker.execute();
        }


    public void clovekovaPoteza(Poteza poteza) {
        if (igra.odigraj(poteza)) {
            if (igra.premikFigure)clovekNaVrsti = false;
            igramo();
        }

    }



}



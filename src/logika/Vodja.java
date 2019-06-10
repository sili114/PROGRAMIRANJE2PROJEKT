
package logika;

import gui.GlavnoOkno;
import inteligenca.AlphaBeta;

import javax.swing.*;
import java.util.*;

public class Vodja {

    // Glavno okno
    private GlavnoOkno okno;

    // Igra, ki jo trenutno igramo.
    public Igra igra;


    // Ali je Igralec beli ali �rni?
    private Igralec clovek; // Potrebujemo za primere, ko bomo morali lo�iti med �lovekom in ra�unalnikom

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
        // Beli ali �rni igralec
        this.clovek = clovek;
        igramo();
    }

    public void igramo () {
    	// Preveri in upravlja s stanjem igre
        okno.osveziGUI();
        switch (igra.stanje()) {
        	// Preveri, �e je kdo �e zmagal
            case ZMAGA_BELI:
            case ZMAGA_CRNI:
                break;
            // Nadaljuj, glede na to kdo je na vrsti
            case NA_POTEZI_BELI:
            case NA_POTEZI_CRNI:
                if (igra.naPotezi == clovek) { // �e je na vrsti �lovek, naj izvede svojo potezo
                    clovekNaVrsti = true;
                } else { // Sicer odigraj ra�unalnikovo potezo.
                    racunalnikovaPoteza();
                }
        }
    }

    public void racunalnikovaPoteza() {
    	// Odigra ra�unalnikovo potezo.
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


    public void clovekovaPoteza(Poteza poteza) { // Odigra �lovekovo potezo in spremlja v kateri fazi je
        if (igra.odigraj(poteza)) {
            if (igra.premikFigure) {
            	clovekNaVrsti = false;
            }
            igramo();
        }

    }



}



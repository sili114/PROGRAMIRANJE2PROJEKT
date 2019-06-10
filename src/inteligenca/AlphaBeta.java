
package inteligenca;

import java.util.List;
import java.util.Random;

import logika.*;


public class AlphaBeta {


    private static final int ZMAGA = (1 << 20); // vrednost zmage, veÄ kot vsaka druga ocena pozicije
    private static final int ZGUBA = -ZMAGA;  // vrednost izgube, mora biti -ZMAGA

    private static final int GLOBINA = 6; // globalna globina algoritma minimax

    public static Poteza alphabetaVrzi (Igra igra, Igralec jaz) {
        // Na zaÄetku alpha = ZGUBA in beta = ZMAGA
        return alphabetaPoteze(igra, GLOBINA, ZGUBA, ZMAGA, jaz).poteza;
    }

    public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
        int ocena;

        // Äe sem raÄunalnik, maksimiramo oceno z zaÄetno oceno ZGUBA
        // Äe sem pa Älovek, minimiziramo oceno z zaÄetno oceno ZMAGA
        if (igra.naPotezi == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
        List<Poteza> moznePoteze = igra.poteze();
        Poteza kandidat = moznePoteze.get(0); // MoĹžno je, da se ne spremini vrednost kanditata. Zato ne more biti null.
        for (Poteza p: moznePoteze) {
            Igra tempIgra = new Igra(igra);
            tempIgra.odigraj (p);
            int ocenap = alphabetaPozicijo (tempIgra, globina-1, alpha, beta, jaz);
            if (igra.naPotezi == jaz) { // Maksimiramo oceno
                if (ocenap > ocena) {// Za alphabeta mora biti > namesto >=
                    ocena = ocenap;
                    kandidat = p;
                    alpha = Math.max(alpha,ocena);
                }
            } else { // igra.naPotezi != jaz, torej minimiziramo oceno
                if (ocenap < ocena) { // Za alphabeta mora biti < namesto <=
                    ocena = ocenap;
                    kandidat = p;
                    beta = Math.min(beta, ocena);
                }
            }
            if (alpha >= beta) {return new OcenjenaPoteza (kandidat, ocena);} // Izstopimo iz "for loop", ker ostale poteze ne pomagajo
        }
        return new OcenjenaPoteza (kandidat, ocena);
    }

    public static int alphabetaPozicijo(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
        Stanje stanje = igra.stanje();
        switch (stanje) {
            case ZMAGA_BELI: return (jaz == Igralec.BELI ? ZMAGA : ZGUBA);
            case ZMAGA_CRNI: return (jaz == Igralec.CRNI ? ZMAGA : ZGUBA);
            default:
                // Nekdo je na potezi
                if (globina == 0) {return oceniPozicijo(igra, jaz);}
                // globina > 0
                OcenjenaPoteza ocenjenaPoteza = alphabetaPoteze (igra, globina, alpha, beta, jaz);
                return ocenjenaPoteza.vrednost;
        }
    }

    
    public static int oceniPozicijo(Igra igra, Igralec jaz) {
        int vsota = 0;
        vsota +=  50 * igra.steviloOkoliskihPolj(jaz, 1);
        vsota +=  2.5 * igra.steviloOkoliskihPolj(jaz, 2);
        vsota += 0.5 * igra.steviloOkoliskihPolj(jaz, 3);
        vsota -= 10 * igra.steviloOkoliskihPolj(jaz.nasprotnik(), 3);
        vsota -= 50 * igra.steviloOkoliskihPolj(jaz.nasprotnik(), 2);
        vsota -=  150 * igra.steviloOkoliskihPolj(jaz.nasprotnik(), 1);
        if (igra.steviloOkoliskihPolj(jaz.nasprotnik(), 1) == 1) vsota += 1000000;
        return vsota;
    }


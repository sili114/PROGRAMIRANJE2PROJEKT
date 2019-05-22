package inteligenca;

import java.util.List;
import java.util.Random;

import logika.*;


public class AlphaBeta {

    private static final Random RANDOM = new Random();

    private static final int ZMAGA = (1 << 20); // vrednost zmage, ki je veèja kot vsaka druga ocena pozicije
    private static final int ZGUBA = -ZMAGA;  // vrednost izgube mora biti -ZMAGA

    private static final int GLOBINA = 6; // globalna globina algoritma minimax

    public static Poteza alphabetaVrzi (Igra igra, Igralec jaz) {
        // Na zaèetku alpha = ZGUBA in beta = ZMAGA
        return alphabetaPoteze(igra, GLOBINA, ZGUBA, ZMAGA, jaz).poteza;
    }

    public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
        int ocena;

        // Èe sem raèunalnik, maksimiramo oceno z zaèetno oceno ZGUBA
        // Èe sem pa èlovek, minimiziramo oceno z zaèetno oceno ZMAGA
        if (igra.naPotezi == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
        List<Poteza> moznePoteze = igra.poteze();
        Poteza kandidat = moznePoteze.get(0); // Možno je, da se ne spremini vrednost kanditata. Zato ne more biti null.
        for (Poteza p: moznePoteze) {
            Igra tempIgra = new Igra(igra);
            tempIgra.odigraj (p);
            int ocenap = alphabetaPozicijo (tempIgra, globina-1, alpha, beta, jaz);
            if (igra.naPotezi == jaz) { // Maksimiramo oceno
                if (ocenap > ocena) { // Za alphabeta mora biti > namesto >=
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

    private static int stevilo_okoliskih_polj(Igra igra, Igralec jaz){
    	// Ne vem èemu služi ta metoda...
        int vsota = 0;
        Figura fig;
        if (jaz == Igralec.BELI) {
        	fig = igra.beli;
        }
        else {
        	fig = igra.crni;
        }
        for (int j= -2; j < 2; j++){
            for (int k=-2; k < 2; k++){
            }
        }
    return vsota;}

    // Nakljuèna ocena pozicije. Metoda ni uporabljena.
    public static int oceniPozicijo(Igra igra, Igralec jaz) {
        int vsota = 0;
        vsota += 3.5 * igra.potezeDef(jaz).size();
        vsota -= 175 * igra.potezeDef(jaz.nasprotnik()).size();
        if (igra.potezeDef(jaz).size() > igra.potezeDef(jaz.nasprotnik()).size()) vsota += 1000;
        if (igra.potezeDef(jaz.nasprotnik()).size() == 1) vsota += 10000;
        return vsota;
    }

}
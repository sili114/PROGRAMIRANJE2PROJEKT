package logika;

public class Vodja {

    // Glavno okno
    private GlavnoOkno okno;

    // Igra, ki jo trenutno igramo.
    public Igra igra;

    // Ali je clovek beli ali crni?
    private Igralec clovek; //rabmo ko bo implementiran clovek vs racunalnik

    public boolean clovekNaVrsti;

    public Vodja(GlavnoOkno okno) {
        random = new Random();
        this.okno = okno;
        clovekNaVrsti = false;
    }
    // funckija za minimax da nardi kopijo igre in na njej odigra poteze in pol zračuna kire so najbolš.
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
        List<OcenjenaPoteza> ocenjenePoteze = Minimax.oceniPoteze (igra, 2, clovek.nasprotnik());
        Poteza poteza = Minimax.maxPoteza(ocenjenePoteze);
        igra.odigraj(poteza);
        igramo();
    }

    public void clovekovaPoteza(Poteza poteza) {
        if (igra.odigraj(poteza)) {
            clovekNaVrsti = false;
            igramo();
        }

    }



}

}

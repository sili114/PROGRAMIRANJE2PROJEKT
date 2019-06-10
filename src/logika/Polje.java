package logika;

/**
 * @author AS
 * Možne vrednosti polj na plošèi.
 */

public enum Polje { // Vsa možna stanja polja na plošèi
    PRAZNO,
    BELO,
    CRNO,
    ODSTRANJENO;

    public String toString() {
        switch (this) {
            case PRAZNO: return " ";
            case ODSTRANJENO: return "X";
            case BELO: return "B";
            case CRNO: return "C";
            default: return "?";
        }
    }
}
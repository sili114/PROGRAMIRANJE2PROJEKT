package logika;

/**
 * @author AS
 * Mo�ne vrednosti polj na plo��i.
 */

public enum Polje { // Vsa mo�na stanja polja na plo��i
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
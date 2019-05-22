package logika;

/**
 * @author AS
 * MoĹžne vrednosti polj na ploĹĄÄi.
 */

public enum Polje {
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
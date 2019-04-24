package logika;

/**
 * @author AS
 * MoĹžne vrednosti polj na ploĹĄÄi.
 */

public enum Polje {
    PRAZNO,
    ODSTRANJENO;

    public String toString() {
        switch (this) {
            case PRAZNO: return " ";
            case ODSTRANJENO: return "X";
            default: return "?";
        }
    }
}
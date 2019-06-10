
package logika;


public enum Igralec { // Igralec lahko igra s �rno ali belo figuro
    BELI, CRNI;

    public Igralec nasprotnik(){ // Nasprotnik igralca, ki je trenutno na vrsti
        return (this == BELI ? CRNI : BELI);
    }

}



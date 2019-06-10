
package logika;

public class Plosca { // Sestavimo igralno plo��o

    public Polje[][] polja;
    public static final int N = 7; // Velikost N x N plo��e


    public Plosca(){ // Na za�etku je vsako polje za prazno

        polja = new Polje[N][N];
        for (int i=0; i< N; i++){
            for(int j=0; j < N; j++){
                polja[i][j] = Polje.PRAZNO;
            }
        }
    }

    public void odstrani(int x, int y){ // Odstranimo prazno polje
        polja[y][x] = Polje.ODSTRANJENO;
    }


}

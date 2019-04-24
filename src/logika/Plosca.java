package logika;

public class Plosca {

    Polje[][] polja;
    public static final int N = 7;


    public Plosca(){

        polja = new Polje[N][N];
        for (int i=0; i< N; i++){
            for(int j=0; j < N; j++){
                polja[i][j] = Polje.PRAZNO;
            }
        }
    }


}

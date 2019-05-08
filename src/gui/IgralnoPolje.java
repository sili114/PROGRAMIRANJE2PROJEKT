package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika.Vodja;
import logika.Igra;
import logika.Plosca;
import logika.Polje;
import logika.Poteza;

/**
 * Pravokotno obmoèje, v katerem je narisano igralno polje.
 *
 * @author AS
 *
 */
@SuppressWarnings("serial")
public class IgralnoPolje extends JPanel implements MouseListener {

    private Vodja vodja;

    Plosca plosca;

    /**
     * Relativna širina èrte
     */
    private final static double LINE_WIDTH = 0.1;

    /**
     * Relativni prostor okoli figur
     */
    private final static double PADDING = 0.1;

    public IgralnoPolje(Vodja vodja) {
        super();
        setBackground(Color.white);
        this.addMouseListener(this);

        this.vodja = vodja;

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(387, 387);
    }

    /**
     * @return širina enega kvadratka
     */
    private double squareWidth() {
        return Math.min(getWidth(), getHeight()) / logika.Plosca.N;
    }

    /**
     * V grafièni kontekst {@g2} nariši križec v polje {@(i,j)}
     * @param g2
     * @param i
     * @param j
     */

    private void paintFigura(Graphics2D g2, int i, int j) {
        if (vodja.igra != null) {
            plosca = vodja.igra.getPlosca();
        }
        double w = squareWidth();
        double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // premer O
        double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
        double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
        if (plosca.polja[i][j] == Polje.BELO) {
            g2.setColor(Color.WHITE);
        }
        else {
            g2.setColor(Color.BLACK);
        }
        //if (vodja.igra.naPotezi == logika.Igralec.BELI) {
        //	g2.setColor(Color.BLACK);
        //}
        //else {
        //	g2.setColor(Color.BLACK);
        //}

        g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
        g2.fillOval((int)x, (int)y, (int)r , (int)r);
    }

    private void paintSquare(Graphics2D g2, int i, int j) {
        if (vodja.igra != null) {
            plosca = vodja.igra.getPlosca();
        }
        double w = squareWidth();
        double r = w * (1.0 - 0.025 * LINE_WIDTH); // premer O
        double x = w * (i + 0.5 * LINE_WIDTH);
        double y = w * (j + 0.5 * LINE_WIDTH);
        if (plosca.polja[i][j] == Polje.ODSTRANJENO) {
            g2.setColor(Color.RED);
        }
        else {
            g2.setColor(Color.YELLOW);
        }
        //if (vodja.igra.naPotezi == logika.Igralec.BELI) {
        //	g2.setColor(Color.BLACK);
        //}
        //else {
        //	g2.setColor(Color.BLACK);
        //}

        g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
        g2.fillRect((int)x, (int)y, (int)r , (int)r);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        double w = squareWidth();

        // èrte
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
        for (int i = 0; i <= logika.Plosca.N; i++) {
            g2.drawLine((int)(i * w),
                    (int)(0),
                    (int)(i * w),
                    (int)(logika.Plosca.N * w));
            g2.drawLine((int)(0),
                    (int)(i * w),
                    (int)(logika.Plosca.N * w),
                    (int)(i * w));
        }

        System.out.println(vodja);

        // figure
        if (vodja.igra != null) {
            plosca = vodja.igra.getPlosca();
            for (int i = 0; i < 7; ++i){
                for (int j = 0; j < 7; ++j) {
                    if (plosca.polja[i][j] == Polje.BELO) {
                        paintFigura(g2, i, j);
                    }
                    else if (plosca.polja[i][j] == Polje.CRNO) {
                        paintFigura(g2, i, j);
                    }

                    paintSquare(g2, i, j);

                }
            }
            paintFigura(g2, vodja.igra.beli.getY(), vodja.igra.beli.getX());
            paintFigura(g2, vodja.igra.crni.getY(), vodja.igra.crni.getX());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (vodja.clovekNaVrsti) {
            int x = e.getX();
            int y = e.getY();
            int w = (int)(squareWidth());
            int i = x / w ;
            double di = (x % w) / squareWidth() ;
            int j = y / w ;
            double dj = (y % w) / squareWidth() ;
            if (0 <= i && i < Plosca.N &&
                    0.5 * LINE_WIDTH < di && di < 1.0 - 0.5 * LINE_WIDTH &&
                    0 <= j && j < Plosca.N &&
                    0.5 * LINE_WIDTH < dj && dj < 1.0 - 0.5 * LINE_WIDTH) {
                vodja.clovekovaPoteza(new Poteza(i, j));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
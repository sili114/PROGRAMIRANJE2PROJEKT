
package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logika.Vodja;
import logika.Igralec;

    @SuppressWarnings("serial")
    public class GlavnoOkno extends JFrame implements ActionListener {


        private IgralnoPolje polje;


        //Statusna vrstica v spodnjem delu okna
        private JLabel status;


        // Vodja igre
        private Vodja vodja;

        // Izbire v menujih
        private JMenuItem igraClovekBELI;
        private JMenuItem igraClovekCRNI;

        /**
         * Ustvari novo glavno okno in priƒçni igrati igro.
         */
        public GlavnoOkno() {

            this.setTitle("Isolation");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLayout(new GridBagLayout());

            // Vodja igre
            this.vodja = new Vodja(this);

            // Menu
            JMenuBar menu_bar = new JMenuBar();
            this.setJMenuBar(menu_bar);
            JMenu igra_menu = new JMenu("Igra");
            menu_bar.add(igra_menu);

            igraClovekBELI = new JMenuItem("Clovek - BELI");
            igra_menu.add(igraClovekBELI);
            igraClovekBELI.addActionListener(this);

            igraClovekCRNI = new JMenuItem("Clovek - CRNI");
            igra_menu.add(igraClovekCRNI);
            igraClovekCRNI.addActionListener(this);

            // Igralno polje
            polje = new IgralnoPolje(vodja);

            GridBagConstraints polje_layout = new GridBagConstraints();
            polje_layout.gridx = 0;
            polje_layout.gridy = 0;
            polje_layout.fill = GridBagConstraints.BOTH;
            polje_layout.weightx = 1.0;
            polje_layout.weighty = 1.0;
            getContentPane().add(polje, polje_layout);

            // Statusna vrstica za sporoËila
            status = new JLabel();
            status.setFont(new Font(status.getFont().getName(),
                    status.getFont().getStyle(),
                    20));
            GridBagConstraints status_layout = new GridBagConstraints();
            status_layout.gridx = 0;
            status_layout.gridy = 1;
            status_layout.anchor = GridBagConstraints.CENTER;
            getContentPane().add(status, status_layout);

            // zaËnemo novo igro Ëloveka proti raËunalniku

            vodja.novaIgra(Igralec.BELI);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == igraClovekBELI) {
                vodja.novaIgra(Igralec.BELI);
            }
            else if (e.getSource() == igraClovekCRNI) {
                vodja.novaIgra(Igralec.CRNI);
            }

        }

        public void osveziGUI() { // Po vsaki spremembi na novo nariöemo GUI.
            if (vodja.igra == null) {
                status.setText("Igra ni v teku.");
            }
            else {
                switch(vodja.igra.stanje()) {
                    case NA_POTEZI_BELI: status.setText("Na potezi je BELI"); break;
                    case NA_POTEZI_CRNI: status.setText("Na potezi je CRNI"); break;
                    case ZMAGA_BELI: status.setText("Zmagal je BELI"); break;
                    case ZMAGA_CRNI: status.setText("Zmagal je CRNI"); break;
                }
            }
            polje.repaint();
        }




    }


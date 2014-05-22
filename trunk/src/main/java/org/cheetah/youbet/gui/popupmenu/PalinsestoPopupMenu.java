/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.gui.PoissonDialog;
import org.cheetah.youbet.gui.model.PalinsestoTableModel;
import org.cheetah.youbet.util.comparators.PalinsestoComparator;

/**
 *
 * @author edoardo
 */
public class PalinsestoPopupMenu extends JPopupMenu {

    private JMenuItem menuItemDistribuzionePoisson = new JMenuItem("Distribuzione Poisson");

    public PalinsestoPopupMenu() {

    }

    public PalinsestoPopupMenu(final JTable source) {
        menuItemDistribuzionePoisson.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                List<Palinsesto> palinsestos = new ArrayList<Palinsesto>();
                for(int r = 0;r<source.getRowCount();r++){
                    if(source.getSelectionModel().isSelectedIndex(r)){
                        //aggiungo il palinsesto nella lista di quelli selezionati
                        palinsestos.add(((PalinsestoTableModel)source.getModel()).getPalinsestos().toArray(new Palinsesto[0])[r]);
                    }
                }
                Collections.sort(palinsestos, new PalinsestoComparator());
                PoissonDialog pf = new PoissonDialog(palinsestos);
                pf.setVisible(true);
            }
        });

        super.add(menuItemDistribuzionePoisson);
    }

}

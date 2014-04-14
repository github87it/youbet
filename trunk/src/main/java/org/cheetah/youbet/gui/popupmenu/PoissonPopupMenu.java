/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.cheetah.youbet.ContextSpringFactory;
import org.cheetah.youbet.bean.Bolletta;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.gui.BollettaDialog;
import org.cheetah.youbet.gui.HeadToHeadDialog;

/**
 *
 * @author edoardo
 */
public class PoissonPopupMenu extends JPopupMenu {

    private final Palinsesto palinsesto;

    JMenu statistiche = new JMenu("Statistiche");

    JMenuItem menuItemHeadToHead = new JMenuItem("Scontri Diretti");
    JMenuItem menuItemSeleziona = new JMenuItem("Seleziona");

    public PoissonPopupMenu(Palinsesto palinsesto) {
        this.palinsesto = palinsesto;
        statistiche.add(menuItemHeadToHead);
        super.add(statistiche);
        super.add(menuItemSeleziona);

        addListeners();

    }

    private void addListeners() {

        menuItemHeadToHead.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                HeadToHeadDialog headToHeadDialog = new HeadToHeadDialog(palinsesto);
                headToHeadDialog.setVisible(true);

            }
        });
        menuItemSeleziona.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Bolletta bolletta = ContextSpringFactory.getInstance().getContext().getBean(Bolletta.class);
                bolletta.addPalinsesto(palinsesto);
                BollettaDialog bd = new BollettaDialog();
                bd.addBolletta(bolletta);
                bd.setVisible(true);
            }
        });

    }

}

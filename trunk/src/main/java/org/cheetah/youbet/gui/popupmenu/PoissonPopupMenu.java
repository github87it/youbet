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
import org.cheetah.youbet.entities.Palinsesto;
import org.springframework.stereotype.Component;

/**
 *
 * @author edoardo
 */

public class PoissonPopupMenu extends JPopupMenu{
    
    private Palinsesto palinsesto;
    
    JMenu statistiche = new JMenu("Statistiche");

    JMenuItem statLastMatch = new JMenuItem("Ultimi incontri");
    public PoissonPopupMenu(Palinsesto palinsesto) {
        this.palinsesto=palinsesto;
        statistiche.add(statLastMatch);
        super.add(statistiche);
        
        addListeners();
        
    }

    private void addListeners() {
        statLastMatch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    
    
    
}

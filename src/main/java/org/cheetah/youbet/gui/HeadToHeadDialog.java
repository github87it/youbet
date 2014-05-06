/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui;

import java.util.List;
import org.cheetah.youbet.ContextSpringFactory;
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.gui.model.IncontroTableModel;
import org.cheetah.youbet.service.IncontroService;

/**
 *
 * @author edoardo
 */
public class HeadToHeadDialog extends javax.swing.JDialog {

    /**
     * Il palinsesto con tutte le informazioni
     */
    private Palinsesto palinsesto;

    private IncontroService incontroService;

    /**
     * Creates new form HeadToHeadDialog
     */
    public HeadToHeadDialog(Palinsesto palinsesto) {
//        super(parent, modal);
        this.palinsesto = palinsesto;
        setTitle("Testa a Testa tra "+palinsesto.getHomeTeam()+" e "+palinsesto.getAwayTeam());
        incontroService = ContextSpringFactory.getInstance().getContext().getBean(IncontroService.class);
        initComponents();
        List<Incontro> incontros = incontroService.findHeadToHead(palinsesto.getHomeTeam(), palinsesto.getAwayTeam(), palinsesto.getIdManifestazione().getDescrizioneLunga());
        IncontroTableModel model = new IncontroTableModel(incontros,
                new IncontroTableModel.IncontroColumn[]{
                    IncontroTableModel.IncontroColumn.DATA_EVENTO,
                    IncontroTableModel.IncontroColumn.HOME_TEAM,
                    IncontroTableModel.IncontroColumn.AWAY_TEAM,
                    IncontroTableModel.IncontroColumn.GOL_HOME,
                    IncontroTableModel.IncontroColumn.GOL_AWAY
                });
        tableHeadToHead.setModel(model);
        model.fireTableDataChanged();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPaneHeadToHead = new javax.swing.JScrollPane();
        tableHeadToHead = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tableHeadToHead.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
            }
        ));
        scrollPaneHeadToHead.setViewportView(tableHeadToHead);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(scrollPaneHeadToHead, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPaneHeadToHead;
    private javax.swing.JTable tableHeadToHead;
    // End of variables declaration//GEN-END:variables
}
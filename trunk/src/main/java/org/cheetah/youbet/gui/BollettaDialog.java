/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui;

import java.util.ArrayList;
import java.util.List;
import org.cheetah.youbet.bean.Bolletta;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.gui.model.PalinsestoTableModel;

/**
 *
 * @author edoardo
 */
public class BollettaDialog extends javax.swing.JDialog implements Cloneable {


    List<Palinsesto> palinsestos = new ArrayList<Palinsesto>();

    /**
     * Creates new form BollettaDialog
     */
    public BollettaDialog() {
//        super(parent, modal);
        initComponents();
//        updateModel();
    }

    private void updateModel(List<Palinsesto> palinsestos) {
        PalinsestoTableModel model = createModel(palinsestos);
        tableBolletta.setModel(model);
        model.fireTableDataChanged();
    }
    
    
   

    private PalinsestoTableModel createModel(List<Palinsesto> palinsestos) {
        PalinsestoTableModel model = new PalinsestoTableModel(palinsestos,
                new PalinsestoTableModel.PalinsestoColumn[]{
                    PalinsestoTableModel.PalinsestoColumn.DATA_EVENTO,
                    PalinsestoTableModel.PalinsestoColumn.ORA_EVENTO,
                    PalinsestoTableModel.PalinsestoColumn.HOME_TEAM,
                    PalinsestoTableModel.PalinsestoColumn.AWAY_TEAM
                }
        );
        return model;
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

        splitPaneBolletta = new javax.swing.JSplitPane();
        scrollPaneBolletta = new javax.swing.JScrollPane();
        tableBolletta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tableBolletta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        scrollPaneBolletta.setViewportView(tableBolletta);

        splitPaneBolletta.setLeftComponent(scrollPaneBolletta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(splitPaneBolletta, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPaneBolletta;
    private javax.swing.JSplitPane splitPaneBolletta;
    private javax.swing.JTable tableBolletta;
    // End of variables declaration//GEN-END:variables

    public void addBolletta(Bolletta bolletta) {
        updateModel(bolletta.getPalinsestos());
    }
}
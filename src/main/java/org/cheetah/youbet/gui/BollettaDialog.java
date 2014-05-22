/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import org.cheetah.youbet.ContextSpringFactory;
import org.cheetah.youbet.bean.Bolletta;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.gui.model.PalinsestoTableModel;
import org.cheetah.youbet.service.GenericService;

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        splitPaneBolletta = new javax.swing.JSplitPane();
        scrollPaneBolletta = new javax.swing.JScrollPane();
        tableBolletta = new javax.swing.JTable();
        panelClassiRadioButton1 = new org.cheetah.youbet.gui.component.PanelClassiRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 22, 1024, 800));
        setPreferredSize(new java.awt.Dimension(1024, 800));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        splitPaneBolletta.setResizeWeight(0.5);

        tableBolletta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        tableBolletta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBollettaMouseClicked(evt);
            }
        });
        scrollPaneBolletta.setViewportView(tableBolletta);

        splitPaneBolletta.setLeftComponent(scrollPaneBolletta);
        splitPaneBolletta.setRightComponent(panelClassiRadioButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(splitPaneBolletta, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableBollettaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBollettaMouseClicked
        JTable table =  (JTable) evt.getSource();
        GenericService service = ContextSpringFactory.getInstance().getContext().getBean(GenericService.class);
        Palinsesto palinsesto = ((PalinsestoTableModel)table.getModel()).getPalinsestos().toArray(new Palinsesto[0])[table.getSelectedRow()];
        panelClassiRadioButton1 = new org.cheetah.youbet.gui.component.PanelClassiRadioButton(service.findQuotaByPalinsesto(palinsesto),palinsesto);
        splitPaneBolletta.setRightComponent(panelClassiRadioButton1);

    }//GEN-LAST:event_tableBollettaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.cheetah.youbet.gui.component.PanelClassiRadioButton panelClassiRadioButton1;
    private javax.swing.JScrollPane scrollPaneBolletta;
    private javax.swing.JSplitPane splitPaneBolletta;
    private javax.swing.JTable tableBolletta;
    // End of variables declaration//GEN-END:variables

    public void addBolletta(Bolletta bolletta) {
        updateModel(bolletta.getPalinsestos());
    }
}

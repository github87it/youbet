/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.gui.editor;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import org.cheetah.youbet.ContextSpringFactory;
import org.cheetah.youbet.bean.Bolletta;
import org.cheetah.youbet.gui.model.QuotaTableModel;

/**
 *
 * @author edoardo
 */
public class QuotaTableCellEditor extends DefaultCellEditor{
    private JCheckBox checkBox=new JCheckBox();
    
    
    public QuotaTableCellEditor(JCheckBox checkBox){
        super(checkBox);
        this.checkBox=checkBox;
    }
    
    public Component getTableCellEditorComponent(final JTable table, Object value,   
            boolean isSelected, int row, int column) {   
        if (value == null)   
            return checkBox;   
        checkBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                
                ContextSpringFactory.getInstance()
                        .getContext()
                        .getBean(Bolletta.class)
                        .confirmGiocata(
                                ((QuotaTableModel)table.getModel()).
                                        getQuotas().
                                        get(table.getSelectedRow()));
                System.out.println("");
            }
        });   
        if (((Boolean) value).booleanValue())   
            checkBox.setSelected(true);   
        else 
            checkBox.setSelected(false);   
   
        return checkBox;   
    }   
   
    public Object getCellEditorValue() {   
        if(checkBox.isSelected() == true)   
            return new Boolean(true);   
        else   
            return new Boolean(false);   
    }   
   
    @Override 
    public void addCellEditorListener(CellEditorListener l) {   
        // TODO Auto-generated method stub   
    }   
   
    @Override 
    public void cancelCellEditing() {   
        // TODO Auto-generated method stub   
   
    }   
   
    @Override 
    public boolean isCellEditable(EventObject anEvent) {   
        // TODO Auto-generated method stub   
        return true;   
    }   
   
    @Override 
    public void removeCellEditorListener(CellEditorListener l) {   
        // TODO Auto-generated method stub   
   
    }   
   
    @Override 
    public boolean shouldSelectCell(EventObject anEvent) {   
        // TODO Auto-generated method stub   
        return true;   
    }   
   
    @Override 
    public boolean stopCellEditing() {   
        // TODO Auto-generated method stub   
        return true;   
    }   
   
//    @Override 
//    public void itemStateChanged(ItemEvent e) {   
//        // TODO Auto-generated method stub   
//        System.out.println("Firing!");   
//    }   
}

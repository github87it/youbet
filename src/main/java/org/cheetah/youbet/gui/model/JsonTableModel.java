/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.gui.model;

import javax.swing.table.AbstractTableModel;
import org.cheetah.youbet.json.JsonTable;
import org.cheetah.youbet.json.JsonTableRow;

/**
 *
 * @author edoardo
 */
public class JsonTableModel extends AbstractTableModel{

    
    private JsonTable table ;
    
    
    
    public int getRowCount() {
//        System.out.println("table: "+table);
//        System.out.println("rows: "+table.getRows());
        return table.getRows().size();
    }

    public int getColumnCount() {
        return getRowCount()>0?table.getRows().get(0).getColumns().size():0;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return getRowCount()>=rowIndex?table.getRows().get(rowIndex).getColumns().get(columnIndex).getColumnValue():null;
    }

    @Override
    public String getColumnName(int column) {
        JsonTableRow row = getRowCount()>0?table.getRows().get(0):null;
        if(row!=null){
            return row.getColumns().get(column).getColumnName();
        }
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public JsonTable getTable() {
        return table;
    }

    public void setTable(JsonTable table) {
        this.table = table;
    }
    
    
    
}

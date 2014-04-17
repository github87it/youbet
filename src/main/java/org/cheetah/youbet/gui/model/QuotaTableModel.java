/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;
import org.cheetah.youbet.entities.Quota;

/**
 *
 * @author edoardo
 */
public class QuotaTableModel extends AbstractTableModel {

    private List<Quota> quotas = new ArrayList<Quota>();

    private static final QuotaTableModel.QuotaColumn[] DEFAUL_VISIBLE_COLUMNS = new QuotaTableModel.QuotaColumn[]{
        QuotaTableModel.QuotaColumn.RADIO_BUTTON_OBJECT,
        QuotaTableModel.QuotaColumn.ID_PALINSESTO,
        QuotaTableModel.QuotaColumn.ID_AVVENIMENTO,
        QuotaTableModel.QuotaColumn.ID_ESITO,
        QuotaTableModel.QuotaColumn.ID_CLASSE,
        QuotaTableModel.QuotaColumn.QUOTA,
        QuotaTableModel.QuotaColumn.DESCRIZIONE_CLASSE,
        QuotaTableModel.QuotaColumn.DESCRIZIONE_ESITO};

    private QuotaColumn[] visibleColumns = DEFAUL_VISIBLE_COLUMNS;

    private static Map<QuotaTableModel.QuotaColumn, String> columns = new HashMap<QuotaTableModel.QuotaColumn, String>();

    static {
        columns.put(QuotaColumn.RADIO_BUTTON_OBJECT, "Seleziona");
        columns.put(QuotaTableModel.QuotaColumn.ID_PALINSESTO, "Palinsesto");
        columns.put(QuotaTableModel.QuotaColumn.ID_AVVENIMENTO, "Avvenimento");
        columns.put(QuotaTableModel.QuotaColumn.ID_CLASSE, "Id Classe");
        columns.put(QuotaTableModel.QuotaColumn.ID_ESITO, "Id Esito");
        columns.put(QuotaTableModel.QuotaColumn.QUOTA, "Quota");
        columns.put(QuotaTableModel.QuotaColumn.DESCRIZIONE_CLASSE, "Classe");
        columns.put(QuotaTableModel.QuotaColumn.DESCRIZIONE_ESITO, "Esito");

    }

    public QuotaTableModel() {
    }

    public QuotaTableModel(List<Quota> quotas) {
        this(quotas, DEFAUL_VISIBLE_COLUMNS);

    }

    public QuotaTableModel(List<Quota> quotas, QuotaColumn[] visibleColumns) {
        this.quotas = quotas;
        this.visibleColumns = visibleColumns;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    public int getRowCount() {
        return quotas.size();
    }

    public int getColumnCount() {
        return visibleColumns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (visibleColumns[columnIndex]) {
            case RADIO_BUTTON_OBJECT:
                return new Boolean(false);
            case QUOTA:
                return quotas.get(rowIndex).getQuota();
            case ID_ESITO:
                return quotas.get(rowIndex).getQuotaPK().getIdEsito();
            case ID_CLASSE:
                return quotas.get(rowIndex).getQuotaPK().getIdClasse();
            case ID_AVVENIMENTO:
                return quotas.get(rowIndex).getQuotaPK().getIdAvvenimento();
            case ID_PALINSESTO:
                return quotas.get(rowIndex).getQuotaPK().getIdPalinsesto();
            case DESCRIZIONE_CLASSE:
                return quotas.get(rowIndex).getEsito().getClasse().getDescrizione();
            case DESCRIZIONE_ESITO:
                return quotas.get(rowIndex).getEsito().getDescesito();

        }
        return null;
    }

    public List<Quota> getQuotas() {
        return quotas;
    }

    public void setQuotas(List<Quota> quotas) {
        this.quotas = quotas;
    }

    public QuotaColumn[] getVisibleColumns() {
        return visibleColumns;
    }

    public void setVisibleColumns(QuotaColumn[] visibleColumns) {
        this.visibleColumns = visibleColumns;
    }
    
    

    public enum QuotaColumn {

        ID_ESITO, ID_CLASSE, ID_PALINSESTO, ID_AVVENIMENTO, QUOTA, DESCRIZIONE_CLASSE, DESCRIZIONE_ESITO, RADIO_BUTTON_OBJECT
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(visibleColumns[column]); //To change body of generated methods, choose Tools | Templates.
    }

}

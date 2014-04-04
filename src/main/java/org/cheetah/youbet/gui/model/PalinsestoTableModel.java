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
import javax.swing.table.AbstractTableModel;
import org.cheetah.youbet.entities.Palinsesto;

/**
 *
 * @author edoardo
 */
public class PalinsestoTableModel extends AbstractTableModel {

    private List<Palinsesto> palinsestos = new ArrayList<Palinsesto>();
    private static final PalinsestoColumn[] DEFAUL_VISIBLE_COLUMNS= new PalinsestoColumn[]{
        PalinsestoColumn.ID_PALINSESTO, 
        PalinsestoColumn.ID_AVVENIMENTO, 
        PalinsestoColumn.DESCRIZIONE, 
        PalinsestoColumn.HOME_TEAM, 
        PalinsestoColumn.AWAY_TEAM, 
        PalinsestoColumn.DATA_EVENTO, 
        PalinsestoColumn.ORA_EVENTO,
        PalinsestoColumn.ID_MANIFESTAZIONE};
    
    private PalinsestoColumn[] visibleColumns = DEFAUL_VISIBLE_COLUMNS;
    
    

    
    private static Map<PalinsestoColumn,String> columns = new HashMap<PalinsestoColumn, String>();
    
    static {
        columns.put(PalinsestoColumn.ID_PALINSESTO, "Palinsesto");
        columns.put(PalinsestoColumn.ID_AVVENIMENTO, "Avvenimento");
        columns.put(PalinsestoColumn.DESCRIZIONE, "Descrizione");
        columns.put(PalinsestoColumn.HOME_TEAM, "Squadra Casa");
        columns.put(PalinsestoColumn.AWAY_TEAM, "Squadra Fuori");
        columns.put(PalinsestoColumn.DATA_EVENTO, "Data Evento");
        columns.put(PalinsestoColumn.ORA_EVENTO, "Ora Evento");
        columns.put(PalinsestoColumn.ID_MANIFESTAZIONE, "Manifestazione");
    }

    public PalinsestoTableModel(List<Palinsesto> palinsestos) {
        this.palinsestos=palinsestos;
    }
    public PalinsestoTableModel(List<Palinsesto> palinsestos,PalinsestoColumn[] visibleColumns) {
        this.palinsestos=palinsestos;
        this.visibleColumns=visibleColumns;
    }

    public int getRowCount() {
        return palinsestos.size();
    }

    public int getColumnCount() {
        return visibleColumns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PalinsestoColumn column = visibleColumns[columnIndex];
        switch(column){
            case ID_PALINSESTO:
                return palinsestos.get(rowIndex).getPalinsestoPK().getIdPalinsesto();
            case ID_AVVENIMENTO:
                return palinsestos.get(rowIndex).getPalinsestoPK().getIdAvvenimento();
            case DESCRIZIONE:
                return palinsestos.get(rowIndex).getDescrizione();
            case HOME_TEAM:
                return palinsestos.get(rowIndex).getHomeTeam();
            case AWAY_TEAM:
                return palinsestos.get(rowIndex).getAwayTeam();
            case DATA_EVENTO:
                return palinsestos.get(rowIndex).getDataEvento();
            case ORA_EVENTO:
                return palinsestos.get(rowIndex).getOraEvento();
            case ID_MANIFESTAZIONE:
                return palinsestos.get(rowIndex).getIdManifestazione().getDescrizione();
        }
        return new Object();
    }

    public enum PalinsestoColumn {
        ID_PALINSESTO, ID_AVVENIMENTO, DESCRIZIONE, HOME_TEAM, AWAY_TEAM, DATA_EVENTO, ORA_EVENTO,ID_MANIFESTAZIONE
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(visibleColumns[column]);
    }
    public List<Palinsesto> getPalinsestos() {
        return palinsestos;
    }
    

}

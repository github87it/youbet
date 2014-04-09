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
import org.cheetah.youbet.entities.Incontro;
import org.springframework.stereotype.Component;

/**
 *
 * @author edoardo
 */
@Component
public class IncontroTableModel extends AbstractTableModel {

    private List<Incontro> incontros = new ArrayList<Incontro>();
    private static final IncontroColumn[] DEFAUL_VISIBLE_COLUMNS = new IncontroColumn[]{
        IncontroColumn.ID_PALINSESTO,
        IncontroColumn.ID_AVVENIMENTO,
        IncontroColumn.COMPETIZIONE,
        IncontroColumn.HOME_TEAM,
        IncontroColumn.AWAY_TEAM,
        IncontroColumn.DATA_EVENTO,
        IncontroColumn.SEGNO_FINALE};

    private IncontroColumn[] visibleColumns = DEFAUL_VISIBLE_COLUMNS;


    private static Map<IncontroColumn, String> columns = new HashMap<IncontroColumn, String>();

    static {
        columns.put(IncontroColumn.ID_PALINSESTO, "Palinsesto");
        columns.put(IncontroColumn.ID_AVVENIMENTO, "Avvenimento");
        columns.put(IncontroColumn.COMPETIZIONE, "Competizione");
        columns.put(IncontroColumn.HOME_TEAM, "Squadra Casa");
        columns.put(IncontroColumn.AWAY_TEAM, "Squadra Fuori");
        columns.put(IncontroColumn.DATA_EVENTO, "Data Evento");
        columns.put(IncontroColumn.GOL_AWAY, "Gol Fuori");
        columns.put(IncontroColumn.GOL_HOME, "Gol Casa");
        columns.put(IncontroColumn.GOL_HOME_PARZIALE, "Gol Casa 1° Tempo");
        columns.put(IncontroColumn.GOL_AWAY_PARZIALE, "Gol Casa 2° Tempo");
        columns.put(IncontroColumn.NOTE, "Note");
        columns.put(IncontroColumn.SEGNO_FINALE, "Segno Finale");
        columns.put(IncontroColumn.SEGNO_PARZIALE, "Segno !° Tempo");
    }

    public IncontroTableModel() {
    }

    
    public IncontroTableModel(List<Incontro> incontros) {
        this.incontros = incontros;
    }

    public IncontroTableModel(List<Incontro> incontros, IncontroColumn[] visibleColumns) {
        this.incontros = incontros;
        this.visibleColumns = visibleColumns;
    }

    public int getRowCount() {
        return incontros.size();
    }

    public int getColumnCount() {
        return visibleColumns.length;
    }
    
    

    public Object getValueAt(int rowIndex, int columnIndex) {
        IncontroColumn column = visibleColumns[columnIndex];
        switch (column) {
            case ID_PALINSESTO:
                return incontros.get(rowIndex).getIncontroPK().getIdPalinsesto();
            case ID_AVVENIMENTO:
                return incontros.get(rowIndex).getIncontroPK().getIdAvvenimento();
            case COMPETIZIONE:
                return incontros.get(rowIndex).getCompetizione();
            case HOME_TEAM:
                return incontros.get(rowIndex).getHomeTeam();
            case AWAY_TEAM:
                return incontros.get(rowIndex).getAwayTeam();
            case DATA_EVENTO:
                return incontros.get(rowIndex).getDataEvento();
            case GOL_AWAY_PARZIALE:
                return incontros.get(rowIndex).getGolAwayParziale();
            case GOL_HOME_PARZIALE:
                return incontros.get(rowIndex).getGolHomeParziale();
            case GOL_AWAY:
                return incontros.get(rowIndex).getGolAway();
            case GOL_HOME:
                return incontros.get(rowIndex).getGolHome();
            case NOTE:
                return incontros.get(rowIndex).getNote();
            case SEGNO_FINALE:
                return incontros.get(rowIndex).getSegnoFinale();
            case SEGNO_PARZIALE:
                return incontros.get(rowIndex).getSegnoParziale();
                

        }
        return new Object();
    }

    public void setIncontros(List<Incontro> incontros) {
        this.incontros = incontros;
    }
    public IncontroColumn[] getVisibleColumns() {
        return visibleColumns;
    }

    public void setVisibleColumns(IncontroColumn[] visibleColumns) {
        this.visibleColumns = visibleColumns;
    }

    
    


    @Override
    public String getColumnName(int column) {
        return columns.get(visibleColumns[column]);
    }

    public List<Incontro> getIncontros() {
        return incontros;
    }

    public enum IncontroColumn {

        ID_PALINSESTO, ID_AVVENIMENTO, HOME_TEAM, AWAY_TEAM, GOL_HOME, GOL_AWAY, SEGNO_FINALE,
        NOTE, DATA_EVENTO, GOL_HOME_PARZIALE, GOL_AWAY_PARZIALE, SEGNO_PARZIALE, COMPETIZIONE
    }
}

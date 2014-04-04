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
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.service.PoissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author edoardo
 */
@Component
public class PoissonTableModel extends AbstractTableModel {

    @Autowired
    private PoissonService poissonService;

    private Palinsesto palinsestoSelected;

    private List<Poisson> poissons = new ArrayList<Poisson>();

    private static final PoissonColumn[] DEFAULT_COLUMNS = new PoissonColumn[]{
        PoissonColumn.ID_PALINSESTO, PoissonColumn.ID_AVVENIMENTO, PoissonColumn.GOL_HOME, PoissonColumn.GOL_AWAY, PoissonColumn.PERCENTUALE
    };

    private PoissonColumn[] visibleColumns = DEFAULT_COLUMNS;

    private static Map<PoissonColumn, String> columns = new HashMap<PoissonColumn, String>();

    static {
        columns.put(PoissonColumn.ID_PALINSESTO, "Palinsesto");
        columns.put(PoissonColumn.ID_AVVENIMENTO, "Avvenimento");
        columns.put(PoissonColumn.GOL_AWAY, "Gol Fuori Casa");
        columns.put(PoissonColumn.GOL_HOME, "Gol in Casa");
        columns.put(PoissonColumn.PERCENTUALE, "Percentuale");
    }

    public PoissonTableModel() {
    }

    public Palinsesto getPalinsestoSelected() {
        return palinsestoSelected;
    }

    public void setPalinsestoSelected(Palinsesto palinsestoSelected) {
        this.palinsestoSelected = palinsestoSelected;
    }

    public PoissonColumn[] getVisibleColumns() {
        return visibleColumns;
    }

    public void setVisibleColumns(PoissonColumn[] visibleColumns) {
        this.visibleColumns = visibleColumns;
    }

    public void loadData() {
        poissons = poissonService.findByIdPalinsestoAndIdAvvenimento(
                palinsestoSelected.getPalinsestoPK().getIdPalinsesto(),
                palinsestoSelected.getPalinsestoPK().getIdAvvenimento());

    }

    public int getRowCount() {
        return poissons.size();
    }

    public int getColumnCount() {
        return visibleColumns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PoissonColumn column = visibleColumns[columnIndex];
        switch (column) {
            case ID_PALINSESTO:
                return poissons.get(rowIndex).getPoissonPK().getIdPalinsesto();
            case ID_AVVENIMENTO:
                return poissons.get(rowIndex).getPoissonPK().getIdAvvenimento();
            case GOL_AWAY:
                return poissons.get(rowIndex).getPoissonPK().getGolAway();
            case GOL_HOME:
                return poissons.get(rowIndex).getPoissonPK().getGolHome();
            case PERCENTUALE:
                return poissons.get(rowIndex).getPercentuale();

        }
        return new Object();
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(visibleColumns[column]); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadDataPercentualeGreaterThan(Palinsesto p, int value) {
        poissons = poissonService.findByIdPalinsestoAndIdAvvenimentoAndPercentualeGreaterThan(
                p.getPalinsestoPK().getIdPalinsesto(), 
                p.getPalinsestoPK().getIdAvvenimento(), 
                new Double(value).doubleValue());
    }

    public enum PoissonColumn {

        ID_PALINSESTO, ID_AVVENIMENTO, GOL_HOME, GOL_AWAY, PERCENTUALE
    }

}

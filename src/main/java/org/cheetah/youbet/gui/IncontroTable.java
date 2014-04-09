/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.gui.model.IncontroTableModel;

/**
 *
 * @author edoardo
 */
public class IncontroTable extends JTable {

    /**
     * Indica a quale team appartiene la tabella
     */
    private String team = "";

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (getModel() instanceof IncontroTableModel) {
            IncontroTableModel model = (IncontroTableModel) getModel();
            if (model.getIncontros().size() > 0) {
                Incontro incontro = model.getIncontros().get(row);
                if (incontro.getGolHome() != incontro.getGolAway()) {
                    if ((team.equals(incontro.getHomeTeam()) && incontro.getGolHome() > incontro.getGolAway()) || (!team.equals(incontro.getHomeTeam()) && incontro.getGolHome() < incontro.getGolAway())) {
                        c.setBackground(new Color(0, 166, 0));
                    }
                    if ((team.equals(incontro.getHomeTeam()) && incontro.getGolHome() < incontro.getGolAway()) || (!team.equals(incontro.getHomeTeam()) && incontro.getGolHome() > incontro.getGolAway())) {
                        c.setBackground(new Color(166, 0, 0));

                    }
                }else{
                    c.setBackground(Color.WHITE);
                }
            }
        }
        return c;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}

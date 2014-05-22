/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.util.comparators;

import java.util.Comparator;
import org.cheetah.youbet.entities.Palinsesto;

/**
 *
 * @author edoardo
 */
public class PalinsestoComparator implements Comparator<Palinsesto> {

    private int orderBy = ORDER_BY_DATA_EVENTO;

    public static final int ORDER_BY_DATA_EVENTO = 1;
    public static final int ORDER_BY_ORA_EVENTO = 3;
    public static final int ORDER_BY_DATA_EVENTO_ORA_EVENTO = ORDER_BY_DATA_EVENTO+ORDER_BY_ORA_EVENTO;
    
    public static final int ORDER_BY_ID_AVVENIMENTO = 5;
    public static final int ORDER_BY_ID_PALINSESTO = 7;
    public static final int ORDER_BY_ID_PALINSESTO_ID_AVVENIMENTO = ORDER_BY_ID_AVVENIMENTO + ORDER_BY_ID_PALINSESTO;

    public PalinsestoComparator() {

    }

    public PalinsestoComparator(int orderBy) {
        this.orderBy = orderBy;
    }

    public int compare(Palinsesto p1, Palinsesto p2) {
        switch (orderBy) {
            case ORDER_BY_ID_PALINSESTO_ID_AVVENIMENTO:
                if (p1.getPalinsestoPK().getIdPalinsesto() == p2.getPalinsestoPK().getIdPalinsesto()) {
                    return p1.getPalinsestoPK().getIdAvvenimento() - p2.getPalinsestoPK().getIdAvvenimento();
                }
                return p1.getPalinsestoPK().getIdPalinsesto() - p2.getPalinsestoPK().getIdPalinsesto();
            case ORDER_BY_DATA_EVENTO_ORA_EVENTO:
                if(p1.getDataEvento().equals(p2.getDataEvento())){
                    return p1.getOraEvento().compareTo(p2.getOraEvento());
                }
            return p1.getDataEvento().compareTo(p2.getDataEvento());
            case ORDER_BY_DATA_EVENTO:
                return p1.getDataEvento().compareTo(p2.getDataEvento());
            case ORDER_BY_ORA_EVENTO:
                return p1.getOraEvento().compareTo(p2.getOraEvento());
            case ORDER_BY_ID_AVVENIMENTO:
                return p1.getPalinsestoPK().getIdAvvenimento()-p2.getPalinsestoPK().getIdAvvenimento();
            case ORDER_BY_ID_PALINSESTO:
                return p1.getPalinsestoPK().getIdPalinsesto()-p2.getPalinsestoPK().getIdPalinsesto();
        }
        return p1.getDataEvento().compareTo(p2.getDataEvento());
    }

  

}

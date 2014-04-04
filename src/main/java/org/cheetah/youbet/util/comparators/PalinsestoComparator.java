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
public class PalinsestoComparator implements Comparator<Palinsesto>{

    public int compare(Palinsesto p1, Palinsesto p2) {
        if(p1.getPalinsestoPK().getIdPalinsesto()==p2.getPalinsestoPK().getIdPalinsesto()){
            return p1.getPalinsestoPK().getIdAvvenimento()-p2.getPalinsestoPK().getIdAvvenimento();
        }
        return p1.getPalinsestoPK().getIdPalinsesto()-p2.getPalinsestoPK().getIdPalinsesto();
        
    }
    
}

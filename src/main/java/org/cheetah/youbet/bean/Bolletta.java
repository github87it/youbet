/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.Quota;
import org.cheetah.youbet.gui.model.QuotaTableModel;
import org.cheetah.youbet.util.comparators.PalinsestoComparator;
import org.springframework.stereotype.Component;

/**
 *
 * @author edoardo
 */
@Component
public class Bolletta {

    private List<Palinsesto> palinsestos = new ArrayList<Palinsesto>();

    private List<Quota> quotasSelected = new ArrayList<Quota>();

    private Map<Palinsesto, List<Quota>> giocata = new HashMap<Palinsesto, List<Quota>>();

    public Map<Palinsesto, List<Quota>> getGiocata() {
        return giocata;
    }

    public List<Quota> getQuotasSelected() {
        return quotasSelected;
    }

    public void setQuotasSelected(List<Quota> quotasSelected) {
        this.quotasSelected = quotasSelected;
    }

    public List<Palinsesto> getPalinsestos() {
        return palinsestos;
    }

    public void addPalinsesto(Palinsesto palinsesto) {
        this.palinsestos.add(palinsesto);
    }

    public void addGiocata(Palinsesto palinsesto, Quota quota) {
        List<Quota> quotas = giocata.get(palinsesto);
        if (quotas == null) {
            quotas = new ArrayList<Quota>();
        }
        quotas.add(quota);
        giocata.put(palinsesto, quotas);
    }

    public void confirmGiocata(Quota quota) {
        quotasSelected.add(quota);

        System.out.println("Quote selezionate:" + quotasSelected.size());
    }

    public void cancelGiocata(Quota quota) {

        boolean rem = quotasSelected.remove(quota);
        System.out.println("remove quota: "+rem);
        System.out.println("Quote selezionate:" + quotasSelected.size());
    }

    public void removeGiocata(Palinsesto palinsesto, Quota quota) {
        List<Quota> quotas = giocata.get(palinsesto);
        boolean rem = quotas.remove(quota);
        System.out.println("remove quota: "+rem);
        giocata.put(palinsesto, quotas);
    }

}

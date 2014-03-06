/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.cheetah.youbet.entities.Manifestazione;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.PercentualeSingoliEsiti;
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.entities.PoissonPK;
import org.cheetah.youbet.service.IncontroService;
import org.cheetah.youbet.service.PalinsestoService;
import org.cheetah.youbet.service.PercentualeSingoliEsitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author edoardo
 */
@Component("calculator")
public class Calculator {

    @Autowired
    @Qualifier("palinsestoService")
    private PalinsestoService palinsestoService;

    @Autowired
    @Qualifier("incontroService")
    private IncontroService incontroService;
    
    @Autowired
    PercentualeSingoliEsitiService esitiService;

    public List<Poisson> calcolaStat() {
        Map<String, Double> poisson = new TreeMap<String, Double>();
        List<Poisson> stats = new ArrayList<Poisson>();

        List<Palinsesto> palinsestos = palinsestoService.findPartiteDaGiocare(new Date());
        System.out.println(palinsestos.size());
        for (Palinsesto palinsesto : palinsestos) {

            String homeTeam = palinsesto.getHomeTeam();
            String awayTeam = palinsesto.getAwayTeam();
            Manifestazione m = palinsesto.getIdManifestazione();
            if (palinsesto.getHomeTeam().equals("SAN LORENZO")) {
                System.out.println("");
            }
            IncontroService.Stat statHomeTeam = incontroService.getSumGoalByTeam(homeTeam, m.getDescrizioneLunga(), IncontroService.HOME_TEAM);
            IncontroService.Stat statAwayTeam = incontroService.getSumGoalByTeam(awayTeam, m.getDescrizioneLunga(), IncontroService.AWAY_TEAM);
            System.out.println("Incontro: " + homeTeam + " - " + awayTeam);

            Map<Integer, Double> ht_gf_Poisson = statHomeTeam.poisson(6, statHomeTeam.mediaGfPartita());
            Map<Integer, Double> at_gs_Poisson = statAwayTeam.poisson(6, statAwayTeam.mediaGsPartita());
            System.out.println(ht_gf_Poisson);
            mediaPoissonByGol(at_gs_Poisson, ht_gf_Poisson);
            //distribuzione della probabilità di fare gol della squadra di casa. E' contentuta in ht_gf_Poisson
            System.out.println("\t" + ht_gf_Poisson);

            Map<Integer, Double> at_gf_Poisson = statAwayTeam.poisson(6, statAwayTeam.mediaGfPartita());
            Map<Integer, Double> ht_gs_Poisson = statHomeTeam.poisson(6, statHomeTeam.mediaGsPartita());

            System.out.println(at_gf_Poisson);
            mediaPoissonByGol(ht_gs_Poisson, at_gf_Poisson);
            //distribuzione della probabilità di fare gol della squadra in trasferts. E' contentuta in at_gf_Poisson
            System.out.println("\t" + at_gf_Poisson);
            for (Map.Entry<Integer, Double> entry : ht_gf_Poisson.entrySet()) {
                Integer ghome = entry.getKey();
                Double double1 = entry.getValue();
                for (Map.Entry<Integer, Double> atEntry : at_gf_Poisson.entrySet()) {
                    Integer gaway = atEntry.getKey();
                    Double double2 = atEntry.getValue();
                    poisson.put(ghome + "-" + gaway, double2 * double1);
                }

            }
            DecimalFormat df = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
            df.applyPattern("#.##");
            for (Map.Entry<String, Double> entry : poisson.entrySet()) {
                String ris = entry.getKey();
                Double prob = entry.getValue();
                Poisson p = new Poisson();
                int gh = Integer.parseInt(ris.substring(0, ris.indexOf("-")));
                int ga = Integer.parseInt(ris.substring(ris.indexOf("-") + 1));
                PoissonPK pk = new PoissonPK(palinsesto.getPalinsestoPK().getIdPalinsesto(), palinsesto.getPalinsestoPK().getIdAvvenimento(), gh, ga);
                p.setPoissonPK(pk);
                p.setPercentuale(new Double(df.format(prob.doubleValue() * 100)));
                stats.add(p);
            }

        }
        return stats;

    }

    private void mediaPoissonByGol(Map<Integer, Double> at_gs_Poisson, Map<Integer, Double> ht_gf_Poisson) {
        for (Map.Entry<Integer, Double> entry : ht_gf_Poisson.entrySet()) {
            Integer ng = entry.getKey();
            Double doubleHt = entry.getValue();
            Double doubleAt = at_gs_Poisson.get(ng);
            ht_gf_Poisson.put(ng, (doubleAt + doubleHt) / 2.0);
        }
    }

    public List<PercentualeSingoliEsiti> calcolaPercEsiti(List<Poisson> stats) {
        List<PercentualeSingoliEsiti> pse = new ArrayList<PercentualeSingoliEsiti>();
        
        for (Poisson poisson : stats) {
            pse.add(esitiService.createPercByIdPalinsestoAndIdAvvenimento(poisson.getPoissonPK().getIdPalinsesto(), poisson.getPoissonPK().getIdAvvenimento()));
        }
        return pse;
    }

}

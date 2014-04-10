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
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.entities.Manifestazione;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.PercentualeSingoliEsiti;
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.entities.PoissonPK;
import org.cheetah.youbet.entities.Serie;
import org.cheetah.youbet.entities.SeriePK;
import org.cheetah.youbet.repositories.SerieRepository;
import org.cheetah.youbet.service.IncontroService;
import org.cheetah.youbet.service.PalinsestoService;
import org.cheetah.youbet.service.PercentualeSingoliEsitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.cheetah.youbet.util.helper.IncontroHelper.*;

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

            IncontroService.Stat statHomeTeam = incontroService.getSumGoalByTeam(homeTeam, m.getDescrizioneLunga(), HOME_TEAM);
            IncontroService.Stat statAwayTeam = incontroService.getSumGoalByTeam(awayTeam, m.getDescrizioneLunga(), AWAY_TEAM);
            System.out.println("Incontro: " + homeTeam + " - " + awayTeam);

            //Cambio calcolo 14-03-2014
//            Map<Integer, Double> ht_gf_Poisson = statHomeTeam.poisson(6, statHomeTeam.mediaGfPartita());
//            Map<Integer, Double> at_gs_Poisson = statAwayTeam.poisson(6, statAwayTeam.mediaGsPartita());
            Map<Integer, Double> ht_gf_Poisson = statHomeTeam.poisson(6, (statHomeTeam.mediaGfPartita() + statAwayTeam.mediaGsPartita()) / 2.0d);

//            System.out.println(ht_gf_Poisson);
//            mediaPoissonByGol(at_gs_Poisson, ht_gf_Poisson);
            //distribuzione della probabilità di fare gol della squadra di casa. E' contentuta in ht_gf_Poisson
            System.out.println("\t" + ht_gf_Poisson);

            Map<Integer, Double> at_gf_Poisson = statAwayTeam.poisson(6, (statAwayTeam.mediaGfPartita() + statHomeTeam.mediaGsPartita()) / 2.0d);
//            Map<Integer, Double> ht_gs_Poisson = statHomeTeam.poisson(6, statHomeTeam.mediaGsPartita());

//            Map<Integer, Double> at_gf_Poisson = statAwayTeam.poisson(6, statAwayTeam.mediaGfPartita());
//            Map<Integer, Double> ht_gs_Poisson = statHomeTeam.poisson(6, statHomeTeam.mediaGsPartita());
            System.out.println(at_gf_Poisson);
//            mediaPoissonByGol(ht_gs_Poisson, at_gf_Poisson);
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

    public static void calcolaSerie(Incontro incontro, List<Incontro> ht, List<Incontro> at, SerieRepository serieRepository) {
        int serieVittorieCasa = 0;
        int seriePareggiCasa = 0;
        int serieSconfitteCasa = 0;
        int serieVittorieFuori = 0;
        int seriePareggiFuori = 0;
        int serieSconfitteFuori = 0;
        int numPartSenzaSubireGolCasa = 0;
        int numPartSenzaSubireGolFuori = 0;
        int numPartiteGolSubitiCasa = 0;
        int numPartiteGolSubitiFuori = 0;

        Serie serieHt = serieRepository.findOne(new SeriePK(incontro.getHomeTeam(), incontro.getCompetizione()));
        Serie serieAt = serieRepository.findOne(new SeriePK(incontro.getAwayTeam(), incontro.getCompetizione()));
        boolean isNewSerieHt = false;
        boolean isNewSerieAt = false;
        if (serieHt == null) {
            serieHt = createNewSerie(new SeriePK(incontro.getHomeTeam(), incontro.getCompetizione()));
            isNewSerieAt = true;
        }
        if (serieAt == null) {
            serieAt = createNewSerie(new SeriePK(incontro.getAwayTeam(), incontro.getCompetizione()));
            isNewSerieAt = true;
        }
        if (isNewSerieHt) {
            for (Incontro incontroHt : ht) {
                serieVittorieCasa = incontroHt.getGolHome() > incontroHt.getGolAway() ? ++serieVittorieCasa : 0;
                seriePareggiCasa = incontroHt.getGolHome() == incontroHt.getGolAway() ? ++seriePareggiCasa : 0;
                serieSconfitteCasa = incontroHt.getGolHome() < incontroHt.getGolAway() ? ++serieSconfitteCasa : 0;
                numPartSenzaSubireGolCasa = incontroHt.getGolAway() == 0 ? ++numPartSenzaSubireGolCasa : 0;
                numPartiteGolSubitiCasa = incontroHt.getGolAway() > 0 ? ++numPartiteGolSubitiCasa : 0;
            }
        } else {
            serieVittorieCasa = incontro.getGolHome() > incontro.getGolAway() ? serieHt.getSerieVittorieCasa() == null ? ++serieVittorieCasa : serieHt.getSerieVittorieCasa() + 1 : 0;
            seriePareggiCasa = incontro.getGolHome() == incontro.getGolAway() ? serieHt.getSeriePareggiCasa() == null ? ++seriePareggiCasa : serieHt.getSeriePareggiCasa() + 1 : 0;
            serieSconfitteCasa = incontro.getGolHome() < incontro.getGolAway() ? serieHt.getSerieSconfitteCasa() == null ? ++serieSconfitteCasa : serieHt.getSerieSconfitteCasa() + 1 : 0;
            numPartSenzaSubireGolCasa = incontro.getGolAway() == 0 ? serieHt.getNumeroPartiteSenzaSubireGolCasa() == null ? ++numPartSenzaSubireGolCasa : serieHt.getNumeroPartiteSenzaSubireGolCasa() + 1 : 0;
            numPartiteGolSubitiCasa = incontro.getGolAway() > 0 ? serieHt.getNumeroPartiteGolSubitiCasa() == null ? ++numPartiteGolSubitiCasa : serieHt.getNumeroPartiteGolSubitiCasa() + 1 : 0;

        }
        if (isNewSerieAt) {
            for (Incontro incontroAt : at) {
                serieVittorieFuori = incontroAt.getGolAway() > incontroAt.getGolHome() ? ++serieVittorieFuori : 0;
                seriePareggiFuori = incontroAt.getGolAway() == incontroAt.getGolHome() ? ++seriePareggiFuori : 0;
                serieSconfitteFuori = incontroAt.getGolAway() < incontroAt.getGolHome() ? ++serieSconfitteFuori : 0;
                numPartSenzaSubireGolFuori = incontroAt.getGolHome() == 0 ? ++numPartSenzaSubireGolFuori : 0;
                numPartiteGolSubitiFuori = incontroAt.getGolHome() > 0 ? ++numPartiteGolSubitiFuori : 0;
            }
        } else {
            serieVittorieFuori = incontro.getGolAway() > incontro.getGolHome() ? serieAt.getSerieVittorieFuori() == null ? ++serieVittorieFuori : serieAt.getSerieVittorieFuori() + 1 : 0;
            seriePareggiFuori = incontro.getGolAway() == incontro.getGolHome() ? serieAt.getSeriePareggiFuori() == null ? ++seriePareggiFuori : serieAt.getSeriePareggiFuori() + 1 : 0;
            serieSconfitteFuori = incontro.getGolAway() < incontro.getGolHome() ? serieAt.getSerieSconfitteFuori() == null ? ++serieSconfitteFuori : serieAt.getSerieSconfitteFuori() + 1 : 0;
            numPartSenzaSubireGolFuori = incontro.getGolHome() == 0 ? serieAt.getNumeroPartiteSenzaSubireGolFuori() == null ? ++numPartSenzaSubireGolFuori : serieAt.getNumeroPartiteSenzaSubireGolFuori() + 1 : 0;
            numPartiteGolSubitiFuori = incontro.getGolHome() > 0 ? serieAt.getNumeroPartiteGolSubitiFuori() == null ? ++numPartiteGolSubitiFuori : serieAt.getNumeroPartiteGolSubitiFuori() + 1 : 0;
        }
        serieHt.setNumeroPartiteGolSubitiCasa(numPartiteGolSubitiCasa);
        serieHt.setNumeroPartiteSenzaSubireGolCasa(numPartSenzaSubireGolCasa);
        serieHt.setSeriePareggiCasa(seriePareggiCasa);
        serieHt.setSerieSconfitteCasa(serieSconfitteCasa);
        serieHt.setSerieVittorieCasa(serieVittorieCasa);

        serieAt.setNumeroPartiteGolSubitiFuori(numPartiteGolSubitiFuori);
        serieAt.setNumeroPartiteSenzaSubireGolFuori(numPartSenzaSubireGolFuori);
        serieAt.setSeriePareggiFuori(seriePareggiFuori);
        serieAt.setSerieSconfitteFuori(serieSconfitteFuori);
        serieAt.setSerieVittorieFuori(serieVittorieFuori);

        serieRepository.save(serieAt);
        serieRepository.save(serieHt);
        System.out.println(serieHt);
        System.out.println("\t" + serieAt);
    }

    private static Serie createNewSerie(SeriePK seriePK) {
        return new Serie(seriePK);
    }

}

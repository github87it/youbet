/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet;

import org.cheetah.youbet.config.Config;
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.service.GenericService;
import org.cheetah.youbet.service.IncontroService;
import org.cheetah.youbet.util.helper.IncontroHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author edoardo
 */
public class Main {

    private static double PESO_VITTORIA_CASA = 1.1;
    private static double PESO_SCONFITTA_CASA = 0.2;
    private static double PESO_PAREGGIO_CASA = 0.4;
    private static double PESO_SCONFITTA_FUORI = 0.7;
    private static double PESO_VITTORIA_FUORI = 1.8;
    private static double PESO_PAREGGIO_FUORI = 0.8;

    public static void main(String[] args) throws Exception {
//        Document d = Jsoup.connect("http://www.livescore.com/soccer/austria/").get();
//        FileOutputStream fout = new FileOutputStream("/Users/edoardo/TEMP/calcio-austria-risultati.html");
//        IOUtils.write(d.toString(), fout);
//        fout.flush();
//        fout.close();
//        System.out.println(d.toString());
//        DecimalFormat df = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
//        df.applyPattern("#.####");
//        System.out.println(df.format(3.45E-4*100));
//        
//        System.out.println((2.0+3.5));
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
//        PalinsestoService ps = (PalinsestoService) ctx.getBean("palinsestoService");
//        Palinsesto palinsesto = ps.findByPk(17,12695);
//        System.out.println("------");
//        System.out.println(palinsesto);
//        System.out.println("------");
//        JsonTableRow r = new JsonTableRow();
//        r.setResult("ciao");
//        ObjectMapper mapper = new ObjectMapper();
//        Hibernate4Module hbm = new Hibernate4Module();
//        hbm.enable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
//        mapper.registerModule(hbm);

//        calcoloPesiSquadre(ctx,"PETERBOROUGH","GILLINGHAM","LEAGUE ONE");
///                calcoloPesiSquadre(ctx,"ESTUDIANTES","ARGENTINOS","CAMPIONATO ARGENTINO");
//        calcoloPesiSquadre(ctx, "SLAVIA PRAGA", "JABLONEC", "CAMPIONATO CECO");
//        System.out.println(ctx.getBean(GenericService.class).findIncontroSquadraByRisultatoAndCompetizione("JUVENTUS", "CAMPIONATO ITALIANO SERIE A", 10,IncontroHelper.AWAY_TEAM));
//        System.out.println(mapper.writeValueAsString(r));
    }

    private static void calcoloPesiSquadre(ApplicationContext ctx, String homeTeam, String awayTeam, String competizione) {
        IncontroService incontroService = ctx.getBean(IncontroService.class);

        Page<Incontro> incontrosH = incontroService.findByHomeTeamAndCompetizione(homeTeam, competizione, new PageRequest(0, 10));
        Page<Incontro> incontrosA = incontroService.findByAwayTeamAndCompetizione(homeTeam, competizione, new PageRequest(0, 10));

        double pesoH = calcolaPeso(incontrosH, homeTeam);
        double pesoA = calcolaPeso(incontrosA, awayTeam);

//        System.out.println("Peso home: " + pesoH);
//        System.out.println("Peso away: " + pesoA);
//        System.out.println("\t" + (pesoH + pesoA) / 2);
    }

    private static double calcolaPeso(Page<Incontro> incontros, String team) {
        double peso = 1.00d;
        for (int i = 0; i < incontros.getSize(); i++) {
            //se team è la squadra di casa
            if (i > 0) {
                Incontro incontro = incontros.getContent().get(i);
                if (team.equals(incontro.getHomeTeam())) {
                    if (incontro.getGolHome() > incontro.getGolAway()) {
                        peso = peso + PESO_VITTORIA_CASA;
                    }
                    if (incontro.getGolHome() < incontro.getGolAway()) {
                        peso = peso + PESO_SCONFITTA_CASA;
                    }
                    if (incontro.getGolHome() == incontro.getGolAway()) {
                        peso = peso + PESO_PAREGGIO_CASA;
                    }
                } else {
                    if (incontro.getGolHome() > incontro.getGolAway()) {
                        peso = peso + PESO_SCONFITTA_FUORI;
                    }
                    if (incontro.getGolHome() < incontro.getGolAway()) {
                        peso = peso + PESO_VITTORIA_FUORI;
                    }
                    if (incontro.getGolHome() == incontro.getGolAway()) {
                        peso = peso + PESO_PAREGGIO_FUORI;
                    }
                }
            }
        }
        return peso / (incontros.getSize()-1);
    }

}

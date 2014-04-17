package org.cheetah.youbet;

import java.io.IOException;
import java.util.List;
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.entities.PercentualeSingoliEsiti;
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.gui.MainFrame;
import org.cheetah.youbet.repositories.SerieRepository;
import org.cheetah.youbet.service.GenericService;
import org.cheetah.youbet.service.IncontroService;
import org.cheetah.youbet.service.PercentualeSingoliEsitiService;
import org.cheetah.youbet.service.PoissonService;
import org.cheetah.youbet.util.Aggregator;
import org.cheetah.youbet.util.Calculator;
import org.cheetah.youbet.util.DownloadScores;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException, Exception {
        AbstractApplicationContext ctx = ContextSpringFactory.getInstance().getContext();
        if (args != null && args.length > 0) {
            if (args[0].equals("--gui")) {
                updateManifestazioneTable(ctx);

                MainFrame.main(args);
                return;
            }
            if(args[0].equals("--stats")){
                calcolaStats(ctx);
                return;
            }
        }

//        String s = "{palinsesto: '14554', avvenimento: '12', classe: '10', esito: '1', legmin: '1', legmax: '20', legmul: '1', blackmin: '0', blackmax: '0', desavvenimento: 'MILAN - BOLOGNA', desclasse: 'UNDER AND OVER 2,5', desesito: 'UNDER', handicap: '', datascad: '2014-02-14T20:45:00', eventolive: '0'}";
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        System.out.println(mapper.readValue(s, JSONQuote.class));
//        
//        s = "{sezione: 'sport', disciplina: 1, manifestazione: 21, classe: 3, filtro: 'tutti'}";
//        
//        System.out.println(mapper.readValue(s, JSONSchedina.class));
//        ManifestazioneService manifestazioneService = (ManifestazioneService) ctx.getBean("manifestazioneService");
//        System.out.println(manifestazioneService.findAll());
        try {

            insertScores(ctx);
            insertBooks(ctx);
            calcolaStats(ctx);

        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

    }

    private static void calcolaSerie(AbstractApplicationContext ctx) throws BeansException {
        IncontroService incontroService = ctx.getBean(IncontroService.class);
        List<Incontro> incontros = incontroService.findAll();
        for (Incontro incontro : incontros) {
            Calculator.calcolaSerie(incontro, incontroService.findByHomeTeamAndCompetizione(incontro.getHomeTeam(), incontro.getCompetizione()), incontroService.findByAwayTeamAndCompetizione(incontro.getAwayTeam(), incontro.getCompetizione()), ctx.getBean(SerieRepository.class));
        }
    }

    private static void calcolaStats(AbstractApplicationContext ctx) throws BeansException {
        Calculator c = ctx.getBean(Calculator.class);
        List<Poisson> stats = c.calcolaStat();
        ctx.getBean(PoissonService.class).saveAll(stats);

        List<PercentualeSingoliEsiti> esitis = c.calcolaPercEsiti(stats);
        PercentualeSingoliEsitiService pses = ctx.getBean(PercentualeSingoliEsitiService.class);
//        pses.deleteAll();
        pses.saveAll(esitis);
    }

    private static void insertBooks(AbstractApplicationContext ctx) throws Exception, BeansException {
        Aggregator agg = (Aggregator) ctx.getBean("aggregator");
        agg.loadData();
    }

    private static void insertScores(AbstractApplicationContext ctx) throws Exception, BeansException {
        //            System.out.println(((IncontroService)ctx.getBean("incontroService")).getSumGoalByTeam("JUVENTUS",IncontroService.HOME_TEAM));
//            System.out.println(((IncontroService)ctx.getBean("incontroService")).getSumGoalByTeam("JUVENTUS",IncontroService.AWAY_TEAM));
//            System.out.println(((IncontroService)ctx.getBean("incontroService")).getSumGoalByTeam("JUVENTUS","CAMPIONATO ITALIANO SERIE A",IncontroService.HOME_TEAM));
//            System.out.println(((IncontroService)ctx.getBean("incontroService")).getSumGoalByTeam("JUVENTUS","CAMPIONATO ITALIANO SERIE A",IncontroService.AWAY_TEAM));

        DownloadScores ds = ctx.getBean(DownloadScores.class);
        ds.execute();
    }

    /**
     * Aggiorna la tabella delle manifestazioni con il nome lungo.
     */
    private static void updateManifestazioneTable(AbstractApplicationContext ctx) {
        GenericService genericService = ctx.getBean(GenericService.class);
        genericService.updateManifestazioneTable();

    }

}

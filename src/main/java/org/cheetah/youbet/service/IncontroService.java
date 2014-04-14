/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.repositories.IncontroRepository;
import org.cheetah.youbet.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.cheetah.youbet.util.helper.IncontroHelper.*;
/**
 *
 * @author edoardo
 */
@Service("incontroService")
public class IncontroService {

    @Autowired
    private IncontroRepository repository;
    private final int DEFAULT_ROWS_PER_PAGE = 15;
    
    @Autowired
    SerieRepository serieRepository;

    @Transactional
    public Date findByMaxDate() {
        return getRepository().findByMaxDate();
    }

    @Transactional
    public List<Incontro> saveAll(Iterable<Incontro> incontros) {

        List<Incontro> result = getRepository().save(incontros);

//        List<Serie> series = new ArrayList<Serie>();
//        for (Incontro incontro : result) {
//            List<Incontro> ht = findByHomeTeamAndCompetizione(incontro.getHomeTeam(), incontro.getCompetizione());
//            List<Incontro> at = findByAwayTeamAndCompetizione(incontro.getAwayTeam(), incontro.getCompetizione());
//            Calculator.calcolaSerie(incontro, ht, at,serieRepository);
//
//        }

        return result;
    }



    public Page<Incontro> findByHomeTeam(String homeTeam, Pageable pageable) {
        return getRepository().findByHomeTeam(homeTeam, pageable);
    }

    public Page<Incontro> findByAwayTeam(String homeTeam, Pageable pageable) {
        return getRepository().findByAwayTeam(homeTeam, pageable);
    }

    public Page<Incontro> findByHomeTeamAndCompetizione(String homeTeam, String competizione, Pageable pageable) {
        return getRepository().findByHomeTeamAndCompetizione(homeTeam, competizione, pageable);
    }

    public Page<Incontro> findByAwayTeamAndCompetizione(String homeTeam, String competizione, Pageable pageable) {
        return getRepository().findByAwayTeamAndCompetizione(homeTeam, competizione, pageable);
    }

    public Stat getSumGoalByTeam(String team, String competizione, int teamType, int maxResults) {
        Page<Incontro> incontros = (competizione == null || competizione.trim().equals("")
                ? (teamType == HOME_TEAM ? findByHomeTeam(team, new PageRequest(0, maxResults)) : findByAwayTeam(team, new PageRequest(0, maxResults)))
                : (teamType == HOME_TEAM ? findByHomeTeamAndCompetizione(team, competizione, new PageRequest(0, maxResults)) : findByAwayTeamAndCompetizione(team, competizione, new PageRequest(0, maxResults))));
        int gf = 0;
        int gs = 0;
        Stat stat = new Stat();
        for (Incontro incontro : incontros) {
            gf += teamType == HOME_TEAM ? incontro.getGolHome().intValue() : incontro.getGolAway().intValue();
            gs += teamType == HOME_TEAM ? incontro.getGolAway().intValue() : incontro.getGolHome().intValue();
        }
        stat.setGf(gf);
        stat.setGs(gs);
        stat.setGiocate(incontros.getSize());
        return stat;
    }
    
    public Page<Incontro> findIncontroByTeam(Palinsesto p,int teamType,int maxResults){
        Page<Incontro> incontros = null;
        switch(teamType){
            case HOME_TEAM:
                return repository.findByHomeTeamAndCompetizione(p.getHomeTeam(), p.getIdManifestazione().getDescrizioneLunga(), new PageRequest(0, maxResults));
            case AWAY_TEAM:
                return repository.findByAwayTeamAndCompetizione(p.getAwayTeam(), p.getIdManifestazione().getDescrizioneLunga(), new PageRequest(0, maxResults));
        }
        return null;
    }
    
    public Page<Incontro> findIncontroByTeam(String competizione,String squadra,int maxResults){
        return repository.findIncontroByTeam(competizione,squadra, new PageRequest(0, maxResults));
    }

    public Stat getSumGoalByTeam(String team, String competizione, int teamType) {
        return getSumGoalByTeam(team, competizione, teamType, DEFAULT_ROWS_PER_PAGE);
    }

    public Stat getSumGoalByTeam(String team, int teamType, int maxResults) {
        return getSumGoalByTeam(team, null, teamType, maxResults);
    }

    public Stat getSumGoalByTeam(String team, int teamType) {
        return getSumGoalByTeam(team, null, teamType, DEFAULT_ROWS_PER_PAGE);
    }

    public List<Incontro> findAll() {
        return getRepository().findAll();
    }

    public List<Incontro> findByAwayTeamAndCompetizione(String awayTeam, String competizione) {
        return getRepository().findByAwayTeamAndCompetizione(awayTeam, competizione);
    }

    public List<Incontro> findByHomeTeamAndCompetizione(String homeTeam, String competizione) {
        return getRepository().findByHomeTeamAndCompetizione(homeTeam, competizione);
    }

    /**
     * @return the repository
     */
    public IncontroRepository getRepository() {
        return repository;
    }

    public List<Incontro> findHeadToHead(String homeTeam, String awayTeam, String nomeCompetizione) {
        return repository.findHeadToHead(homeTeam,awayTeam,nomeCompetizione);
    }

    public static class Stat {

        //gol fatti
        private double gf;
        private double giocate;
        //gol subiti        
        private double gs;

        public Stat() {
        }

        public double getGiocate() {
            return giocate;
        }

        public void setGiocate(double giocate) {
            this.giocate = giocate;
        }

        public double getGf() {
            return gf;
        }

        public void setGf(double gf) {
            this.gf = gf;
        }

        public double getGs() {
            return gs;
        }

        public void setGs(double gs) {
            this.gs = gs;
        }

        @Override
        public String toString() {
            return "Stat{" + "gf=" + gf + ", gs=" + gs + '}';
        }

        public double mediaGfPartita() {
            return getGf() / getGiocate();
        }

        public double mediaGsPartita() {
            return getGs() / getGiocate();
        }

        /**
         * Restituisce una mappa con il valore della distribuzione per nnumero
         * di eventi(gol)
         *
         * @param golmax
         * @return
         */
        public Map<Integer, Double> poisson(int golmax, double media) {
            Map<Integer, Double> p = new TreeMap<Integer, Double>();

            for (int i = 0; i <= golmax; i++) {
//                System.out.println(Math.E+"^"+media*-1+": "+Math.pow(Math.E, (media*-1)));
//                System.out.println(media+"^"+i+": "+Math.pow(media, i));
//                System.out.println(i+"!: "+fattoriale(i));
                double ris = Math.pow(Math.E, (media * -1)) * Math.pow(media, i) / fattoriale(i);
                p.put(i, ris);
//                System.out.println(ris);
            }
//            System.out.println(p);
            return p;
        }

        public int fattoriale(int x) {
            int result = 1;
            if (x == 0) {
                return result;
            }

            while (x > 0) {
                result *= x--;
            }
            return result;

        }

    }

    public static void main(String[] a) {
        System.out.println(new Stat().fattoriale(1));
        System.out.println(Math.pow(5, -2));

    }

}

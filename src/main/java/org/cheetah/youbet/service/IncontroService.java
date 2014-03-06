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
import org.cheetah.youbet.repositories.IncontroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Service("incontroService")
public class IncontroService {

    @Autowired
    private IncontroRepository repository;
    private final int DEFAULT_ROWS_PER_PAGE = 15;
    public static final int AWAY_TEAM = 2;
    public static final int HOME_TEAM = 4;
    

    @Transactional
    public Date findByMaxDate() {
        return repository.findByMaxDate();
    }

    @Transactional
    public List<Incontro> saveAll(Iterable<Incontro> incontros) {
        return repository.save(incontros);
    }

    public Page<Incontro> findByHomeTeam(String homeTeam, Pageable pageable) {
        return repository.findByHomeTeam(homeTeam, pageable);
    }

    public Page<Incontro> findByAwayTeam(String homeTeam, Pageable pageable) {
        return repository.findByAwayTeam(homeTeam, pageable);
    }

    public Page<Incontro> findByHomeTeamAndCompetizione(String homeTeam, String competizione, Pageable pageable) {
        return repository.findByHomeTeamAndCompetizione(homeTeam, competizione, pageable);
    }

    public Page<Incontro> findByAwayTeamAndCompetizione(String homeTeam, String competizione, Pageable pageable) {
        return repository.findByAwayTeamAndCompetizione(homeTeam, competizione, pageable);
    }

    public Stat getSumGoalByTeam(String team, String competizione, int teamType,  int maxResults) {
        Page<Incontro> incontros = (competizione == null || competizione.trim().equals("")
                ? (teamType == HOME_TEAM ? findByHomeTeam(team, new PageRequest(0, maxResults)) : findByAwayTeam(team, new PageRequest(0, maxResults)))
                : (teamType == HOME_TEAM ? findByHomeTeamAndCompetizione(team, competizione, new PageRequest(0, maxResults)) : findByAwayTeamAndCompetizione(team, competizione, new PageRequest(0, maxResults))));
        int gf = 0;
        int gs = 0;
        Stat stat = new Stat();
        for (Incontro incontro : incontros) {
            gf += teamType==HOME_TEAM?incontro.getGolHome().intValue():incontro.getGolAway().intValue();
            gs += teamType==HOME_TEAM?incontro.getGolAway().intValue():incontro.getGolHome().intValue();
        }
        stat.setGf(gf);
        stat.setGs(gs);
        stat.setGiocate(incontros.getSize());
        return stat;
    }

    public Stat getSumGoalByTeam(String team,String competizione, int teamType) {
        return getSumGoalByTeam(team, competizione, teamType,DEFAULT_ROWS_PER_PAGE);
    }
    
    public Stat getSumGoalByTeam(String team, int teamType, int maxResults) {
        return getSumGoalByTeam(team, null, teamType, maxResults);
    }

    public Stat getSumGoalByTeam(String team, int teamType) {
        return getSumGoalByTeam(team, null, teamType, DEFAULT_ROWS_PER_PAGE);
    }

    public List<Incontro> findAll() {
        return repository.findAll();
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
        
        public double mediaGfPartita(){
            return getGf()/getGiocate();
        }
        public double mediaGsPartita(){
            return getGs()/getGiocate();
        }
        
        /**
         * Restituisce una mappa con il valore della distribuzione per nnumero di eventi(gol)
         * @param golmax
         * @return 
         */
        public Map<Integer,Double> poisson(int golmax,double media){
            Map<Integer,Double> p = new TreeMap<Integer, Double>();
            
            for (int i = 0; i <= golmax; i++) {
//                System.out.println(Math.E+"^"+media*-1+": "+Math.pow(Math.E, (media*-1)));
//                System.out.println(media+"^"+i+": "+Math.pow(media, i));
//                System.out.println(i+"!: "+fattoriale(i));
                double ris = Math.pow(Math.E, (media*-1))*Math.pow(media, i)/fattoriale(i);
                p.put(i, ris);
//                System.out.println(ris);
            }
//            System.out.println(p);
            return p;
        }
        
        public int fattoriale(int x){
            int result = 1;
            if(x==0 ){
                return result;
            }
            
            while(x>0){
                result*=x--;
            }
            return result;
            
        }
        
       
        
    }
     public static void main(String[] a){
            System.out.println(new Stat().fattoriale(1));
            System.out.println(Math.pow(5, -2));
            
        }

}

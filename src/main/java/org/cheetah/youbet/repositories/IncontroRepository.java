/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.repositories;

import java.util.Date;
import java.util.List;
import org.cheetah.youbet.entities.Incontro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */

@Repository
@Transactional
public interface IncontroRepository extends JpaRepository<Incontro, String>{
    
    @Query("select max(i.dataEvento) from Incontro i ")
    public Date findByMaxDate();    
    
    
    @Query("select i from Incontro i where i.homeTeam=?1 order by i.dataEvento desc")
    public Page<Incontro> findByHomeTeam(String homeTeam,Pageable pageable);
    
    @Query("select i from Incontro i where i.awayTeam=?1 order by i.dataEvento Desc")
    public Page<Incontro> findByAwayTeam(String awayTeam,Pageable pageable);

    @Query("select i from Incontro i where i.homeTeam=?1 and i.competizione=?2 order by i.dataEvento desc")
    public Page<Incontro> findByHomeTeamAndCompetizione(String homeTeam,String competizione,Pageable pageable);
    
    @Query("select i from Incontro i where i.awayTeam=?1 and i.competizione=?2 order by i.dataEvento Desc")
    public Page<Incontro> findByAwayTeamAndCompetizione(String awayTeam,String competizione,Pageable pageable);

    
    /**
     * Trova tutte le partite giocate fuori casa da awayTeam per competizione
     * @param homeTeam
     * @param competizione
     * @return 
     */
    public List<Incontro> findByAwayTeamAndCompetizione(String awayTeam, String competizione);

    
    /**
     * Trova tutte le partite giocate in casa da homeTeam per competizione
     * @param homeTeam
     * @param competizione
     * @return 
     */
    public List<Incontro> findByHomeTeamAndCompetizione(String homeTeam, String competizione);

    
    @Query("select i from Incontro i where i.competizione=?1 and (i.awayTeam=?2 or i.homeTeam=?2) order by i.dataEvento desc")
    public Page<Incontro> findIncontroByTeam(String competizione,String squadra, Pageable pageable);

    @Query("select i from Incontro i where i.competizione=?3 and (i.homeTeam=?1 or i.awayTeam=?1) and (i.homeTeam=?2 or i.awayTeam=?2)")
    public List<Incontro> findHeadToHead(String homeTeam, String awayTeam, String nomeCompetizione);
    
    
    
}

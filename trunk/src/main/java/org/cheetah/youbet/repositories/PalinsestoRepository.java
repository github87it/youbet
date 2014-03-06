/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.repositories;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.PalinsestoPK;
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
public interface PalinsestoRepository extends JpaRepository<Palinsesto, PalinsestoPK>{

    
    @Query("select p from Palinsesto p,Quota q "
            + "where p.dataEvento>=?1 and "
            + "p.oraEvento>?2 and "
            + "p.palinsestoPK.idPalinsesto=q.quotaPK.idPalinsesto and "
            + "p.palinsestoPK.idAvvenimento=q.quotaPK.idAvvenimento and "
            + "q.esito.esitoPK.idEsito=1 and "
            + "q.esito.esitoPK.idClasse=3")
    public List<Palinsesto> findPartiteDaGiocare(Date d,Time t);

    public List<Palinsesto> findByDataEventoAfter(Date d);
    
    
    
}

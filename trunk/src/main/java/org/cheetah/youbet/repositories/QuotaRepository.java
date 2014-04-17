/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.repositories;

import java.util.List;
import org.cheetah.youbet.entities.Quota;
import org.cheetah.youbet.entities.QuotaPK;
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
public interface QuotaRepository extends JpaRepository<Quota,QuotaPK>{

    @Query("select q from Quota q where q.quotaPK.idPalinsesto=?1 and q.quotaPK.idAvvenimento=?2")
    public List<Quota> findByIdPalinsestoAndIdAvvenimento(int idPalinsesto, int idAvvenimento);

    @Query("select q from Quota q where q.quotaPK.idPalinsesto=?1 and q.quotaPK.idAvvenimento=?2 and q.quotaPK.idClasse=?3 ")
    public List<Quota> findByIdPalinsestoAndIdAvvenimentoAndIdClasse(int idPalinsesto, int idAvvenimento, int idClasse);
    
}

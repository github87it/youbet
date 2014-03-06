/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.repositories;

import java.util.List;
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.entities.PoissonPK;
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
public interface PoissonRepository extends JpaRepository<Poisson, PoissonPK>{

    @Query("SELECT p FROM Poisson p where p.poissonPK.idAvvenimento=?2 and p.poissonPK.idPalinsesto=?1")
    public List<Poisson> findByIdPalinsestoAndIdAvvenimento(int idPalinsesto, int idAvvenimento);
    
}

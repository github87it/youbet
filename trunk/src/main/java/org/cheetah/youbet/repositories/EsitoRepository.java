/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.repositories;

import org.cheetah.youbet.entities.Esito;
import org.cheetah.youbet.entities.EsitoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Repository
@Transactional
public interface EsitoRepository extends JpaRepository<Esito, EsitoPK>{
    
}

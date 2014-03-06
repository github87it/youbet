/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.repositories;

import org.cheetah.youbet.entities.Quota;
import org.cheetah.youbet.entities.QuotaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Repository
@Transactional
public interface QuotaRepository extends JpaRepository<Quota,QuotaPK>{
    
}

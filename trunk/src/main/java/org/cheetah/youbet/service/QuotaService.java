/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import org.cheetah.youbet.entities.Quota;
import org.cheetah.youbet.repositories.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edoardo
 */
@Service(value = "quotaService")
public class QuotaService  {
    
    @Autowired
    private QuotaRepository repository;
    
    public Quota save(Quota quota){
        return repository.saveAndFlush(quota);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import org.cheetah.youbet.entities.LogError;
import org.cheetah.youbet.repositories.LogErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edoardo
 */

@Service("logErrorService")
public class LogErrorService {
    
    
    @Autowired
    private LogErrorRepository repository;
    
    
    public LogError save(LogError logError){
        return repository.save(logError);
    }
}

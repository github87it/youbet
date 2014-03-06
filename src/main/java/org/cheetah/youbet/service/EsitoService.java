/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import org.cheetah.youbet.entities.Esito;
import org.cheetah.youbet.repositories.EsitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edoardo
 */
@Service(value = "esitoService")
public class EsitoService {

    
    @Autowired
    private EsitoRepository repository;
    
    public Esito save(Esito esito){
        return repository.saveAndFlush(esito);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.service;

import java.util.List;
import org.cheetah.youbet.entities.Manifestazione;
import org.cheetah.youbet.repositories.ManifestazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Service(value = "manifestazioneService")
public class ManifestazioneService {

    @Autowired
    private ManifestazioneRepository repository;

    public Manifestazione findByPk(Integer id ){
        return repository.findOne(id);
    }
    
    @Transactional
    public List<Manifestazione> findAll() {
        return repository.findAll();
    }

    public List<Manifestazione> save(Iterable<Manifestazione> disciplinas) {
        return repository.save(disciplinas);
    }
    
    public Manifestazione save(Manifestazione m){
        return repository.saveAndFlush(m);
    }

}

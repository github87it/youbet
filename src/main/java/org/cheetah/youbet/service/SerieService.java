/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import java.util.List;
import org.cheetah.youbet.entities.Serie;
import org.cheetah.youbet.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edoardo
 */
@Service
public class SerieService {
    
    
    @Autowired
    SerieRepository repository;

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Serie> saveAll(List<Serie> series) {
        return repository.save(series);
    }
    
    
}

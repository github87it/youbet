/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import java.util.List;
import org.cheetah.youbet.entities.Classe;
import org.cheetah.youbet.repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Service(value = "classeService")
public class ClasseService {
    
    @Autowired
    ClasseRepository repository;
    
    @Transactional
    public List<Classe> save(Iterable<Classe> classes){
        return repository.save(classes);
    }
    
}

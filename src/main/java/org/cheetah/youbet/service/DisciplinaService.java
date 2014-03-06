/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import java.util.List;
import org.cheetah.youbet.entities.Disciplina;
import org.cheetah.youbet.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Service(value = "disciplinaService")
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository repository;
    
    @Transactional
    public List<Disciplina> save(Iterable<Disciplina> disciplinas){
        return repository.save(disciplinas);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.service;

import java.util.List;
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.repositories.PoissonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edoardo
 */
@Service("poissonService")
public class PoissonService {
    
    @Autowired
    PoissonRepository poissonRepository;
    
    public List<Poisson> saveAll(Iterable<Poisson> p){
        return poissonRepository.save(p);
    }
    
    public List<Poisson> findByIdPalinsestoAndIdAvvenimento(int idPalinsesto, int idAvvenimento){
        return poissonRepository.findByIdPalinsestoAndIdAvvenimento(idPalinsesto, idAvvenimento);
    }

    public List<Poisson> findByIdPalinsestoAndIdAvvenimentoAndPercentualeGreaterThan(int idPalinsesto, int idAvvenimento, double percentuale) {
        return poissonRepository.findByIdPalinsestoAndIdAvvenimentoAndPercentualeGreaterThan(idPalinsesto,idAvvenimento, percentuale);
    }
}

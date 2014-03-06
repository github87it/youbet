/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.PalinsestoPK;
import org.cheetah.youbet.repositories.PalinsestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Service(value = "palinsestoService")
public class PalinsestoService {

    @Autowired
    private PalinsestoRepository repository;

    @Transactional
    public Palinsesto save(Palinsesto palinsesto) {
        return repository.save(palinsesto);
    }

    @Transactional
    public Palinsesto findByPk(int idAvvenimento, int idPalinsesto) {
        return repository.findOne(new PalinsestoPK(idPalinsesto, idAvvenimento));
    }
    
    
    public List<Palinsesto> findPartiteDaGiocare(Date d){
        
        return repository.findPartiteDaGiocare(d, new Time(d.getTime()));
    }
}

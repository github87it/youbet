    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.service;

import java.util.List;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.PercentualeSingoliEsiti;
import org.cheetah.youbet.entities.PercentualeSingoliEsitiPK;
import org.cheetah.youbet.entities.Poisson;
import org.cheetah.youbet.entities.PoissonPK;
import org.cheetah.youbet.repositories.PercentualeSingoliEsitiRepository;
import org.cheetah.youbet.repositories.PoissonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edoardo
 */
@Service
public class PercentualeSingoliEsitiService {

    @Autowired
    PercentualeSingoliEsitiRepository repository;

    @Autowired
    PoissonRepository poissonRepository;
    @Autowired
    PalinsestoService palinsestoService;

    public List<PercentualeSingoliEsiti> saveAll(Iterable<PercentualeSingoliEsiti> pse) {
        return repository.save(pse);
    }

    public PercentualeSingoliEsiti createPercByIdPalinsestoAndIdAvvenimento(int idPalinsesto, int idAvvenimento) {
        List<Poisson> stats = poissonRepository.findByIdPalinsestoAndIdAvvenimento(idPalinsesto, idAvvenimento);
        PercentualeSingoliEsiti pse = new PercentualeSingoliEsiti();
        pse.setPercentualeSingoliEsitiPK(new PercentualeSingoliEsitiPK(idPalinsesto, idAvvenimento));
        Palinsesto palinsesto = palinsestoService.findByPk(idAvvenimento, idPalinsesto);
        for (Poisson p : stats) {
            PoissonPK pk = p.getPoissonPK();
            pse.setAwayTeam(palinsesto.getAwayTeam());
            pse.setHomeTeam(palinsesto.getHomeTeam());
            if (pk.getGolHome() > pk.getGolAway()) {
                pse.setVittoriaCasa((pse.getVittoriaCasa()==null?0.0:pse.getVittoriaCasa()) + p.getPercentuale());
            }
            if (pk.getGolHome() < pk.getGolAway()) {
                pse.setVittoriaFuori((pse.getVittoriaFuori()==null?0.0:pse.getVittoriaFuori() )+ p.getPercentuale());
            }
            if (pk.getGolHome() == pk.getGolAway()) {
                pse.setPareggio((pse.getPareggio()==null?0.0:pse.getPareggio()) + p.getPercentuale());
            }
            if (pk.getGolHome() <= pk.getGolAway()) {
                pse.setDoppiaChanceOut((pse.getDoppiaChanceOut()==null?0.0:pse.getDoppiaChanceOut()) + p.getPercentuale());
            }
            if (pk.getGolHome() >= pk.getGolAway()) {
                pse.setDoppiaChanceIn((pse.getDoppiaChanceIn()==null?0.0:pse.getDoppiaChanceIn())+ p.getPercentuale());
            }
            if (pk.getGolHome() > pk.getGolAway() || pk.getGolHome() < pk.getGolAway()) {
                pse.setDoppiaChanceInOut((pse.getDoppiaChanceInOut()==null?0.0:pse.getDoppiaChanceInOut())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()>1.5){
                pse.setOverUnoCinque((pse.getOverUnoCinque()==null?0.0:pse.getOverUnoCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()<1.5){
                pse.setUnderUnoCinque((pse.getUnderUnoCinque()==null?0.0:pse.getUnderUnoCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()>2.5){
                pse.setOverDueCinque((pse.getOverDueCinque()==null?0.0:pse.getOverDueCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()<2.5){
                pse.setUnderDueCinque((pse.getUnderDueCinque()==null?0.0:pse.getUnderDueCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()>3.5){
                pse.setOverTreCinque((pse.getOverTreCinque()==null?0.0:pse.getOverTreCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()<3.5){
                pse.setUnderTreCinque((pse.getUnderTreCinque()==null?0.0:pse.getUnderTreCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()>4.5){
                pse.setOverQuattroCinque((pse.getOverQuattroCinque()==null?0.0:pse.getOverQuattroCinque())+p.getPercentuale());
            }
            if(pk.getGolHome()+pk.getGolAway()<4.5){
                pse.setUnderQuattroCinque((pse.getUnderQuattroCinque()==null?0.0:pse.getUnderQuattroCinque())+p.getPercentuale());
            }
            if(pk.getGolAway()>0 && pk.getGolHome()>0 ){
                pse.setGol((pse.getGol()==null?0.0:pse.getGol())+p.getPercentuale());
            }
            if(pk.getGolAway()==0 || pk.getGolHome()==0 ){
                pse.setNoGol((pse.getNoGol()==null?0.0:pse.getNoGol())+p.getPercentuale());
            }
            if((pk.getGolAway()+pk.getGolHome())%2==0){
                pse.setPari((pse.getPari()==null?0.0:pse.getPari())+p.getPercentuale());
            }else{
                pse.setDispari((pse.getDispari()==null?0.0:pse.getDispari())+p.getPercentuale());
            }
        }
        return pse;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

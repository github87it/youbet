/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edoardo
 */
@Entity
@Table(name = "PERCENTUALE_SINGOLI_ESITI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PercentualeSingoliEsiti.findAll", query = "SELECT p FROM PercentualeSingoliEsiti p"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByIdPalinsesto", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.percentualeSingoliEsitiPK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByIdAvvenimento", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.percentualeSingoliEsitiPK.idAvvenimento = :idAvvenimento"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByHomeTeam", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.homeTeam = :homeTeam"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByAwayTeam", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.awayTeam = :awayTeam"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByVittoriaCasa", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.vittoriaCasa = :vittoriaCasa"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByPareggio", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.pareggio = :pareggio"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByVittoriaFuori", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.vittoriaFuori = :vittoriaFuori"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByDoppiaChanceIn", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.doppiaChanceIn = :doppiaChanceIn"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByDoppiaChanceInOut", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.doppiaChanceInOut = :doppiaChanceInOut"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByDoppiaChanceOut", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.doppiaChanceOut = :doppiaChanceOut"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByPari", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.pari = :pari"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByDispari", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.dispari = :dispari"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByOverUnoCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.overUnoCinque = :overUnoCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByUnderUnoCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.underUnoCinque = :underUnoCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByOverDueCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.overDueCinque = :overDueCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByUnderDueCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.underDueCinque = :underDueCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByOverTreCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.overTreCinque = :overTreCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByUnderTreCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.underTreCinque = :underTreCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByOverQuattroCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.overQuattroCinque = :overQuattroCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByUnderQuattroCinque", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.underQuattroCinque = :underQuattroCinque"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByGol", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.gol = :gol"),
    @NamedQuery(name = "PercentualeSingoliEsiti.findByNoGol", query = "SELECT p FROM PercentualeSingoliEsiti p WHERE p.noGol = :noGol")})
public class PercentualeSingoliEsiti implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PercentualeSingoliEsitiPK percentualeSingoliEsitiPK;
    @Column(name = "HOME_TEAM")
    private String homeTeam;
    @Column(name = "AWAY_TEAM")
    private String awayTeam;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VITTORIA_CASA")
    private Double vittoriaCasa;
    @Column(name = "PAREGGIO")
    private Double pareggio;
    @Column(name = "VITTORIA_FUORI")
    private Double vittoriaFuori;
    @Column(name = "DOPPIA_CHANCE_IN")
    private Double doppiaChanceIn;
    @Column(name = "DOPPIA_CHANCE_IN_OUT")
    private Double doppiaChanceInOut;
    @Column(name = "DOPPIA_CHANCE_OUT")
    private Double doppiaChanceOut;
    @Column(name = "PARI")
    private Double pari;
    @Column(name = "DISPARI")
    private Double dispari;
    @Column(name = "OVER_UNO_CINQUE")
    private Double overUnoCinque;
    @Column(name = "UNDER_UNO_CINQUE")
    private Double underUnoCinque;
    @Column(name = "OVER_DUE_CINQUE")
    private Double overDueCinque;
    @Column(name = "UNDER_DUE_CINQUE")
    private Double underDueCinque;
    @Column(name = "OVER_TRE_CINQUE")
    private Double overTreCinque;
    @Column(name = "UNDER_TRE_CINQUE")
    private Double underTreCinque;
    @Column(name = "OVER_QUATTRO_CINQUE")
    private Double overQuattroCinque;
    @Column(name = "UNDER_QUATTRO_CINQUE")
    private Double underQuattroCinque;
    @Column(name = "GOL")
    private Double gol;
    @Column(name = "NO_GOL")
    private Double noGol;

    public PercentualeSingoliEsiti() {
    }

    public PercentualeSingoliEsiti(PercentualeSingoliEsitiPK percentualeSingoliEsitiPK) {
        this.percentualeSingoliEsitiPK = percentualeSingoliEsitiPK;
    }

    public PercentualeSingoliEsiti(int idPalinsesto, int idAvvenimento) {
        this.percentualeSingoliEsitiPK = new PercentualeSingoliEsitiPK(idPalinsesto, idAvvenimento);
    }

    public PercentualeSingoliEsitiPK getPercentualeSingoliEsitiPK() {
        return percentualeSingoliEsitiPK;
    }

    public void setPercentualeSingoliEsitiPK(PercentualeSingoliEsitiPK percentualeSingoliEsitiPK) {
        this.percentualeSingoliEsitiPK = percentualeSingoliEsitiPK;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Double getVittoriaCasa() {
        return vittoriaCasa;
    }

    public void setVittoriaCasa(Double vittoriaCasa) {
        this.vittoriaCasa = vittoriaCasa;
    }

    public Double getPareggio() {
        return pareggio;
    }

    public void setPareggio(Double pareggio) {
        this.pareggio = pareggio;
    }

    public Double getVittoriaFuori() {
        return vittoriaFuori;
    }

    public void setVittoriaFuori(Double vittoriaFuori) {
        this.vittoriaFuori = vittoriaFuori;
    }

    public Double getDoppiaChanceIn() {
        return doppiaChanceIn;
    }

    public void setDoppiaChanceIn(Double doppiaChanceIn) {
        this.doppiaChanceIn = doppiaChanceIn;
    }

    public Double getDoppiaChanceInOut() {
        return doppiaChanceInOut;
    }

    public void setDoppiaChanceInOut(Double doppiaChanceInOut) {
        this.doppiaChanceInOut = doppiaChanceInOut;
    }

    public Double getDoppiaChanceOut() {
        return doppiaChanceOut;
    }

    public void setDoppiaChanceOut(Double doppiaChanceOut) {
        this.doppiaChanceOut = doppiaChanceOut;
    }

    public Double getPari() {
        return pari;
    }

    public void setPari(Double pari) {
        this.pari = pari;
    }

    public Double getDispari() {
        return dispari;
    }

    public void setDispari(Double dispari) {
        this.dispari = dispari;
    }

    public Double getOverUnoCinque() {
        return overUnoCinque;
    }

    public void setOverUnoCinque(Double overUnoCinque) {
        this.overUnoCinque = overUnoCinque;
    }

    public Double getUnderUnoCinque() {
        return underUnoCinque;
    }

    public void setUnderUnoCinque(Double underUnoCinque) {
        this.underUnoCinque = underUnoCinque;
    }

    public Double getOverDueCinque() {
        return overDueCinque;
    }

    public void setOverDueCinque(Double overDueCinque) {
        this.overDueCinque = overDueCinque;
    }

    public Double getUnderDueCinque() {
        return underDueCinque;
    }

    public void setUnderDueCinque(Double underDueCinque) {
        this.underDueCinque = underDueCinque;
    }

    public Double getOverTreCinque() {
        return overTreCinque;
    }

    public void setOverTreCinque(Double overTreCinque) {
        this.overTreCinque = overTreCinque;
    }

    public Double getUnderTreCinque() {
        return underTreCinque;
    }

    public void setUnderTreCinque(Double underTreCinque) {
        this.underTreCinque = underTreCinque;
    }

    public Double getOverQuattroCinque() {
        return overQuattroCinque;
    }

    public void setOverQuattroCinque(Double overQuattroCinque) {
        this.overQuattroCinque = overQuattroCinque;
    }

    public Double getUnderQuattroCinque() {
        return underQuattroCinque;
    }

    public void setUnderQuattroCinque(Double underQuattroCinque) {
        this.underQuattroCinque = underQuattroCinque;
    }

    public Double getGol() {
        return gol;
    }

    public void setGol(Double gol) {
        this.gol = gol;
    }

    public Double getNoGol() {
        return noGol;
    }

    public void setNoGol(Double noGol) {
        this.noGol = noGol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (percentualeSingoliEsitiPK != null ? percentualeSingoliEsitiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercentualeSingoliEsiti)) {
            return false;
        }
        PercentualeSingoliEsiti other = (PercentualeSingoliEsiti) object;
        if ((this.percentualeSingoliEsitiPK == null && other.percentualeSingoliEsitiPK != null) || (this.percentualeSingoliEsitiPK != null && !this.percentualeSingoliEsitiPK.equals(other.percentualeSingoliEsitiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PercentualeSingoliEsiti{" + "percentualeSingoliEsitiPK=" + percentualeSingoliEsitiPK + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", vittoriaCasa=" + vittoriaCasa + ", pareggio=" + pareggio + ", vittoriaFuori=" + vittoriaFuori + ", doppiaChanceIn=" + doppiaChanceIn + ", doppiaChanceInOut=" + doppiaChanceInOut + ", doppiaChanceOut=" + doppiaChanceOut + ", pari=" + pari + ", dispari=" + dispari + ", overUnoCinque=" + overUnoCinque + ", underUnoCinque=" + underUnoCinque + ", overDueCinque=" + overDueCinque + ", underDueCinque=" + underDueCinque + ", overTreCinque=" + overTreCinque + ", underTreCinque=" + underTreCinque + ", overQuattroCinque=" + overQuattroCinque + ", underQuattroCinque=" + underQuattroCinque + ", gol=" + gol + ", noGol=" + noGol + '}';
    }

    
}

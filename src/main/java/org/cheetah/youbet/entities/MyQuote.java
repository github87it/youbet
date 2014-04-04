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
@Table(name = "MY_QUOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyQuote.findAll", query = "SELECT m FROM MyQuote m"),
    @NamedQuery(name = "MyQuote.findByIdPalinsesto", query = "SELECT m FROM MyQuote m WHERE m.myQuotePK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "MyQuote.findByIdAvvenimento", query = "SELECT m FROM MyQuote m WHERE m.myQuotePK.idAvvenimento = :idAvvenimento"),
    @NamedQuery(name = "MyQuote.findByHomeTeam", query = "SELECT m FROM MyQuote m WHERE m.homeTeam = :homeTeam"),
    @NamedQuery(name = "MyQuote.findByAwayTeam", query = "SELECT m FROM MyQuote m WHERE m.awayTeam = :awayTeam"),
    @NamedQuery(name = "MyQuote.findByVittoriaCasa", query = "SELECT m FROM MyQuote m WHERE m.vittoriaCasa = :vittoriaCasa"),
    @NamedQuery(name = "MyQuote.findByPareggio", query = "SELECT m FROM MyQuote m WHERE m.pareggio = :pareggio"),
    @NamedQuery(name = "MyQuote.findByVittoriaFuori", query = "SELECT m FROM MyQuote m WHERE m.vittoriaFuori = :vittoriaFuori"),
    @NamedQuery(name = "MyQuote.findByDoppiaChanceIn", query = "SELECT m FROM MyQuote m WHERE m.doppiaChanceIn = :doppiaChanceIn"),
    @NamedQuery(name = "MyQuote.findByDoppiaChanceInOut", query = "SELECT m FROM MyQuote m WHERE m.doppiaChanceInOut = :doppiaChanceInOut"),
    @NamedQuery(name = "MyQuote.findByDoppiaChanceOut", query = "SELECT m FROM MyQuote m WHERE m.doppiaChanceOut = :doppiaChanceOut"),
    @NamedQuery(name = "MyQuote.findByPari", query = "SELECT m FROM MyQuote m WHERE m.pari = :pari"),
    @NamedQuery(name = "MyQuote.findByDispari", query = "SELECT m FROM MyQuote m WHERE m.dispari = :dispari"),
    @NamedQuery(name = "MyQuote.findByOverUnoCinque", query = "SELECT m FROM MyQuote m WHERE m.overUnoCinque = :overUnoCinque"),
    @NamedQuery(name = "MyQuote.findByUnderUnoCinque", query = "SELECT m FROM MyQuote m WHERE m.underUnoCinque = :underUnoCinque"),
    @NamedQuery(name = "MyQuote.findByOverDueCinque", query = "SELECT m FROM MyQuote m WHERE m.overDueCinque = :overDueCinque"),
    @NamedQuery(name = "MyQuote.findByUnderDueCinque", query = "SELECT m FROM MyQuote m WHERE m.underDueCinque = :underDueCinque"),
    @NamedQuery(name = "MyQuote.findByOverTreCinque", query = "SELECT m FROM MyQuote m WHERE m.overTreCinque = :overTreCinque"),
    @NamedQuery(name = "MyQuote.findByUnderTreCinque", query = "SELECT m FROM MyQuote m WHERE m.underTreCinque = :underTreCinque"),
    @NamedQuery(name = "MyQuote.findByOverQuattroCinque", query = "SELECT m FROM MyQuote m WHERE m.overQuattroCinque = :overQuattroCinque"),
    @NamedQuery(name = "MyQuote.findByUnderQuattroCinque", query = "SELECT m FROM MyQuote m WHERE m.underQuattroCinque = :underQuattroCinque"),
    @NamedQuery(name = "MyQuote.findByGol", query = "SELECT m FROM MyQuote m WHERE m.gol = :gol"),
    @NamedQuery(name = "MyQuote.findByNoGol", query = "SELECT m FROM MyQuote m WHERE m.noGol = :noGol")})
public class MyQuote implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MyQuotePK myQuotePK;
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

    public MyQuote() {
    }

    public MyQuote(MyQuotePK myQuotePK) {
        this.myQuotePK = myQuotePK;
    }

    public MyQuote(int idPalinsesto, int idAvvenimento) {
        this.myQuotePK = new MyQuotePK(idPalinsesto, idAvvenimento);
    }

    public MyQuotePK getMyQuotePK() {
        return myQuotePK;
    }

    public void setMyQuotePK(MyQuotePK myQuotePK) {
        this.myQuotePK = myQuotePK;
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
        hash += (myQuotePK != null ? myQuotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyQuote)) {
            return false;
        }
        MyQuote other = (MyQuote) object;
        if ((this.myQuotePK == null && other.myQuotePK != null) || (this.myQuotePK != null && !this.myQuotePK.equals(other.myQuotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.MyQuote[ myQuotePK=" + myQuotePK + " ]";
    }
    
}

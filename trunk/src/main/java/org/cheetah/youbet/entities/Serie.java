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
@Table(name = "SERIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s"),
    @NamedQuery(name = "Serie.findBySquadra", query = "SELECT s FROM Serie s WHERE s.seriePK.squadra = :squadra"),
    @NamedQuery(name = "Serie.findByCompetizione", query = "SELECT s FROM Serie s WHERE s.seriePK.competizione = :competizione"),
    @NamedQuery(name = "Serie.findBySerieVittorieCasa", query = "SELECT s FROM Serie s WHERE s.serieVittorieCasa = :serieVittorieCasa"),
    @NamedQuery(name = "Serie.findBySeriePareggiCasa", query = "SELECT s FROM Serie s WHERE s.seriePareggiCasa = :seriePareggiCasa"),
    @NamedQuery(name = "Serie.findBySerieSconfitteCasa", query = "SELECT s FROM Serie s WHERE s.serieSconfitteCasa = :serieSconfitteCasa"),
    @NamedQuery(name = "Serie.findBySerieVittorieFuori", query = "SELECT s FROM Serie s WHERE s.serieVittorieFuori = :serieVittorieFuori"),
    @NamedQuery(name = "Serie.findBySeriePareggiFuori", query = "SELECT s FROM Serie s WHERE s.seriePareggiFuori = :seriePareggiFuori"),
    @NamedQuery(name = "Serie.findBySerieSconfitteFuori", query = "SELECT s FROM Serie s WHERE s.serieSconfitteFuori = :serieSconfitteFuori"),
    @NamedQuery(name = "Serie.findByNumeroPartiteSenzaSubireGolCasa", query = "SELECT s FROM Serie s WHERE s.numeroPartiteSenzaSubireGolCasa = :numeroPartiteSenzaSubireGolCasa"),
    @NamedQuery(name = "Serie.findByNumeroPartiteSenzaSubireGolFuori", query = "SELECT s FROM Serie s WHERE s.numeroPartiteSenzaSubireGolFuori = :numeroPartiteSenzaSubireGolFuori"),
    @NamedQuery(name = "Serie.findByNumeroPartiteGolSubitiCasa", query = "SELECT s FROM Serie s WHERE s.numeroPartiteGolSubitiCasa = :numeroPartiteGolSubitiCasa"),
    @NamedQuery(name = "Serie.findByNumeroPartiteGolSubitiFuori", query = "SELECT s FROM Serie s WHERE s.numeroPartiteGolSubitiFuori = :numeroPartiteGolSubitiFuori")})
public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeriePK seriePK;
    @Column(name = "SERIE_VITTORIE_CASA")
    private Integer serieVittorieCasa;
    @Column(name = "SERIE_PAREGGI_CASA")
    private Integer seriePareggiCasa;
    @Column(name = "SERIE_SCONFITTE_CASA")
    private Integer serieSconfitteCasa;
    @Column(name = "SERIE_VITTORIE_FUORI")
    private Integer serieVittorieFuori;
    @Column(name = "SERIE_PAREGGI_FUORI")
    private Integer seriePareggiFuori;
    @Column(name = "SERIE_SCONFITTE_FUORI")
    private Integer serieSconfitteFuori;
    @Column(name = "NUMERO_PARTITE_SENZA_SUBIRE_GOL_CASA")
    private Integer numeroPartiteSenzaSubireGolCasa;
    @Column(name = "NUMERO_PARTITE_SENZA_SUBIRE_GOL_FUORI")
    private Integer numeroPartiteSenzaSubireGolFuori;
    @Column(name = "NUMERO_PARTITE_GOL_SUBITI_CASA")
    private Integer numeroPartiteGolSubitiCasa;
    @Column(name = "NUMERO_PARTITE_GOL_SUBITI_FUORI")
    private Integer numeroPartiteGolSubitiFuori;

    public Serie() {
    }

    public Serie(SeriePK seriePK) {
        this.seriePK = seriePK;
    }

    public Serie(String squadra, String competizione) {
        this.seriePK = new SeriePK(squadra, competizione);
    }

    public SeriePK getSeriePK() {
        return seriePK;
    }

    public void setSeriePK(SeriePK seriePK) {
        this.seriePK = seriePK;
    }

    public Integer getSerieVittorieCasa() {
        return serieVittorieCasa;
    }

    public void setSerieVittorieCasa(Integer serieVittorieCasa) {
        this.serieVittorieCasa = serieVittorieCasa;
    }

    public Integer getSeriePareggiCasa() {
        return seriePareggiCasa;
    }

    public void setSeriePareggiCasa(Integer seriePareggiCasa) {
        this.seriePareggiCasa = seriePareggiCasa;
    }

    public Integer getSerieSconfitteCasa() {
        return serieSconfitteCasa;
    }

    public void setSerieSconfitteCasa(Integer serieSconfitteCasa) {
        this.serieSconfitteCasa = serieSconfitteCasa;
    }

    public Integer getSerieVittorieFuori() {
        return serieVittorieFuori;
    }

    public void setSerieVittorieFuori(Integer serieVittorieFuori) {
        this.serieVittorieFuori = serieVittorieFuori;
    }

    public Integer getSeriePareggiFuori() {
        return seriePareggiFuori;
    }

    public void setSeriePareggiFuori(Integer seriePareggiFuori) {
        this.seriePareggiFuori = seriePareggiFuori;
    }

    public Integer getSerieSconfitteFuori() {
        return serieSconfitteFuori;
    }

    public void setSerieSconfitteFuori(Integer serieSconfitteFuori) {
        this.serieSconfitteFuori = serieSconfitteFuori;
    }

    public Integer getNumeroPartiteSenzaSubireGolCasa() {
        return numeroPartiteSenzaSubireGolCasa;
    }

    public void setNumeroPartiteSenzaSubireGolCasa(Integer numeroPartiteSenzaSubireGolCasa) {
        this.numeroPartiteSenzaSubireGolCasa = numeroPartiteSenzaSubireGolCasa;
    }

    public Integer getNumeroPartiteSenzaSubireGolFuori() {
        return numeroPartiteSenzaSubireGolFuori;
    }

    public void setNumeroPartiteSenzaSubireGolFuori(Integer numeroPartiteSenzaSubireGolFuori) {
        this.numeroPartiteSenzaSubireGolFuori = numeroPartiteSenzaSubireGolFuori;
    }

    public Integer getNumeroPartiteGolSubitiCasa() {
        return numeroPartiteGolSubitiCasa;
    }

    public void setNumeroPartiteGolSubitiCasa(Integer numeroPartiteGolSubitiCasa) {
        this.numeroPartiteGolSubitiCasa = numeroPartiteGolSubitiCasa;
    }

    public Integer getNumeroPartiteGolSubitiFuori() {
        return numeroPartiteGolSubitiFuori;
    }

    public void setNumeroPartiteGolSubitiFuori(Integer numeroPartiteGolSubitiFuori) {
        this.numeroPartiteGolSubitiFuori = numeroPartiteGolSubitiFuori;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seriePK != null ? seriePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.seriePK == null && other.seriePK != null) || (this.seriePK != null && !this.seriePK.equals(other.seriePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Serie[ seriePK=" + seriePK + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edoardo
 */
@Entity
@Table(name = "INCONTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incontro.findAll", query = "SELECT i FROM Incontro i"),
    @NamedQuery(name = "Incontro.findByIdPalinsesto", query = "SELECT i FROM Incontro i WHERE i.incontroPK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "Incontro.findByIdAvvenimento", query = "SELECT i FROM Incontro i WHERE i.incontroPK.idAvvenimento = :idAvvenimento"),
    @NamedQuery(name = "Incontro.findByHomeTeam", query = "SELECT i FROM Incontro i WHERE i.homeTeam = :homeTeam"),
    @NamedQuery(name = "Incontro.findByAwayTeam", query = "SELECT i FROM Incontro i WHERE i.awayTeam = :awayTeam"),
    @NamedQuery(name = "Incontro.findByGolHome", query = "SELECT i FROM Incontro i WHERE i.golHome = :golHome"),
    @NamedQuery(name = "Incontro.findByGolAway", query = "SELECT i FROM Incontro i WHERE i.golAway = :golAway"),
    @NamedQuery(name = "Incontro.findBySegnoFinale", query = "SELECT i FROM Incontro i WHERE i.segnoFinale = :segnoFinale"),
    @NamedQuery(name = "Incontro.findByNote", query = "SELECT i FROM Incontro i WHERE i.note = :note"),
    @NamedQuery(name = "Incontro.findByDataEvento", query = "SELECT i FROM Incontro i WHERE i.dataEvento = :dataEvento"),
    @NamedQuery(name = "Incontro.findByGolHomeParziale", query = "SELECT i FROM Incontro i WHERE i.golHomeParziale = :golHomeParziale"),
    @NamedQuery(name = "Incontro.findByGolAwayParziale", query = "SELECT i FROM Incontro i WHERE i.golAwayParziale = :golAwayParziale"),
    @NamedQuery(name = "Incontro.findBySegnoParziale", query = "SELECT i FROM Incontro i WHERE i.segnoParziale = :segnoParziale"),
    @NamedQuery(name = "Incontro.findByCompetizione", query = "SELECT i FROM Incontro i WHERE i.competizione = :competizione")})
public class Incontro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IncontroPK incontroPK;
    @Column(name = "HOME_TEAM")
    private String homeTeam;
    @Column(name = "AWAY_TEAM")
    private String awayTeam;
    @Column(name = "GOL_HOME")
    private Integer golHome;
    @Column(name = "GOL_AWAY")
    private Integer golAway;
    @Column(name = "SEGNO_FINALE")
    private String segnoFinale;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "DATA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @Column(name = "GOL_HOME_PARZIALE")
    private Integer golHomeParziale;
    @Column(name = "GOL_AWAY_PARZIALE")
    private Integer golAwayParziale;
    @Column(name = "SEGNO_PARZIALE")
    private String segnoParziale;
    @Column(name = "COMPETIZIONE")
    private String competizione;

    public Incontro() {
    }

    public Incontro(IncontroPK incontroPK) {
        this.incontroPK = incontroPK;
    }

    public Incontro(int idPalinsesto, int idAvvenimento) {
        this.incontroPK = new IncontroPK(idPalinsesto, idAvvenimento);
    }

    public IncontroPK getIncontroPK() {
        return incontroPK;
    }

    public void setIncontroPK(IncontroPK incontroPK) {
        this.incontroPK = incontroPK;
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

    public Integer getGolHome() {
        return golHome;
    }

    public void setGolHome(Integer golHome) {
        this.golHome = golHome;
    }

    public Integer getGolAway() {
        return golAway;
    }

    public void setGolAway(Integer golAway) {
        this.golAway = golAway;
    }

    public String getSegnoFinale() {
        return segnoFinale;
    }

    public void setSegnoFinale(String segnoFinale) {
        this.segnoFinale = segnoFinale;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Integer getGolHomeParziale() {
        return golHomeParziale;
    }

    public void setGolHomeParziale(Integer golHomeParziale) {
        this.golHomeParziale = golHomeParziale;
    }

    public Integer getGolAwayParziale() {
        return golAwayParziale;
    }

    public void setGolAwayParziale(Integer golAwayParziale) {
        this.golAwayParziale = golAwayParziale;
    }

    public String getSegnoParziale() {
        return segnoParziale;
    }

    public void setSegnoParziale(String segnoParziale) {
        this.segnoParziale = segnoParziale;
    }

    public String getCompetizione() {
        return competizione;
    }

    public void setCompetizione(String competizione) {
        this.competizione = competizione;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incontroPK != null ? incontroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incontro)) {
            return false;
        }
        Incontro other = (Incontro) object;
        if ((this.incontroPK == null && other.incontroPK != null) || (this.incontroPK != null && !this.incontroPK.equals(other.incontroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Incontro[ incontroPK=" + incontroPK + " ]";
    }
    
}

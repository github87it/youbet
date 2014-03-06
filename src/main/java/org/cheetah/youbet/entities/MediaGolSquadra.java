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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edoardo
 */
@Entity
@Table(name = "MEDIA_GOL_SQUADRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MediaGolSquadra.findAll", query = "SELECT m FROM MediaGolSquadra m"),
    @NamedQuery(name = "MediaGolSquadra.findBySquadra", query = "SELECT m FROM MediaGolSquadra m WHERE m.mediaGolSquadraPK.squadra = :squadra"),
    @NamedQuery(name = "MediaGolSquadra.findByCompetizione", query = "SELECT m FROM MediaGolSquadra m WHERE m.mediaGolSquadraPK.competizione = :competizione"),
    @NamedQuery(name = "MediaGolSquadra.findByCampo", query = "SELECT m FROM MediaGolSquadra m WHERE m.mediaGolSquadraPK.campo = :campo"),
    @NamedQuery(name = "MediaGolSquadra.findByMediaGol", query = "SELECT m FROM MediaGolSquadra m WHERE m.mediaGol = :mediaGol"),
    @NamedQuery(name = "MediaGolSquadra.findByTotalePartite", query = "SELECT m FROM MediaGolSquadra m WHERE m.totalePartite = :totalePartite"),
    @NamedQuery(name = "MediaGolSquadra.findByTotaleGol", query = "SELECT m FROM MediaGolSquadra m WHERE m.totaleGol = :totaleGol")})
public class MediaGolSquadra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MediaGolSquadraPK mediaGolSquadraPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MEDIA_GOL")
    private Double mediaGol;
    @Column(name = "TOTALE_PARTITE")
    private Integer totalePartite;
    @Column(name = "TOTALE_GOL")
    private Integer totaleGol;
    @JoinColumn(name = "CAMPO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Campo campo1;

    public MediaGolSquadra() {
    }

    public MediaGolSquadra(MediaGolSquadraPK mediaGolSquadraPK) {
        this.mediaGolSquadraPK = mediaGolSquadraPK;
    }

    public MediaGolSquadra(String squadra, String competizione, int campo) {
        this.mediaGolSquadraPK = new MediaGolSquadraPK(squadra, competizione, campo);
    }

    public MediaGolSquadraPK getMediaGolSquadraPK() {
        return mediaGolSquadraPK;
    }

    public void setMediaGolSquadraPK(MediaGolSquadraPK mediaGolSquadraPK) {
        this.mediaGolSquadraPK = mediaGolSquadraPK;
    }

    public Double getMediaGol() {
        return mediaGol;
    }

    public void setMediaGol(Double mediaGol) {
        this.mediaGol = mediaGol;
    }

    public Integer getTotalePartite() {
        return totalePartite;
    }

    public void setTotalePartite(Integer totalePartite) {
        this.totalePartite = totalePartite;
    }

    public Integer getTotaleGol() {
        return totaleGol;
    }

    public void setTotaleGol(Integer totaleGol) {
        this.totaleGol = totaleGol;
    }

    public Campo getCampo1() {
        return campo1;
    }

    public void setCampo1(Campo campo1) {
        this.campo1 = campo1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mediaGolSquadraPK != null ? mediaGolSquadraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediaGolSquadra)) {
            return false;
        }
        MediaGolSquadra other = (MediaGolSquadra) object;
        if ((this.mediaGolSquadraPK == null && other.mediaGolSquadraPK != null) || (this.mediaGolSquadraPK != null && !this.mediaGolSquadraPK.equals(other.mediaGolSquadraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.MediaGolSquadra[ mediaGolSquadraPK=" + mediaGolSquadraPK + " ]";
    }
    
}

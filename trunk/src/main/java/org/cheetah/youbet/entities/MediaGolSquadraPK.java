/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author edoardo
 */
@Embeddable
public class MediaGolSquadraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "SQUADRA")
    private String squadra;
    @Basic(optional = false)
    @Column(name = "COMPETIZIONE")
    private String competizione;
    @Basic(optional = false)
    @Column(name = "CAMPO")
    private int campo;

    public MediaGolSquadraPK() {
    }

    public MediaGolSquadraPK(String squadra, String competizione, int campo) {
        this.squadra = squadra;
        this.competizione = competizione;
        this.campo = campo;
    }

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }

    public String getCompetizione() {
        return competizione;
    }

    public void setCompetizione(String competizione) {
        this.competizione = competizione;
    }

    public int getCampo() {
        return campo;
    }

    public void setCampo(int campo) {
        this.campo = campo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (squadra != null ? squadra.hashCode() : 0);
        hash += (competizione != null ? competizione.hashCode() : 0);
        hash += (int) campo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediaGolSquadraPK)) {
            return false;
        }
        MediaGolSquadraPK other = (MediaGolSquadraPK) object;
        if ((this.squadra == null && other.squadra != null) || (this.squadra != null && !this.squadra.equals(other.squadra))) {
            return false;
        }
        if ((this.competizione == null && other.competizione != null) || (this.competizione != null && !this.competizione.equals(other.competizione))) {
            return false;
        }
        if (this.campo != other.campo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.MediaGolSquadraPK[ squadra=" + squadra + ", competizione=" + competizione + ", campo=" + campo + " ]";
    }
    
}

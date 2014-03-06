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
public class PoissonPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PALINSESTO")
    private int idPalinsesto;
    @Basic(optional = false)
    @Column(name = "ID_AVVENIMENTO")
    private int idAvvenimento;
    @Basic(optional = false)
    @Column(name = "GOL_HOME")
    private int golHome;
    @Basic(optional = false)
    @Column(name = "GOL_AWAY")
    private int golAway;

    public PoissonPK() {
    }

    public PoissonPK(int idPalinsesto, int idAvvenimento, int golHome, int golAway) {
        this.idPalinsesto = idPalinsesto;
        this.idAvvenimento = idAvvenimento;
        this.golHome = golHome;
        this.golAway = golAway;
    }

    public int getIdPalinsesto() {
        return idPalinsesto;
    }

    public void setIdPalinsesto(int idPalinsesto) {
        this.idPalinsesto = idPalinsesto;
    }

    public int getIdAvvenimento() {
        return idAvvenimento;
    }

    public void setIdAvvenimento(int idAvvenimento) {
        this.idAvvenimento = idAvvenimento;
    }

    public int getGolHome() {
        return golHome;
    }

    public void setGolHome(int golHome) {
        this.golHome = golHome;
    }

    public int getGolAway() {
        return golAway;
    }

    public void setGolAway(int golAway) {
        this.golAway = golAway;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPalinsesto;
        hash += (int) idAvvenimento;
        hash += (int) golHome;
        hash += (int) golAway;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoissonPK)) {
            return false;
        }
        PoissonPK other = (PoissonPK) object;
        if (this.idPalinsesto != other.idPalinsesto) {
            return false;
        }
        if (this.idAvvenimento != other.idAvvenimento) {
            return false;
        }
        if (this.golHome != other.golHome) {
            return false;
        }
        if (this.golAway != other.golAway) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.PoissonPK[ idPalinsesto=" + idPalinsesto + ", idAvvenimento=" + idAvvenimento + ", golHome=" + golHome + ", golAway=" + golAway + " ]";
    }
    
}

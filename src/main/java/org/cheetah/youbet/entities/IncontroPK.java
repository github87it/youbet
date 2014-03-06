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
public class IncontroPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PALINSESTO")
    private int idPalinsesto;
    @Basic(optional = false)
    @Column(name = "ID_AVVENIMENTO")
    private int idAvvenimento;

    public IncontroPK() {
    }

    public IncontroPK(int idPalinsesto, int idAvvenimento) {
        this.idPalinsesto = idPalinsesto;
        this.idAvvenimento = idAvvenimento;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPalinsesto;
        hash += (int) idAvvenimento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncontroPK)) {
            return false;
        }
        IncontroPK other = (IncontroPK) object;
        if (this.idPalinsesto != other.idPalinsesto) {
            return false;
        }
        if (this.idAvvenimento != other.idAvvenimento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.IncontroPK[ idPalinsesto=" + idPalinsesto + ", idAvvenimento=" + idAvvenimento + " ]";
    }
    
}

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
public class MetodoDivisorePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PALINSESTO")
    private int idPalinsesto;
    @Basic(optional = false)
    @Column(name = "AVVENIMENTO")
    private String avvenimento;

    public MetodoDivisorePK() {
    }

    public MetodoDivisorePK(int idPalinsesto, String avvenimento) {
        this.idPalinsesto = idPalinsesto;
        this.avvenimento = avvenimento;
    }

    public int getIdPalinsesto() {
        return idPalinsesto;
    }

    public void setIdPalinsesto(int idPalinsesto) {
        this.idPalinsesto = idPalinsesto;
    }

    public String getAvvenimento() {
        return avvenimento;
    }

    public void setAvvenimento(String avvenimento) {
        this.avvenimento = avvenimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPalinsesto;
        hash += (avvenimento != null ? avvenimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoDivisorePK)) {
            return false;
        }
        MetodoDivisorePK other = (MetodoDivisorePK) object;
        if (this.idPalinsesto != other.idPalinsesto) {
            return false;
        }
        if ((this.avvenimento == null && other.avvenimento != null) || (this.avvenimento != null && !this.avvenimento.equals(other.avvenimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.MetodoDivisorePK[ idPalinsesto=" + idPalinsesto + ", avvenimento=" + avvenimento + " ]";
    }
    
}

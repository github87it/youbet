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
public class MyQuotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PALINSESTO")
    private int idPalinsesto;
    @Basic(optional = false)
    @Column(name = "ID_AVVENIMENTO")
    private int idAvvenimento;

    public MyQuotePK() {
    }

    public MyQuotePK(int idPalinsesto, int idAvvenimento) {
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
        if (!(object instanceof MyQuotePK)) {
            return false;
        }
        MyQuotePK other = (MyQuotePK) object;
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
        return "org.cheetah.youbet.entities.MyQuotePK[ idPalinsesto=" + idPalinsesto + ", idAvvenimento=" + idAvvenimento + " ]";
    }
    
}

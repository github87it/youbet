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
public class QuotaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PALINSESTO")
    private int idPalinsesto;
    @Basic(optional = false)
    @Column(name = "ID_AVVENIMENTO")
    private int idAvvenimento;
    @Basic(optional = false)
    @Column(name = "ID_ESITO")
    private int idEsito;
    @Basic(optional = false)
    @Column(name = "ID_CLASSE")
    private int idClasse;

    public QuotaPK() {
    }

    public QuotaPK(int idPalinsesto, int idAvvenimento, int idEsito, int idClasse) {
        this.idPalinsesto = idPalinsesto;
        this.idAvvenimento = idAvvenimento;
        this.idEsito = idEsito;
        this.idClasse = idClasse;
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

    public int getIdEsito() {
        return idEsito;
    }

    public void setIdEsito(int idEsito) {
        this.idEsito = idEsito;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPalinsesto;
        hash += (int) idAvvenimento;
        hash += (int) idEsito;
        hash += (int) idClasse;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuotaPK)) {
            return false;
        }
        QuotaPK other = (QuotaPK) object;
        if (this.idPalinsesto != other.idPalinsesto) {
            return false;
        }
        if (this.idAvvenimento != other.idAvvenimento) {
            return false;
        }
        if (this.idEsito != other.idEsito) {
            return false;
        }
        if (this.idClasse != other.idClasse) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.QuotaPK[ idPalinsesto=" + idPalinsesto + ", idAvvenimento=" + idAvvenimento + ", idEsito=" + idEsito + ", idClasse=" + idClasse + " ]";
    }
    
}

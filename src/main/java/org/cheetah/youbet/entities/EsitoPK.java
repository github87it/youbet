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
public class EsitoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_ESITO")
    private int idEsito;
    @Basic(optional = false)
    @Column(name = "ID_CLASSE")
    private int idClasse;

    public EsitoPK() {
    }

    public EsitoPK(int idEsito, int idClasse) {
        this.idEsito = idEsito;
        this.idClasse = idClasse;
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
        hash += (int) idEsito;
        hash += (int) idClasse;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsitoPK)) {
            return false;
        }
        EsitoPK other = (EsitoPK) object;
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
        return "org.cheetah.youbet.entities.EsitoPK[ idEsito=" + idEsito + ", idClasse=" + idClasse + " ]";
    }
    
}

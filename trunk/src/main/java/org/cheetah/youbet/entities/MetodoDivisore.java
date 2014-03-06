/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "METODO_DIVISORE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MetodoDivisore.findAll", query = "SELECT m FROM MetodoDivisore m"),
    @NamedQuery(name = "MetodoDivisore.findByIdPalinsesto", query = "SELECT m FROM MetodoDivisore m WHERE m.metodoDivisorePK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "MetodoDivisore.findByAvvenimento", query = "SELECT m FROM MetodoDivisore m WHERE m.metodoDivisorePK.avvenimento = :avvenimento"),
    @NamedQuery(name = "MetodoDivisore.findByPronostico", query = "SELECT m FROM MetodoDivisore m WHERE m.pronostico = :pronostico"),
    @NamedQuery(name = "MetodoDivisore.findByDivisione", query = "SELECT m FROM MetodoDivisore m WHERE m.divisione = :divisione"),
    @NamedQuery(name = "MetodoDivisore.findByRisultato", query = "SELECT m FROM MetodoDivisore m WHERE m.risultato = :risultato")})
public class MetodoDivisore implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MetodoDivisorePK metodoDivisorePK;
    @Basic(optional = false)
    @Column(name = "PRONOSTICO")
    private String pronostico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DIVISIONE")
    private Double divisione;
    @Column(name = "RISULTATO")
    private String risultato;

    public MetodoDivisore() {
    }

    public MetodoDivisore(MetodoDivisorePK metodoDivisorePK) {
        this.metodoDivisorePK = metodoDivisorePK;
    }

    public MetodoDivisore(MetodoDivisorePK metodoDivisorePK, String pronostico) {
        this.metodoDivisorePK = metodoDivisorePK;
        this.pronostico = pronostico;
    }

    public MetodoDivisore(int idPalinsesto, String avvenimento) {
        this.metodoDivisorePK = new MetodoDivisorePK(idPalinsesto, avvenimento);
    }

    public MetodoDivisorePK getMetodoDivisorePK() {
        return metodoDivisorePK;
    }

    public void setMetodoDivisorePK(MetodoDivisorePK metodoDivisorePK) {
        this.metodoDivisorePK = metodoDivisorePK;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public Double getDivisione() {
        return divisione;
    }

    public void setDivisione(Double divisione) {
        this.divisione = divisione;
    }

    public String getRisultato() {
        return risultato;
    }

    public void setRisultato(String risultato) {
        this.risultato = risultato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metodoDivisorePK != null ? metodoDivisorePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoDivisore)) {
            return false;
        }
        MetodoDivisore other = (MetodoDivisore) object;
        if ((this.metodoDivisorePK == null && other.metodoDivisorePK != null) || (this.metodoDivisorePK != null && !this.metodoDivisorePK.equals(other.metodoDivisorePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.MetodoDivisore[ metodoDivisorePK=" + metodoDivisorePK + " ]";
    }
    
}

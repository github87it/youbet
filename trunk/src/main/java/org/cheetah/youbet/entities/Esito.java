/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author edoardo
 */
@Entity
@Table(name = "ESITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Esito.findAll", query = "SELECT e FROM Esito e"),
    @NamedQuery(name = "Esito.findByIdEsito", query = "SELECT e FROM Esito e WHERE e.esitoPK.idEsito = :idEsito"),
    @NamedQuery(name = "Esito.findByIdClasse", query = "SELECT e FROM Esito e WHERE e.esitoPK.idClasse = :idClasse"),
    @NamedQuery(name = "Esito.findByDescesito", query = "SELECT e FROM Esito e WHERE e.descesito = :descesito")})
public class Esito implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EsitoPK esitoPK;
    @Column(name = "DESCESITO")
    private String descesito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esito")
    private Collection<Quota> quotaCollection;
    @JoinColumn(name = "ID_CLASSE", referencedColumnName = "ID_CLASSE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classe classe;

    public Esito() {
    }

    public Esito(EsitoPK esitoPK) {
        this.esitoPK = esitoPK;
    }

    public Esito(int idEsito, int idClasse) {
        this.esitoPK = new EsitoPK(idEsito, idClasse);
    }

    public EsitoPK getEsitoPK() {
        return esitoPK;
    }

    public void setEsitoPK(EsitoPK esitoPK) {
        this.esitoPK = esitoPK;
    }

    public String getDescesito() {
        return descesito;
    }

    public void setDescesito(String descesito) {
        this.descesito = descesito;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Quota> getQuotaCollection() {
        return quotaCollection;
    }

    public void setQuotaCollection(Collection<Quota> quotaCollection) {
        this.quotaCollection = quotaCollection;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esitoPK != null ? esitoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Esito)) {
            return false;
        }
        Esito other = (Esito) object;
        if ((this.esitoPK == null && other.esitoPK != null) || (this.esitoPK != null && !this.esitoPK.equals(other.esitoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Esito[ esitoPK=" + esitoPK + " ]";
    }
    
}

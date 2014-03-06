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
import javax.persistence.JoinColumns;
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
@Table(name = "QUOTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quota.findAll", query = "SELECT q FROM Quota q"),
    @NamedQuery(name = "Quota.findByIdPalinsesto", query = "SELECT q FROM Quota q WHERE q.quotaPK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "Quota.findByIdAvvenimento", query = "SELECT q FROM Quota q WHERE q.quotaPK.idAvvenimento = :idAvvenimento"),
    @NamedQuery(name = "Quota.findByQuota", query = "SELECT q FROM Quota q WHERE q.quota = :quota"),
    @NamedQuery(name = "Quota.findByIdEsito", query = "SELECT q FROM Quota q WHERE q.quotaPK.idEsito = :idEsito"),
    @NamedQuery(name = "Quota.findByIdClasse", query = "SELECT q FROM Quota q WHERE q.quotaPK.idClasse = :idClasse")})
public class Quota implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QuotaPK quotaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUOTA")
    private Double quota;
    @JoinColumns({
        @JoinColumn(name = "ID_PALINSESTO", referencedColumnName = "ID_PALINSESTO", insertable = false, updatable = false),
        @JoinColumn(name = "ID_AVVENIMENTO", referencedColumnName = "ID_AVVENIMENTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Palinsesto palinsesto;
    @JoinColumns({
        @JoinColumn(name = "ID_ESITO", referencedColumnName = "ID_ESITO", insertable = false, updatable = false),
        @JoinColumn(name = "ID_CLASSE", referencedColumnName = "ID_CLASSE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Esito esito;

    public Quota() {
    }

    public Quota(QuotaPK quotaPK) {
        this.quotaPK = quotaPK;
    }

    public Quota(int idPalinsesto, int idAvvenimento, int idEsito, int idClasse) {
        this.quotaPK = new QuotaPK(idPalinsesto, idAvvenimento, idEsito, idClasse);
    }

    public QuotaPK getQuotaPK() {
        return quotaPK;
    }

    public void setQuotaPK(QuotaPK quotaPK) {
        this.quotaPK = quotaPK;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Palinsesto getPalinsesto() {
        return palinsesto;
    }

    public void setPalinsesto(Palinsesto palinsesto) {
        this.palinsesto = palinsesto;
    }

    public Esito getEsito() {
        return esito;
    }

    public void setEsito(Esito esito) {
        this.esito = esito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quotaPK != null ? quotaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quota)) {
            return false;
        }
        Quota other = (Quota) object;
        if ((this.quotaPK == null && other.quotaPK != null) || (this.quotaPK != null && !this.quotaPK.equals(other.quotaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Quota[ quotaPK=" + quotaPK + " ]";
    }
    
}

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edoardo
 */
@Entity
@Table(name = "POISSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poisson.findAll", query = "SELECT p FROM Poisson p"),
    @NamedQuery(name = "Poisson.findByIdPalinsesto", query = "SELECT p FROM Poisson p WHERE p.poissonPK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "Poisson.findByIdAvvenimento", query = "SELECT p FROM Poisson p WHERE p.poissonPK.idAvvenimento = :idAvvenimento"),
    @NamedQuery(name = "Poisson.findByGolHome", query = "SELECT p FROM Poisson p WHERE p.poissonPK.golHome = :golHome"),
    @NamedQuery(name = "Poisson.findByGolAway", query = "SELECT p FROM Poisson p WHERE p.poissonPK.golAway = :golAway"),
    @NamedQuery(name = "Poisson.findByPercentuale", query = "SELECT p FROM Poisson p WHERE p.percentuale = :percentuale")})
public class Poisson implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PoissonPK poissonPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PERCENTUALE")
    private Double percentuale;

    public Poisson() {
    }

    public Poisson(PoissonPK poissonPK) {
        this.poissonPK = poissonPK;
    }

    public Poisson(int idPalinsesto, int idAvvenimento, int golHome, int golAway) {
        this.poissonPK = new PoissonPK(idPalinsesto, idAvvenimento, golHome, golAway);
    }

    public PoissonPK getPoissonPK() {
        return poissonPK;
    }

    public void setPoissonPK(PoissonPK poissonPK) {
        this.poissonPK = poissonPK;
    }

    public Double getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(Double percentuale) {
        this.percentuale = percentuale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poissonPK != null ? poissonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poisson)) {
            return false;
        }
        Poisson other = (Poisson) object;
        if ((this.poissonPK == null && other.poissonPK != null) || (this.poissonPK != null && !this.poissonPK.equals(other.poissonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Poisson[ poissonPK=" + poissonPK + " ]";
    }
    
}

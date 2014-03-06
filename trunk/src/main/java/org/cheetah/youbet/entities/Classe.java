/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "CLASSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classe.findAll", query = "SELECT c FROM Classe c"),
    @NamedQuery(name = "Classe.findByIdClasse", query = "SELECT c FROM Classe c WHERE c.idClasse = :idClasse"),
    @NamedQuery(name = "Classe.findByDescrizione", query = "SELECT c FROM Classe c WHERE c.descrizione = :descrizione")})
public class Classe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLASSE")
    private Integer idClasse;
    @Column(name = "DESCRIZIONE")
    private String descrizione;
    @ManyToMany(mappedBy = "classeCollection")
    private Collection<Manifestazione> manifestazioneCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classe")
    private Collection<Esito> esitoCollection;

    public Classe() {
    }

    public Classe(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Manifestazione> getManifestazioneCollection() {
        return manifestazioneCollection;
    }

    public void setManifestazioneCollection(Collection<Manifestazione> manifestazioneCollection) {
        this.manifestazioneCollection = manifestazioneCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Esito> getEsitoCollection() {
        return esitoCollection;
    }

    public void setEsitoCollection(Collection<Esito> esitoCollection) {
        this.esitoCollection = esitoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasse != null ? idClasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classe)) {
            return false;
        }
        Classe other = (Classe) object;
        if ((this.idClasse == null && other.idClasse != null) || (this.idClasse != null && !this.idClasse.equals(other.idClasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Classe[ idClasse=" + idClasse + " ]";
    }
    
}

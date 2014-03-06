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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CAMPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campo.findAll", query = "SELECT c FROM Campo c"),
    @NamedQuery(name = "Campo.findById", query = "SELECT c FROM Campo c WHERE c.id = :id"),
    @NamedQuery(name = "Campo.findByCodiceCampo", query = "SELECT c FROM Campo c WHERE c.codiceCampo = :codiceCampo"),
    @NamedQuery(name = "Campo.findByDescrizione", query = "SELECT c FROM Campo c WHERE c.descrizione = :descrizione")})
public class Campo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODICE_CAMPO")
    private String codiceCampo;
    @Column(name = "DESCRIZIONE")
    private String descrizione;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campo1")
    private Collection<MediaGolSquadra> mediaGolSquadraCollection;

    public Campo() {
    }

    public Campo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodiceCampo() {
        return codiceCampo;
    }

    public void setCodiceCampo(String codiceCampo) {
        this.codiceCampo = codiceCampo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<MediaGolSquadra> getMediaGolSquadraCollection() {
        return mediaGolSquadraCollection;
    }

    public void setMediaGolSquadraCollection(Collection<MediaGolSquadra> mediaGolSquadraCollection) {
        this.mediaGolSquadraCollection = mediaGolSquadraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campo)) {
            return false;
        }
        Campo other = (Campo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Campo[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "MANIFESTAZIONE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manifestazione.findAll", query = "SELECT m FROM Manifestazione m"),
    @NamedQuery(name = "Manifestazione.findByIdManifestazione", query = "SELECT m FROM Manifestazione m WHERE m.idManifestazione = :idManifestazione"),
    @NamedQuery(name = "Manifestazione.findByDescrizione", query = "SELECT m FROM Manifestazione m WHERE m.descrizione = :descrizione")})
public class Manifestazione implements Serializable {
    @Column(name = "DESCRIZIONE_LUNGA")
    private String descrizioneLunga;
    @OneToMany(mappedBy = "idManifestazione")
    private Collection<Palinsesto> palinsestoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MANIFESTAZIONE")
    private Integer idManifestazione;
    @Column(name = "DESCRIZIONE")
    private String descrizione;
    @JoinTable(name = "MANIFESTAZIONE_CLASSE", joinColumns = {
        @JoinColumn(name = "ID_MANIFESTAZIONE", referencedColumnName = "ID_MANIFESTAZIONE")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_CLASSE", referencedColumnName = "ID_CLASSE")})
    @ManyToMany
    private Collection<Classe> classeCollection;
    @JoinColumn(name = "ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA")
    @ManyToOne
    private Disciplina idDisciplina;

    public Manifestazione() {
    }

    public Manifestazione(Integer idManifestazione) {
        this.idManifestazione = idManifestazione;
    }

    public Integer getIdManifestazione() {
        return idManifestazione;
    }

    public void setIdManifestazione(Integer idManifestazione) {
        this.idManifestazione = idManifestazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Classe> getClasseCollection() {
        return classeCollection;
    }

    public void setClasseCollection(Collection<Classe> classeCollection) {
        this.classeCollection = classeCollection;
    }

    public Disciplina getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Disciplina idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idManifestazione != null ? idManifestazione.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manifestazione)) {
            return false;
        }
        Manifestazione other = (Manifestazione) object;
        if ((this.idManifestazione == null && other.idManifestazione != null) || (this.idManifestazione != null && !this.idManifestazione.equals(other.idManifestazione))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Manifestazione[ idManifestazione=" + idManifestazione + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Palinsesto> getPalinsestoCollection() {
        return palinsestoCollection;
    }

    public void setPalinsestoCollection(Collection<Palinsesto> palinsestoCollection) {
        this.palinsestoCollection = palinsestoCollection;
    }

    public String getDescrizioneLunga() {
        return descrizioneLunga;
    }

    public void setDescrizioneLunga(String descrizioneLunga) {
        this.descrizioneLunga = descrizioneLunga;
    }
    
}

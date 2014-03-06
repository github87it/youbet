/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author edoardo
 */
@Entity
@Table(name = "PALINSESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Palinsesto.findAll", query = "SELECT p FROM Palinsesto p"),
    @NamedQuery(name = "Palinsesto.findByIdPalinsesto", query = "SELECT p FROM Palinsesto p WHERE p.palinsestoPK.idPalinsesto = :idPalinsesto"),
    @NamedQuery(name = "Palinsesto.findByIdAvvenimento", query = "SELECT p FROM Palinsesto p WHERE p.palinsestoPK.idAvvenimento = :idAvvenimento"),
    @NamedQuery(name = "Palinsesto.findByDescrizione", query = "SELECT p FROM Palinsesto p WHERE p.descrizione = :descrizione"),
    @NamedQuery(name = "Palinsesto.findByHomeTeam", query = "SELECT p FROM Palinsesto p WHERE p.homeTeam = :homeTeam"),
    @NamedQuery(name = "Palinsesto.findByAwayTeam", query = "SELECT p FROM Palinsesto p WHERE p.awayTeam = :awayTeam"),
    @NamedQuery(name = "Palinsesto.findByDataEvento", query = "SELECT p FROM Palinsesto p WHERE p.dataEvento = :dataEvento"),
    @NamedQuery(name = "Palinsesto.findByOraEvento", query = "SELECT p FROM Palinsesto p WHERE p.oraEvento = :oraEvento")})
public class Palinsesto implements Serializable {
    @JoinColumn(name = "ID_MANIFESTAZIONE", referencedColumnName = "ID_MANIFESTAZIONE")
    @ManyToOne
    private Manifestazione idManifestazione;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PalinsestoPK palinsestoPK;
    @Column(name = "DESCRIZIONE")
    private String descrizione;
    @Column(name = "HOME_TEAM")
    private String homeTeam;
    @Column(name = "AWAY_TEAM")
    private String awayTeam;
    @Column(name = "DATA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @Column(name = "ORA_EVENTO")
    @Temporal(TemporalType.TIME)
    private Date oraEvento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "palinsesto")
    private Collection<Quota> quotaCollection;

    public Palinsesto() {
    }

    public Palinsesto(PalinsestoPK palinsestoPK) {
        this.palinsestoPK = palinsestoPK;
    }

    public Palinsesto(int idPalinsesto, int idAvvenimento) {
        this.palinsestoPK = new PalinsestoPK(idPalinsesto, idAvvenimento);
    }

    public PalinsestoPK getPalinsestoPK() {
        return palinsestoPK;
    }

    public void setPalinsestoPK(PalinsestoPK palinsestoPK) {
        this.palinsestoPK = palinsestoPK;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Date getOraEvento() {
        return oraEvento;
    }

    public void setOraEvento(Date oraEvento) {
        this.oraEvento = oraEvento;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Quota> getQuotaCollection() {
        return quotaCollection;
    }

    public void setQuotaCollection(Collection<Quota> quotaCollection) {
        this.quotaCollection = quotaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (palinsestoPK != null ? palinsestoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palinsesto)) {
            return false;
        }
        Palinsesto other = (Palinsesto) object;
        if ((this.palinsestoPK == null && other.palinsestoPK != null) || (this.palinsestoPK != null && !this.palinsestoPK.equals(other.palinsestoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cheetah.youbet.entities.Palinsesto[ palinsestoPK=" + palinsestoPK + " ]";
    }

    public Manifestazione getIdManifestazione() {
        return idManifestazione;
    }

    public void setIdManifestazione(Manifestazione idManifestazione) {
        this.idManifestazione = idManifestazione;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.bean.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author edoardo
 */
//@JsonAutoDetect
public class JSONQuote {
    @JsonProperty
    private int palinsesto;
    @JsonProperty
    private int avvenimento;
    @JsonProperty
    private int classe;
    @JsonProperty
    private int esito;
    @JsonProperty
    private String desavvenimento;
    @JsonProperty
    private String desclasse;
    @JsonProperty
    private String desesito;
    @JsonProperty
    private String datascad;
    
    @JsonProperty
    private String eventolive;
    @JsonProperty
    private String handicap;
    @JsonProperty
    private String legmin; 
    @JsonProperty
    private String legmax;
    @JsonProperty
    private String legmul;
    @JsonProperty
    private String blackmin;
    @JsonProperty
    private String blackmax;

    @Override
    public String toString() {
        return "JSONQuote{" + "palinsesto=" + palinsesto + ", avvenimento=" + avvenimento + ", classe=" + classe + ", esito=" + esito + ", desavvenimento=" + desavvenimento + ", desclasse=" + desclasse + ", desesito=" + desesito + ", datascad=" + datascad + '}';
    }

    public String getEventolive() {
        return eventolive;
    }

    public void setEventolive(String eventolive) {
        this.eventolive = eventolive;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getLegmin() {
        return legmin;
    }

    public void setLegmin(String legmin) {
        this.legmin = legmin;
    }

    public String getLegmax() {
        return legmax;
    }

    public void setLegmax(String legmax) {
        this.legmax = legmax;
    }

    public String getLegmul() {
        return legmul;
    }

    public void setLegmul(String legmul) {
        this.legmul = legmul;
    }

    public String getBlackmin() {
        return blackmin;
    }

    public void setBlackmin(String blackmin) {
        this.blackmin = blackmin;
    }

    public String getBlackmax() {
        return blackmax;
    }

    public void setBlackmax(String blackmax) {
        this.blackmax = blackmax;
    }
    
    

    /**
     * @return the palinsesto
     */
    public int getPalinsesto() {
        return palinsesto;
    }

    /**
     * @param palinsesto the palinsesto to set
     */
    public void setPalinsesto(int palinsesto) {
        this.palinsesto = palinsesto;
    }

    /**
     * @return the avvenimento
     */
    public int getAvvenimento() {
        return avvenimento;
    }

    /**
     * @param avvenimento the avvenimento to set
     */
    public void setAvvenimento(int avvenimento) {
        this.avvenimento = avvenimento;
    }

    /**
     * @return the classe
     */
    public int getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(int classe) {
        this.classe = classe;
    }

    /**
     * @return the esito
     */
    public int getEsito() {
        return esito;
    }

    /**
     * @param esito the esito to set
     */
    public void setEsito(int esito) {
        this.esito = esito;
    }

    /**
     * @return the desavvenimento
     */
    public String getDesavvenimento() {
        return desavvenimento;
    }

    /**
     * @param desavvenimento the desavvenimento to set
     */
    public void setDesavvenimento(String desavvenimento) {
        this.desavvenimento = desavvenimento;
    }

    /**
     * @return the desclasse
     */
    public String getDesclasse() {
        return desclasse;
    }

    /**
     * @param desclasse the desclasse to set
     */
    public void setDesclasse(String desclasse) {
        this.desclasse = desclasse;
    }

    /**
     * @return the desesito
     */
    public String getDesesito() {
        return desesito;
    }

    /**
     * @param desesito the desesito to set
     */
    public void setDesesito(String desesito) {
        this.desesito = desesito;
    }

    /**
     * @return the datascad
     */
    public String getDatascad() {
        return datascad;
    }

    /**
     * @param datascad the datascad to set
     */
    public void setDatascad(String datascad) {
        this.datascad = datascad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.palinsesto;
        hash = 59 * hash + this.avvenimento;
        hash = 59 * hash + this.classe;
        hash = 59 * hash + this.esito;
        hash = 59 * hash + (this.desavvenimento != null ? this.desavvenimento.hashCode() : 0);
        hash = 59 * hash + (this.desclasse != null ? this.desclasse.hashCode() : 0);
        hash = 59 * hash + (this.desesito != null ? this.desesito.hashCode() : 0);
        hash = 59 * hash + (this.datascad != null ? this.datascad.hashCode() : 0);
        hash = 59 * hash + (this.eventolive != null ? this.eventolive.hashCode() : 0);
        hash = 59 * hash + (this.handicap != null ? this.handicap.hashCode() : 0);
        hash = 59 * hash + (this.legmin != null ? this.legmin.hashCode() : 0);
        hash = 59 * hash + (this.legmax != null ? this.legmax.hashCode() : 0);
        hash = 59 * hash + (this.legmul != null ? this.legmul.hashCode() : 0);
        hash = 59 * hash + (this.blackmin != null ? this.blackmin.hashCode() : 0);
        hash = 59 * hash + (this.blackmax != null ? this.blackmax.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JSONQuote other = (JSONQuote) obj;
        if (this.palinsesto != other.palinsesto) {
            return false;
        }
        if (this.avvenimento != other.avvenimento) {
            return false;
        }
        if (this.classe != other.classe) {
            return false;
        }
        if (this.esito != other.esito) {
            return false;
        }
        if ((this.desavvenimento == null) ? (other.desavvenimento != null) : !this.desavvenimento.equals(other.desavvenimento)) {
            return false;
        }
        if ((this.desclasse == null) ? (other.desclasse != null) : !this.desclasse.equals(other.desclasse)) {
            return false;
        }
        if ((this.desesito == null) ? (other.desesito != null) : !this.desesito.equals(other.desesito)) {
            return false;
        }
        if ((this.datascad == null) ? (other.datascad != null) : !this.datascad.equals(other.datascad)) {
            return false;
        }
        if ((this.eventolive == null) ? (other.eventolive != null) : !this.eventolive.equals(other.eventolive)) {
            return false;
        }
        if ((this.handicap == null) ? (other.handicap != null) : !this.handicap.equals(other.handicap)) {
            return false;
        }
        if ((this.legmin == null) ? (other.legmin != null) : !this.legmin.equals(other.legmin)) {
            return false;
        }
        if ((this.legmax == null) ? (other.legmax != null) : !this.legmax.equals(other.legmax)) {
            return false;
        }
        if ((this.legmul == null) ? (other.legmul != null) : !this.legmul.equals(other.legmul)) {
            return false;
        }
        if ((this.blackmin == null) ? (other.blackmin != null) : !this.blackmin.equals(other.blackmin)) {
            return false;
        }
        if ((this.blackmax == null) ? (other.blackmax != null) : !this.blackmax.equals(other.blackmax)) {
            return false;
        }
        return true;
    }
    
    
    
   
}

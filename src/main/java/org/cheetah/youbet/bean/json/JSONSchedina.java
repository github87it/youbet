/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.bean.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author edoardo
 */
public class JSONSchedina {

    @JsonProperty
    private String sezione;
    @JsonProperty
    private int disciplina;
    @JsonProperty
    private int manifestazione;
    @JsonProperty
    private int classe;
    @JsonProperty
    private String filtro;

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    public int getManifestazione() {
        return manifestazione;
    }

    public void setManifestazione(int manifestazione) {
        this.manifestazione = manifestazione;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public String toString() {
        return "JSONSchedina{" + "sezione=" + sezione + ", disciplina=" + disciplina + ", manifestazione=" + manifestazione + ", classe=" + classe + ", filtro=" + filtro + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.sezione != null ? this.sezione.hashCode() : 0);
        hash = 53 * hash + this.disciplina;
        hash = 53 * hash + this.manifestazione;
        hash = 53 * hash + this.classe;
        hash = 53 * hash + (this.filtro != null ? this.filtro.hashCode() : 0);
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
        final JSONSchedina other = (JSONSchedina) obj;
        if ((this.sezione == null) ? (other.sezione != null) : !this.sezione.equals(other.sezione)) {
            return false;
        }
        if (this.disciplina != other.disciplina) {
            return false;
        }
        if (this.manifestazione != other.manifestazione) {
            return false;
        }
        if (this.classe != other.classe) {
            return false;
        }
        if ((this.filtro == null) ? (other.filtro != null) : !this.filtro.equals(other.filtro)) {
            return false;
        }
        return true;
    }
    
    

}

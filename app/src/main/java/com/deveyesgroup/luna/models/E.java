package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 07/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class E {

    @SerializedName("NumeroAtomico")
    @Expose
    private Integer NumeroAtomico;
    @SerializedName("Simbolo")
    @Expose
    private String Simbolo;
    @SerializedName("Elemento")
    @Expose
    private String Elemento;
    @SerializedName("Serie")
    @Expose
    private String Serie;
    @SerializedName("Gruppo")
    @Expose
    private Integer Gruppo;
    @SerializedName("Periodo")
    @Expose
    private Integer Periodo;
    @SerializedName("Serie2")
    @Expose
    private String Serie2;
    @SerializedName("Stato")
    @Expose
    private String Stato;
    @SerializedName("Elettronegativita")
    @Expose
    private Double Elettronegativita;
    @SerializedName("AffinitaElettronica")
    @Expose
    private Integer AffinitaElettronica;
    @SerializedName("EnergiaPrimaIonizzazione")
    @Expose
    private Double EnergiaPrimaIonizzazione;
    @SerializedName("ProfiloPsicologico")
    @Expose
    private Object ProfiloPsicologico;
    @SerializedName("Lingua")
    @Expose
    private String Lingua;
    @SerializedName("PesoAtomico")
    @Expose
    private Double PesoAtomico;

    /**
     *
     * @return
     * The NumeroAtomico
     */
    public Integer getNumeroAtomico() {
        return NumeroAtomico;
    }

    /**
     *
     * @param NumeroAtomico
     * The NumeroAtomico
     */
    public void setNumeroAtomico(Integer NumeroAtomico) {
        this.NumeroAtomico = NumeroAtomico;
    }

    /**
     *
     * @return
     * The Simbolo
     */
    public String getSimbolo() {
        return Simbolo;
    }

    /**
     *
     * @param Simbolo
     * The Simbolo
     */
    public void setSimbolo(String Simbolo) {
        this.Simbolo = Simbolo;
    }

    /**
     *
     * @return
     * The Elemento
     */
    public String getElemento() {
        return Elemento;
    }

    /**
     *
     * @param Elemento
     * The Elemento
     */
    public void setElemento(String Elemento) {
        this.Elemento = Elemento;
    }

    /**
     *
     * @return
     * The Serie
     */
    public String getSerie() {
        return Serie;
    }

    /**
     *
     * @param Serie
     * The Serie
     */
    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    /**
     *
     * @return
     * The Gruppo
     */
    public Integer getGruppo() {
        return Gruppo;
    }

    /**
     *
     * @param Gruppo
     * The Gruppo
     */
    public void setGruppo(Integer Gruppo) {
        this.Gruppo = Gruppo;
    }

    /**
     *
     * @return
     * The Periodo
     */
    public Integer getPeriodo() {
        return Periodo;
    }

    /**
     *
     * @param Periodo
     * The Periodo
     */
    public void setPeriodo(Integer Periodo) {
        this.Periodo = Periodo;
    }

    /**
     *
     * @return
     * The Serie2
     */
    public String getSerie2() {
        return Serie2;
    }

    /**
     *
     * @param Serie2
     * The Serie2
     */
    public void setSerie2(String Serie2) {
        this.Serie2 = Serie2;
    }

    /**
     *
     * @return
     * The Stato
     */
    public String getStato() {
        return Stato;
    }

    /**
     *
     * @param Stato
     * The Stato
     */
    public void setStato(String Stato) {
        this.Stato = Stato;
    }

    /**
     *
     * @return
     * The Elettronegativita
     */
    public Double getElettronegativita() {
        return Elettronegativita;
    }

    /**
     *
     * @param Elettronegativita
     * The Elettronegativita
     */
    public void setElettronegativita(Double Elettronegativita) {
        this.Elettronegativita = Elettronegativita;
    }

    /**
     *
     * @return
     * The AffinitaElettronica
     */
    public Integer getAffinitaElettronica() {
        return AffinitaElettronica;
    }

    /**
     *
     * @param AffinitaElettronica
     * The AffinitaElettronica
     */
    public void setAffinitaElettronica(Integer AffinitaElettronica) {
        this.AffinitaElettronica = AffinitaElettronica;
    }

    /**
     *
     * @return
     * The EnergiaPrimaIonizzazione
     */
    public Double getEnergiaPrimaIonizzazione() {
        return EnergiaPrimaIonizzazione;
    }

    /**
     *
     * @param EnergiaPrimaIonizzazione
     * The EnergiaPrimaIonizzazione
     */
    public void setEnergiaPrimaIonizzazione(Double EnergiaPrimaIonizzazione) {
        this.EnergiaPrimaIonizzazione = EnergiaPrimaIonizzazione;
    }

    /**
     *
     * @return
     * The ProfiloPsicologico
     */
    public Object getProfiloPsicologico() {
        return ProfiloPsicologico;
    }

    /**
     *
     * @param ProfiloPsicologico
     * The ProfiloPsicologico
     */
    public void setProfiloPsicologico(Object ProfiloPsicologico) {
        this.ProfiloPsicologico = ProfiloPsicologico;
    }

    /**
     *
     * @return
     * The Lingua
     */
    public String getLingua() {
        return Lingua;
    }

    /**
     *
     * @param Lingua
     * The Lingua
     */
    public void setLingua(String Lingua) {
        this.Lingua = Lingua;
    }

    /**
     *
     * @return
     * The PesoAtomico
     */
    public Double getPesoAtomico() {
        return PesoAtomico;
    }

    /**
     *
     * @param PesoAtomico
     * The PesoAtomico
     */
    public void setPesoAtomico(Double PesoAtomico) {
        this.PesoAtomico = PesoAtomico;
    }

}

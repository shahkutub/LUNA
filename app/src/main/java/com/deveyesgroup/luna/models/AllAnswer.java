package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 03/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllAnswer {

    @SerializedName("IDDomanda")
    @Expose
    private Integer IDDomanda;
    @SerializedName("ValoreRisposta")
    @Expose
    private Integer ValoreRisposta;
    @SerializedName("TestoRisposta")
    @Expose
    private String TestoRisposta;
    @SerializedName("IDRisposta")
    @Expose
    private Integer IDRisposta;
    @SerializedName("Lingua")
    @Expose
    private String Lingua;

    /**
     *
     * @return
     * The IDDomanda
     */
    public Integer getIDDomanda() {
        return IDDomanda;
    }

    /**
     *
     * @param IDDomanda
     * The IDDomanda
     */
    public void setIDDomanda(Integer IDDomanda) {
        this.IDDomanda = IDDomanda;
    }

    /**
     *
     * @return
     * The ValoreRisposta
     */
    public Integer getValoreRisposta() {
        return ValoreRisposta;
    }

    /**
     *
     * @param ValoreRisposta
     * The ValoreRisposta
     */
    public void setValoreRisposta(Integer ValoreRisposta) {
        this.ValoreRisposta = ValoreRisposta;
    }

    /**
     *
     * @return
     * The TestoRisposta
     */
    public String getTestoRisposta() {
        return TestoRisposta;
    }

    /**
     *
     * @param TestoRisposta
     * The TestoRisposta
     */
    public void setTestoRisposta(String TestoRisposta) {
        this.TestoRisposta = TestoRisposta;
    }

    /**
     *
     * @return
     * The IDRisposta
     */
    public Integer getIDRisposta() {
        return IDRisposta;
    }

    /**
     *
     * @param IDRisposta
     * The IDRisposta
     */
    public void setIDRisposta(Integer IDRisposta) {
        this.IDRisposta = IDRisposta;
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

}

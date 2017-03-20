package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 03/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Answer {

    @SerializedName("IDUtente")
    @Expose
    private Integer IDUtente;
    @SerializedName("IDDomanda")
    @Expose
    private Integer IDDomanda;
    @SerializedName("IDRisposta")
    @Expose
    private Integer IDRisposta;
    @SerializedName("Data_ora_record")
    @Expose
    private String DataOraRecord;
    @SerializedName("IDRisultato")
    @Expose
    private Integer IDRisultato;

    /**
     *
     * @return
     * The IDUtente
     */
    public Integer getIDUtente() {
        return IDUtente;
    }

    /**
     *
     * @param IDUtente
     * The IDUtente
     */
    public void setIDUtente(Integer IDUtente) {
        this.IDUtente = IDUtente;
    }

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
     * The DataOraRecord
     */
    public String getDataOraRecord() {
        return DataOraRecord;
    }

    /**
     *
     * @param DataOraRecord
     * The Data_ora_record
     */
    public void setDataOraRecord(String DataOraRecord) {
        this.DataOraRecord = DataOraRecord;
    }

    /**
     *
     * @return
     * The IDRisultato
     */
    public Integer getIDRisultato() {
        return IDRisultato;
    }

    /**
     *
     * @param IDRisultato
     * The IDRisultato
     */
    public void setIDRisultato(Integer IDRisultato) {
        this.IDRisultato = IDRisultato;
    }

}

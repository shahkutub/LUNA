package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 07/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class S {

    @SerializedName("Step")
    @Expose
    private Integer Step;
    @SerializedName("DaPunti")
    @Expose
    private Integer DaPunti;
    @SerializedName("APunti")
    @Expose
    private Integer APunti;
    @SerializedName("RedirectToStep")
    @Expose
    private Object RedirectToStep;
    @SerializedName("TestoSoluzione")
    @Expose
    private String TestoSoluzione;
    @SerializedName("IDSoluzione")
    @Expose
    private Integer IDSoluzione;
    @SerializedName("NumeroAtomico")
    @Expose
    private Integer NumeroAtomico;
    @SerializedName("Lingua")
    @Expose
    private String Lingua;
    @SerializedName("NomeGruppo")
    @Expose
    private Object NomeGruppo;

    /**
     *
     * @return
     * The Step
     */
    public Integer getStep() {
        return Step;
    }

    /**
     *
     * @param Step
     * The Step
     */
    public void setStep(Integer Step) {
        this.Step = Step;
    }

    /**
     *
     * @return
     * The DaPunti
     */
    public Integer getDaPunti() {
        return DaPunti;
    }

    /**
     *
     * @param DaPunti
     * The DaPunti
     */
    public void setDaPunti(Integer DaPunti) {
        this.DaPunti = DaPunti;
    }

    /**
     *
     * @return
     * The APunti
     */
    public Integer getAPunti() {
        return APunti;
    }

    /**
     *
     * @param APunti
     * The APunti
     */
    public void setAPunti(Integer APunti) {
        this.APunti = APunti;
    }

    /**
     *
     * @return
     * The RedirectToStep
     */
    public Object getRedirectToStep() {
        return RedirectToStep;
    }

    /**
     *
     * @param RedirectToStep
     * The RedirectToStep
     */
    public void setRedirectToStep(Object RedirectToStep) {
        this.RedirectToStep = RedirectToStep;
    }

    /**
     *
     * @return
     * The TestoSoluzione
     */
    public String getTestoSoluzione() {
        return TestoSoluzione;
    }

    /**
     *
     * @param TestoSoluzione
     * The TestoSoluzione
     */
    public void setTestoSoluzione(String TestoSoluzione) {
        this.TestoSoluzione = TestoSoluzione;
    }

    /**
     *
     * @return
     * The IDSoluzione
     */
    public Integer getIDSoluzione() {
        return IDSoluzione;
    }

    /**
     *
     * @param IDSoluzione
     * The IDSoluzione
     */
    public void setIDSoluzione(Integer IDSoluzione) {
        this.IDSoluzione = IDSoluzione;
    }

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
     * The NomeGruppo
     */
    public Object getNomeGruppo() {
        return NomeGruppo;
    }

    /**
     *
     * @param NomeGruppo
     * The NomeGruppo
     */
    public void setNomeGruppo(Object NomeGruppo) {
        this.NomeGruppo = NomeGruppo;
    }

}

package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 03/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllQuestion {

    @SerializedName("TestoDomanda")
    @Expose
    private String TestoDomanda;
    @SerializedName("Step")
    @Expose
    private Integer Step;
    @SerializedName("Form")
    @Expose
    private Integer Form;
    @SerializedName("IDDomanda")
    @Expose
    private Integer IDDomanda;
    @SerializedName("Lingua")
    @Expose
    private String Lingua;

    /**
     *
     * @return
     * The TestoDomanda
     */
    public String getTestoDomanda() {
        return TestoDomanda;
    }

    /**
     *
     * @param TestoDomanda
     * The TestoDomanda
     */
    public void setTestoDomanda(String TestoDomanda) {
        this.TestoDomanda = TestoDomanda;
    }

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
     * The Form
     */
    public Integer getForm() {
        return Form;
    }

    /**
     *
     * @param Form
     * The Form
     */
    public void setForm(Integer Form) {
        this.Form = Form;
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

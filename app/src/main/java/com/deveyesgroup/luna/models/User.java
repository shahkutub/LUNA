package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 03/11/15.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("Nome")
    @Expose
    private String Nome;
    @SerializedName("TcpIp")
    @Expose
    private String TcpIp;
    @SerializedName("Data_ora_record")
    @Expose
    private String DataOraRecord;
    @SerializedName("IDUtente")
    @Expose
    private Integer IDUtente;
    @SerializedName("Cognome")
    @Expose
    private String Cognome;
    @SerializedName("Citta")
    @Expose
    private String Citta;
    @SerializedName("Sesso")
    @Expose
    private String Sesso;
    @SerializedName("IDSoluzione")
    @Expose
    private String IDSoluzione;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("DataNascita")
    @Expose
    private String DataNascita;
    @SerializedName("Psw")
    @Expose
    private String Psw;
    @SerializedName("Perc_1")
    @Expose
    private String Perc1;
    @SerializedName("Perc_2")
    @Expose
    private String Perc2;
    @SerializedName("Perc_data")
    @Expose
    private String PercData;
    @SerializedName("Perc_3")
    @Expose
    private String Perc3;

    /**
     *
     * @return
     * The Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     *
     * @param Nome
     * The Nome
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     *
     * @return
     * The TcpIp
     */
    public String getTcpIp() {
        return TcpIp;
    }

    /**
     *
     * @param TcpIp
     * The TcpIp
     */
    public void setTcpIp(String TcpIp) {
        this.TcpIp = TcpIp;
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
     * The Cognome
     */
    public String getCognome() {
        return Cognome;
    }

    /**
     *
     * @param Cognome
     * The Cognome
     */
    public void setCognome(String Cognome) {
        this.Cognome = Cognome;
    }

    /**
     *
     * @return
     * The Citta
     */
    public String getCitta() {
        return Citta;
    }

    /**
     *
     * @param Citta
     * The Citta
     */
    public void setCitta(String Citta) {
        this.Citta = Citta;
    }

    /**
     *
     * @return
     * The Sesso
     */
    public String getSesso() {
        return Sesso;
    }

    /**
     *
     * @param Sesso
     * The Sesso
     */
    public void setSesso(String Sesso) {
        this.Sesso = Sesso;
    }

    /**
     *
     * @return
     * The IDSoluzione
     */
    public String getIDSoluzione() {
        return IDSoluzione;
    }

    /**
     *
     * @param IDSoluzione
     * The IDSoluzione
     */
    public void setIDSoluzione(String IDSoluzione) {
        this.IDSoluzione = IDSoluzione;
    }

    /**
     *
     * @return
     * The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email
     * The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     *
     * @return
     * The DataNascita
     */
    public String getDataNascita() {
        return DataNascita;
    }

    /**
     *
     * @param DataNascita
     * The DataNascita
     */
    public void setDataNascita(String DataNascita) {
        this.DataNascita = DataNascita;
    }

    /**
     *
     * @return
     * The Psw
     */
    public String getPsw() {
        return Psw;
    }

    /**
     *
     * @param Psw
     * The Psw
     */
    public void setPsw(String Psw) {
        this.Psw = Psw;
    }

    /**
     *
     * @return
     * The Perc1
     */
    public String getPerc1() {
        return Perc1;
    }

    /**
     *
     * @param Perc1
     * The Perc_1
     */
    public void setPerc1(String Perc1) {
        this.Perc1 = Perc1;
    }

    /**
     *
     * @return
     * The Perc2
     */
    public String getPerc2() {
        return Perc2;
    }

    /**
     *
     * @param Perc2
     * The Perc_2
     */
    public void setPerc2(String Perc2) {
        this.Perc2 = Perc2;
    }

    /**
     *
     * @return
     * The PercData
     */
    public String getPercData() {
        return PercData;
    }

    /**
     *
     * @param PercData
     * The Perc_data
     */
    public void setPercData(String PercData) {
        this.PercData = PercData;
    }

    /**
     *
     * @return
     * The Perc3
     */
    public String getPerc3() {
        return Perc3;
    }

    /**
     *
     * @param Perc3
     * The Perc_3
     */
    public void setPerc3(String Perc3) {
        this.Perc3 = Perc3;
    }

}

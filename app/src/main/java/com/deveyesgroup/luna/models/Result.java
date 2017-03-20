package com.deveyesgroup.luna.models;

/**
 * Created by oneenam on 07/11/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("s")
    @Expose
    private S s;
    @SerializedName("e")
    @Expose
    private E e;

    /**
     *
     * @return
     * The s
     */
    public S getS() {
        return s;
    }

    /**
     *
     * @param s
     * The s
     */
    public void setS(S s) {
        this.s = s;
    }

    /**
     *
     * @return
     * The e
     */
    public E getE() {
        return e;
    }

    /**
     *
     * @param e
     * The e
     */
    public void setE(E e) {
        this.e = e;
    }

}

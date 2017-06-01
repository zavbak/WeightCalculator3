package ru.a799000.android.weightcalculator3.repository.tovarfile.intities.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.load.IntitiesTovarLoad;

/**
 * Created by Alex on 30.05.2017.
 */

public class IntitiesTovarSend {

    @SerializedName("Код")
    @Expose
    private String code;
    @SerializedName("Наименование")
    @Expose
    private String name;
    @SerializedName("Единица")
    @Expose
    private String ed;

    @SerializedName("id")
    @Expose
    long   id;

    @SerializedName("Штрихкод")
    @Expose
    String initBarcode;

    @SerializedName("Начало")
    @Expose
    int    start;

    @SerializedName("Конец")
    @Expose
    int    finish;

    @SerializedName("Коэффицент")
    @Expose
    float  coef;

    @SerializedName("Подсчет")
    @Expose
    private List<IntitiesBarcodeSend> mBarcodes = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInitBarcode() {
        return initBarcode;
    }

    public void setInitBarcode(String initBarcode) {
        this.initBarcode = initBarcode;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public float getCoef() {
        return coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }

    public List<IntitiesBarcodeSend> getBarcodes() {
        return mBarcodes;
    }

    public void setBarcodes(List<IntitiesBarcodeSend> barcodes) {
        mBarcodes = barcodes;
    }
}

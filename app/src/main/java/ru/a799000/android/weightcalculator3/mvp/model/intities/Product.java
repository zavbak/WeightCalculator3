package ru.a799000.android.weightcalculator3.mvp.model.intities;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {
    @PrimaryKey
    long   id;
    String code;
    String ed;
    String name;
    String initBarcode;
    int    start;
    int    finish;
    float  coef;


    private RealmList<Barcode> barcodes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public RealmList<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(RealmList<Barcode> barcodes) {
        this.barcodes = barcodes;
    }
}

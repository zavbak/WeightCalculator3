package ru.a799000.android.weightcalculator3.repository.tovarfile.intities.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 30.05.2017.
 */

public class IntitiesBarcodeSend {


    @SerializedName("Код")
    @Expose
    long id;
    @SerializedName("Штрихкод")
    @Expose
    String barcode;
    @SerializedName("Мест")
    @Expose
    int places;
    @SerializedName("Вес")
    @Expose
    float weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

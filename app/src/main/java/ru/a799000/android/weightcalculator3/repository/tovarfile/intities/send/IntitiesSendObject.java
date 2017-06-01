package ru.a799000.android.weightcalculator3.repository.tovarfile.intities.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.load.IntitiesTovarLoad;

/**
 * Created by Alex on 30.05.2017.
 */

public class IntitiesSendObject {

    @SerializedName("Номенклатура")
    @Expose
    private List<IntitiesTovarSend> tovars = null;

    @SerializedName("Дата")
    @Expose
    Date date;

    public List<IntitiesTovarSend> getTovars() {
        return tovars;
    }

    public void setTovars(List<IntitiesTovarSend> tovars) {
        this.tovars = tovars;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

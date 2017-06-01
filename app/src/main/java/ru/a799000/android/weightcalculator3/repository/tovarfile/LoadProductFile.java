package ru.a799000.android.weightcalculator3.repository.tovarfile;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.load.IntitiesLoadObject;
import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.load.IntitiesTovarLoad;
import rx.Observable;

/**
 * Created by Alex on 30.05.2017.
 */

public class LoadProductFile {

    public Observable<IntitiesTovarLoad> readStringObservable(String fileName) {

        String fullString = "";


        String state = Environment.getExternalStorageState();
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            return Observable.error(new Throwable("Нет доступа для чтения и записи"));
        }


        File sdPath = Environment.getExternalStorageDirectory();


        sdPath = new File(sdPath.getAbsolutePath());

        File sdFile = new File(sdPath, fileName);
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";


            // читаем содержимое
            while ((str = br.readLine()) != null) {
                fullString = fullString + "\n" +str;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Observable.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            return Observable.error(e);
        }




        return Observable.just(fullString)
                .map(s -> {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    return gson.fromJson(s, IntitiesLoadObject.class);
                })
                .flatMap(intitiesLoadObject -> {
                    return Observable.from(intitiesLoadObject.getTovars());
                });



    }
}

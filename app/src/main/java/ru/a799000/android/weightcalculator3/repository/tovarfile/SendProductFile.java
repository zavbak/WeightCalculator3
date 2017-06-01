package ru.a799000.android.weightcalculator3.repository.tovarfile;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IProductRepository;
import ru.a799000.android.weightcalculator3.repository.realm.repository.impl.ProductRepository;
import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.send.IntitiesBarcodeSend;
import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.send.IntitiesSendObject;
import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.send.IntitiesTovarSend;
import rx.Observable;

/**
 * Created by Alex on 31.05.2017.
 */

public class SendProductFile {
    @Inject
    IProductRepository mProductRepository;


    RealmResults<Product> listProduct;

    public SendProductFile() {
        App.getAppComponent().injectSendProductFile(this);
    }


    IntitiesBarcodeSend getIntitiesBarcodeSend(Barcode barcode){
        IntitiesBarcodeSend barcodeSend = new IntitiesBarcodeSend();
        barcodeSend.setId(barcode.getId());
        barcodeSend.setPlaces(barcode.getPlaces());
        barcodeSend.setWeight(barcode.getWeight());
        barcodeSend.setBarcode(barcode.getBarcode());

        return barcodeSend;

    }


    IntitiesTovarSend getTovarSend(Product product){

        IntitiesTovarSend tovarSend = new IntitiesTovarSend();
        tovarSend.setName(product.getName());
        tovarSend.setCode(product.getCode());
        tovarSend.setCoef(product.getCoef());
        tovarSend.setStart(product.getStart());
        tovarSend.setFinish(product.getFinish());
        tovarSend.setEd(product.getEd());
        tovarSend.setId(product.getId());
        tovarSend.setInitBarcode(product.getInitBarcode());

        RealmList<Barcode> barcodes = product.getBarcodes();
        List<IntitiesBarcodeSend> listBar = new ArrayList<IntitiesBarcodeSend>();

        for (Barcode barcode:barcodes){
            listBar.add(getIntitiesBarcodeSend(barcode));
        }

        tovarSend.setBarcodes(listBar);
        return tovarSend;

    }

    public String getIntitiesSendObject(){
        mProductRepository.getAllProduct(new IProductRepository.OnGetAllProductCallback() {
            @Override
            public void onSuccess(RealmResults<Product> products) {
                listProduct = products;
            }

            @Override
            public void onError(String message) {

            }
        });



        List<IntitiesTovarSend> listTovar = new ArrayList<>();

        for(Product product: listProduct){
            listTovar.add(getTovarSend(product));
        }


        IntitiesSendObject sendObject = new IntitiesSendObject();
        sendObject.setDate(new Date());
        sendObject.setTovars(listTovar);

        return new Gson().toJson(sendObject);
    }


    public void SaveFile (String filePath, String FileContent) throws IOException {
        //Создание объекта файла.
        File fhandle = new File(filePath);

            //Если нет директорий в пути, то они будут созданы:
            if (!fhandle.getParentFile().exists())
                fhandle.getParentFile().mkdirs();
            //Если файл существует, то он будет перезаписан:
            fhandle.createNewFile();

            FileOutputStream fOut = new FileOutputStream(fhandle);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.write(FileContent);
            myOutWriter.close();
            fOut.close();

    }

    public Observable<Boolean> save(String datStr, String fileName){

        String state = Environment.getExternalStorageState();
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Observable.error(new Throwable("Нет доступа для чтения и записи"));
        }



        try {
            File sdPath = Environment.getExternalStorageDirectory();


            sdPath = new File(sdPath.getAbsolutePath());

            File sdFile = new File(sdPath, fileName);




            SaveFile (sdFile.getAbsolutePath(), datStr);
        } catch (IOException e) {
            e.printStackTrace();
            return Observable.error(e);
        }


        return Observable.just(true);
    }


}

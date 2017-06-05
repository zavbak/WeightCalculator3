package ru.a799000.android.weightcalculator3.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.barcode.GetAllBarcodeInteracor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.mvp.view.ListBarcodeActivityView;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IBarcodeRepository;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IProductRepository;
import rx.Subscriber;


/**
 * Created by Alex on 23.05.2017.
 */

@InjectViewState
public class ListBarcodeActivityPresenter extends MvpPresenter<ListBarcodeActivityView>{

    Product mProduct;
    RealmList<Barcode> mBarcodes;

    @Inject
    IProductRepository mProductRepository;

    //@Inject
    //IBarcodeRepository mBarcodeRepository;



    public ListBarcodeActivityPresenter() {
        App.getAppComponent().injectBarcodesActivityPresenter(this);
    }


    private void refreshList(){
        GetAllBarcodeInteracor interacor = new GetAllBarcodeInteracor(mProduct.getId());
        interacor.execute(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                getViewState().refresh((RealmList<Barcode>) o);
            }
        });

    }


    public void onStart() {

//        if(mProduct.getId() != 0) {
//           mBarcodeRepository.getAllBarcodesByProductId(mProduct.getId(), new IBarcodeRepository.OnGetBarcodesCallback() {
//               @Override
//               public void onSuccess(RealmList<Barcode> barcodes) {
//                   mBarcodes = barcodes;
//               }
//
//               @Override
//               public void onError(String message) {
//
//               }
//           });
//        }

        refreshList();
        getViewState().registerBarcodeReceiver();
    }

    public void setProduct(String id) {

        if(id == null){
            if(mProduct == null){
                mProduct = new Product();
            }
        }else{

            mProductRepository.getProductById(Long.parseLong(id), new IProductRepository.OnGetProductByIdCallback() {
                @Override
                public void onSuccess(Product productRealm) {
                    mProduct = new Product();
                    mProduct.setId(productRealm.getId());
                    mProduct.setName(productRealm.getName());
                    mProduct.setStart(productRealm.getStart());
                    mProduct.setFinish(productRealm.getFinish());
                    mProduct.setCoef(productRealm.getCoef());
                    mProduct.setInitBarcode(productRealm.getInitBarcode());
                }

                @Override
                public void onError(String message) {

                }
            });
        }

    }

    public RealmList<Barcode> getBarcodes() {
        return mBarcodes;
    }

    public void clickItem(String id) {
        getViewState().startDetailBarcodeActivityView(Long.toString(mProduct.getId()),id,null);
    }



    public void onStop() {
        getViewState().unregisterReceiver();
    }

    public void onBarcode(String barcode) {

        getViewState().startDetailBarcodeActivityView(Long.toString(mProduct.getId()),null,barcode);
    }
}

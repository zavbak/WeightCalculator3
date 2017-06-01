package ru.a799000.android.weightcalculator3.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.mvp.view.ListProductActivityView;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IProductRepository;

/**
 * Created by Alex on 30.05.2017.
 */

@InjectViewState
public class ListProductActivityPresenter extends MvpPresenter<ListProductActivityView> {
    @Inject
    IProductRepository mProductRepository;

    RealmResults<Product> mProducts;

    public ListProductActivityPresenter() {
        App.getAppComponent().injectListProductActivityPresenter(this);
    }

    public void onStart() {
        mProductRepository.getAllProduct(new IProductRepository.OnGetAllProductCallback() {
            @Override
            public void onSuccess(RealmResults<Product> products) {
                mProducts = products;
            }

            @Override
            public void onError(String message) {

            }
        });
    }



    public RealmResults<Product> getProducts() {
        return mProducts;
    }

    public void clickItem(String id) {
        getViewState().startDetailProductActivityView(id);
    }

    public void addProdact() {
        getViewState().startDetailProductActivityView(null);
    }

    public void onStop() {
    }
}

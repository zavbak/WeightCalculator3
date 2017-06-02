package ru.a799000.android.weightcalculator3.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products.GetAllProductInteracor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products.SaveProductInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.mvp.view.ListProductActivityView;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IProductRepository;
import rx.Subscriber;



@InjectViewState
public class ListProductActivityPresenter extends MvpPresenter<ListProductActivityView> {

    public ListProductActivityPresenter() {
        App.getAppComponent().injectListProductActivityPresenter(this);
    }

    private void refreshList(){
        GetAllProductInteracor interacor = new GetAllProductInteracor();
        interacor.execute(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object products) {
                getViewState().refresh((RealmResults<Product>) products);
            }
        });
    }

    public void onStart() {
        refreshList();
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

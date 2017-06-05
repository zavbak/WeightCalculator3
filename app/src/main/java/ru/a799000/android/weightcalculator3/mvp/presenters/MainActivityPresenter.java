package ru.a799000.android.weightcalculator3.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.BarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.ProductInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.barcode.DellAllBarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.barcode.DellBarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products.DellAllProductInteracor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.mvp.view.MainActivityView;
import ru.a799000.android.weightcalculator3.repository.settings.RepoSettingsI;
import ru.a799000.android.weightcalculator3.repository.tovarfile.SendProductFile;
import ru.a799000.android.weightcalculator3.repository.tovarfile.intities.load.IntitiesTovarLoad;
import ru.a799000.android.weightcalculator3.repository.tovarfile.LoadProductFile;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    @Inject
    RepoSettingsI mRepoSettings;

    @Inject
    ProductInteractor mProductInteractor;

    @Inject
    BarcodeInteractor mBarcodeInteractor;


    public MainActivityPresenter() {
        App.getAppComponent().injectMainActivityPresenter(this);
    }

    public void StartProductActivity() {
        getViewState().startProductsActivityView();
    }

    public void StartSettingsActivity() {
        getViewState().startSettingsActivityView();
    }

    public void Load() {

        DellAllProductInteracor dellAllProductInteracor = new DellAllProductInteracor();
        dellAllProductInteracor.execute(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });


        DellAllBarcodeInteractor interactor = new DellAllBarcodeInteractor();
        interactor.execute(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });

        //mProductInteractor.dellAllProduct();

        LoadProductFile loadProductFile = new LoadProductFile();
        Observable<IntitiesTovarLoad> stringObservable = loadProductFile.readStringObservable(mRepoSettings.getSettings().mName);



        Subscription subscription = stringObservable
                .subscribeOn(Schedulers.io()) //делаем запрос, преобразование, кэширование в отдельном потоке
                .observeOn(AndroidSchedulers.mainThread()) // обработка результата - в main thread
                .doOnNext(intitiesTovar -> {
                    Product product = new Product();
                    product.setCode(intitiesTovar.getCode());
                    product.setName(intitiesTovar.getName());
                    product.setEd(intitiesTovar.getEd());
                    mProductInteractor.saveProduct(product);

                })
                .count()
                .subscribe(integer -> {

                    getViewState().showMessage("Загружено: " + integer);

                }, throwable -> {

                    getViewState().showMessage(throwable.getMessage());


                }, () -> {


                });


    }

    public void onClickStartProductActivity() {
        getViewState().startProductsActivityView();
    }

    public void Send() {
        SendProductFile sendProductFile = new SendProductFile();
        String str = sendProductFile.getIntitiesSendObject();

        Observable<Boolean> booleanObservable = sendProductFile.save(str,"send_to1c.json");

        Subscription subscription = booleanObservable
                .subscribeOn(Schedulers.io()) //делаем запрос, преобразование, кэширование в отдельном потоке
                .observeOn(AndroidSchedulers.mainThread()) // обработка результата - в main thread
                .subscribe(aBoolean -> {
                            getViewState().showMessage("Выгрузили!");
                        },
                        throwable -> {
                            getViewState().showMessage(throwable.getMessage());
                        });


    }
}

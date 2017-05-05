package it.localhost.app.mobile.learningandroid.ui.fragment;

import com.jakewharton.rxbinding2.widget.RxAdapterView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */

public class RxOperatorsCreatingFragment extends BaseFragment {

    private static final String TAG = RxOperatorsCreatingFragment.class.getSimpleName();
    private Consumer<Integer> mConsumerItemSelected;
    private Disposable mDisposableItemSelected;
    private ArrayAdapter<String> mLvArrayAdapter;

    @BindArray(R.array.rx_operators_items)
    String[] mStringsRxOperatorsItems;
    @BindView(R.id.sp_items)
    Spinner mSpinnerItems;
    @BindView(R.id.lv_items)
    ListView mLvItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        // LISTVIEW
        mLvArrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1);
        mLvItems.setAdapter(mLvArrayAdapter);

        // SPINNER
        mSpinnerItems.setAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, mStringsRxOperatorsItems));

        mConsumerItemSelected = new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                loadData(integer);
            }
        };
        mDisposableItemSelected = RxAdapterView.itemSelections(mSpinnerItems).subscribe(mConsumerItemSelected);
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();

        if (mDisposableItemSelected != null && !mDisposableItemSelected.isDisposed()) {
            Log.v(TAG, "mDisposableItemSelected.dispose()");
            mDisposableItemSelected.dispose();
        }
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_rx_operatorscreating;
    }

    /**
     * Popola la ListView
     *
     * @param integer Integer
     */
    private void loadData(@android.support.annotation.NonNull Integer integer) {
        Log.v(TAG, "" + integer);
        clearListView();

        switch (integer) {
            case 0:
                // empty
                break;
            case 1:
                // CREATE
                getObservableOperatorCreate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserverOperatorCreate());

                // DEFER - JUST
                Observable<String> observableOperatorDefer = getObservableOperatorDefer();
                Log.v(TAG, "observableOperatorDefer after getObservableOperatorDefer");
                // In questo punto l'Observable non è ancora stato creato!
                observableOperatorDefer
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserverOperatorCreate());
                Log.v(TAG, "observableOperatorDefer after subscribe");

                // INTERVAL - REPEAT
                getObservableOperatorInterval()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserverOperatorCreate());
                break;
            default:
                break;
        }
    }

    /**
     * Aggiunge un elemento alla ListView
     *
     * @param s String
     */
    private void loadListView(String s) {
        mLvArrayAdapter.add(s);
    }

    /**
     * Aggiunge una collection di elementi alla ListView
     *
     * @param list List<String>
     */
    private void loadListView(List<String> list) {
        clearListView();
        mLvArrayAdapter.addAll(list);
    }

    /**
     * Svuota la collection nella ListView
     */
    private void clearListView() {
        mLvArrayAdapter.clear();
    }

    /**
     * Restituisce un Observer
     *
     * @return Observer<String>
     */
    private Observer<String> getObserverOperatorCreate() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.v(TAG, "getObserverOperatorCreate.onSubscribe: d.isDisposed=" + d.isDisposed());
                // Azzero la ListView
//                clearListView();
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.v(TAG, "getObserverOperatorCreate.onNext");
                // Aggiungo la string ricevuta alla ListView
                loadListView(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "getObserverOperatorCreate.onError", e);
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "getObserverOperatorCreate.onComplete");
            }
        };
    }

    /**
     * Restituisce un Observable ottenuto con l'operatore create
     *
     * @return Observable<String>
     */
    private Observable<String> getObservableOperatorCreate() {
        Log.v(TAG, "getObservableOperatorCreate");
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.v(TAG, "ObservableOnSubscribe.subscribe");

                // L'Observer emette (push) una serie di strings. L'emissione avviene dopo aver
                // sottoscritto un Observer
                for (int i = 0; i <= 20; i++) {
                    emitter.onNext("create " + "" + i);
                }

                emitter.onComplete();
            }
        });

        // Stessa cosa con lambdaexpr
//        return Observable.create(emitter -> {
//            emitter.onNext("ciao");
//        });
    }

    /**
     * Restituisce un Observable ottenuto con l'operatore just. </br>
     * E' un Observable defer, cioè l'emissione dei dati avviene dopo aver sottoscritto l'Observer.
     *
     * @return Observable<String>
     */
    private Observable<String> getObservableOperatorDefer() {
        Log.v(TAG, "getObservableOperatorDefer");
        return Observable.defer(() -> {
            // L'emissione avviene solo dopo aver chiamato il subscribe()
            Log.v(TAG, "getObservableOperatorDefer.just");
            return Observable.just("defer + just");
        });
    }

    /**
     * Restituisce un Observable ottenuto con l'operatore interval. </ br>
     *
     * @return Observable<String>
     */
    private Observable<String> getObservableOperatorInterval() {
        Log.v(TAG, "getObservableOperatorDefer");
        return Observable
                .interval(2, 3, TimeUnit.SECONDS) // Inizia il conteggio dopo 2'', si ripete ogni 3''
                .takeWhile(tick -> tick < 5) // Si ripete fino a 5 volte (0-based)
                .map(tick -> "item at interval: " + tick) // mappa il conteggio in una string
                .doOnComplete(() -> Log.v(TAG, "getObservableOperatorInterval.onComplete"))
                .doOnError(e -> Log.e(TAG, "getObservableOperatorInterval.doOnError", e))
                .doOnNext(string -> Log.v(TAG, "getObservableOperatorInterval.doOnNext: " + string))
                .retry(); // in caso di errore riparte dal conteggio successivo
    }
}

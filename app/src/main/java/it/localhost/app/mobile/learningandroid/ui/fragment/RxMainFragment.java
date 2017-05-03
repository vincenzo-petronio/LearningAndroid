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

import butterknife.BindArray;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.activity.RxActivity;

/**
 *
 */

public class RxMainFragment extends BaseFragment {

    private static final String TAG = RxMainFragment.class.getSimpleName();
    private Observable<String> mObservableRxItems;
    private Observer<String> mObserverRxItems;
    private Consumer<Integer> mConsumerItemClicked;
    private Disposable mDisposableItemClicked;

    @BindView(R.id.lv_items)
    ListView mLvItems;
    @BindArray(R.array.rx_items)
    String[] mStringsRxItems;

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

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1);
        mLvItems.setAdapter(arrayAdapter);

        // Con RxBinding creo un Consumer per l'evento onItemClicked della ListView
        mConsumerItemClicked = new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                switch (integer) {
                    case 0:
                        ((RxActivity) getActivity()).navigateTo(new RxOperatorsCreatingFragment());
                        break;
                    default:
                        break;
                }
            }
        };
        mDisposableItemClicked = RxAdapterView.itemClicks(mLvItems).subscribe(mConsumerItemClicked);
        // N.B. il Disposable serve perché subscribe() è marcato con @CheckReturnValue quindi
        // errorprone segnala che è obbligatorio gestire il value return!
        // @see https://github.com/google/error-prone/blob/master/docs/bugpattern/CheckReturnValue.md


        // 1 ) Creo l'Observable (emette i dati!)
        mObservableRxItems = Observable.fromArray(mStringsRxItems)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        // 2 ) eseguo Operator se necessario
//        mObservableRxItems = mObservableRxItems.sorted();

        // 3 ) Creo l'Observer (consuma i dati!)
        mObserverRxItems = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.v(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.v(TAG, "onNext: " + s);
                arrayAdapter.add(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError", e);
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "onComplete");
            }
        };

        // 4 ) collego Observable ad Observer
        mObservableRxItems.subscribe(mObserverRxItems);
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

        if (mDisposableItemClicked != null && !mDisposableItemClicked.isDisposed()) {
            Log.v(TAG, "mDisposableItemClicked.dispose()");
            mDisposableItemClicked.dispose();
        }
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_rx_main;
    }
}

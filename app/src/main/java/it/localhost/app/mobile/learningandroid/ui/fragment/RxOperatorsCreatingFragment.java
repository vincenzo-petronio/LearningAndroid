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

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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


        // LISTVIEW
        mLvArrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1);
        mLvItems.setAdapter(mLvArrayAdapter);
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

    private void loadData(@android.support.annotation.NonNull Integer integer) {
        Log.v(TAG, "" + integer);
        switch (integer) {
            case 0:
                // empty
                break;
            case 1:
                Disposable d = getOperatorCreate().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        loadListView(s);
                    }
                });
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
        mLvArrayAdapter.clear();
        mLvArrayAdapter.addAll(list);
    }

    private Observable<String> getOperatorCreate() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("ciao");
            }
        });
    }
}

package it.localhost.app.mobile.learningandroid.ui.activity;

import com.akaita.java.rxjava2debug.RxJava2Debug;
import com.jakewharton.rxbinding2.widget.RxTextView;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import bolts.CancellationToken;
import bolts.CancellationTokenSource;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.ApiJsonPlaceholderEndpoint;
import it.localhost.app.mobile.learningandroid.data.ServiceGenerator;
import it.localhost.app.mobile.learningandroid.data.model.Comment;
import it.localhost.app.mobile.learningandroid.data.model.Post;
import it.localhost.app.mobile.learningandroid.ui.adapter.CommentsAdapter;
import it.localhost.app.mobile.learningandroid.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class RetrofitActivity extends BaseActivity {

    private static final String TAG = RetrofitActivity.class.getSimpleName();
    private CommentsAdapter mAdapter;
    private List<Comment> mCommentList;
    private Observable<List<Comment>> mObservableCommentList;
    private ApiJsonPlaceholderEndpoint mClient;
    private Disposable mDisposableEtSearch;
    private CancellationTokenSource mCts = new CancellationTokenSource();

    @BindView(R.id.lvItems)
    protected ListView mLvItems;
    @BindView(R.id.pb)
    protected ProgressBar mProgressBar;
    @BindView(R.id.et_search)
    EditText mEtSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        mClient = new ServiceGenerator
                .Builder(Constants.API_JSONPLACEHOLDER_URL_BASE)
                .connectionTimeout(2)
                .build()
                .createService(ApiJsonPlaceholderEndpoint.class);

        initUI();
        loadData();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();

        if (mDisposableEtSearch != null && mDisposableEtSearch.isDisposed()) {
            mDisposableEtSearch.dispose();
        }

        // Con il tasto back posso simulare la cancellazione del Token
        if (mCts != null) {
            mCts.cancel();
        }
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_retrofit;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @OnClick({R.id.btCase1, R.id.btCase2, R.id.btCase3, R.id.btCase4})
    protected void onBtCaseClickListener(View view) {
        switch (view.getId()) {
            case R.id.btCase1:
                loadDataWithRx();
                break;
            case R.id.btCase2:
                // search with rx
                break;
            case R.id.btCase3:
                loadDataWithBolts();
                break;
            case R.id.btCase4:
                loadDataWithBoltsInParallel();
                break;
            default:
                break;
        }
    }

    private void initUI() {
        mCommentList = new ArrayList<>();
//                Collections.emptyList();
        mAdapter = new CommentsAdapter(this);
        mLvItems.setAdapter(mAdapter);
    }

    private void showProgress(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private void loadData() {
        showProgress(true);

        // execute() sul Thread UI va in Exception, è da utilizzare solo in un Background Service
        // o Thread secondario e viene eseguito in sincrono
        Call<List<Comment>> call = mClient.getComments("1");
        try {
            mCommentList = call.execute().body();
            mAdapter.updateCollection(mCommentList);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }


        // enqueue viene eseguito in un thread secondario asincrono.
        // clone() serve per creare una copia di call, che altrimenti non può essere eseguito più
        // di una volta.
        try {
            call.clone().enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    mCommentList = response.body();
                    mAdapter.updateCollection(mCommentList);
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    Log.e(TAG, "onFailure", t);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }

        showProgress(false);
    }

    private void loadDataWithBolts() {
        Log.v(TAG, "loadDataWithBolts");
        showProgress(true);

        mCommentList.clear();

        getCallTask(mClient.getComments("1"), mCts.getToken())
                .continueWithTask(new Continuation<List<Comment>, Task<List<Comment>>>() {
                    @Override
                    public Task<List<Comment>> then(Task<List<Comment>> firstTask) throws Exception {
                        Log.v(TAG, "then from first continueWithTask");

                        if (firstTask.isCancelled()) {
                            throw new Exception("Cancel from first continueWithTask");
                        } else if (firstTask.isFaulted()) {
                            throw new Exception(firstTask.getError());
                        } else {
                            mCommentList.addAll(firstTask.getResult());

                            // task da passare alla successiva Continuation
                            Task<List<Comment>> secondTask = getCallTask(mClient.getComments("2"), mCts.getToken());
                            return secondTask;
                        }
                    }
                })
                .continueWithTask(new Continuation<List<Comment>, Task<List<Comment>>>() {
                    @Override
                    public Task<List<Comment>> then(Task<List<Comment>> secondTask) throws Exception {
                        Log.v(TAG, "then from second continueWithTask");

                        if (secondTask.isCancelled()) {
                            throw new Exception("Cancel from second continueWithTask");
                        } else if (secondTask.isFaulted()) {
                            throw new Exception(secondTask.getError());
                        } else {
                            // cambio valore ad un elemento
                            List<Comment> secondTaskResult = secondTask.getResult();
                            secondTaskResult.get(1).setName("Name from second Task!");

                            // task da passare alla successiva Continuation
                            TaskCompletionSource<List<Comment>> thirdTask = new TaskCompletionSource<>();
                            thirdTask.setResult(secondTaskResult);
                            return thirdTask.getTask();
                        }
                    }
                })
                .continueWith(new Continuation<List<Comment>, Object>() {
                    @Override
                    public Object then(Task<List<Comment>> thirdTask) throws Exception {
                        Log.v(TAG, "then from last continue");

                        if (thirdTask.isCancelled()) {
                            Log.i(TAG, "task canceled!");
                        } else if (thirdTask.isFaulted()) {
                            // handle all error
                            Log.e(TAG, "EXCEPTION", thirdTask.getError());
                            mCommentList.clear();
                            Comment comment = new Comment();
                            comment.setName("ERROR!");
                            mCommentList.add(comment);
                            mAdapter.updateCollection(mCommentList);
                        } else {
                            mCommentList.addAll(thirdTask.getResult());
                            mAdapter.updateCollection(mCommentList);
                        }
                        showProgress(false);
                        return null;
                    }
                });
    }

    private void loadDataWithBoltsInParallel() {
        Log.v(TAG, "loadDataWithBoltsInParallel");
        showProgress(true);

        getCallTask(mClient.getComments("1"), mCts.getToken())
                .continueWithTask(new Continuation<List<Comment>, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<List<Comment>> task) throws Exception {
                        // Crea un array di Task eseguiti in parallelo
                        List<Task<List<Comment>>> parallelTasks = new ArrayList<>();
                        for (int i = 2; i < 5; i++) {
                            parallelTasks.add(getCallTask(mClient.getComments("" + i), mCts.getToken()));
                        }

                        // ritorna quando tutti i Task sono completi
                        return Task.whenAll(parallelTasks);
                    }
                })
                .onSuccess(new Continuation<Void, Void>() {
                    @Override
                    public Void then(Task<Void> task) throws Exception {
                        Log.i(TAG, "all task completed!");
                        showProgress(false);
                        return null;
                    }
                });
    }

    private Task<List<Comment>> getCallTask(Call<List<Comment>> call, CancellationToken cToken) {
        Log.v(TAG, "getCallTask");
        final TaskCompletionSource<List<Comment>> task = new TaskCompletionSource<>();

        // aggiungo un ritardo di 2s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cToken.isCancellationRequested()) {
                    task.setCancelled();
                    return;
                }

                call.clone().enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if (cToken.isCancellationRequested()) {
                            task.setCancelled();
                            return;
                        }

                        task.setResult(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        if (cToken.isCancellationRequested()) {
                            task.setCancelled();
                            return;
                        }

                        task.setError(new Exception(t));
                    }
                });

            }
        }, 2000);

        return task.getTask();
    }

    private void loadDataWithRx() {
        getObservableOperatorCreate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverOperatorCreate());

        mDisposableEtSearch = RxTextView.textChanges(mEtSearch)
                .debounce(800, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .filter(charSequence -> charSequence.length() > 0)
                .flatMap(charSequence -> getRxObservableCommentFromCharSequence(charSequence.toString()))
//                .switchMap(charSequence -> getRxObservableCommentFromCharSequence(charSequence.toString()))
                .subscribe(getRxConsumerForSearch(), RxJava2Debug::getEnhancedStackTrace);
//                .subscribe(charSequence -> Log.v(TAG, charSequence.toString()), RxJava2Debug::getEnhancedStackTrace);
    }

    /**
     * Observer
     *
     * @return Observer<Comment>
     */
    private Observer<Comment> getObserverOperatorCreate() {
        return new Observer<Comment>() {

            List<Comment> mCommentList = new ArrayList<>();

            @Override
            public void onSubscribe(Disposable d) {
                Log.v(TAG, "getObserverOperatorCreate:onSubscribe");
                mCommentList.clear();
            }

            @Override
            public void onNext(Comment comment) {
                Log.v(TAG, "getObserverOperatorCreate:onNext");
                mCommentList.add(comment);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "getObserverOperatorCreate:onError", e);
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "getObserverOperatorCreate:onComplete");
                mAdapter.updateCollection(mCommentList);
            }
        };
    }

    /**
     * Observable ottenuto da fonti diverse.
     *
     * @return Observable<Comment>
     */
    private Observable<Comment> getObservableOperatorCreate() {
        return getRxPostIdComments("10")    // 1° Observable dal BE
                .concatWith(getRxPostIdComments("20"))  // 2° Observable dal BE
                .flatMapIterable(c -> c)    // Converte una List<Comment> in Comment
                .concatWith(getRxComment().toObservable()); // 3° Observable da un Single
    }

    /**
     * @return List<Post>
     */
    private Observable<List<Post>> getRxPostsObservable() {
        return mClient.getRxPosts();
    }

    /**
     * @param id String
     * @return Post
     */
    private Observable<Post> getRxPostIdObservable(String id) {
        return mClient.getRxPostId(id);
    }

    /**
     * @param id String
     * @return List<Comment>
     */
    private Observable<List<Comment>> getRxPostIdComments(String id) {
        return mClient.getRxPostIdComments(id);
    }

    /**
     * Emette un Single Comment
     *
     * @return Single<Comment>
     */
    private Single<Comment> getRxComment() {
        Comment comment = new Comment();
        comment.setId(9999);
        comment.setName("Single Comment name");
        comment.setPostId(8888);
        comment.setBody("Single Comment body");
        comment.setEmail("Single Comment email");
        return Single.just(comment);
    }

    /**
     * @return Consumer<CharSequence>
     */
    private Consumer<Comment> getRxConsumerForSearch() {
        return new Consumer<Comment>() {
            @Override
            public void accept(Comment comment) throws Exception {
                Log.i(TAG, "getRxConsumerForSearch.accept:  " + "" + comment.getId());
            }
        };
    }

    private Observable<Comment> getRxObservableCommentFromCharSequence(String query) {
//        if (TextUtils.isEmpty(query)) {
//            return getObservableOperatorCreate();
//        }

        List<Comment> mCommentFoundedList = new ArrayList<>();
//        for (Comment comment : getObservableOperatorCreate().ite)
        return getObservableOperatorCreate().filter(comment -> comment.getName().toLowerCase().equals(query.toLowerCase()));

//        return Observable.just(mCommentFoundedList).flatMapIterable(q -> q);
    }
}

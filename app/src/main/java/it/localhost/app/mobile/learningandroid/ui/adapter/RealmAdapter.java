package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.Sort;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Task;
import it.localhost.app.mobile.learningandroid.data.model.UserStory;

/**
 *
 */

public class RealmAdapter extends RealmRecyclerViewAdapter<UserStory, RealmAdapter.ViewHolder> {

    private static final String TAG = RealmAdapter.class.getSimpleName();

    // N.B. Con Realm è inutile gestire la collection, basta avere l'instanza del DB e aggiornare i dati su DB,
    // perché poi il notify verrà gestito automaticamente da RealmAdapter.
    private OrderedRealmCollection<UserStory> mCollection;

    private Realm mRealmInstance;

    /**
     * Costruttore
     *
     * @param data       OrderedRealmCollection<UserStory>
     * @param autoUpdate boolean
     */
    public RealmAdapter(@Nullable OrderedRealmCollection<UserStory> data, boolean autoUpdate, Realm realm) {
        super(data, autoUpdate);
        this.mCollection = data;
        this.mRealmInstance = realm;
    }


    /**
     * @param data OrderedRealmCollection
     * @deprecated C'è updateData() in Realm!
     */
    public void updateCollection(@NonNull OrderedRealmCollection<UserStory> data) {
//        this.mCollection.clear();
//        this.mCollection.addAll(data);

    }

    /**
     * Aggiunge un elemento alla collection
     *
     * @param userStory UserStory
     */
    public void addItem(final UserStory userStory) {
        if (userStory == null) {
            return;
        }

        mRealmInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm localRealm) {
//                localRealm.copyToRealmOrUpdate(userStory);
                localRealm.insertOrUpdate(userStory);

                if (getData() != null && mIAdapterCallback != null) {
                    mIAdapterCallback.onCollectionSizeChanged(getData().size());
                }
            }
        });
    }

    /**
     * Elimina un elemento dalla collection
     *
     * @param position int
     */
    private void deleteItem(final int position) {
        mRealmInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm localRealm) {
                Log.i(TAG, "deleteItem:" + "" + position);
//                mCollection.get(position).deleteFromRealm();
//                mCollection.deleteFromRealm(position);
                if (getData() != null) {
                    getData().deleteFromRealm(position);

                    // notify collection size changed
                    if (mIAdapterCallback != null) {
                        mIAdapterCallback.onCollectionSizeChanged(getData().size());
                    }
                }
            }
        });
    }

    /**
     * Cerca gli elementi nella collection
     *
     * @param query String
     */
    public void searchItem(final String query) {
        if (TextUtils.isEmpty(query)) {
            updateData(
                    mRealmInstance
                            .where(UserStory.class)
                            .findAll()
            );
        } else {
            updateData(
                    mRealmInstance
                            .where(UserStory.class)
                            .contains("name", query, Case.INSENSITIVE)
                            .findAll()
            );
        }

        // N.B. updateData() mostra solo il risultato di una query,
        // ma non sovrascrive l'adapter con la nuova collection!

        if (getData() != null && mIAdapterCallback != null) {
            mIAdapterCallback.onCollectionSizeChanged(getData().size());
        }
    }

    /**
     * Cerca gli elementi nella collection
     *
     * @param needed boolean
     */
    public void searchItem(boolean needed) {
        updateData(
                mRealmInstance
                        .where(UserStory.class)
                        .in("taskRealmCollection.needed", new Boolean[]{needed})
                        .findAll()
        );

        if (getData() != null && mIAdapterCallback != null) {
            mIAdapterCallback.onCollectionSizeChanged(getData().size());
        }
    }

    public void sortItems() {
        updateData(
                mRealmInstance
                        .where(UserStory.class)
                        .findAllSorted("id", Sort.ASCENDING)
        );
    }

    @Override
    public RealmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_realm_userstory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RealmAdapter.ViewHolder holder, int position) {
        UserStory userStory = getData() == null ? null : getData().get(position);

        if (userStory == null) {
            return;
        }

        holder.mTvUsTitle.setText(userStory.getName());
        holder.mTvUsId.setText(String.valueOf(userStory.getId()));
    }


    /**
     * View Holder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_us_title)
        TextView mTvUsTitle;
        @BindView(R.id.tv_us_id)
        TextView mTvUsId;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.bt_us_del)
        void onBtUsDelClickListener() {
            deleteItem(getAdapterPosition());
        }

        @OnLongClick(R.id.tv_us_title)
        boolean onTvLongClickListener() {
            Log.d(TAG, "onTvLongClickListener: " + "" + getAdapterPosition());
            if (mIAdapterCallback != null && getData() != null) {
                mIAdapterCallback.onItemLongClicked(getData().get(getAdapterPosition()).getTaskRealmCollection());
            }
            return true;
        }
    }


    // CALLBACK
    private IAdapterCallback mIAdapterCallback;

    public void setIAdapterCallback(IAdapterCallback IAdapterCallback) {
        mIAdapterCallback = IAdapterCallback;
    }

    /**
     * Callback da Adapter a Activity
     */
    public interface IAdapterCallback {

        /**
         * Notifica il longclick su un elemento della collection
         *
         * @param tasks RealmList<Task>
         */
        void onItemLongClicked(RealmList<Task> tasks);

        /**
         * Notifica il cambio di elementi nella collection
         *
         * @param size int
         */
        void onCollectionSizeChanged(int size);
    }
}

package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.ApiJsonPlaceholderEndpoint;
import it.localhost.app.mobile.learningandroid.data.ServiceGenerator;
import it.localhost.app.mobile.learningandroid.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tutorial.Sample;

/**
 * @author vincenzo.petronio on 13/04/2018.
 */
public class ProtoBufMainFragment extends BaseFragment {

    private static final String TAG = ProtoBufMainFragment.class.getSimpleName();

    private Unbinder mUnbinder;
    private ApiJsonPlaceholderEndpoint mClient;

    @Override
    public void onAttach(Context context) {
        Log.v(TAG, "onAttach");
        super.onAttach(context);
    }

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
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        // REST API
        List<Sample.TodoProto> todos = new ArrayList<>();

        Sample.TodoProto.Builder t = Sample.TodoProto.newBuilder();


//        try {
//            t = Todo.TodoProto.parseFrom(ByteString.copyFromUtf8("{\"userId\": 1,\"id\": 2,\"title\": \"quis ut nam facilis et officia qui\",\"completed\": false}"));
//        } catch (InvalidProtocolBufferException e) {
//            Log.e(TAG, "onFailure: ", e);
//        }

        mClient = new ServiceGenerator
                .Builder(Constants.API_JSONPLACEHOLDER_URL_BASE)
                .build()
                .createService(ApiJsonPlaceholderEndpoint.class);
        mClient.getTodo("2").enqueue(new Callback<Sample.TodoProto>() {
            @Override
            public void onResponse(Call<Sample.TodoProto> call, Response<Sample.TodoProto> response) {
                if (response.body() == null) return;

                Log.v(TAG, "onResponse size: " + "" + response.body());
            }

            @Override
            public void onFailure(Call<Sample.TodoProto> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });
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
        mUnbinder.unbind();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_protobufmain;
    }
}

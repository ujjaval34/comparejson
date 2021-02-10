package com.ujjaval.comparejson.viewmodel;

import android.content.Context;

import com.ujjaval.comparejson.interfaces.ApiInterface;
import com.ujjaval.comparejson.interfaces.FlowCallBack;
import com.ujjaval.comparejson.model.PostDetails;
import com.ujjaval.comparejson.model.UserDetails;
import com.ujjaval.comparejson.utils.ApiClient;
import com.ujjaval.comparejson.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserdeatilsViewModel {

    FlowCallBack flowCallBack;
    Context context;

    public UserdeatilsViewModel(MainActivity context){
        this.context=context;
    }

    public void firapi(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<UserDetails>> call;
        call = apiInterface.getusers();

        call.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, Response<List<UserDetails>> response) {
                flowCallBack=  (FlowCallBack) context;
                List<UserDetails> mainDataJsonResponse = response.body();
                flowCallBack.onSuccess(mainDataJsonResponse);
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {
                flowCallBack=  (FlowCallBack) context;
                flowCallBack.onFailed(t);
            }
        });


    }





    public void firPostapi(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<PostDetails>> call;
        call = apiInterface.getpost();

        call.enqueue(new Callback<List<PostDetails>>() {
            @Override
            public void onResponse(Call<List<PostDetails>> call, Response<List<PostDetails>> response) {
                flowCallBack=  (FlowCallBack) context;
                List<PostDetails> postResponse = response.body();
                flowCallBack.onPostSuccess(postResponse);
            }

            @Override
            public void onFailure(Call<List<PostDetails>> call, Throwable t) {
                flowCallBack=  (FlowCallBack) context;
                flowCallBack.onPostFailed(t);
            }
        });


    }

}

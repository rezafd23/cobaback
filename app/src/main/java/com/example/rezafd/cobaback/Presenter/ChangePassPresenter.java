package com.example.rezafd.cobaback.Presenter;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.Interface.ChangePassPresenterInterface;
import com.example.rezafd.cobaback.Interface.ChangePassViewInterface;
import com.example.rezafd.cobaback.Model.ChangePassResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROTECT-32 on 12/10/2017.
 */

public class ChangePassPresenter implements ChangePassPresenterInterface {
    private ChangePassViewInterface view;
    private ApiRequest api;

    public ChangePassPresenter(ChangePassViewInterface view, ApiRequest api) {
        this.view = view;
        this.api = api;
    }


    @Override
    public void doChange(String nrp, String pass) {
        view.showLoading();
        view.log("init");

        Call<ChangePassResponse> call = api.update(nrp, pass);
        call.enqueue(new Callback<ChangePassResponse>() {
            @Override
            public void onResponse(Call<ChangePassResponse> call, Response<ChangePassResponse> response) {
                view.log("response");
                view.hideLoading();
                if (response.isSuccessful()) {
                    ChangePassResponse res = response.body();
                    if (res.isSuccess()) {
                        view.onFinishChange();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePassResponse> call, Throwable t) {
                view.log(t.toString());
            }
        });
    }
}
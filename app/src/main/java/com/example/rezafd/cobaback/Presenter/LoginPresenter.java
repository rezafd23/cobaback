package com.example.rezafd.cobaback.Presenter;

import android.content.Intent;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.Activity.Login;
import com.example.rezafd.cobaback.Activity.MainActivity;
import com.example.rezafd.cobaback.Interface.LoginPresenterInterface;
import com.example.rezafd.cobaback.Interface.LoginViewInterface;
import com.example.rezafd.cobaback.Model.Profil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROTECT-32 on 12/8/2017.
 */

public class LoginPresenter implements LoginPresenterInterface {
    private LoginViewInterface view;
    private ApiRequest api;

    public LoginPresenter(LoginViewInterface view, ApiRequest api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void doLogin(String nrp, String pass) {
        view.showLoading();
        view.log("init");

        Call<Profil> call = api.login(nrp,pass);
        call.enqueue(new Callback<Profil>() {
            @Override
            public void onResponse(Call<Profil> call, Response<Profil> response) {
                view.log("response");
                view.hideLoading();
                if (response.isSuccessful()) {
                    Profil res = response.body();
                    if (res.isSuccess()) {
                        view.onFinishLogin(res);
                    }
                }
            }

            @Override
            public void onFailure(Call<Profil> call, Throwable t) {
                view.log(t.toString());
            }
        });
    }
}
package com.example.rezafd.cobaback.Presenter;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.Interface.UpdateProfilePresenterInterface;
import com.example.rezafd.cobaback.Interface.UpdateProfileViewInterface;
import com.example.rezafd.cobaback.Model.UpdateProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROTECT-32 on 12/10/2017.
 */

public class UpdateProfilePresenter implements UpdateProfilePresenterInterface {
    private UpdateProfileViewInterface view;
    private ApiRequest api;

    public UpdateProfilePresenter(UpdateProfileViewInterface view, ApiRequest api) {
        this.view = view;
        this.api = api;
    }


    @Override
    public void doUpdateProfile(String nrp, String nama, String tmpt_lhr, String tgl_lhr, String jurusan, String alamat, String nohp, String email) {
        view.showLoading();
        view.log("init");

        Call<UpdateProfileResponse> call = api.update_profile(nrp, nama, tmpt_lhr, tgl_lhr, jurusan, alamat, nohp, email);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                view.log("response");
                view.hideLoading();
                if (response.isSuccessful()) {
                    UpdateProfileResponse res = response.body();
                    if (res.isSuccess()) {
                        view.onFinishUpdateProfile();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                view.log(t.toString());
            }
        });
    }
}
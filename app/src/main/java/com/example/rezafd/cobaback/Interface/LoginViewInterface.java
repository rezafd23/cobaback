package com.example.rezafd.cobaback.Interface;

import com.example.rezafd.cobaback.Model.Profil;

/**
 * Created by PROTECT-32 on 12/8/2017.
 */

public interface LoginViewInterface {
    void showLoading();
    void hideLoading();
    void onFinishLogin(Profil p);

    void log(String t);
}

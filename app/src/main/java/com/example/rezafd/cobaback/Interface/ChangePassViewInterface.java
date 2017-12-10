package com.example.rezafd.cobaback.Interface;

import com.example.rezafd.cobaback.Model.ChangePassResponse;

/**
 * Created by PROTECT-32 on 12/10/2017.
 */

public interface ChangePassViewInterface {
    void showLoading();
    void hideLoading();
    void onFinishChange();

    void log(String t);
}

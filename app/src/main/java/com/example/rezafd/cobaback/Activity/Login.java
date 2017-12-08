package com.example.rezafd.cobaback.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.API.Retroserver;
import com.example.rezafd.cobaback.Interface.LoginPresenterInterface;
import com.example.rezafd.cobaback.Interface.LoginViewInterface;
import com.example.rezafd.cobaback.Model.LoginResponse;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.Presenter.LoginPresenter;
import com.example.rezafd.cobaback.R;
import com.example.rezafd.cobaback.API.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity implements LoginViewInterface {
    @BindView(R.id.input_nrp) EditText input_nrp;
    @BindView(R.id.input_password) EditText input_password;
    ProgressDialog pg;
    private LoginPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pg=new ProgressDialog(this);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this,getApi());

        pg.setMessage("Please Wait...");
        pg.setCancelable(false);
    }

    @OnClick(R.id.btn_login) void doLogin() {
        presenter.doLogin(input_nrp.getText().toString(), input_password.getText().toString());
    }

    @Override
    public void showLoading() {
        pg.show();
    }

    @Override
    public void hideLoading() {
        pg.dismiss();
    }

    @Override
    public void onFinishLogin(Profil res) {
        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.putExtra("Nama", res.getNama());
        intent.putExtra("NRP", res.getNRP());
        intent.putExtra("TmptLahir", res.getTmptLahir());
        intent.putExtra("TglLahir", res.getTglLahir());
        intent.putExtra("Jurusan", res.getJurusan());
        intent.putExtra("Alamat", res.getAlamat());
        intent.putExtra("NoHP", res.getNoHp());
        intent.putExtra("Email", res.getEmail());
        startActivity(intent);
        finish();
        log("berhasil");
    }
}
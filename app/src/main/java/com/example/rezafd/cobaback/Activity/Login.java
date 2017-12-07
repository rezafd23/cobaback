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
import com.example.rezafd.cobaback.Model.LoginResponse;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.R;
import com.example.rezafd.cobaback.API.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity {
    @BindView(R.id.input_nrp) EditText input_nrp;
    @BindView(R.id.input_password) EditText input_password;
    ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pg=new ProgressDialog(this);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_login) void doLogin() {
        pg.setMessage("Please Wait...");
        pg.setCancelable(false);
        pg.show();


        Call<Profil> call = getApi().login(input_nrp.getText().toString(), input_password.getText().toString());
        call.enqueue(new Callback<Profil>() {
            @Override
            public void onResponse(Call<Profil> call, Response<Profil> response) {
                pg.hide();
                if (response.isSuccessful()) {
                    Profil res = response.body();
                    if (res.isSuccess()) {
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
            }

            @Override
            public void onFailure(Call<Profil> call, Throwable t) {

            }
        });
    }
}

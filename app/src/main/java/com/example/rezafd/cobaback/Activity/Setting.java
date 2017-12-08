package com.example.rezafd.cobaback.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.Model.UpdateProfileResponse;
import com.example.rezafd.cobaback.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Setting extends BaseActivity {
    Button btnchangepassword, btnupdateprofile;
    TextView tvNRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        tvNRP = (TextView) findViewById(R.id.setNRP);

        Intent i = getIntent();
        String nrp = i.getStringExtra("NRP");

        tvNRP.setText(nrp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            Call<Profil> call = getApi().select_profile(tvNRP.getText().toString());
            call.enqueue(new Callback<Profil>() {
                @Override
                public void onResponse(Call<Profil> call, Response<Profil> response) {
                    if(response.isSuccessful()) {
                        Profil res = response.body();
                        if(res.isSuccess()) {
                            Intent intent = new Intent(Setting.this, UpdateProfile.class);
                            intent.putExtra("Nama", res.getNama());
                            intent.putExtra("NRP", res.getNRP());
                            intent.putExtra("TmptLahir", res.getTmptLahir());
                            intent.putExtra("TglLahir", res.getTglLahir());
                            intent.putExtra("Jurusan", res.getJurusan());
                            intent.putExtra("Alamat", res.getAlamat());
                            intent.putExtra("NoHP", res.getNoHp());
                            intent.putExtra("Email", res.getEmail());
                            startActivity(intent);
                            log("BERHASIL");
                        }
                    }
                }

                @Override
                public void onFailure(Call<Profil> call, Throwable t) {
                    log(t.toString());
                }
            });
        }
        btnchangepassword =(Button) findViewById(R.id.btnchangepassword);
        btnupdateprofile = (Button) findViewById(R.id.btnUpdateProfile);

        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, ChangePassword.class);
                startActivity(intent);
            }
        });

        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Profil> call = getApi().select_profile(tvNRP.getText().toString());
                call.enqueue(new Callback<Profil>() {
                    @Override
                    public void onResponse(Call<Profil> call, Response<Profil> response) {
                        if(response.isSuccessful()) {
                            Profil res = response.body();
                            if(res.isSuccess()) {
                                Intent intent = new Intent(Setting.this, UpdateProfile.class);
                                intent.putExtra("Nama", res.getNama());
                                intent.putExtra("NRP", res.getNRP());
                                intent.putExtra("TmptLahir", res.getTmptLahir());
                                intent.putExtra("TglLahir", res.getTglLahir());
                                intent.putExtra("Jurusan", res.getJurusan());
                                intent.putExtra("Alamat", res.getAlamat());
                                intent.putExtra("NoHP", res.getNoHp());
                                intent.putExtra("Email", res.getEmail());
                                startActivity(intent);
                                log("BERHASIL");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Profil> call, Throwable t) {
                        log(t.toString());
                    }
                });
            }
        });
    }
}
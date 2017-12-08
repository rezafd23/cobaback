package com.example.rezafd.cobaback.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.Model.ChangePassResponse;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends BaseActivity {
    EditText Pass,Pass2,Pass3;
    TextView NRP;
    Button  btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        NRP = (TextView) findViewById(R.id.settingNRP);
        Pass = (EditText) findViewById(R.id.settingCurrentPassword);
        Pass2 = (EditText) findViewById(R.id.settingPassword);
        Pass3 = (EditText) findViewById(R.id.settingConfirmPassword);
        btnupdate = (Button) findViewById(R.id.btnUpdatePass);

        Intent intent =getIntent();
        String iNRP = intent.getStringExtra("NRP");

        NRP.setText(iNRP);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            Call<Profil> call = getApi().select_profile(NRP.getText().toString());
            call.enqueue(new Callback<Profil>() {
                @Override
                public void onResponse(Call<Profil> call, Response<Profil> response) {
                    if (response.isSuccessful()) {
                        Profil res = response.body();
                        if (res.isSuccess()) {
                            Intent intent = new Intent(ChangePassword.this, MainActivity.class);
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
                    log(t.toString());
                }
            });
        }

//        btnupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!Pass2.getText().toString().equals(Pass3.getText().toString())){
//                    Toast.makeText(ChangePassword.this,"Password doesn't Match",Toast.LENGTH_SHORT).show();
//                }
//                else
//                    String pas = Pass2.getText().toString();
//            }
//        });

    }
}

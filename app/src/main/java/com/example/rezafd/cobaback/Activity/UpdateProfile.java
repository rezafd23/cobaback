package com.example.rezafd.cobaback.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.Model.UpdateProfileResponse;
import com.example.rezafd.cobaback.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROTECT-32 on 12/7/2017.
 */

public class UpdateProfile extends BaseActivity {
    private TextView uNRP,uNama,uTgl;
    private EditText uTmptLahir,uJurusan,uAlamat,uNoHp,uEmail;
    private Button uTglLahir, uProfile;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        uNRP = (TextView) findViewById(R.id.updateNRP);
        uNama = (TextView) findViewById(R.id.updateNama);
        uTmptLahir = (EditText) findViewById(R.id.updateTmptLahir);
        uJurusan = (EditText) findViewById(R.id.updateJurusan);
        uAlamat = (EditText) findViewById(R.id.updateAlamat);
        uNoHp = (EditText) findViewById(R.id.updateNoHP);
        uEmail = (EditText) findViewById(R.id.updateEmail);
        uTglLahir = (Button) findViewById(R.id.updateTanggalLahir);
        uTgl = (TextView) findViewById(R.id.uTglLahir);
        uProfile = (Button) findViewById(R.id.btnUProfile);

        Intent intent = getIntent();
        String nrp = intent.getStringExtra("NRP");
        String nama = intent.getStringExtra("Nama");
        String tmptlahir = intent.getStringExtra("TmptLahir");
        String tgllahir = intent.getStringExtra("TglLahir");
        String jurusan = intent.getStringExtra("Jurusan");
        String alamat = intent.getStringExtra("Alamat");
        String noHP = intent.getStringExtra("NoHP");
        String email = intent.getStringExtra("Email");

        uNRP.setText(nrp);
        uNama.setText(nama);
        uTmptLahir.setText(tmptlahir);
        uTgl.setText(tgllahir);
        uJurusan.setText(jurusan);
        uAlamat.setText(alamat);
        uNoHp.setText(noHP);
        uEmail.setText(email);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            Call<Profil> call = getApi().select_profile(uNRP.getText().toString());
            call.enqueue(new Callback<Profil>() {
                @Override
                public void onResponse(Call<Profil> call, Response<Profil> response) {
                    if (response.isSuccessful()) {
                        Profil res = response.body();
                        if (res.isSuccess()) {
                            Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
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

        uTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        uProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateProfileResponse> call = getApi().update_profile(uNRP.getText().toString(), uNama.getText().toString(),
                        uTmptLahir.getText().toString(), uTgl.getText().toString(), uJurusan.getText().toString(),
                        uAlamat.getText().toString(), uNoHp.getText().toString(), uEmail.getText().toString());
                call.enqueue(new Callback<UpdateProfileResponse>() {
                    @Override
                    public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                        if(response.isSuccessful()) {
                            UpdateProfileResponse res = response.body();
                            if(res.isSuccess()) {
                                Toast.makeText(UpdateProfile.this, "Update Profile Berhasil", Toast.LENGTH_SHORT).show();
                                Call<Profil> call1 = getApi().select_profile(uNRP.getText().toString());
                                call1.enqueue(new Callback<Profil>() {
                                    @Override
                                    public void onResponse(Call<Profil> call, Response<Profil> response) {
                                        if(response.isSuccessful()) {
                                            Profil res = response.body();
                                            if(res.isSuccess()) {
                                                Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
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
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                        log(t.toString());
                    }
                });
            }
        });
    }

    private void showDateDialog() {
        Calendar newCalender = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                uTgl.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH), newCalender.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
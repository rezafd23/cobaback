package com.example.rezafd.cobaback.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.API.Retroserver;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.Model.ResponsModel;
import com.example.rezafd.cobaback.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Prestasi extends BaseActivity {
    EditText Nama, Peringkat, Tingkat, Penyelenggara;
    Button btnsave;
    ProgressDialog pg;
    private ActionBarDrawerToggle mToggle;
    TextView nrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestasi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nrp = (TextView) findViewById(R.id.presetasi_NRP);

        Intent i = getIntent();
        String iNRP = i.getStringExtra("NRP");

        nrp.setText(iNRP);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            Call<Profil> call = getApi().select_profile(nrp.getText().toString());
            call.enqueue(new Callback<Profil>() {
                @Override
                public void onResponse(Call<Profil> call, Response<Profil> response) {
                    if (response.isSuccessful()) {
                        Profil res = response.body();
                        if (res.isSuccess()) {
                            Intent intent = new Intent(Prestasi.this, MainActivity.class);
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

        Nama = (EditText) findViewById(R.id.input_namap);
        Peringkat = (EditText) findViewById(R.id.input_peringkatp);
        Tingkat = (EditText) findViewById(R.id.input_Tingkatp);
        Penyelenggara = (EditText) findViewById(R.id.input_penyelenggarap);
        btnsave = (Button) findViewById(R.id.btn_savep);
        pg = new ProgressDialog(this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setMessage("Sending Data....");
                pg.setCancelable(false);
                pg.show();

                String sNama = Nama.getText().toString();
                String sPeringkat = Peringkat.getText().toString();
                String sTingkat = Tingkat.getText().toString();
                String sPenyelenggara = Penyelenggara.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponsModel> send = api.sendPrestasi(sNama, sPeringkat, sTingkat, sPenyelenggara);
                send.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Prestasi.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Prestasi.this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");

                    }
                });
            }
        });


     /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    }
}


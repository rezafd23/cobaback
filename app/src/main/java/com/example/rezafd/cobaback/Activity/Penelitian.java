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
import android.widget.Toast;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.API.Retroserver;
import com.example.rezafd.cobaback.Model.ResponsModel;
import com.example.rezafd.cobaback.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Penelitian extends AppCompatActivity {
    EditText Nama, Bidang, Lembaga;
    Button btnsave;
    ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penelitian);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Nama = (EditText) findViewById(R.id.input_namapenelitian);
        Bidang = (EditText) findViewById(R.id.input_bidang);
        Lembaga = (EditText) findViewById(R.id.input_lembaga);
        btnsave = (Button) findViewById(R.id.btn_savepen);
        pg = new ProgressDialog(this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setMessage("Sending Data...");
                pg.setCancelable(false);
                pg.show();

                String sNama = Nama.getText().toString();
                String sBidang = Bidang.getText().toString();
                String sLembaga = Lembaga.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponsModel> send = api.sendPenelitian(sNama, sBidang, sLembaga);
                send.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Penelitian.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Penelitian.this, "Data Gagal Ditambah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("hghjkl",t.toString());
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

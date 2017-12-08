package com.example.rezafd.cobaback.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nama1,tmptlahir1,tgllahir1,alamat1,noHP1,email1,jurusan1;

    @BindView(R.id.textNRP) TextView NRP1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       // NRP1 = (TextView) findViewById(R.id.textNRP);
        nama1 = (TextView) findViewById(R.id.textNama);
        tmptlahir1 = (TextView) findViewById(R.id.texttmptlahir);
        tgllahir1 = (TextView) findViewById(R.id.texttgllahir);
        jurusan1 = (TextView) findViewById(R.id.textjurusan);
        alamat1 = (TextView) findViewById(R.id.textalamat);
        noHP1 = (TextView) findViewById(R.id.textNoHp);
        email1 = (TextView) findViewById(R.id.textemail);

        Intent intent = getIntent();
        String nrp = intent.getStringExtra("NRP");
        String nama = intent.getStringExtra("Nama");
        String tmptlahir = intent.getStringExtra("TmptLahir");
        String tgllahir = intent.getStringExtra("TglLahir");
        String jurusan = intent.getStringExtra("Jurusan");
        String alamat = intent.getStringExtra("Alamat");
        String noHP = intent.getStringExtra("NoHP");
        String email = intent.getStringExtra("Email");

        NRP1.setText(nrp);
        email1.setText(email);
        nama1.setText(nama);
        tmptlahir1.setText(tmptlahir);
        tgllahir1.setText(tgllahir);
        jurusan1.setText(jurusan);
        alamat1.setText(alamat);
        noHP1.setText(noHP);
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
            Intent intent = new Intent(MainActivity.this, Setting.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_logout) {
            Intent intent1 = new Intent(MainActivity.this, Login.class);
            startActivity(intent1);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_narasumber) {
            Intent intent = new Intent(MainActivity.this, Narasumber.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_prestasi) {
            Intent intent = new Intent(MainActivity.this, Prestasi.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_pekerjaan) {
            Intent intent = new Intent(MainActivity.this, Pekerjaan.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_penelitian) {
            Intent intent = new Intent(MainActivity.this, Penelitian.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_pm) {
            Intent intent = new Intent(MainActivity.this, Pm.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_quesioner) {
            Intent intent = new Intent(MainActivity.this, Quesioner.class);
            intent.putExtra("NRP",NRP1.getText());
            startActivity(intent);
        } else if (id == R.id.nav_home){
            Call<Profil> call = getApi().select_profile(NRP1.getText().toString());
            call.enqueue(new Callback<Profil>() {
                @Override
                public void onResponse(Call<Profil> call, Response<Profil> response) {
                    if (response.isSuccessful()) {
                        Profil res = response.body();
                        if (res.isSuccess()) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
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
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

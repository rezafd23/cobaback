package com.example.rezafd.cobaback.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.Interface.ChangePassPresenterInterface;
import com.example.rezafd.cobaback.Interface.ChangePassViewInterface;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.Presenter.ChangePassPresenter;
import com.example.rezafd.cobaback.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends BaseActivity implements ChangePassViewInterface {
    EditText Pass,Pass2,Pass3;
    TextView NRP,uNRP;
    Button  btnupdate;
    ProgressDialog pg;
    private ChangePassPresenterInterface presenter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        uNRP = (TextView) findViewById(R.id.change_NRP);
        NRP = (TextView) findViewById(R.id.settingNRP);
        Pass = (EditText) findViewById(R.id.settingCurrentPassword);
        Pass2 = (EditText) findViewById(R.id.settingPassword);
        Pass3 = (EditText) findViewById(R.id.settingConfirmPassword);
        btnupdate = (Button) findViewById(R.id.btnUpdatePass);

        String iNRP = getSp().getString("NRP",null);
        NRP.setText(iNRP);
        uNRP.setText(iNRP);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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

        pg=new ProgressDialog(this);
        ButterKnife.bind(this);
        presenter = new ChangePassPresenter(this,getApi());

        pg.setMessage("Please Wait...");
        pg.setCancelable(false);
    }

    @OnClick(R.id.btnUpdatePass) void doChange() {
        presenter.doChange(NRP.getText().toString(), Pass2.getText().toString());
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
    public void onFinishChange() {
        Toast.makeText(ChangePassword.this, "Change Password Berhasil", Toast.LENGTH_SHORT).show();
        Call<Profil> call1 = getApi().select_profile(uNRP.getText().toString());
        call1.enqueue(new Callback<Profil>() {
            @Override
            public void onResponse(Call<Profil> call, Response<Profil> response) {
                if(response.isSuccessful()) {
                    Profil res = response.body();
                    if(res.isSuccess()) {
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
}
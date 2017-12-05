package com.example.rezafd.cobaback.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.API.Retroserver;
import com.example.rezafd.cobaback.Model.ResponsModel;
import com.example.rezafd.cobaback.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insertque extends AppCompatActivity {
    TextView Q1,Q1bulan, statQ12;
    RadioButton checksblm2, checkstlh2;
    RadioGroup checkQ12;
    EditText inputQ1, NRP;
    ProgressDialog pg;
    Button btnsumbmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertque);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Q1bulan=(TextView) findViewById(R.id.Q1bulan);
        checkQ12=(RadioGroup) findViewById(R.id.checkq12);
        statQ12=(TextView) findViewById(R.id.Q1stat2);
        NRP = (EditText) findViewById(R.id.insNRP);
        inputQ1 = (EditText) findViewById(R.id.inputQ1_2);
        Q1 = (TextView) findViewById(R.id.Q1_2);
        pg = new ProgressDialog(this);
        btnsumbmit = (Button) findViewById(R.id.btnsubmit);

        btnsumbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setMessage("Sending Data...");
                pg.setCancelable(false);
                pg.show();

                String sQ1bulan=Q1bulan.getText().toString();
                String sQ1 = Q1.getText().toString();
                String sStatQ12=statQ12.getText().toString();
                String sNRP = NRP.getText().toString();
                String sinputQ1 = inputQ1.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponsModel> send = api.sendQue(sQ1, sNRP, sinputQ1+sQ1bulan+sStatQ12);
                send.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(insertque.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(insertque.this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.checksblm2:
                if (checked)
                    statQ12.setText("Sebelum Lulus");
                break;
            case R.id.checkstlh2:
                if (checked)
                    statQ12.setText("Setelah Lulus");
                break;
            case R.id.checktidak2:
                if (checked)
                    inputQ1.setEnabled(false);
                    statQ12.setText("Tidak Mencari Pekerjaan");
        }

    }
}
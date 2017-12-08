package com.example.rezafd.cobaback.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    TextView Q1,Q1bulan, statQ12,Q3_2,Q2_2,jawabQ2;
    CheckBox checkQ21_2,checkQ22_2,checkQ23_2,checkQ24_2;
    RadioButton checksblm2, checkstlh2;
    RadioGroup checkQ12;
    EditText inputQ1, NRP,inputQ3_2;
    ProgressDialog pg;
    Button btnsumbmit;
    String a ="";
    String temp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertque);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Q2_2=(TextView) findViewById(R.id.Q2_2);
        Q3_2=(TextView) findViewById(R.id.Q3_2);
        jawabQ2=(TextView) findViewById(R.id.jawabQ2);
        inputQ3_2=(EditText) findViewById(R.id.inputQ3_2);
        Q1bulan=(TextView) findViewById(R.id.Q1bulan);
        checkQ12=(RadioGroup) findViewById(R.id.checkq12);
        statQ12=(TextView) findViewById(R.id.Q1stat2);
        NRP = (EditText) findViewById(R.id.insNRP);
        inputQ1 = (EditText) findViewById(R.id.inputQ1_2);
        Q1 = (TextView) findViewById(R.id.Q1_2);
        pg = new ProgressDialog(this);
        btnsumbmit = (Button) findViewById(R.id.btnsubmit);
       // setChecBoxListener();

        btnsumbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setMessage("Sending Data...");
                pg.setCancelable(false);
                pg.show();

                String scheckQ21_2=checkQ21_2.getText().toString();
                String sQ2_2=Q2_2.getText().toString();
                String sjawabQ2=jawabQ2.getText().toString();
                String sQ1bulan=Q1bulan.getText().toString();
                String sQ1 = Q1.getText().toString();
                String sStatQ12=statQ12.getText().toString();
                String sNRP = NRP.getText().toString();
                String sinputQ1 = inputQ1.getText().toString();
                String sQ3_2=Q3_2.getText().toString();
                String sinputQ3_2=inputQ3_2.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponsModel> send = api.sendQue(sQ1, sNRP, sinputQ1+sQ1bulan+sStatQ12);
                Call<ResponsModel> send2 = api.sendQue(sQ3_2,sNRP,sinputQ3_2);
                Call<ResponsModel> send3 = api.sendQue(sQ2_2,sNRP,sjawabQ2);
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
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");


                    }
                });
                send2.enqueue(new Callback<ResponsModel>() {
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
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");

                    }
                });
//                send2.enqueue(new Callback<ResponsModel>() {
//                    @Override
//                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
//                        pg.hide();
//                        Log.d("RETRO", "response : " + response.body().toString());
//                        int kode = response.body().getKode();
//                        if (kode == 1) {
//                            Toast.makeText(insertque.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(insertque.this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponsModel> call, Throwable t) {
//
//                    }
//                });
//                send3.enqueue(new Callback<ResponsModel>() {
//                    @Override
//                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
//                        pg.hide();
//                        Log.d("RETRO","response: "+response.body().toString());
//                        int kode = response.body().getKode();
//                        if (kode==1){
//                            Toast.makeText(insertque.this, " Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(insertque.this, " Data Berhasil Ditambah",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponsModel> call, Throwable t) {
//
//                    }
//                });
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.checksblm2:
                if (checked)
                    statQ12.setText("Sebelum");
                break;
            case R.id.checkstlh2:
                if (checked)
                    statQ12.setText("Setelah");
                break;
            case R.id.checktidak2:
                if (checked)
                    inputQ1.setEnabled(false);
                statQ12.setText("Tidak Mencari Pekerjaan");
        }
    }
//    public void onClick(View view){
//        String a="";
//        if (checkQ21_2.isChecked()){
//            a+=checkQ21_2.getText().toString();
//            //jawabQ2.setText(checkQ21_2.getText().toString()+",");
//        }
//        if (checkQ22_2.isChecked()){
//            jawabQ2.setText("Dari Koran atau Majalah");
//        }
//        if (checkQ23_2.isChecked()){
//            jawabQ2.setText(checkQ23_2.getText().toString()+",");
//        }
//        if (checkQ24_2.isChecked()){
//            jawabQ2.setText(checkQ24_2.getText().toString()+",");
//        }
//        Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();
//    }
//    private void setChecBoxListener(){
//        checkQ21_2=(CheckBox) findViewById(R.id.checkQ21_2);
//        checkQ22_2=(CheckBox) findViewById(R.id.checkQ22_2);
//        checkQ23_2=(CheckBox) findViewById(R.id.checkQ23_2);
//        checkQ24_2=(CheckBox) findViewById(R.id.checkQ24_2);
//        checkQ21_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (((CheckBox) view).isChecked()){
//                    a+=checkQ21_2.getText().toString()+";";
//                    temp+=checkQ21_2.getText().toString()+";";
//                    jawabQ2.setText(a);
//                    //  jawabQ2.setText(checkQ21_2.getText().toString());
//                }
//            }
//        });
//        checkQ22_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (((CheckBox) view).isChecked()){
//                    a+=checkQ22_2.getText().toString()+";";
//                    jawabQ2.setText(a);
//                    // jawabQ2.(checkQ22_2.getText().toString());
//                }
//            }
//        });
//
//    }

}
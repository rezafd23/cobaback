package com.example.rezafd.cobaback.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.cobaback.API.ApiRequest;
import com.example.rezafd.cobaback.API.BaseActivity;
import com.example.rezafd.cobaback.API.Retroserver;
import com.example.rezafd.cobaback.Model.QueDResponse;
import com.example.rezafd.cobaback.Model.ResponsModel;
import com.example.rezafd.cobaback.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quesioner extends AppCompatActivity {
    TextView F1,F2,F3,F4,F5,F6,F7,bulan,f3stat,f4stat,Q7stat,jarak;
    EditText input_nrp,inputf3,inputQ3,inputQ4,inputQ5,checkQ2_16,checkQ7_6;
    Button btn_saveque,saveque;
    Spinner checkkodeprodi;
    RadioButton checkf3_1,checkf3_2,checkf3_3,checkQ6_1,checkQ6_2;
    RadioGroup checkf3,checkQ6;
    CheckBox checkf4_1,checkf4_2,checkf4_3,checkf4_4,checkf4_5,checkf4_6,checkf4_7,
            checkf4_8,checkf4_9,checkf4_10,checkf4_11,checkf4_12,checkf4_13,checkf4_14,checkf4_15,
            checkQ7_1,checkQ7_2,checkQ7_3,checkQ7_4,checkQ7_5;
    ProgressDialog pg;
    String b="",a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesioner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        input_nrp=(EditText) findViewById(R.id.input_nrp);
        checkf3=(RadioGroup) findViewById(R.id.checkf3);
        F1=(TextView) findViewById(R.id.F1);
        F3=(TextView) findViewById(R.id.F3);
        F4=(TextView) findViewById(R.id.F4);
        bulan=(TextView) findViewById(R.id.bulan);
        inputf3=(EditText) findViewById(R.id.inputf3);
        //inputQ3=(EditText) findViewById(R.id.inputQ3);
        //inputQ4=(EditText) findViewById(R.id.inputQ4);
        pg=new ProgressDialog(this);
        saveque=(Button) findViewById(R.id.btn_saveque);
        checkkodeprodi=(Spinner) findViewById(R.id.checkkodeprodi);
        jarak=(TextView)findViewById(R.id.jarak);



        saveque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setMessage("Sending data..");
                pg.setCancelable(false);
                pg.show();

                String sNRP=input_nrp.getText().toString();
                String sF3=F3.getText().toString();
                String sinputf3=inputf3.getText().toString();
                String sbulan=bulan.getText().toString();
                String sf3stat=f3stat.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<ResponsModel> send = api.sendQue(sF3,sNRP,sinputf3+sbulan+sf3stat);
                send.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Quesioner.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        Log.d("RETRO","response : "+"Gagal Mengirim Data");
                    }
                });

                //Quesioner 4
               // Call<ResponsModel> send2= api.sendQue(F4,sNRP)


            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.checksblm2:
                if (checked)
                    f3stat.setText(checkf3_1.getText().toString());
                break;
            case R.id.checkstlh2:
                if (checked)
                    f3stat.setText(checkf3_2.getText().toString());
                break;
            case R.id.checktidak2:
                if (checked)
                    inputf3.setEnabled(false);
                f3stat.setText("Tidak Mencari Pekerjaan");
        }

    }
    private void setChecBoxListener(){
        checkf4_1=(CheckBox) findViewById(R.id.checkf4_1);
        checkf4_2=(CheckBox) findViewById(R.id.checkf4_2);
        checkf4_3=(CheckBox) findViewById(R.id.checkf4_3);
        checkf4_4=(CheckBox) findViewById(R.id.checkf4_4);
        checkf4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f401;";
                    f4stat.setText(a);
                    //  jawabQ2.setText(checkQ21_2.getText().toString());
                }
            }
        });
        checkf4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f402;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f403;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f404;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f405;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f406;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f407;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f408;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f409;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f410;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f411;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f412;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f413;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f414;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });
        checkf4_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()){
                    a+="f415;";
                    f4stat.setText(a);
                    // jawabQ2.(checkQ22_2.getText().toString());
                }
            }
        });

    }
}

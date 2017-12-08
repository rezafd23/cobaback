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
import android.widget.CompoundButton;
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
    TextView F1, F2, F3, F4, F5, F6, F7,F7a,F8,F9,F10,F11,F12,F13,F14, bulan, f3stat, jarak,jawabf21,jawabf22,jawabf23,
            jawabf24,jawabf25,jawabf26,jawabf27,jawabf2,jawabf5,jawabf13,jawabf14,jawabf8,jawabf4;
    EditText input_nrp, inputf3, inputf5, inputf6, inputf7, inputf7a, checkQ7_6,inputf12_1,inputf12_2,inputf12_3;
    Button btn_saveque,submitque,btnsubmitque;
    Spinner checkkodeprodi;
    RadioButton checkf3_1, checkf3_2, checkf3_3, checkQ6_1, checkQ6_2;
    RadioGroup checkf3,checkf5, checkf13,checkf14,checkf8;
    CheckBox checkf4_1, checkf4_2, checkf4_3, checkf4_4, checkf4_5, checkf4_6, checkf4_7,
            checkf4_8, checkf4_9, checkf4_10, checkf4_11, checkf4_12, checkf4_13, checkf4_14, checkf4_15,
            checkconfirm, checkQ7_2, checkQ7_3, checkQ7_4, checkQ7_5;
    ProgressDialog pg;
    String b = "", a = "";

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
        checkconfirm=(CheckBox)findViewById(R.id.checkconfirm);
        bulan=(TextView) findViewById(R.id.bulan);
        f3stat=(TextView) findViewById(R.id.f3stat);
        checkf3=(RadioGroup) findViewById(R.id.checkf3);
        F3=(TextView) findViewById(R.id.F3);
        input_nrp=(EditText) findViewById(R.id.input_nrp);
        inputf3=(EditText) findViewById(R.id.inputf3);
        btnsubmitque=(Button) findViewById(R.id.btn_saveque);
        jawabf21=(TextView) findViewById(R.id.jawabf21);
        jawabf22=(TextView) findViewById(R.id.jawabf22);
        jawabf23=(TextView) findViewById(R.id.jawabf23);
        jawabf24=(TextView) findViewById(R.id.jawabf24);
        jawabf25=(TextView) findViewById(R.id.jawabf25);
        jawabf26=(TextView) findViewById(R.id.jawabf26);
        jawabf27=(TextView) findViewById(R.id.jawabf27);
        jawabf2=(TextView) findViewById(R.id.jawabf2);

        checkf4_1 = (CheckBox) findViewById(R.id.checkf4_1);
        checkf4_2 = (CheckBox) findViewById(R.id.checkf4_2);
        checkf4_3 = (CheckBox) findViewById(R.id.checkf4_3);
        checkf4_4 = (CheckBox) findViewById(R.id.checkf4_4);
        checkf4_5 = (CheckBox) findViewById(R.id.checkf4_5);
        checkf4_6 = (CheckBox) findViewById(R.id.checkf4_6);
        checkf4_7 = (CheckBox) findViewById(R.id.checkf4_7);
        checkf4_8 = (CheckBox) findViewById(R.id.checkf4_8);
        checkf4_9 = (CheckBox) findViewById(R.id.checkf4_9);
        checkf4_10 = (CheckBox) findViewById(R.id.checkf4_10);
        checkf4_11 = (CheckBox) findViewById(R.id.checkf4_11);
        checkf4_12 = (CheckBox) findViewById(R.id.checkf4_12);
        checkf4_13 = (CheckBox) findViewById(R.id.checkf4_13);
        checkf4_14 = (CheckBox) findViewById(R.id.checkf4_14);
        checkf4_15 = (CheckBox) findViewById(R.id.checkf4_15);
        pg=new ProgressDialog(this);
        F2=(TextView) findViewById(R.id.F2);
        //Question 4
        F4=(TextView)findViewById(R.id.F4);
        jawabf4=(TextView)findViewById(R.id.jawabf4);
        //Question5
        F5= (TextView) findViewById(R.id.F5);
        jawabf5=(TextView) findViewById(R.id.jawabf5);
        checkf5=(RadioGroup) findViewById(R.id.checkf5);
        inputf5=(EditText) findViewById(R.id.inputf5);
        //Question 6
        F6=(TextView) findViewById(R.id.F6);
        inputf6=(EditText) findViewById(R.id.inputf6);
        //Question 7
        F7=(TextView)findViewById(R.id.F7);
        inputf7=(EditText)findViewById(R.id.inputf7);
        //Question 7a
        F7a=(TextView)findViewById(R.id.F7a);
        inputf7a=(EditText)findViewById(R.id.inputf7a);
        //Question 8
        F8=(TextView)findViewById(R.id.F8);
        jawabf8=(TextView)findViewById(R.id.jawabf8);
        //Question 12
        F12=(TextView)findViewById(R.id.F12);
        inputf12_1=(EditText)findViewById(R.id.inputf12_1);
        inputf12_2=(EditText)findViewById(R.id.inputf12_2);
        inputf12_3=(EditText)findViewById(R.id.inputf12_3);
        //Question 13
        F13=(TextView)findViewById(R.id.F13);
        checkf13=(RadioGroup)findViewById(R.id.checkf13);
        jawabf13=(TextView)findViewById(R.id.jawabf13);
        //Question 14
        F14=(TextView)findViewById(R.id.F14);
        checkf14=(RadioGroup)findViewById(R.id.checkf14);
        jawabf14=(TextView)findViewById(R.id.jawabf14);

//        if (checkf4_1.isChecked()){
//            a+="f4_1;";}
//        else if (checkf4_2.isChecked()){
//            a+="f4_2;";}
//        jawabf4.setText(a);
//
        checkf4_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkf4_1.isChecked()){
                    a+="f4_1;";
                    jawabf4.setText(a);
                } else {
                    a="";
                    jawabf4.setText(a);
                }
            }
        });




//        checkconfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CheckBox cb= (CheckBox)view
//            }
//        });



        btnsubmitque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setMessage("Sending Data...");
                pg.setCancelable(false);
                pg.show();
                String sF3=F3.getText().toString();
                String sF2 = F2.getText().toString();
                String sjawabf2= jawabf2.getText().toString();
                String sinput_nrp=input_nrp.getText().toString();
                String sinputf3=inputf3.getText().toString();
                String sbulan=bulan.getText().toString();
                String sf3stat=f3stat.getText().toString();
                //question 4
                String sF4 = F4.getText().toString();
                String sjawabf4=jawabf4.getText().toString();
                //question 5
                String sF5=F5.getText().toString();
                String sinputf5=inputf5.getText().toString();
                String sjawabf5=jawabf5.getText().toString();
                //question 6
                String sF6=F6.getText().toString();
                String sinputf6=inputf6.getText().toString();
                //question 7
                String sF7=F7.getText().toString();
                String sinputf7=inputf7.getText().toString();
                //question 7a
                String sF7a=F7a.getText().toString();
                String sinputf7a=inputf7a.getText().toString();
                //question f8
                String sF8=F8.getText().toString();
                String sjawabf8=jawabf8.getText().toString();
                //question 12
                String sF12=F12.getText().toString();
                String sinputf12_1=inputf12_1.getText().toString();
                String sinputf12_2=inputf12_2.getText().toString();
                String sinputf12_3=inputf12_3.getText().toString();
                //question 13
                String sF13=F13.getText().toString();
                String sjawabf13=jawabf13.getText().toString();
                //question 14
                String sF14=F14.getText().toString();
                String sjawabf14=jawabf14.getText().toString();

                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<ResponsModel> send3 = api.sendQue(sF3,sinput_nrp,sinputf3+sbulan+sf3stat);
                Call<ResponsModel> send2 = api.sendQue(sF2,sinput_nrp,sjawabf2);
                Call<ResponsModel> send4 = api.sendQue(sF4,sinput_nrp,sjawabf4);
                Call<ResponsModel> send5 = api.sendQue(sF5,sinput_nrp,sinputf5+sjawabf5);
                Call<ResponsModel> send6 = api.sendQue(sF6,sinput_nrp,sinputf6);
                Call<ResponsModel> send7 = api.sendQue(sF7,sinput_nrp,sinputf7);
                Call<ResponsModel> send7a = api.sendQue(sF7a,sinput_nrp,sinputf7a);
                Call<ResponsModel> send8 = api.sendQue(sF8,sinput_nrp,sjawabf8);
                Call<ResponsModel> send12 = api.sendQue(sF12,sinput_nrp,sinputf12_1+";"+sinputf12_2+";"+sinputf12_3);
                Call<ResponsModel> send13 = api.sendQue(sF13,sinput_nrp,sjawabf13);
                Call<ResponsModel> send14 = api.sendQue(sF14,sinput_nrp,sjawabf14);

                //send question 3
                send3.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Ketiga masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });

                //send question 2
                send2.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Kedua masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });
                //send question 4


                send4.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Keempat masih Kosong", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });


                //send question 5
                send5.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();
                        if (kode == 1) {
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Kelima masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });

                //Send question 6
                send6.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode =response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Keenam masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
                //send question 7
                send7.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode =response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Ketujuh masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
                //send question 7a
                send7a.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode= response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan 7a masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
                //Send question 8
                send8.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode= response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Kedelapan masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
                //Send question 12
                send12.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode= response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan 7a masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
                //Send question 13
                send13.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode= response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Ketiga belas masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
                send14.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pg.hide();
                        Log.d("RETRO","response : "+response.body().toString());
                        int kode= response.body().getKode();
                        if (kode==1){
                            Toast.makeText(Quesioner.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Quesioner.this, "Pertanyaan Keempat Belas masih Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pg.hide();
                        Log.d("RETRO","response : "+ "Gagal Mengirim Request");
                    }
                });
            }
        });


    }

    //getRadioF3
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.checkf3_1:
                if (checked)
                    f3stat.setText("f3_1");
                inputf3.setEnabled(true);
                break;
            case R.id.checkf3_2:
                if (checked)
                    f3stat.setText("f3_2");
                inputf3.setEnabled(true);
                break;
            case R.id.checkf3_3:
                if (checked)
                    inputf3.setEnabled(false);
                f3stat.setText("f3_3");
                break;

            //jawaban f21
            case R.id.checkf21_1:
                if (checked)
                    jawabf21.setText("f21_1;");
                break;
            case R.id.checkf21_2:
                if (checked)
                    jawabf21.setText("f21_2;");
                break;
            case R.id.checkf21_3:
                if (checked)
                    jawabf21.setText("f21_3;");
                break;
            case R.id.checkf21_4:
                if (checked)
                    jawabf21.setText("f21_4;");
                break;
            case R.id.checkf21_5:
                if (checked)
                    jawabf21.setText("f21_5;");
                break;

            //jawaban f22
            case R.id.checkf22_1:
                if (checked)
                    jawabf22.setText("f22_1;");
                break;
            case R.id.checkf22_2:
                if (checked)
                    jawabf22.setText("f22_2;");
                break;
            case R.id.checkf22_3:
                if (checked)
                    jawabf22.setText("f22_3;");
                break;
            case R.id.checkf22_4:
                if (checked)
                    jawabf22.setText("f22_4;");
                break;
            case R.id.checkf22_5:
                if (checked)
                    jawabf22.setText("f22_5;");
                break;

            //jawaban f23
            case R.id.checkf23_1:
                if (checked)
                    jawabf23.setText("f23_1;");
                break;
            case R.id.checkf23_2:
                if (checked)
                    jawabf23.setText("f23_2;");
                break;
            case R.id.checkf23_3:
                if (checked)
                    jawabf23.setText("f23_3;");
                break;
            case R.id.checkf23_4:
                if (checked)
                    jawabf23.setText("f23_4;");
                break;
            case R.id.checkf23_5:
                if (checked)
                    jawabf23.setText("f23_5;");
                break;

            //jawaban f24
            case R.id.checkf24_1:
                if (checked)
                    jawabf24.setText("f24_1;");
                break;
            case R.id.checkf24_2:
                if (checked)
                    jawabf24.setText("f24_2;");
                break;
            case R.id.checkf24_3:
                if (checked)
                    jawabf24.setText("f24_3;");
                break;
            case R.id.checkf24_4:
                if (checked)
                    jawabf24.setText("f24_4;");
                break;
            case R.id.checkf24_5:
                if (checked)
                    jawabf24.setText("f24_5;");
                break;

            //jawaban f25
            case R.id.checkf25_1:
                if (checked)
                    jawabf25.setText("f25_1;");
                break;
            case R.id.checkf25_2:
                if (checked)
                    jawabf25.setText("f25_2;");
                break;
            case R.id.checkf25_3:
                if (checked)
                    jawabf25.setText("f25_3;");
                break;
            case R.id.checkf25_4:
                if (checked)
                    jawabf25.setText("f25_4;");
                break;
            case R.id.checkf25_5:
                if (checked)
                    jawabf25.setText("f25_5;");
                break;

            //jawaban f26
            case R.id.checkf26_1:
                if (checked)
                    jawabf26.setText("f26_1;");
                break;
            case R.id.checkf26_2:
                if (checked)
                    jawabf26.setText("f26_2;");
                break;
            case R.id.checkf26_3:
                if (checked)
                    jawabf26.setText("f26_3;");
                break;
            case R.id.checkf26_4:
                if (checked)
                    jawabf26.setText("f26_4;");
                break;
            case R.id.checkf26_5:
                if (checked)
                    jawabf26.setText("f26_5;");
                break;

            //jawaban f27
            case R.id.checkf27_1:
                if (checked)
                    jawabf27.setText("f27_1");
                break;
            case R.id.checkf27_2:
                if (checked)
                    jawabf27.setText("f27_2");
                break;
            case R.id.checkf27_3:
                if (checked)
                    jawabf27.setText("f27_3");
                break;
            case R.id.checkf27_4:
                if (checked)
                    jawabf27.setText("f27_4");
                break;
            case R.id.checkf27_5:
                if (checked)
                    jawabf27.setText("f27_5");
                break;

            //jawaban f5
            case R.id.checkf5_1:
                if (checked)
                    jawabf5.setText(" f5_1");
                break;
            case R.id.checkf5_2:
                if (checked)
                    jawabf5.setText(" f5_2");
                break;

            //jawaban f8
            case R.id.checkf8_1:
                if (checked)
                    jawabf8.setText("f8_1");
                break;
            case R.id.checkf8_2:
                if (checked)
                    jawabf8.setText("f8_2");
                break;

            //jawaban f13
            case R.id.checkf13_1:
                if (checked)
                    jawabf13.setText("f13_1");
                break;
            case R.id.checkf13_2:
                if (checked)
                    jawabf13.setText("f13_2");
                break;
            case R.id.checkf13_3:
                if (checked)
                    jawabf13.setText("f13_3");
                break;
            case R.id.checkf13_4:
                if (checked)
                    jawabf13.setText("f13_4");
                break;
            case R.id.checkf13_5:
                if (checked)
                    jawabf13.setText("f13_5");
                break;
            //jawaban f14
            case R.id.checkf14_1:
                if (checked)
                    jawabf14.setText("f14_1");
                break;
            case R.id.checkf14_2:
                if (checked)
                    jawabf14.setText("f14_2");
                break;
            case R.id.checkf14_3:
                if (checked)
                    jawabf14.setText("f14_3");
                break;
            case R.id.checkf14_4:
                if (checked)
                    jawabf14.setText("f14_4");
                break;

        }
        //String jfk2=;
        jawabf2.setText(jawabf21.getText().toString()+jawabf22.getText().toString()+jawabf23.getText().toString()+jawabf24.getText().toString()+jawabf25.getText().toString()+jawabf26.getText().toString()+jawabf27.getText().toString());
    }

}
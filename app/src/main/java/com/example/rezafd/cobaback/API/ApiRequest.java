package com.example.rezafd.cobaback.API;

import com.example.rezafd.cobaback.Model.ChangePassResponse;
import com.example.rezafd.cobaback.Model.Profil;
import com.example.rezafd.cobaback.Model.QueDResponse;
import com.example.rezafd.cobaback.Model.RegisterResponse;
import com.example.rezafd.cobaback.Model.ResponsModel;
import com.example.rezafd.cobaback.Model.SelectPassResponse;
import com.example.rezafd.cobaback.Model.UpdateProfileResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by REZAFD on 18/11/2017.
 */

public interface ApiRequest {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendPrestasi(@Field("Nama") String nama,
                                    @Field("Peringkat") String peringkat,
                                    @Field("Tingkat") String tingkat,
                                    @Field("Penyelenggara") String penyelenggara);

    @GET("SELECT2.php")
    Call<ResponsModel> getProfile();

    @FormUrlEncoded
    @POST("insertn.php")
    Call<ResponsModel> sendNarasumber(@Field("Nama") String nama,
                                      @Field("Tempat") String tempat,
                                      @Field("Penyelenggara") String penyelenggara,
                                      @Field("Tgl") String tgl);

    @FormUrlEncoded
    @POST("insertpeker.php")
    Call<ResponsModel> sendPekerjaan(@Field("Nama") String nama,
                                     @Field("Tempat") String tempat,
                                     @Field("Status") String status,
                                     @Field("Gaji") String gaji);

    @FormUrlEncoded
    @POST("insertpen.php")
    Call<ResponsModel> sendPenelitian(@Field("Nama") String nama,
                                      @Field("Bidang") String bidang,
                                      @Field("Lembaga") String lembaga);

    @FormUrlEncoded
    @POST("insertpm.php")
    Call<ResponsModel> sendPm(@Field("Nama") String nama,
                              @Field("Waktu") String waktu,
                              @Field("Tempat") String tempat);

    @FormUrlEncoded
    @POST("ins_que_sedikit.php")
    Call<ResponsModel> sendQue(@Field("ID") String ID,
                               @Field("NRP") String NRP,
                               @Field("Jawab") String Jawab);
    @FormUrlEncoded
    @POST("update_pass_account.php")
    Call<ChangePassResponse> update (@Field("NRP") String nrp,
                                     @Field("password") String pass,
                                     @Field("email") String email);

    @FormUrlEncoded
    @POST("login.php")
    Call<Profil> login (@Field("NRP") String nrp,
                        @Field("password") String pwd);

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register (@Field("NRP") String nrp,
                                     @Field("password") String pass,
                                     @Field("email") String email);

    @FormUrlEncoded
    @POST("select_changepass.php")
    Call<SelectPassResponse> select_pass (@Field("NRP") String nrp);

    @FormUrlEncoded
    @POST("selectpass.php")
    Call<ChangePassResponse> selectpass(@Field("NRP") String nrp,
                                        @Field("password") String pass);

    @FormUrlEncoded
    @POST("select_change.php")
    Call<Profil> select_profile(@Field("NRP") String nrp);

    @FormUrlEncoded
    @POST("update_pass.php")
    Call<UpdateProfileResponse> update_profile(@Field("NRP") String nrp,
                                               @Field("Nama") String nama,
                                               @Field("TmptLahir") String tmptlahir,
                                               @Field("TglLahir") String tgllahir,
                                               @Field("Jurusan") String jurusan,
                                               @Field("Alamat") String alamat,
                                               @Field("NoHp") String nohp,
                                               @Field("Email") String email);
}

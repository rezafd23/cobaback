package com.example.rezafd.cobaback.Model;

/**
 * Created by REZAFD on 02/12/2017.
 */

public class LoginResponse {
    private String NRP;
    private String Nama;
    private String TmptLahir;
    private String Jurusan;
    private String Alamat;
    private String NoHP;
    private String Email;
    private String TglLahir;
    private boolean success;

    public void setTglLahir(String tglLahir) {
        TglLahir = tglLahir;
    }

    public String getTglLahir() {
        return TglLahir;
    }

    public String getNRP() {
        return NRP;
    }

    public void setNRP(String NRP) {
        this.NRP = NRP;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getTmptLahir() {
        return TmptLahir;
    }

    public void setTmptLahir(String tmptLahir) {
        TmptLahir = tmptLahir;
    }

    public String getJurusan() {
        return Jurusan;
    }

    public void setJurusan(String jurusan) {
        Jurusan = jurusan;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getNoHP() {
        return NoHP;
    }

    public void setNoHP(String noHP) {
        NoHP = noHP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

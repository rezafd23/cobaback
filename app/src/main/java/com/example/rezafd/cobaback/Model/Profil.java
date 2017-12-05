package com.example.rezafd.cobaback.Model;

/**
 * Created by REZAFD on 18/11/2017.
 */

public class Profil {
    private String NRP,Nama,TmptLahir,TglLahir,Jurusan,Alamat,NoHp,Email;
    private boolean success;

    public Profil(String NRP, String nama, String tmptLahir, String tglLahir, String jurusan, String alamat, String noHp, String email) {
        this.NRP = NRP;
        Nama = nama;
        TmptLahir = tmptLahir;
        TglLahir = tglLahir;
        Jurusan = jurusan;
        Alamat = alamat;
        NoHp = noHp;
        Email = email;
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

    public String getTglLahir() {
        return TglLahir;
    }

    public void setTglLahir(String tglLahir) {
        TglLahir = tglLahir;
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

    public String getNoHp() {
        return NoHp;
    }

    public void setNoHp(String noHp) {
        NoHp = noHp;
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

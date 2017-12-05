package com.example.rezafd.cobaback.Model;

import java.util.List;

/**
 * Created by REZAFD on 18/11/2017.
 */

public class ResponsModel {
    String  Pesan;
    int kode;
    List<Profil> result;

    public ResponsModel(String pesan, int kode, List<Profil> result) {
        Pesan = pesan;
        this.kode = kode;
        this.result = result;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public List<Profil> getResult() {
        return result;
    }

    public void setResult(List<Profil> result) {
        this.result = result;
    }
}

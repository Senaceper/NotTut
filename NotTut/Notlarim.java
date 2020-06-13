package com.senaceper.nottut;

import java.io.Serializable;

public class Notlarim {
    private int id;
    private String konu;
    private String notum;
    private String tarih;


    public Notlarim()
    {

    }

    public Notlarim(String konu, String notum, String tarih) {
        this.konu = konu;
        this.notum = notum;
        this.tarih = tarih;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getNotum() {
        return notum;
    }

    public void setNotum(String notum) {
        this.notum = notum;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}

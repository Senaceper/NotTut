package com.senaceper.nottut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String veritabani_adi="veritabani_notlar";
    private static final String notlar_tablosu="not_tablo";
    private static final int veritabani_versiyon = 1;





    public Database(Context context) {
        super(context, veritabani_adi, null, veritabani_versiyon);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlNotTablosuOlusturma = "create TABLE "+notlar_tablosu+" ( ID INTEGER PRIMARY KEY AUTOINCREMENT,notunKonusu Text, notunAdi Text , notunTarihi DATE )";
        db.execSQL(sqlNotTablosuOlusturma);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE "+notlar_tablosu);

    }

    public long ekleNotum(Notlarim notum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("notunKonusu",notum.getKonu());
        cv.put("notunAdi",notum.getNotum());
        cv.put("notunTarihi",notum.getTarih());

        long id = db.insert(notlar_tablosu,null,cv);
        return id;
    }

    public ArrayList<String> getirNotListesi() {
        ArrayList<String> notlarList = new ArrayList<String>();

        SQLiteDatabase db =  this.getReadableDatabase();
        String sqlQuery =  "SELECT * FROM "+notlar_tablosu;

        Cursor c =  db.rawQuery(sqlQuery,null);
        int siraNoID = c.getColumnIndex("ID");
        int siraNoKonu= c.getColumnIndex("notunKonusu");
        int siraNoAd= c.getColumnIndex("notunAdi");
        int siraNoTarih = c.getColumnIndex("notunTarihi");

        try
        {
                while(c.moveToNext())
                {


                    String konu = c.getString(siraNoKonu);
                    String notum = c.getString(siraNoAd);
                    String tarih = c.getString(siraNoTarih);
                    notlarList.add( konu + "  " + notum + "  "  + tarih);

                }
        }
        finally {
            c.close();
            db.close();
        }
        return  notlarList;
    }


    public Notlarim SecilenNot(String konu) {
        Notlarim secilen = new Notlarim();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "Select * from "+notlar_tablosu+" where notunKonusu = '"+konu+"' ";
        Cursor c = db.rawQuery(sqlQuery,null);

        int siraNoID = c.getColumnIndex("ID");
        int siraNoKonu= c.getColumnIndex("notunKonusu");
        int siraNoAd= c.getColumnIndex("notunAdi");
        int siraNoTarih = c.getColumnIndex("notunTarihi");

        secilen.setId(c.getInt(siraNoID));
        secilen.setKonu(c.getString(siraNoKonu));
        secilen.setTarih(c.getString(siraNoTarih));
        secilen.setNotum(c.getString(siraNoTarih));

        return secilen;



    }


    public void guncelle(Notlarim guncel, String eski_konu) {

        SQLiteDatabase db =  this.getWritableDatabase();
        db.execSQL("UPDATE "+notlar_tablosu+" SET notunKonusu = '"+guncel.getKonu()+"', notunAdi = '"+guncel.getNotum()+"',notunTarihi = '"+guncel.getTarih()+"' WHERE notunKonusu='"+eski_konu+"' ");




    }

    public void sil(String ekonu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+notlar_tablosu+" WHERE notunKonusu = '"+ekonu+"'");
    }
}

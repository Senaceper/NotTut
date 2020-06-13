package com.senaceper.nottut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Goruntule extends AppCompatActivity {

    EditText Ekonu, Etarih, Eyapilacak;
    String secilen_not ;
    String onceki,konu,tarih,notu;
    String[] s_not;
    Notlarim secilen =  new Notlarim();
    Notlarim guncel = new Notlarim();
    Database db;
    Button guncelle,sil;
    int i=0;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goruntule);
         db =  new Database(getApplicationContext());
        Ekonu=(EditText)findViewById(R.id.konu);
        Etarih=(EditText)findViewById(R.id.tarih);
        Eyapilacak=(EditText)findViewById(R.id.yapilacak);
        guncelle=(Button)findViewById(R.id.button2);
        sil=(Button)findViewById(R.id.button);

        Bundle bundle = getIntent().getExtras();
        secilen_not = bundle.getString("Yollanan");

         s_not= secilen_not.split(" ");
         while(i<s_not.length)
        {
            if(i==0)
            {
                Ekonu.setText(s_not[i]);
                konu=s_not[i];
            }

            else if(i==s_not.length-1)
            {
                Etarih.setText(s_not[i]);
            }
            else
            {
                onceki= Eyapilacak.getText().toString();
                Eyapilacak.setText(onceki + " " + s_not[i]);
            }
            i++;
        }
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guncelle();
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

         sil.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Sil();
                 Intent i = new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(i);
             }
         });

    }

    public void Guncelle()
    {
        guncel.setKonu(konu);
        guncel.setNotum(Eyapilacak.getText().toString());
        guncel.setTarih(Etarih.getText().toString());
        db.guncelle(guncel, konu);
        Toast.makeText(getApplicationContext(),"Güncelleme yapıldı",Toast.LENGTH_LONG).show();
    }

    public void Sil()
    {
        db.sil(Ekonu.getText().toString());
        Toast.makeText(getApplicationContext(),"Silme işlemi tamamlandı",Toast.LENGTH_LONG).show();
    }

}

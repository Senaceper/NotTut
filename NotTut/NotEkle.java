package com.senaceper.nottut;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NotEkle extends AppCompatActivity {


    Context context=this;
    Button btn_kaydet;
    EditText eTarih, notlar , konu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);

        btn_kaydet=(Button)findViewById(R.id.kaydet);
        notlar=(EditText)findViewById(R.id.notum);
        konu= (EditText)findViewById(R.id.konu);
        eTarih=(EditText)findViewById(R.id.tarih);

        eTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar takvim= Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay =  takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd= new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view , int yil, int ay, int gun) {
                        ay+=1;
                        eTarih.setText(gun+"/"+ay+"/"+yil);
                    }
                } , yil , ay , gun);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Seç",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Iptal",dpd);
                dpd.show();
            }
        });

        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _konu = konu.getText().toString();
                String _notlar= notlar.getText().toString();
                String _tarih= eTarih.getText().toString();

                if( _konu.isEmpty() || _notlar.isEmpty() || _tarih.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Alanları boş geçmeyiniz", Toast.LENGTH_LONG ).show();
                    return;
                }
                else
                {
                    Notlarim _notum= new Notlarim(_konu,_notlar, _tarih);
                    Database db =  new Database(getApplicationContext());

                    long id= db.ekleNotum(_notum);

                    if(id>0)
                    {

                        Toast.makeText(getApplicationContext(),"Kayıt başarılı",Toast.LENGTH_LONG).show();
                        konu.setText("");
                        notlar.setText("");
                        eTarih.setText("");
                        Intent i =  new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Kayıt başarısız",Toast.LENGTH_LONG).show();
                    }


                }


            }
        });


    }
}

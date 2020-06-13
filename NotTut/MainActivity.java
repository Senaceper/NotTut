package com.senaceper.nottut;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int secilen_id;
    Notlarim secilen_not;
    String[] notlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView list_not= (ListView)findViewById(R.id.listnotlar);
        final Database db =  new Database(getApplicationContext());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NotEkle.class));
            }
        });



        ArrayList<String> _notlarList = new ArrayList<String>();
        _notlarList = db.getirNotListesi();
        ListAdapter myListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, _notlarList);
        list_not.setAdapter(myListAdapter);

        list_not.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected =  (String ) parent.getItemAtPosition(position);
              Toast.makeText(getApplicationContext(),"Seçilen öğe : "+ selected,Toast.LENGTH_LONG).show();
               Intent intent = new Intent(getApplicationContext(), Goruntule.class);
                intent.putExtra("Yollanan",  selected);
                startActivity(intent);

            }
        });


    }
}

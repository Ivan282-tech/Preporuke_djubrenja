package com.example.preporukedjubrenja;

import android.database.Cursor;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText humus = findViewById(R.id.humus);
        EditText N = findViewById(R.id.N);
        Button dugme = findViewById(R.id.dugmeUnesi);

        N.setKeyListener(null);

        Spinner biljke = findViewById(R.id.biljke);
        Spinner prinosi = findViewById(R.id.prinosi);

        DataBaseHelper helper = new DataBaseHelper(MainActivity.this);

        Cursor biljkeCursor = helper.uzmiBiljke();

        ArrayList<String> idINaziviBiljaka = new ArrayList<>();

        if (biljkeCursor != null && biljkeCursor.moveToFirst()) {

            int columnIndexId = biljkeCursor.getColumnIndex("_id");
            int columnIndexNaziv = biljkeCursor.getColumnIndex("naziv");

            do {
                int idBiljke = biljkeCursor.getInt(columnIndexId);
                String nazivBiljke = biljkeCursor.getString(columnIndexNaziv);
                String idINaziv = idBiljke + " - " + nazivBiljke;
                idINaziviBiljaka.add(idINaziv);
            } while (biljkeCursor.moveToNext());
        }

            ArrayAdapter<String> biljkeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, idINaziviBiljaka);


            biljkeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            biljke.setAdapter(biljkeAdapter);

            humus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    double Humus = Double.parseDouble(humus.getText().toString());
                    double rezultat = Humus * 0.06;
                    N.setText(String.valueOf(rezultat));
                }catch (NumberFormatException e){
                    N.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

      biljke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

          }
      });

    }





}
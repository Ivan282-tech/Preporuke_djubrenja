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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner prinosi;
    Spinner biljke;
    Button dugme;
    DataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText humus = findViewById(R.id.humus);
        EditText N = findViewById(R.id.N);
        dugme = findViewById(R.id.dugmeUnesi);

        N.setKeyListener(null);

         biljke = findViewById(R.id.biljke);
         prinosi = findViewById(R.id.prinosi);

        helper = new DataBaseHelper(MainActivity.this);


        Cursor biljkeCursor = helper.uzmiBiljke();


        List<Biljka> biljkeList = new ArrayList<>();
        if (biljkeCursor != null && biljkeCursor.moveToFirst()) {

            int columnIndexId = biljkeCursor.getColumnIndex("_id");
            int columnIndexNaziv = biljkeCursor.getColumnIndex("naziv");

            do {
                int idBiljke = biljkeCursor.getInt(columnIndexId);
                String nazivBiljke = biljkeCursor.getString(columnIndexNaziv);
                Biljka biljka = new Biljka(idBiljke, nazivBiljke);
                biljkeList.add(biljka);
            } while (biljkeCursor.moveToNext());
        }

            BiljkaAdapter biljkaAdapter = new BiljkaAdapter(MainActivity.this, biljkeList);
            biljke.setAdapter(biljkaAdapter);


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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Ovdje se izvršava kôd kada se odabere nova vrijednost u Spinneru
                // Možete dohvatiti odabranu vrijednost i izvršiti potrebne akcije

                Biljka selectedBiljka = (Biljka) parent.getItemAtPosition(position);
                int selektovaniID = selectedBiljka.getId();
                helper = new DataBaseHelper(MainActivity.this);

                Cursor prinosiCursor = helper.uzmiPrinos(selektovaniID);

                if (prinosiCursor != null && prinosiCursor.moveToFirst()) {

                    int columnIndexBrojevi= prinosiCursor.getColumnIndex("brojevi");
                    try {
                        String brojevi = prinosiCursor.getString(columnIndexBrojevi);
                        String[] ulaz = brojevi.split(" ");
                        List<Float> listaBrojeva = new ArrayList<>();

                        for (String brojStr : ulaz) {
                            try {
                                float broj = Float.parseFloat(brojStr);
                                listaBrojeva.add(broj);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        prinosi = findViewById(R.id.prinosi);
                        ArrayAdapter<Float> prinosiAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, listaBrojeva);
                        prinosi.setAdapter(prinosiAdapter);

                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "GREŠKA", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }





}
package com.example.preporukedjubrenja;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "preporuke_djubriva.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableBiljke = "CREATE TABLE IF NOT EXISTS Biljke (_id INTEGER, naziv TEXT);";
        db.execSQL(createTableBiljke);

        String createTablePrinosi = "CREATE TABLE IF NOT EXISTS Prinosi (_id INTEGER PRIMARY KEY AUTOINCREMENT, biljka_id INTEGER, brojevi TEXT, FOREIGN KEY(biljka_id) REFERENCES Biljke(_id));";
        db.execSQL(createTablePrinosi);

        String insertBiljka1 = "INSERT INTO Biljke (_id, naziv) VALUES(1, 'PŠENICA');";
        db.execSQL(insertBiljka1);

        String insertBiljka2 = "INSERT INTO Biljke (_id, naziv) VALUES(2, 'JEČAM');";
        db.execSQL(insertBiljka2);

        String insertBiljka3 = "INSERT INTO Biljke (_id, naziv) VALUES(3, 'KUKURUZ');";
        db.execSQL(insertBiljka3);

        String insertBiljka4 = "INSERT INTO Biljke (_id, naziv) VALUES(4, 'SUNCOKRET');";
        db.execSQL(insertBiljka4);

        String insertBiljka5 = "INSERT INTO Biljke (_id, naziv) VALUES(5, 'SOJA');";
        db.execSQL(insertBiljka5);

        String insertBiljka6 = "INSERT INTO Biljke (_id, naziv) VALUES(6, 'ŠEĆERNA REPA');";
        db.execSQL(insertBiljka6);

        String insertBiljka7 = "INSERT INTO Biljke (_id, naziv) VALUES(7, 'LUCERKA');";
        db.execSQL(insertBiljka7);

        String insertBiljka8 = "INSERT INTO Biljke (_id, naziv) VALUES(8, 'PAPRIKA');";
        db.execSQL(insertBiljka8);

        String insertBiljka9 = "INSERT INTO Biljke (_id, naziv) VALUES(9, 'ARPADŽIK');";
        db.execSQL(insertBiljka9);

        String insertPrinos1 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(1, '4 5 6 7');";
        db.execSQL(insertPrinos1);

        String insertPrinos2 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(2, '4 5 6 7');";
        db.execSQL(insertPrinos2);

        String insertPrinos3 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(3, '5 6 7 8 9 10');";
        db.execSQL(insertPrinos3);

        String insertPrinos4 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(4, '2 2.4 2.8 3');";
        db.execSQL(insertPrinos4);

        String insertPrinos5 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(5, '1.8 2.2 2.6 3');";
        db.execSQL(insertPrinos5);

        String insertPrinos6 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(6, '35 40 45 50');";
        db.execSQL(insertPrinos6);

        String insertPrinos7 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(7, '50 60 70 80 90 100');";
        db.execSQL(insertPrinos7);

        String insertPrinos8 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(8, '9');";
        db.execSQL(insertPrinos8);

        String insertPrinos9 = "INSERT INTO Prinosi(biljka_id, brojevi) VALUES(9, '5');";
        db.execSQL(insertPrinos9);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ažuriranje baze podataka ako je verzija promijenjena
        db.execSQL("DROP TABLE IF EXISTS Biljke");
        onCreate(db);
    }

    public Cursor uzmiBiljke(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Biljke order by 1 asc";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor uzmiPrinos(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT brojevi FROM Prinosi WHERE biljka_id = ? ORDER BY 1 ASC";
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        return cursor;
    }

}

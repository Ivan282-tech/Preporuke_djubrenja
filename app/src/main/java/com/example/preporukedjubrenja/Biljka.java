package com.example.preporukedjubrenja;

public class Biljka {
    private int id;
    private String naziv;


    public Biljka(int id, String naziv){
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;

    }
}

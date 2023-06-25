package com.example.preporukedjubrenja;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class BiljkaAdapter extends ArrayAdapter<Biljka> {
    public BiljkaAdapter(Context context, List<Biljka> biljkeList) {
        super(context, android.R.layout.simple_spinner_item, biljkeList);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(getItem(position).getNaziv());
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        textView.setText(getItem(position).toString());
        return textView;

    }
}

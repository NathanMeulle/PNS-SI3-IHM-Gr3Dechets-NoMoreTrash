package com.example.nomoretrash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adapter extends ArrayAdapter<String> {

    private String text="";

    public Adapter(Context context, String[] values) {
        super(context, R.layout.liste_signalement_recap, values);
    }


    private Integer[] tab_images_pour_la_liste = {
            R.drawable.icon_valide,
            R.drawable.icon_encours,
            R.drawable.icon_attente,};

    @Override
    public View getView(int position , View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.liste_signalement_recap, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(getItem(position));
        if(convertView==null){
            imageView.setImageResource(tab_images_pour_la_liste[position]);
            textView.setText(text);
        }

        else{
            rowView = (View)convertView;
        }
        System.out.println(text);
        return rowView;
    }

    public void setText(String text) {
        this.text = text;
    }
}

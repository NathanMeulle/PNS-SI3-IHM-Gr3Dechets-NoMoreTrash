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

import java.lang.reflect.Array;

public class Adapter extends ArrayAdapter<String> {

    private String[] values;
    private String text="";
    private Integer[] tab_images = {
            R.drawable.icon_valide,
            R.drawable.icon_encours,
            R.drawable.icon_attente,};
    private Integer[] tab_images_pour_la_liste;

    public Adapter(Context context, String[] values) {
        super(context, R.layout.liste_signalement_recap, values);
        this.values = values;
        tab_images_pour_la_liste = new Integer[values.length];
    }


    @Override
    public View getView(int position , View convertView, ViewGroup parent) {
        //Mise à jour des images
        for (int i = 0; i<values.length; i ++){
            if(values[position].contains("Traité"))
                tab_images_pour_la_liste[i] = tab_images[0];
            else {
                if (values[position].contains("attente"))
                    tab_images_pour_la_liste[i] = tab_images[2];
                else
                    tab_images_pour_la_liste[i] = tab_images[1];
            }
        }

        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.liste_signalement_recap, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(getItem(position));
        if(convertView==null){
            imageView.setImageResource(tab_images_pour_la_liste[position]);
            textView.setText(values[position]);
        }

        else{
            rowView = (View)convertView;
        }
        System.out.println(text);
        return rowView;
    }

}

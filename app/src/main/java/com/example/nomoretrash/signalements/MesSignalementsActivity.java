package com.example.nomoretrash.signalements;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.R;

import java.util.ArrayList;
import java.util.Collections;

public class MesSignalementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_signalements);

        ListView listView = findViewById(R.id.signalementList);

        Intent intent = getIntent();
        if(intent.hasExtra("ma_liste_de_signalements")){
            ArrayList<String> res = intent.getStringArrayListExtra("ma_liste_de_signalements");
            Collections.reverse(res);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, res);


        TextView emptyText = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyText);listView.setAdapter(adapter);
    }




        final Button buttonStat = findViewById(R.id.boutonRetour);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MesSignalementsActivity.this.finish();
            }
        });

    }


}

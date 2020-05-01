package com.example.nomoretrash.signalements;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.R;

import com.example.nomoretrash.SignalementsObjectsList;



import java.util.ArrayList;
import java.util.Collections;

public class MesSignalementsActivity extends AppCompatActivity implements SignalementsObjectsList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_signalements);


        //Liste de signalements
        ListView listView = findViewById(R.id.signalementList);

        Intent intent = getIntent();
        if (intent.hasExtra("ma_liste_de_signalements")) {
            ArrayList<String> res = intent.getStringArrayListExtra("ma_liste_de_signalements");
            Collections.reverse(res);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, res);


            TextView emptyText = findViewById(android.R.id.empty);
            listView.setEmptyView(emptyText);
            listView.setAdapter(adapter);


            ImageButton share = findViewById(R.id.Share);
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    Intent intent = getIntent();
                    int nbDechet = intent.getStringArrayListExtra("ma_liste_de_signalements").size();
                    String textShare = "J'ai pu signaler " + nbDechet + " déchet" + ((nbDechet > 1) ? "s " : " ");
                    if (nbDechet != 0) {
                        String date = SignalementsObjectsList.signalementsObjetsArray.get(0).getDate();
                        textShare = textShare + "depuis le " + date + " grâce à l'application noMoreTrash ! Télécharge la vite sur le Google Play store pour sauver notre planête !";
                    } else {
                        textShare = textShare + " il faudrait donc que j'utilise l'application noMoreTrash plus souvent ! Viens vite la télécharger pour m'aider !";
                    }
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, textShare);
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                }
            });


            //Bouton Retour
            final Button buttonStat = findViewById(R.id.boutonRetour);
            buttonStat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MesSignalementsActivity.this.finish();
                }
            });
        }
    }

}

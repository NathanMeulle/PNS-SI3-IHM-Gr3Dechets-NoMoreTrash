package com.example.nomoretrash.signalements.mes_signalements;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nomoretrash.R;

import java.util.ArrayList;
import java.util.Collections;

public class MesSignalementsActivity extends ListActivity implements SignalementsObjectsList {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_signalements);


        //Liste de signalements
        listView = getListView();

        Intent intent = getIntent();
        if (intent.hasExtra("ma_liste_de_signalements")) {
            updateSignalementList(listView, intent);


            ImageButton share = findViewById(R.id.Share);
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    Intent intent = getIntent();
                    String textShare = createMessage(intent);
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

    private void updateSignalementList(ListView listView, Intent intent) {
        ArrayList<String> res = intent.getStringArrayListExtra("ma_liste_de_signalements");
        Collections.reverse(res);
        String[] values = res.toArray(new String[res.size()]);

        Adapter adapter = new Adapter(getApplicationContext(), values);

        TextView emptyText = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyText);
        setListAdapter(adapter);
    }

    private String createMessage(Intent intent) {
        int nbDechet = intent.getStringArrayListExtra("ma_liste_de_signalements").size();
        String textShare = "J'ai pu signaler " + nbDechet + " déchet" + ((nbDechet > 1) ? "s " : " ");
        if (nbDechet != 0) {
            String date = SignalementsObjectsList.signalementsObjetsArray.get(0).getDate();
            textShare = textShare + "depuis le " + date + " grâce à l'application noMoreTrash ! Télécharge la vite sur le Google Play store pour sauver notre planête !";
        } else {
            textShare = textShare + " il faudrait donc que j'utilise l'application noMoreTrash plus souvent ! Viens vite la télécharger pour m'aider !";
        }
        return textShare;
    }

}
package com.example.nomoretrash.signalements;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.R;

import com.example.nomoretrash.SignalementsObjectsList;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;


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
        if(intent.hasExtra("ma_liste_de_signalements")){
            ArrayList<String> res = intent.getStringArrayListExtra("ma_liste_de_signalements");
            Collections.reverse(res);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, res);


        TextView emptyText = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyText);
        listView.setAdapter(adapter);


        //Facebook
        final ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Toast.makeText(getApplicationContext(), "Post partagé !", Toast.LENGTH_SHORT);
                    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(image)
                            .build();

                    SharePhotoContent content = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .setShareHashtag(new ShareHashtag.Builder().setHashtag("#NoMoreTrash").build())
                            .build();
                    shareButton.setShareContent(content);

                }
            });

        }

        ImageButton share = findViewById(R.id.Share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                Intent intent = getIntent();
                int nbDechet = intent.getStringArrayListExtra("ma_liste_de_signalements").size();
                String textShare = "J'ai pu signaler " + nbDechet + " déchet" + ((nbDechet>1)?"s ":" ") ;
                if(nbDechet!=0){
                    String date = SignalementsObjectsList.signalementsObjetsArray.get(0).getDate();
                    textShare = textShare +"depuis le " + date + " grâce à l'application noMoreTrash ! Télécharge la vite sur le Google Play store pour sauver notre planête !";
                }else{
                    textShare = textShare + " il faudra donc que j'utile l'application noMoreTrash plus souvent ! Viens vite la télécharger pour m'aider !";
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

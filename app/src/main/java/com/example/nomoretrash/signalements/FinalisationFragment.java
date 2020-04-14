package com.example.nomoretrash.signalements;


import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.nomoretrash.MainActivity;
import com.example.nomoretrash.ApplicationDemo;
import com.example.nomoretrash.R;
import com.example.nomoretrash.map.MainMapActivity;

import static com.example.nomoretrash.ApplicationDemo.CHANNEL_ID;

public class FinalisationFragment extends Fragment {

    private SignalementObject signalementObject;
    ImageView mImageView;

    public FinalisationFragment() {
        //on récupère l'objet signalemnt
        this.signalementObject = DescriptionFragment.getSignalementObject();
    }


    private String recap = "";

    private boolean part1 = false;
    private boolean part2 = false;
    private boolean part3 = false;


    private int notificationId = 0;

    public static FinalisationFragment newInstance() {
        return (new FinalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recap = "";

        View rootView = inflater.inflate(R.layout.finalisation_fragment, container, false);

        Button boutonFinaliser = rootView.findViewById(R.id.boutonFinir);
        boutonFinaliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (part1 && part2 && part3) {
                    Toast.makeText(getContext(), "Votre signalement a bien été enregistré !", Toast.LENGTH_LONG).show();

                    // Create two threads:
                    Thread thread1 = new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("mon_signalement", DescriptionFragment.getSignalementObject().toString());
                            getActivity().setResult(Activity.RESULT_OK, resultIntent);
                            getActivity().finish();
                        }
                    };

                    Thread thread2 = new Thread() {
                        public void run() {
                            Context saveContext = getActivity().getApplicationContext();
                            String saveChannelId = CHANNEL_ID;
                            String titleConfirmation = "Prise en compte de votre signalement";
                            String notifConfirmation = "Il sera traité dans les plus brefs délais.";
                            String titleChecked = "Déchet nettoyé";
                            String notifChecked = "Le déchet signalé a été nettoyé, Merci !";
                            sendNotificationOnChannel(R.drawable.chargement, titleConfirmation, notifConfirmation, saveChannelId, NotificationCompat.PRIORITY_DEFAULT, saveContext);
                            sendNotificationOnChannel(R.drawable.validation, titleChecked, notifChecked, saveChannelId, NotificationCompat.PRIORITY_HIGH, saveContext);
                        }
                    };

                    // Start the downloads.
                    thread1.start();
                    thread2.start();

                    // Wait for them both to finish
                    try {
                        thread1.join();
                        //thread2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(getContext(), "Des champs dans la  page description sont manquants", Toast.LENGTH_LONG).show();
                }
            }
        });

        setRecap();
        ((TextView) rootView.findViewById(R.id.recap)).setText(recap);

        //Affichage de la photo
        if (PhotoFragment.getImage_uri() != null) {
            mImageView = rootView.findViewById(R.id.photo);
            mImageView.setImageURI(PhotoFragment.getImage_uri());
            mImageView.setRotation(90);
        }


        return rootView;
    }

    private void sendNotificationOnChannel(int icon, String title, String message, String channelId, int priority, Context context) {

        try {
            for(int i = 1 ; i <= 10; i++){
                Thread.sleep(1000);
                System.out.println(i+" seconde(s) se sont écoulée");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder( context,  channelId)
                .setSmallIcon(icon)
                .setContentTitle( title )
                .setContentText( message )
                .setPriority( priority );
        ApplicationDemo.getNotificationManager().notify( ++notificationId, notification.build());
    }

    public void setRecap() {
        recap = "Recapitulatif : ";
        if (signalementObject.isDECHET_UNIQUE() || signalementObject.isDECHARGE_SAUVAGE()) {
            part1 = true;
            if (signalementObject.isDECHET_UNIQUE())
                recap += "dechet unique";
            else
                recap += "décharge sauvage";
        } else part1 = false;

        if (signalementObject.isVERRE() || signalementObject.isCARTON() || signalementObject.isPAPIER() || signalementObject.isPLASTIQUE() || signalementObject.isMETAL() || signalementObject.isAUTRE()) {
            recap += ", composé de";
            part2 = true;
            if (signalementObject.isVERRE())
                recap += " verre,";
            if (signalementObject.isCARTON())
                recap += " carton,";
            if (signalementObject.isPAPIER())
                recap += " papier,";
            if (signalementObject.isPLASTIQUE())
                recap += " plastique,";
            if (signalementObject.isMETAL())
                recap += " métal,";
            if (signalementObject.isAUTRE())
                // TODO: 12/04/2020 a modifier en fonction de se qu'écrit l'utilisateur
                recap += " autre,";
        } else part2 = false;
        if (signalementObject.isGROS() || signalementObject.isPETIT()) {
            part3 = true;
            if (signalementObject.isGROS())
                recap += " mesurant plus de 30 cm";
            else
                recap += " mesurant moins de 30 cm";
        } else part3 = false;
    }
}


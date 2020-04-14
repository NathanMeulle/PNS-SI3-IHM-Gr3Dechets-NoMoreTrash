package com.example.nomoretrash.signalements;

import android.media.Image;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class SignalementObject implements Parcelable {

    private String date;

    private boolean DECHET_UNIQUE;
    private boolean DECHARGE_SAUVAGE;

    private boolean VERRE;
    private boolean CARTON;
    private boolean PAPIER;
    private boolean PLASTIQUE;
    private boolean METAL;
    private boolean AUTRE;

    private boolean GROS;
    private boolean PETIT;

    private String localisation;
    private String photo;
    private String autreInfos;

    public SignalementObject() {
        DECHET_UNIQUE = false;
        DECHARGE_SAUVAGE = false;

        VERRE = false;
        CARTON = false;
        PAPIER = false;
        PLASTIQUE = false;
        METAL = false;
        AUTRE = false;

        GROS = false;
        PETIT = false;

        localisation = "";
        photo = "";
        autreInfos = "";
    }

    /*
    ########################### GETTERS ###########################
     */

    public boolean isDECHET_UNIQUE() {
        return DECHET_UNIQUE;
    }

    public boolean isDECHARGE_SAUVAGE() {
        return DECHARGE_SAUVAGE;
    }

    public boolean isVERRE() {
        return VERRE;
    }

    public boolean isCARTON() {
        return CARTON;
    }

    public boolean isPAPIER() {
        return PAPIER;
    }

    public boolean isPLASTIQUE() {
        return PLASTIQUE;
    }

    public boolean isMETAL() {
        return METAL;
    }

    public boolean isAUTRE() {
        return AUTRE;
    }

    public boolean isGROS() {
        return GROS;
    }

    public boolean isPETIT() {
        return PETIT;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getPhoto() {
        return photo;
    }

    public String getAutreInfos() {
        return autreInfos;
    }
    public String getDate() {
        return date;
    }

        /*
    ########################### SETTERS ###########################
     */


    public void setDECHET_UNIQUE(boolean DECHET_UNIQUE) {
        this.DECHET_UNIQUE = DECHET_UNIQUE;
    }

    public void setDECHARGE_SAUVAGE(boolean DECHARGE_SAUVAGE) {
        this.DECHARGE_SAUVAGE = DECHARGE_SAUVAGE;
    }

    public void setVERRE(boolean VERRE) {
        this.VERRE = VERRE;
    }

    public void setCARTON(boolean CARTON) {
        this.CARTON = CARTON;
    }

    public void setPAPIER(boolean PAPIER) {
        this.PAPIER = PAPIER;
    }

    public void setPLASTIQUE(boolean PLASTIQUE) {
        this.PLASTIQUE = PLASTIQUE;
    }

    public void setMETAL(boolean METAL) {
        this.METAL = METAL;
    }

    public void setAUTRE(boolean AUTRE) {
        this.AUTRE = AUTRE;
    }

    public void setGROS(boolean GROS) {
        this.GROS = GROS;
    }

    public void setPETIT(boolean PETIT) {
        this.PETIT = PETIT;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAutreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
    }

    public void setDate(String date) {
        this.date = date;
    }
    /*
    ########################### INVERSEURS ###########################
     */

    public void changeDECHET_UNIQUE() {
        this.DECHET_UNIQUE = !this.DECHET_UNIQUE;
    }

    public void changeDECHARGE_SAUVAGE() {
        this.DECHARGE_SAUVAGE = !this.DECHARGE_SAUVAGE;
    }

    public void changeVERRE() {
        this.VERRE = !this.VERRE;
    }

    public void changeCARTON() {
        this.CARTON = !this.CARTON;
    }

    public void changePAPIER() {
        this.PAPIER = !this.PAPIER;
    }

    public void changePLASTIQUE() {
        this.PLASTIQUE = !this.PLASTIQUE;
    }

    public void changeMETAL() {
        this.METAL = !this.METAL;
    }

    public void changeAUTRE() {
        this.AUTRE = !this.AUTRE;
    }

    public void changeGROS() {
        this.GROS = !this.GROS;
    }

    public void changePETIT() {
        this.PETIT = !this.PETIT;
    }

    /*
    ########################### Parseur ###########################
     */

    public SignalementObject(Parcel in) {
        String[] data = new String[14];

        in.readStringArray(data);
        this.DECHET_UNIQUE = Boolean.parseBoolean(data[0]);
        this.DECHARGE_SAUVAGE = Boolean.parseBoolean(data[1]);

        this.VERRE = Boolean.parseBoolean(data[2]);
        this.CARTON = Boolean.parseBoolean(data[3]);
        this.PAPIER = Boolean.parseBoolean(data[4]);
        this.PLASTIQUE = Boolean.parseBoolean(data[5]);
        this.METAL = Boolean.parseBoolean(data[6]);
        this.AUTRE = Boolean.parseBoolean(data[7]);

        this.GROS = Boolean.parseBoolean(data[8]);
        this.PETIT = Boolean.parseBoolean(data[9]);

        this.localisation = data[10];
        this.photo = data[11];
        this.autreInfos = data[12];
        this.date = data[13];

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{String.valueOf(this.DECHET_UNIQUE),
                String.valueOf(this.DECHARGE_SAUVAGE),
                String.valueOf(this.VERRE),
                String.valueOf(this.CARTON),
                String.valueOf(this.PAPIER),
                String.valueOf(this.PLASTIQUE),
                String.valueOf(this.METAL),
                String.valueOf(this.AUTRE),
                String.valueOf(this.GROS),
                String.valueOf(this.PETIT),
                String.valueOf(this.localisation),
                String.valueOf(this.photo),
                String.valueOf(this.autreInfos),
                date});

    }

    public static final Parcelable.Creator<SignalementObject> CREATOR = new Parcelable.Creator<SignalementObject>() {
        @Override
        public SignalementObject createFromParcel(Parcel source) {
            return new SignalementObject(source);
        }

        @Override
        public SignalementObject[] newArray(int size) {
            return new SignalementObject[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        String recap = "Signalement du " + date + ". \nRécapitulatif : ";
        if (isDECHET_UNIQUE() || isDECHARGE_SAUVAGE()) {
            if (isDECHET_UNIQUE())
                recap += "dechet unique";
            else
                recap += "décharge sauvage";
        }
        if (isVERRE() || isCARTON() || isPAPIER() || isPLASTIQUE() || isMETAL() || isAUTRE()) {
            recap += ", composé de";
            if (isVERRE())
                recap += " verre,";
            if (isCARTON())
                recap += " carton,";
            if (isPAPIER())
                recap += " papier,";
            if (isPLASTIQUE())
                recap += " plastique,";
            if (isMETAL())
                recap += " métal,";
            if (isAUTRE())
                // TODO: 12/04/2020 a modifier en fonction de se qu'écrit l'utilisateur
                recap += " autre,";
        }
        if (isGROS() || isPETIT()) {
            if (isGROS())
                recap += " mesurant plus de 30 cm";
            else
                recap += " mesurant moins de 30 cm";

        }

        recap+="\nStatut : en cours de traitement";
        return recap;

    }
}
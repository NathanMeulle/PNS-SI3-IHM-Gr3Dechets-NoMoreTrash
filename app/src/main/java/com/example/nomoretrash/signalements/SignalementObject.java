package com.example.nomoretrash.signalements;

import android.graphics.Bitmap;


import androidx.annotation.NonNull;

import com.example.nomoretrash.Status;

public class SignalementObject {

    private Status status;

    private String date;

    private boolean DECHET_UNIQUE;
    private boolean DECHARGE_SAUVAGE;

    private boolean VERRE;
    private boolean CARTON;
    private boolean PAPIER;
    private boolean PLASTIQUE;
    private boolean METAL;
    private boolean AUTRE;
    private String autreType;

    private boolean GROS;
    private boolean PETIT;

    private String localisation;
    private double latitude;
    private double longitude;
    private boolean haveLocalisation;

    private Bitmap photo;
    private String autreInfos;

    public SignalementObject() {
        DECHET_UNIQUE = true;
        DECHARGE_SAUVAGE = false;

        VERRE = false;
        CARTON = false;
        PAPIER = false;
        PLASTIQUE = false;
        METAL = false;
        AUTRE = false;
        autreType="";

        GROS = false;
        PETIT = true;

        localisation = "";
        haveLocalisation = false;

        photo = null;
        autreInfos = "";
        status = Status.EN_COURS;
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

    public Bitmap getPhoto() {
        return photo;
    }

    public String getAutreInfos() {
        return autreInfos;
    }

    public String getDate() {
        return date;
    }

    public String getAutreType() {
        return autreType;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean haveLocalisation() {
        return this.haveLocalisation;
    }

    public Status getStatus() {
        return status;
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

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public void setAutreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAutreType(String autreType) {
        this.autreType = autreType;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setHaveLocalisation(boolean haveLocalisation) {
        this.haveLocalisation = haveLocalisation;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void changeHaveLocalisation(){
        this.haveLocalisation=!this.haveLocalisation;
    }


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

        recap+= "\n"+localisation;

        if(!autreInfos.equals(""))
            recap+="\nAutres informations : " + autreInfos;


        recap+="\nStatut :" + getStatus();
        return recap;

    }


}
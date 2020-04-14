package com.example.nomoretrash.signalements;

import android.net.Uri;
import android.widget.ImageView;

public class SignalementObject {

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
    private ImageView photo;
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
        photo = null;
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

    public ImageView getPhoto() {
        return photo;
    }

    public String getAutreInfos() {
        return autreInfos;
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

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public void setAutreInfos(String autreInfos) {
        this.autreInfos = autreInfos;
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

}

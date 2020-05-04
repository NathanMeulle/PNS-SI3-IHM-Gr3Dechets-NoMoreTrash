package com.example.nomoretrash.signalements.mes_signalements;

import androidx.annotation.NonNull;

public enum Status {
    EN_COURS {
        @NonNull
        @Override
        public String toString() {
            return " En attente de prise en charge.";
        }
    },
    PRIS_EN_CHARGE {
        @NonNull
        @Override
        public String toString() {
            return " Pris en charge.";
        }
    },
    TRAITE {
        @NonNull
        @Override
        public String toString() {
            return " Traité !";
        }
    };


}



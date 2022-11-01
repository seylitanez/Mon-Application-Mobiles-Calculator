package com.example.appmobile.modele;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class SaisieControle {
    final float VIDE=-1;
    final int DURATION_=2000;
    @SuppressLint("WrongConstant")
    public boolean controle_de_saisie(Context context, float notetd, float notetp, float noteemd) {
        boolean correct = false;

        if ((noteemd <= 20 && noteemd >= 0) && (notetp==VIDE&&notetd==VIDE)) {
            correct = true;
        }

        if (((notetd <= 20 && notetd >= 0)&&(noteemd <= 20 &&noteemd >= 0))&&(notetp==VIDE)) {
            correct = true;
        }
        if (((notetp <= 20 && notetp >= 0)&&(noteemd <= 20 &&noteemd >= 0))&&(notetd==VIDE)) {
            correct = true;
        }
        if (((notetp <= 20 && notetp >= 0)&&(noteemd <= 20 &&noteemd >= 0))&&(notetd==VIDE)) {
            correct = true;
        }
        if (((notetp <= 20 && notetp >= 0)&&(noteemd <= 20 &&noteemd >= 0))&&(notetd <= 20 && notetd >= 0)) {
            correct = true;
        }
        if (correct==false)
            Toast.makeText(context,"0<=Note<=20",DURATION_).show();


        return correct;
    }


}

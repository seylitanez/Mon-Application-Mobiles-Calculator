package com.example.appmobile.Vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appmobile.R;

public class ActivityAcceuil extends AppCompatActivity {
    private ConstraintLayout choix_calcul_moyenne_module;
    private ConstraintLayout choix_calcule_moyenne_semestre;
    private ConstraintLayout choix_apropos;
    private ImageView img;

    /**
     * init pour initialiser
     */
    //----------------------------------------------------------------------------------------------
    private void init(){
    //setFocusableInTouchMode(false);  elle permet de desactiver le focus sur une view
    // car sans cette methode on est obligé d'appuyer plusieurs fois sur la view avant d'y acceder
        choix_calcul_moyenne_module = findViewById(R.id.ChoixCalculMoyenneModule);
        choix_calcul_moyenne_module.setFocusableInTouchMode(false);


        choix_calcule_moyenne_semestre = findViewById(R.id.ChoixCalculeMoyenneSemestre);
        choix_calcule_moyenne_semestre.setFocusableInTouchMode(false);


        choix_apropos = findViewById(R.id.ChoixApropos);
        choix_apropos.setFocusableInTouchMode(false);

     //l'ajout des listener
        ecouteurs();
    }
    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        init();

    }

    /**
     * on definit nos ecouteurs
     */
    private void ecouteurs(){
        choix_calcul_moyenne_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ActivityAcceuil.this, ActivityCalculMoyenneModule.class));


            }
        });
        choix_calcule_moyenne_semestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ActivityAcceuil.this, ActivityCalculMoyenneSemestre.class));


            }
        });
        choix_apropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img=findViewById(R.id.imgaporopos);
                Intent in=new Intent(ActivityAcceuil.this, ActivityApropos.class);
                ActivityOptions op= ActivityOptions.makeSceneTransitionAnimation(ActivityAcceuil.this,img,"transitionally");
                startActivity(in,op.toBundle());


            }
        });
    }
}
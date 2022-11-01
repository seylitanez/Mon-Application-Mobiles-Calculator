package com.example.appmobile.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmobile.R;
import com.example.appmobile.controleur.Controle;
import com.example.appmobile.modele.SaisieControle;

import java.sql.SQLDataException;

public class ActivityCalculMoyenneModule extends AppCompatActivity {
    private Button calcul,s1,s2;
    private ImageButton btncm;
    public EditText td,tp,emd,coef,module,credit;
    private TextView moy;
    private final int DURATION_=2000;
    private final int S1=1;
    private final int S2=2;
    private Controle controle;
    private final float VIDE=-1;
    private Dialog dialog;

    /**
     * initialiser
     * faire appel a tous les composants de ma vue
     */
    private void init(){
        controle=Controle.Instance(this);
        calcul=findViewById(R.id.calculer);
        module=findViewById(R.id.modules);
        td=findViewById(R.id.tds);
        tp=findViewById(R.id.tpc);
        emd=findViewById(R.id.emdc);
        coef=findViewById(R.id.coef);
        credit=findViewById(R.id.credie);
        moy=findViewById(R.id.moy);
        btncm=findViewById(R.id.btnms);

        ecouteurs();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);
        init();
    }

    /**
     * ecouteurs et action
     */
    private void ecouteurs(){
        btncm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(ActivityCalculMoyenneModule.this, Activity4_test.class);
//                startActivity(intent);
                Intent intent=new Intent(ActivityCalculMoyenneModule.this, ActivityCalculMoyenneSemestre.class);
                startActivity(intent);
                finish();
            }
        });
        calcul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {


                try {
                    String module_nom=module.getText().toString();


                        System.out.println(module_nom);

                        float note_td = VIDE;
                        float note_tp = VIDE;

                        if (td.getText() + "" != "") {
                            note_td = Float.parseFloat(td.getText() + "");
                        }
                        if (tp.getText() + "" != "") {
                            note_tp = Float.parseFloat(tp.getText() + "");
                        }

                        float note_emd = Float.parseFloat(emd.getText() + "");
                        int coef_ = Integer.parseInt(coef.getText() + "");
                        int credit_ = Integer.parseInt(credit.getText() + "");

                        if (new SaisieControle().controle_de_saisie(ActivityCalculMoyenneModule.this,note_td, note_tp, note_emd) == true) {
                            if (module_nom.length()>0) {
                                dialog=new Dialog(ActivityCalculMoyenneModule.this);
                                dialog.setContentView(R.layout.popup);
                                dialog.show();
                                dialog.getWindow().setBackgroundDrawableResource(R.drawable.corner_radiues);
                                float notetd=note_td;
                                float notetp=note_tp;
                                controle.creer_module(module_nom, notetd, notetp, note_emd, coef_, credit_,S1);
                                moy.setText(controle.getMoyenne()+"");
                                couleur_text(moy);
                                reenitialiser(module,td,tp,emd,coef,credit);
//                                s1=findViewById(R.id.s1);
//                                s2=findViewById(R.id.s2);
//                                s1.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        dialog.cancel();
//
//                                        reenitialiser(module,td,tp,emd,coef,credit);
//                                    }
//                                });
//                                s2.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        dialog.cancel();
//                                        controle.creer_module(module_nom, notetd, notetp, note_emd, coef_, credit_,S2);
//                                        moy.setText(controle.getMoyenne()+"");
//                                        couleur_text(moy);
//                                        reenitialiser(module,td,tp,emd,coef,credit);
//                                    }
//                                });


                            }else
                                Toast.makeText(ActivityCalculMoyenneModule.this,"Saisie incorrecte",DURATION_).show();


                        }

                }catch (Exception e){
                    Toast.makeText(ActivityCalculMoyenneModule.this,"Saisie incorrecte",DURATION_).show();
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * reenitioaliser les champs de saisie
     * @param module
     * @param Td
     * @param Tp
     * @param Emd
     * @param Coef
     * @param Credit
     */
    private void reenitialiser(EditText module,EditText Td,EditText Tp,EditText Emd,EditText Coef,EditText Credit){
        module.setText("");
        Td.setText("");
        Tp.setText("");
        Emd.setText("");
        Coef.setText("");
        Credit.setText("");
        module.requestFocus();
    }

    /**
     * couleur selon la moyenne
     * @param moy
     */
            private void couleur_text(TextView moy){
                if (Float.parseFloat(moy.getText()+"")>=10){
                    moy.setTextColor(getResources().getColor(R.color.Good));
                }else
                    moy.setTextColor(getResources().getColor(R.color.Bad));
            }



}
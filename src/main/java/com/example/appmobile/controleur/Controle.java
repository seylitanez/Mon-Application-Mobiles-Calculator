package com.example.appmobile.controleur;

import android.content.Context;

import com.example.appmobile.modele.CanalBD;
import com.example.appmobile.modele.Module;
import com.example.appmobile.modele.Semestre;

import java.util.ArrayList;

public final class Controle {
    private static Controle instance= null;
    private static Module module;
    private static Module modif;
    private static Semestre modules;
    private static CanalBD canalBD;


    /**
     * constructeur privé on peut l'instancier
     */
    private Controle(){

    }

    /**
     * constructeur static pour qu'il soit generer qu'une seule fois
     * @return
     */
    public static Controle Instance(Context context){
        if (instance==null){
        instance=new Controle();
        canalBD=new CanalBD(context);
        }
        return instance;
    }

    /**
     * creation de module
     * @param module
     * @param notetd
     * @param notetp
     * @param noteemd
     * @param coef
     * @param credit
     * @param semestre
     */
    public void creer_module(String module, float notetd, float notetp, float noteemd, int coef, int credit, int semestre){
        // instancier la class module
        Controle.module =new Module(module,notetd,notetp,noteemd,coef,credit,semestre);
        //on fait le calcule de la moyenne avec la methode getMoyenne()
        float moyenne= Controle.module.getMoyenne();
        System.out.println(moyenne);
        //on ajoute notre module a un bd qui elle prends comme parametre une moyenne qu'on va recuperer avec la variable moyenne
        canalBD.ajouter_module(Controle.module.getModule(), Controle.module.getNotetd(), Controle.module.getNotetp(), Controle.module.getNoteemd(),moyenne, Controle.module.getCoef(), Controle.module.getCredit(), Controle.module.getSemestre());
        System.out.println("--------Ajouté-------");
    }

    public ArrayList recuperer_modules(){

        return canalBD.recuper();
    }
    public void supprimer(String nom_module) {
        canalBD.supprimer_module(nom_module);
        recuperer_modules();
    }
    public float modifier(String module,String td,String tp,String emd,String coef,String where){
        System.out.println("----------------avant la modif -----------------");
        Module modulemodif=new Module(module,Float.parseFloat(td),Float.parseFloat(tp),Float.parseFloat(emd),Integer.parseInt(coef),1,1);
        canalBD.modifier_modules(module,td,tp,emd,coef,where,modulemodif.getMoyenne()+"");
        System.out.println(modulemodif.getMoyenne());
        return modulemodif.getMoyenne();
    }



        public static float getMoyenne() {
        return module.getMoyenne();
    }
}

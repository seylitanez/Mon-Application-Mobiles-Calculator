package com.example.appmobile.modele;

import java.util.ArrayList;

public class Semestre {
    ArrayList <Module> modules;

    public ArrayList<Module> getModules() {
        return modules;
    }


//    public float getMoySem(){
//        float sommeXcoef=0;
//        float coefs=0;
//        for (int i = 0; i < modules.size(); i++) {
//
//        sommeXcoef+=((Module)modules.get(i)).getMoyenne()*((Module)modules.get(i)).getCoef();
//        coefs+=modules.get(i).getCoef();
//        }
//        return sommeXcoef/coefs;
//    }

    public String getModule(){

        return modules.get(1).getModule();
    }
    public Semestre(ArrayList<Module> modules) {
        this.modules = modules;
    }
}

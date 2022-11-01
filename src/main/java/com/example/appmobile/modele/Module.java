package com.example.appmobile.modele;

public class Module {
    private String module;
    private static final float Vide =-1;
    private float notetd= Vide;

    public Module() {
    }

    /**
     * creer un module en precisant le semestre 1/2
     * @param module
     * @param notetd
     * @param notetp
     * @param noteemd
     * @param coef
     * @param credit
     * @param semestre
     */
    public Module(String module, float notetd, float notetp, float noteemd, int coef, int credit, int semestre) {
        this.module = module;
        this.notetd = notetd;
        this.notetp = notetp;
        this.noteemd = noteemd;
        this.coef = coef;
        this.credit = credit;
        this.semestre = semestre;
    }


    private float notetp= Vide;
    private float noteemd=0;
    private float moyenne=0;
    private int coef=1,credit=1,semestre=1;

    public String getModule() {
        return module;
    }

    public float getNotetd() {
        return notetd;
    }

    public float getNotetp() {
        return notetp;
    }

    public float getNoteemd() {
        return noteemd;
    }

    public int getCoef() {
        return coef;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setNotetd(float notetd) {
        this.notetd = notetd;
    }

    public void setNotetp(float notetp) {
        this.notetp = notetp;
    }

    public void setNoteemd(float noteemd) {
        this.noteemd = noteemd;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public int getCredit() {
        return credit;
    }

    public int getSemestre() {
        return semestre;
    }

    /**
     * calcul moyenne module avec contraintes
     * @return
     */
    public float getMoyenne() {
        if (notetd!= Vide &&notetp!= Vide) {
            moyenne = (notetp + notetd + (noteemd * 2)) / 4;
        }else if ((notetd!= Vide &&notetp== Vide)){
            moyenne = (notetd + (noteemd * 2))/3;
        }else if ((notetd== Vide &&notetp!= Vide)){
            moyenne = (notetp + (noteemd * 2))/3;
        }else if ((notetd== Vide &&notetp== Vide)){
            moyenne = noteemd;
        }
        return moyenne;
    }
}

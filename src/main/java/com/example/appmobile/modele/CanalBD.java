package com.example.appmobile.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appmobile.outils.MySqlOpenHelper;

import java.util.ArrayList;

public class CanalBD {
    private MySqlOpenHelper openHelper;
    private SQLiteDatabase sqldb;
    private final String nom="BDModules2.sqlite";
    private final int version= 1;
    private final float VIDE=-1;
    private Cursor cursor;
    private ArrayList<ArrayList> list_des_modules;
    public CanalBD(Context context){
        openHelper=new MySqlOpenHelper(context,nom,null,version);
    }
    public void ajouter_module(String module,Object notetd,Object notetp,float noteemd,float moyenne,int coef ,int credit ,int semestre){

        if ((float)notetd == VIDE){
            notetd=null;
        }
        if ((float)notetp == VIDE){
            notetp=null;
        }
        String insert="INSERT INTO Modules (Nommodule,NoteTd,NoteTp,Emd,Moyenne,Coef,Credit,Semestre)\n" +
                "VALUES";
        insert +="('"+module+"','"+notetd+"','"+notetp+"','"+noteemd+"','"+moyenne+"','"+coef+"','"+credit+"','"+semestre+"');";
        openHelper.getWritableDatabase().execSQL(insert);
    }
    public ArrayList recuper(){
        final String select="SELECT * FROM Modules";
//        modules=new Modules();
         list_des_modules=new ArrayList();

        sqldb=openHelper.getWritableDatabase();
        cursor= sqldb.rawQuery(select,null);
        cursor.moveToFirst();

        int j=0;
        while (!cursor.isAfterLast()) {
        ArrayList<String> ligne_module=new ArrayList();
            for (int i=0;i<8;i++){
                ligne_module.add(cursor.getString(i));
            }
            list_des_modules.add(ligne_module);
            cursor.moveToNext();
        }
        for (int i = 0; i < list_des_modules.size(); i++) {
                System.out.println("**************************"+list_des_modules.get(i).get(1));

        }
        cursor.close();
        return list_des_modules;
    }
        public void supprimer_module(String nom_module){
        final String DELETE="DELETE FROM Modules WHERE Nommodule='";;

            sqldb.execSQL(DELETE+nom_module+"';");
        }
        public void modifier_modules(String module,String td,String tp,String emd,String coef,String where,String moyenne){
            sqldb.execSQL("UPDATE Modules SET Nommodule='"+module+"',NoteTd='"+td+"',NoteTp='"+tp+"',Emd='"+emd+"',Coef='"+coef+"',Moyenne='"+moyenne+"' WHERE Nommodule ='"+where+"' ");
        }
}

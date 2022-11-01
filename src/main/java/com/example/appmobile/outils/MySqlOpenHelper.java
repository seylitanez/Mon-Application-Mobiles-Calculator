package com.example.appmobile.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlOpenHelper extends SQLiteOpenHelper {

    private static final String create_table="CREATE TABLE \"Modules\" (\n" +
            "\t\"idModule\"\tINTEGER UNIQUE,\n" +
            "\t\"Nommodule\"\tCHAR(10) NOT NULL UNIQUE,\n" +
            "\t\"NoteTd\"\tREAL,\n" +
            "\t\"NoteTp\"\tREAL,\n" +
            "\t\"Emd\"\tREAL,\n" +
            "\t\"Moyenne\"\tREAL,\n" +
            "\t\"Coef\"\tINTEGER,\n" +
            "\t\"Credit\"\tINTEGER,\n" +
            "\t\"Semestre\"\tINTEGER,\n" +
            "\tPRIMARY KEY(\"Nommodule\")\n" +
            ");";



    public MySqlOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

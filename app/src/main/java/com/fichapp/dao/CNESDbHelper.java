package com.fichapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fichapp.util.Constantes;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class CNESDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS cnes (id INTEGER PRIMARY KEY, codigo VARCHAR, nome VARCHAR, flag_ativo BOOLEAN);";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS cnes;";

    public CNESDbHelper(Context context) {
        super(context, Constantes.CNES_DB, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}

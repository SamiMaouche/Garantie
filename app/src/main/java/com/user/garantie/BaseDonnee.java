package com.user.garantie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Maouche on 22/11/2015.
 */
public class BaseDonnee extends SQLiteOpenHelper {

    public static final int bdd_version = 1;
    public String qery = "CREATE TABLE IF NOT EXISTS " + TabLes.TableInfo.table_Name + " ( " + TabLes.TableInfo.id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + TabLes.TableInfo.num_ser + " TEXT(100), " + TabLes.TableInfo.nom_client + " TEXT(100), " + TabLes.TableInfo.date + "DATE, " + TabLes.TableInfo.adr_client + " TEXT(250));";

    //constructeur du class BaseDonnee qui a un seul argument : le context
    public BaseDonnee(Context context) {
        super(context, TabLes.TableInfo.bdd_Name, null, bdd_version);
        Log.d("baseDeDonnee opertion", "BDDee Createddddddddddddddddddddddd 01230123");// ********
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(qery);
        Log.d("baseDeDonnee opertion", "Table Createdoooooooooooooooooooooooooo 321321");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor get_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TabLes.TableInfo.table_Name, null);
        return cur;

    }

}


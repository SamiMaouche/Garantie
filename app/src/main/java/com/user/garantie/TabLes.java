package com.user.garantie;

import android.provider.BaseColumns;

/**
 * Created by Maouche on 22/11/2015.
 */
public class TabLes {
    public TabLes() {
    }


    public static abstract class TableInfo implements BaseColumns {

        public static final String id = "_id";
        public static final String num_ser = "Num De Serie";
        public static final String date = "Date Sortie";
        public static final String nom_client = "Nom Client";
        public static final String adr_client = "Adresse";
        public static final String bdd_Name = "Fluide";
        public static final String table_Name = "Volucompteur";


    }
}

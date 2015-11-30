package com.user.garantie;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {
    public Context ctx = this;
    BaseDonnee bdd;
    EditText editText;
    Button button;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdd = new BaseDonnee(this);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button2);
        verifier();
        //bdd.close();
    }

    public void verifier() {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor crs = bdd.get_data();
                        if (crs.getCount() == 0) {
                            show_msg("Erreur", " pas d'enregistrement ");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            String txt = editText.getText().toString();
                            while (crs.moveToNext()) {
                                if (txt.equals(crs.getString(1))) {
                                    buffer.append("id : " + crs.getString(0) + "\n");
                                    buffer.append("N° Serie : " + crs.getString(1) + "\n");
                                    buffer.append("Nom Client : " + crs.getString(2) + "\n");
                                    buffer.append("Date Sortie : " + crs.getString(3) + "\n");
                                    buffer.append("Adresse Client : " + crs.getString(4) + "\n\n");

                                    //recupere la date actuelle
                                    Date actuelle = new Date();
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//dd/MM/yyyy
                                    String datet = dateFormat.format(actuelle);
                                    //supprimer la séparation du date
                                    String dat = crs.getString(3);
                                    dat = dat.replace("-", "");
                                    dat = dat.replace("/", "");
                                    String yyyy = dat.substring(4, 8);
                                    int y = Integer.parseInt(yyyy);
                                    y += 2;
                                    String yyy = String.valueOf(y);
                                    String MM = dat.substring(2, 4);
                                    String ddd = dat.substring(0, 2);
                                    dat = yyy;
                                    dat += MM;
                                    dat += ddd;
                                    datet = datet.replace("-", "");
                                    datet = datet.replace("/", "");
                                    int i = Integer.parseInt(dat);
                                    int j = Integer.parseInt(datet);
                                    if (i > j) {
                                        buffer.append("   Volucompteur Garantie i " + dat + " j " + datet + " \n");
                                    } else {
                                        buffer.append("   Hors Garantie  " + dat + "\n");
                                    }
                                }
                            }
                            if (buffer.length() == 0) {
                                show_msg("resultat", "Not Match");
                            } else
                                show_msg("resultat", buffer.toString());

                        }
                                crs.close();
                    }
                }
        );
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdd.close();
                bdd.get_data().close();
                finish();
            }
        });
    }


    public void show_msg(String titre, String mess) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(titre);
        build.setMessage(mess);
        build.show();
    }
}

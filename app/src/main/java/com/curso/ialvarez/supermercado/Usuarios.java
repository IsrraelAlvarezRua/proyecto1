package com.curso.ialvarez.supermercado;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Usuarios extends AppCompatActivity {
    private EditText ci,nombre,telefono ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        ci=(EditText)findViewById(R.id.etci);
        nombre=(EditText)findViewById(R.id.etnomu);
        telefono=(EditText)findViewById(R.id.ettel);
        Bundle bundle = getIntent().getExtras();
       String a= bundle.getString("lista");
        consultaus1(a);
        Toast mensaje = Toast.makeText(getApplicationContext(), "presionaste" + a, Toast.LENGTH_SHORT);
        mensaje.show();
    }
    public void altaus(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = ci.getText().toString();
        String nom = nombre.getText().toString();
        String tel = telefono.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("ci", cod);
        registro.put("nombre", nom);
        registro.put("telefono", tel);


        bd.insert("clientes", null, registro);
        bd.close();
        ci.setText("");
        nombre.setText("");
        telefono.setText("");

        Toast.makeText(this, "Se cargaron los datos del Usuario", Toast.LENGTH_SHORT).show();
    }
    public void consultaus1(String a) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod = a;
        Cursor fila = bd.rawQuery(
                "select ci,nombre,telefono from clientes where nombre='" + cod + "'", null);
        if (fila.moveToFirst()) {
            ci.setText(fila.getString(0));
            nombre.setText(fila.getString(1));
            telefono.setText(fila.getString(2));


        } else
            Toast.makeText(this, "No existe ningun Usuario",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void consultaus(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod = ci.getText().toString();
        Cursor fila = bd.rawQuery(
                "select ci,nombre,telefono from clientes where ci='" + cod + "'", null);
        if (fila.moveToFirst()) {
            nombre.setText(fila.getString(1));
            telefono.setText(fila.getString(2));


        } else
            Toast.makeText(this, "No existe ningun Usuario",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void modificacionus(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = ci.getText().toString();
        String nom = nombre.getText().toString();
        String tel = telefono.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("ci", cod);
        registro.put("nombre", nom);
        registro.put("telefono", tel);

        int cant = bd.update("clientes", registro, "ci=" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un Usuario con el código ingresado "+cod,
                    Toast.LENGTH_SHORT).show();
    }
}

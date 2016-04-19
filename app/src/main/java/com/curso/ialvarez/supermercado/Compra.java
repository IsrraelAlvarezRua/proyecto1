package com.curso.ialvarez.supermercado;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Compra extends AppCompatActivity {
private EditText ci,codigoproducto,precio,cantidad,pagado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        ci = (EditText) findViewById(R.id.edci);
        codigoproducto = (EditText) findViewById(R.id.edcod);
        precio = (EditText) findViewById(R.id.edpre);
        cantidad = (EditText) findViewById(R.id.edcan);
        pagado = (EditText) findViewById(R.id.edcanc);

    }
    public void altacom(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ci1 = ci.getText().toString();
        String cod1 = codigoproducto.getText().toString();
        String pre1 = precio.getText().toString();
        String can1 = cantidad.getText().toString();
        String pag1 = pagado.getText().toString();


        ContentValues registro = new ContentValues();

        registro.put("ci", ci1);
        registro.put("codigoproducto", cod1);
        registro.put("precio", pre1);
        registro.put("cantidad", can1);
        registro.put("pagado", pag1);

        bd.insert("compra", null, registro);
        bd.close();
        ci.setText("");
        codigoproducto .setText("");
        precio .setText("");
        cantidad .setText("");
        pagado .setText("");


        Toast.makeText(this, "La Compra Fue realizada", Toast.LENGTH_SHORT).show();
    }

    public void consultapro(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod = codigoproducto.getText().toString();
        Cursor fila = bd.rawQuery(
                "select precio from productos where codigoproducto='" + cod+"'", null);
        if (fila.moveToFirst()) {
             precio.setText(fila.getString(0));

        } else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void consultacom(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod1 = ci.getText().toString();
        Cursor fila = bd.rawQuery(
                "select ci,codigoproducto,precio,cantidad,pagado from compra where ci='" + cod1 + "'", null);
        if (fila.moveToFirst()) {

            codigoproducto.setText(fila.getString(1));
            precio.setText(fila.getString(2));
            cantidad.setText(fila.getString(3));
            pagado.setText(fila.getString(4));



        } else
            Toast.makeText(this, "No existe ningun Producto con ese codigo",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void modificaciocom(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ci11 = ci.getText().toString();
        String cod11 = codigoproducto.getText().toString();
        String pre11 = precio.getText().toString();
        String can11 = cantidad.getText().toString();
        String pag11 = pagado.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("ci", ci11);
        registro.put("codigoproducto", cod11);
        registro.put("precio", pre11);
        registro.put("cantidad", can11);
        registro.put("pagado", pag11);

        int cant = bd.update("compra", registro, "ci=" + ci11, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe ninguna compra con el código ingresado ",
                    Toast.LENGTH_SHORT).show();
    }
}

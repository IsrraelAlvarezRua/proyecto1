package com.curso.ialvarez.supermercado;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Productos extends AppCompatActivity {
   private EditText codigo,nombre ,precio ,descripcion ,stock ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        codigo=(EditText)findViewById(R.id.etcodigo);
        nombre=(EditText)findViewById(R.id.etnombre);
        precio=(EditText)findViewById(R.id.etprecio);
        descripcion=(EditText)findViewById(R.id.etdescrip);
        stock=(EditText)findViewById(R.id.etstock);
    }
    public void alta(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = codigo.getText().toString();
        String nom = nombre.getText().toString();
        String pre = precio.getText().toString();
        String des = descripcion.getText().toString();
        String sto = stock.getText().toString();
        ContentValues registro = new ContentValues();

        registro.put("codigoproducto", cod);
        registro.put("nombreproducto", nom);
        registro.put("precio", pre);
        registro.put("descripcion", des);
        registro.put("stock", sto);

        bd.insert("productos", null, registro);
        bd.close();
        codigo.setText("");
        nombre.setText("");
        precio.setText("");
        descripcion.setText("");
        stock.setText("");
        Toast.makeText(this, "Se cargaron los datos del Producto", Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod = codigo.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigoproducto,nombreproducto,precio,descripcion,stock from productos where codigoproducto='" + cod+"'", null);
        if (fila.moveToFirst()) {
            nombre.setText(fila.getString(1));
            precio.setText(fila.getString(2));
            descripcion.setText(fila.getString(3));
            stock.setText(fila.getString(4));

        } else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void bajaporcodigo(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = codigo.getText().toString();
        int cant = bd.delete("productos", "codigoproducto=" + cod, null);
        bd.close();
        codigo.setText("");
        nombre.setText("");
        precio.setText("");
        descripcion.setText("");
        stock.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró el artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
    }
    public void modificacion(View v) {
        SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = codigo.getText().toString();
        String nom = nombre.getText().toString();
        String pre = precio.getText().toString();
        String des = descripcion.getText().toString();
        String sto=stock.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigoproducto", cod);
        registro.put("nombreproducto", nom);
        registro.put("precio", pre);
        registro.put("descripcion", des);
        registro.put("stock", sto);
        int cant = bd.update("productos", registro, "codigoproducto=" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
}

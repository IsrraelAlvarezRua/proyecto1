package com.curso.ialvarez.supermercado;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends ListActivity  {
    private SuperDbAdapter dbAdapter;
    private Cursor cursor;
    private SuperCursorAdapter superCursorAdapter ;
    private ListView lista;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
    context=this;
        lista = (ListView) findViewById(android.R.id.list);
        dbAdapter = new SuperDbAdapter(this);
        dbAdapter.abrir();
        consultar();

      lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          public void onItemClick(AdapterView<?> array, View vista, int posicion, long id) {
              TextView texto = (TextView) vista.findViewById(android.R.id.text1);
              String contenido = texto.getText().toString();
             /* Toast mensaje = Toast.makeText(getApplicationContext(), "presionaste" + contenido, Toast.LENGTH_SHORT);
              mensaje.show();*/
              Intent i = new Intent(context, Usuarios.class);
              i.putExtra("lista", contenido);
              startActivity(i);

          }
      });




    }




    private void consultar()
    {
        cursor = dbAdapter.getCursor();
        startManagingCursor(cursor);
        superCursorAdapter = new SuperCursorAdapter(this, cursor);
        lista.setAdapter(superCursorAdapter);
    }


}

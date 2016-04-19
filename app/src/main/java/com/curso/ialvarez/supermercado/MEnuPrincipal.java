package com.curso.ialvarez.supermercado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MEnuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Intent b=getIntent();
        String[] datos_recibidos=new String[2];
        datos_recibidos=b.getStringArrayExtra("datos_usuario");
        Toast.makeText(this, "Bienvenido " + datos_recibidos[0], Toast.LENGTH_LONG).show();
    }
    public  void list_usu(View view){
        Intent in=new Intent(this,Clientes.class) ;
        startActivity(in);
    }
    public  void productos(View view){
        Intent in=new Intent(this,Productos.class) ;
        startActivity(in);
    }
}

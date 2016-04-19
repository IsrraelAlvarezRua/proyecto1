package com.curso.ialvarez.supermercado;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnEnviar;
    private TextView txtResultado;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        btnEnviar=(Button)findViewById(R.id.btnEnviar);
        txtResultado=(TextView)findViewById(R.id.txtResultado);
        SuperDbHelper admin = new SuperDbHelper(this);
        final SQLiteDatabase bd = admin.getWritableDatabase();
        //Evento de click en el botón
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String campo_usuario = txtUsuario.getText().toString().trim();
                String campo_password = txtPassword.getText().toString().trim();
                consulta(campo_usuario);
            }


        });
    }
    public void consulta(String us) {
        String campo_usuario = txtUsuario.getText().toString().trim();
        String campo_pass = txtPassword.getText().toString().trim();
       SuperDbHelper admin = new SuperDbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila=bd.rawQuery("SELECT nombre FROM clientes WHERE nombre='" +us + "'", null);
        Cursor fila2=bd.rawQuery("SELECT ci FROM clientes WHERE ci='" + campo_pass+"'", null);
        if (fila.moveToFirst()){

            if (fila2.moveToFirst()){
               Intent a=new Intent(context,MEnuPrincipal.class);
                String[] datos=new String[2];
                datos[0]=txtUsuario.getText().toString();
                datos[1]=txtPassword.getText().toString();
                a.putExtra("datos_usuario", datos);
                startActivity(a);
            }
            else
                Toast.makeText(this, "Contraseña incorrecta",Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "Este Usuario no Existe",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

}

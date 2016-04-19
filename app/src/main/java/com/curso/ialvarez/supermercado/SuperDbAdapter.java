package com.curso.ialvarez.supermercado;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ialvarez on 14/4/2016.
 */
public class SuperDbAdapter {
    public static final String C_TABLA = "clientes" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID   = "_id";
    public static final String C_COLUMNA_CI   = "ci";
    public static final String C_COLUMNA_NOMBRE = "nombre";
    public static final String C_COLUMNA_TELEFONO = "telefono";


    private Context contexto;
    private SuperDbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_COLUMNA_ID, C_COLUMNA_CI, C_COLUMNA_NOMBRE,C_COLUMNA_TELEFONO} ;

    public SuperDbAdapter(Context context)
    {
        this.contexto = context;
    }

    public SuperDbAdapter abrir() throws SQLException
    {
        dbHelper = new SuperDbHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        dbHelper.close();
    }

    /**
     * Devuelve cursor con todos las columnas de la tabla
     */
    public Cursor getCursor() throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);

        return c;
    }
}

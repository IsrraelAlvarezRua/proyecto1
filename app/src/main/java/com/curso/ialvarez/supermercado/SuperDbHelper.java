package com.curso.ialvarez.supermercado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ialvarez on 14/4/2016.
 */
public class SuperDbHelper extends SQLiteOpenHelper {
    // aqui se crea los datos de la base
    private static  int version=1;
    private static String name="SuperMercado";
    private static SQLiteDatabase.CursorFactory factory=null;
    //-----------------------------------------------

    public SuperDbHelper(Context context){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creando una tabla en nuestra base de datos
        Log.i(this.getClass().toString(), "Creacion de Base de Datos");
        db.execSQL("CREATE TABLE clientes(_id INTEGER PRIMARY KEY AUTOINCREMENT,ci TEXT NOT NULL,nombre TEXT NOT NULL,telefono TEXT )");
        db.execSQL("CREATE TABLE productos(_id INTEGER PRIMARY KEY AUTOINCREMENT,codigoproducto TEXT NOT NULL,nombreproducto TEXT NOT NULL,precio INTEGER,descripcion TEXT,stock INTEGER )");
        db.execSQL("CREATE TABLE compra(_id INTEGER PRIMARY KEY AUTOINCREMENT,ci TEXT NOT NULL,codigoproducto TEXT NOT NULL,precio INTEGER,cantidad INTEGER,pagado INTEGER )");
        Log.i(this.getClass().toString(), "Tablas creada");
//insertamos registros
        db.execSQL("INSERT INTO clientes(ci,nombre,telefono)VALUES('4809489','Isrrael Jorge Alvarez Rua','76517972')");
        db.execSQL("INSERT INTO clientes(ci,nombre,telefono)VALUES('4809490','Christhiam Jorge Alvarez Rua','70571665')");
        db.execSQL("INSERT INTO clientes(ci,nombre,telefono)VALUES('1234567','Juan Perez Alarcon','65478912')");
        db.execSQL("INSERT INTO clientes(ci,nombre,telefono)VALUES('9876543','Pedro Perez Pereira','45698785')");
        db.execSQL("INSERT INTO clientes(ci,nombre,telefono)VALUES('1','Prueba','1')");
        db.execSQL("INSERT INTO productos(codigoproducto,nombreproducto,precio,descripcion,stock)VALUES ('1','papas fritas','10','papa fritas en bolsa de plastico Grande','20')");
        db.execSQL("INSERT INTO productos(codigoproducto,nombreproducto,precio,descripcion,stock)VALUES ('2','chicharon','15','chicharones en bolsa grande','20')");
        db.execSQL("INSERT INTO productos(codigoproducto,nombreproducto,precio,descripcion,stock)VALUES ('3','mani','10','Manie en bolsa plastico Grande','20')");
        db.execSQL("INSERT INTO productos(codigoproducto,nombreproducto,precio,descripcion,stock)VALUES ('4','COCA COLA','11','paquete de gaceosas 2 litros y medio','26')");
        db.execSQL("INSERT INTO productos(codigoproducto,nombreproducto,precio,descripcion,stock)VALUES ('5','gomas','8','bolsa de Gomitas Mediano','20')");
        db.execSQL("INSERT INTO compra(ci,codigoproducto,precio,cantidad,pagado)VALUES ('1','5','8','1','8')");
        db.execSQL("INSERT INTO compra(ci,codigoproducto,precio,cantidad,pagado)VALUES ('1','2','15','2','30')");
        db.execSQL("INSERT INTO compra(ci,codigoproducto,precio,cantidad,pagado)VALUES ('4809489','5','8','1','8')");
        db.execSQL("INSERT INTO compra(ci,codigoproducto,precio,cantidad,pagado)VALUES ('9876543','4','11','2','22')");
        db.execSQL("INSERT INTO compra(ci,codigoproducto,precio,cantidad,pagado)VALUES ('4809490','3','10','7','70')");
        db.execSQL("INSERT INTO compra(ci,codigoproducto,precio,cantidad,pagado)VALUES ('1234567','5','8','1','8')");
        Log.i(this.getClass().toString(), "DAtos supermercado insertados");
        Log.i(this.getClass().toString(),"base creada");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

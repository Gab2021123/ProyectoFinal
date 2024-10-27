package com.proyecto.proyectofinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MiSQLiteHelper(context: Context): SQLiteOpenHelper
    (context, "clientes.db", null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion="CREATE TABLE clientes" + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre TEXT, apellido TEXT, telefono TEXT, email TEXT, direccion TEXT, contrasena TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado="DROP TABLE IF EXISTS clientes"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun anadirDatos(nombre: String, apellido: String, telefono: String, email: String, direccion:String, contrasena:String){
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("apellido",apellido)
        datos.put("telefono", telefono)
        datos.put("email",email)
        datos.put("direccion",direccion)
        datos.put("contraseña",contrasena)

        val db = this.writableDatabase
        db.insert("clientes", null, datos)
    }

}
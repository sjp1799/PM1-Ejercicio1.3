package com.example.ejercicio1_3.transacciones;

public class Transacciones {


    //Tablas
    public static  final  String tablapersonas="personas";

    //Campos
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";


    //tablas CREATE, DROP

    public static final String CreateTablePersonas= "CREATE TABLE personas" +
            " ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,nombre TEXT, apellido TEXT, edad INTEGER,"+
            "correo TEXT,direccion TEXT)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";
    //Creacion del nombre de la base de datos
    public static final String NameDateBase = "DBPersonas";

}


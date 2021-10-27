package com.example.ejercicio1_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio1_3.transacciones.Transacciones;

public class ConsultaActivity extends AppCompatActivity {
    SQLiteConexion conexion;
    EditText id,nombres,apellidos ,edad,correo,direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        //lamar a la conexion de bd SQLite
        conexion = new SQLiteConexion(this, Transacciones.NameDateBase,null,1);
        //Botones
        Button btnconsulta = (Button)findViewById(R.id.btnconsulta);
        Button btneliminar = (Button)findViewById(R.id.btneliminar);
        Button btnactualizar= (Button)findViewById(R.id.btnactualizar);

        id =(EditText)findViewById(R.id.txt_id);
        nombres =(EditText)findViewById(R.id.txt_nombres);
        apellidos =(EditText)findViewById(R.id.txt_apellidos);
        edad =(EditText)findViewById(R.id.txt_edad);
        correo =(EditText)findViewById(R.id.txt_correo);
        direccion =(EditText)findViewById(R.id.txt_direccion);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });




    }


    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};
        String wherecond = Transacciones.id +"=?";
        db.delete(Transacciones.tablapersonas,wherecond,params);
        Toast.makeText(getApplicationContext(),"DATO ELIMINADO",Toast.LENGTH_LONG).show();
        ClearScreen();
    }


    private void ClearScreen(){
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");

    }

    private void Buscar() {
        //a partir de la conexion de arriba , creamos una conexion escribible
        SQLiteDatabase db = conexion.getWritableDatabase();
        //
        //Parametros de configuracion de la sentncia
        String[] params ={id.getText().toString()};//parametro de la busqueda, id por el que lo vamos a buscar
        String[] fields ={Transacciones.nombre,
                Transacciones.apellido,
                Transacciones.correo,
                Transacciones.edad,
                Transacciones.direccion};

        String wherecond = Transacciones.id + "=?";

        try {

            Cursor cdata =db.query(Transacciones.tablapersonas,fields,wherecond,params,null,null,null);
            //obtener posicion del cursor
            cdata.moveToFirst();
            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            correo.setText(cdata.getString(2));
            edad.setText(cdata.getString(3));
            direccion.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(),"Consulta realizada exitosamente",Toast.LENGTH_LONG).show();
        }catch(Exception e)
        {
            ClearScreen();
            Toast.makeText(getApplicationContext(),"Elemento no encontrado",Toast.LENGTH_LONG).show();
        }


    }


    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre,nombres.getText().toString());
        valores.put(Transacciones.apellido,apellidos.getText().toString());
        valores.put(Transacciones.edad,edad.getText().toString());
        valores.put(Transacciones.correo,correo.getText().toString());
        valores.put(Transacciones.direccion,direccion.getText().toString());
        db.update(Transacciones.tablapersonas,valores,Transacciones.id +"=?",params);
        Toast.makeText(getApplicationContext(),"DATO ACTUALIZADO EXITOSAMENTE",Toast.LENGTH_LONG).show();
        ClearScreen();

    }

    public void clickELiminar(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Eliminar();

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Esta Seguro?").setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();



    }
}
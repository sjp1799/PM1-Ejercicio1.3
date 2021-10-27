package com.example.ejercicio1_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio1_3.tablas.Personas;
import com.example.ejercicio1_3.transacciones.Transacciones;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nombres,apellidos,edad,correo,direccion;
    Button btn_enviar,btn_verRegistro;
    ArrayList<Personas> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText)findViewById(R.id.txt_nombre);
        apellidos =(EditText)findViewById(R.id.txt_apellido);
        edad =(EditText)findViewById(R.id.txt_edad1);
        correo =(EditText)findViewById(R.id.txt_correo1);
        direccion=(EditText)findViewById(R.id.txt_direccion1) ;
        btn_enviar =(Button)findViewById(R.id.btn_enviar);
        btn_verRegistro=(Button)findViewById(R.id.btn_verRegistros);



        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarContacto();
            }
        });

        btn_verRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerRegistro();
            }
        });



    }

    private void VerRegistro() {
        Intent intent =new Intent(this,ConsultaActivity.class);
        startActivity(intent);

    }



    private void AgregarContacto() {


        try{
            if (nombres.getText().toString().isEmpty() ||nombres.getText().toString().isEmpty() ||
                    edad.getText().toString().isEmpty() || correo.getText().toString().isEmpty() ||
                    direccion.getText().toString().isEmpty())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("¡Alerta!");
                builder.setMessage("No Puede dejar campos Vacios");
                builder.setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else
            {
                SQLiteConexion conexion = new SQLiteConexion( this, Transacciones.NameDateBase, null, 1 );
                SQLiteDatabase db = conexion.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(Transacciones.nombre,nombres.getText().toString());
                valores.put(Transacciones.apellido, apellidos.getText().toString());
                valores.put(Transacciones.edad, edad.getText().toString());
                valores.put(Transacciones.correo, correo.getText().toString());
                valores.put(Transacciones.direccion,direccion.getText().toString());

                Long resultado = db.insert(Transacciones.tablapersonas,Transacciones.id, valores);
                //Toast
                Toast.makeText(getApplicationContext(), "Registro ingresado: " + resultado.toString(),Toast.LENGTH_LONG).show();
                db.close();

                ClearScreen();
            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error al guardar",Toast.LENGTH_LONG).show();
        }


    }

    private void ClearScreen() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");

    }


}
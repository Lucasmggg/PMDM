package com.example.practica_carta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var botonEmpezar:Button
    private lateinit var editNombre:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instancias()
        acciones()


    }

    private fun instancias() {
        this.botonEmpezar = findViewById(R.id.boton_empezar)
        this.editNombre = findViewById(R.id.edit_nombre)
    }

    private fun acciones() {
        botonEmpezar.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){

            R.id.boton_empezar-> {

                if(!editNombre.text.isEmpty()){

                    var accionPasar = Intent(applicationContext, SecondActivity::class.java)
                    var datosPasar: Bundle = Bundle();
                    datosPasar.putString("nombre", editNombre.text.toString())
                    accionPasar.putExtras(datosPasar)
                    startActivity(accionPasar)
                }
                else{
                    Toast.makeText(applicationContext, "Por favor introduce el nombre",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
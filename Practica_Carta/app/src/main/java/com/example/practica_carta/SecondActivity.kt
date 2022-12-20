package com.example.practica_carta

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random
import kotlin.Array as Array


class SecondActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var botonMayor : ImageButton
    private lateinit var botonMenor : ImageButton
    private lateinit var nombreRecuperado : String
    private lateinit var barraProgreso1 : ProgressBar
    private lateinit var barraProgreso2 : ProgressBar
    private lateinit var vida1 : ImageView
    private lateinit var vida2 : ImageView
    private lateinit var vida3 : ImageView
    private lateinit var vidaExtra : ImageView
    private lateinit var puntuacion : TextView
    private lateinit var liner:LinearLayout
    private var cartaPresente : Int = 0
    private var cartaFutura : Int = 0
    private var miProgreso1 : Int = 0
    private var miProgreso2 : Int = 0
    private var contadorPuntos : Int  = 0
    private var vidas : Int = 3
    private var vidasExtras : Boolean = false
    private var cartas: Array<Int> = arrayOf(R.drawable.cf,R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4,R.drawable.c5,
        R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        instancias()
        acciones()
        recuperardatos()
        botonMayor.isEnabled = false
        botonMenor.isEnabled = false
        noti()
    }



    private fun aumentarPuntuacion(){
        ++contadorPuntos
        puntuacion.setText(contadorPuntos.toString())
    }

    private fun modificarProgreso1(porcentaje:Int){
        barraProgreso1.setProgress(porcentaje, true)
        if(porcentaje==10){
            if (vidas<3){
                aumentarVidas(vidas)
                ++vidas
                miProgreso1 = 0
            }
            else{miProgreso1 = 0}
        }
    }

    private fun modificarProgreso2(porcentaje:Int){
        barraProgreso2.setProgress(porcentaje, true)
        if (porcentaje==10){
            vidasExtras=true
            vidaExtra.isVisible = true
        }
    }

    private fun cambiarFondo(cartaPresente:Int) {
        liner.setBackgroundResource(cartas[cartaPresente])
    }

    private fun descontarVidaExtra(){
        vidaExtra.setBackgroundResource(R.drawable.vida0)
        vidasExtras=false
    }

    private fun descontarVidas(){
            --vidas
            if (this.vidas == 2) {
                vida1.setBackgroundResource(R.drawable.vida0)
            } else if (this.vidas == 1) {
                vida2.setBackgroundResource(R.drawable.vida0)
            } else if (this.vidas == 0) {
                vida3.setBackgroundResource(R.drawable.vida0)
            }
    }

    private fun aumentarVidas(vidas: Int){
        if (vidas==2){
            vida1.setBackgroundResource(R.drawable.vida)
        }
        else if (vidas == 1){
            vida2.setBackgroundResource(R.drawable.vida)
        }
    }

    private fun noti() {

        var notification = Snackbar.make(liner,
            "Bienvenido "+nombreRecuperado
            ,Snackbar.LENGTH_INDEFINITE)
        notification.setAction("Aceptar"){
            notification.dismiss()
            cartaPresente = Random.nextInt(1,13)
            cambiarFondo(cartaPresente)
            botonMayor.isEnabled = true
            botonMenor.isEnabled = true
            vida1.isVisible = true
            vida2.isVisible = true
            vida3.isVisible = true
        }
        notification.show()

    }

    private fun recuperardatos() {
       nombreRecuperado = intent.extras!!.getString("nombre", "")
    }

    private fun acciones() {
        botonMayor.setOnClickListener(this)
        botonMenor.setOnClickListener(this)
    }

    private fun instancias() {
        this.botonMayor = findViewById(R.id.boton_mayor)
        this.botonMenor = findViewById(R.id.boton_menor)
        this.vida1 = findViewById(R.id.vida1)
        this.vida2 = findViewById(R.id.vida2)
        this.vida3 = findViewById(R.id.vida3)
        this.vidaExtra = findViewById(R.id.vida_extra)
        this.barraProgreso1 = findViewById(R.id.barra_progreso)
        this.barraProgreso2 = findViewById(R.id.barra_progreso2)
        this.puntuacion = findViewById(R.id.puntuacion)
        liner=findViewById(R.id.fondo)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.boton_mayor->{
                cartaFutura = Random.nextInt(1,13)


                if (cartaFutura>cartaPresente){
                    cambiarFondo(cartaFutura)
                    cartaPresente=this.cartaFutura
                    aumentarPuntuacion()
                    ++miProgreso2
                    modificarProgreso2(miProgreso2)
                    ++miProgreso1
                    modificarProgreso1(miProgreso1)
                }

                else if (cartaFutura==cartaPresente){
                    cambiarFondo(cartaFutura)
                    cartaPresente=this.cartaFutura
                    aumentarPuntuacion()
                    ++miProgreso2
                    modificarProgreso2(miProgreso2)
                    ++miProgreso1
                    modificarProgreso1(miProgreso1)
                    Toast.makeText(applicationContext, "Esta carta era igual que la anterior, se ha sumado un punto",Toast.LENGTH_SHORT).show()
                }

                else {
                        miProgreso2 = 0
                        modificarProgreso2(miProgreso2)
                    if(vidasExtras==false){descontarVidas()}
                    else if(vidasExtras==true){descontarVidaExtra()}
                    if (vidas>0) {
                        cambiarFondo(cartaFutura)
                        Toast.makeText(
                            applicationContext,
                            "Has perdido una vida, te quedan " + vidas,
                            Toast.LENGTH_SHORT
                        ).show()
                        cartaPresente = this.cartaFutura
                    }
                    else{
                        cambiarFondo(cartaFutura)
                        botonMayor.isEnabled = false
                        botonMenor.isEnabled = false
                        var notification = Snackbar.make(
                            liner,
                            "Has perdido!!\nLa puntuación fue de: " + contadorPuntos,
                            Snackbar.LENGTH_INDEFINITE
                        )
                        notification.setAction("Aceptar tu derrota") {
                            notification.dismiss()
                            var accionPasar = Intent(applicationContext, MainActivity::class.java)
                            startActivity(accionPasar)
                        }
                        notification.show()
                    }
                }
            }

            R.id.boton_menor->{
                cartaFutura = Random.nextInt(1,13)

                if (cartaFutura<cartaPresente){
                    cambiarFondo(cartaFutura)
                    cartaPresente=this.cartaFutura
                    aumentarPuntuacion()
                    ++miProgreso2
                    modificarProgreso2(miProgreso2)
                    ++miProgreso1
                    modificarProgreso1(miProgreso1)
                }

                else if (cartaFutura==cartaPresente){
                    cambiarFondo(cartaFutura)
                    cartaPresente=this.cartaFutura
                    aumentarPuntuacion()
                    ++miProgreso2
                    modificarProgreso2(miProgreso2)
                    ++miProgreso1
                    modificarProgreso1(miProgreso1)
                    Toast.makeText(applicationContext, "Esta carta era igual que la anterior, se ha sumado un punto",Toast.LENGTH_SHORT).show()
                }

                else {
                    miProgreso2 = 0
                    modificarProgreso2(miProgreso2)
                    if(vidasExtras==false){descontarVidas()}
                    else if(vidasExtras==true){descontarVidaExtra()}
                    if (vidas>0) {
                        cambiarFondo(cartaFutura)
                        Toast.makeText(
                            applicationContext,
                            "Has perdido una vida, te quedan " + vidas, Toast.LENGTH_SHORT).show()
                        cartaPresente = this.cartaFutura
                    }
                    else{
                        cambiarFondo(cartaFutura)
                        botonMayor.isEnabled = false
                        botonMenor.isEnabled = false
                        var notification = Snackbar.make(
                            liner,
                            "Has perdido!!\nLa puntuación fue de: " + contadorPuntos,
                            Snackbar.LENGTH_INDEFINITE
                        )
                        notification.setAction("Aceptar tu derrota") {
                            notification.dismiss()
                            var accionPasar = Intent(applicationContext, MainActivity::class.java)
                            startActivity(accionPasar)
                        }
                        notification.show()
                    }
                }
            }

        }
    }


}


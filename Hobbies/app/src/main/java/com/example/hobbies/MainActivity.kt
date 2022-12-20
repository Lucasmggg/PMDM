package com.example.hobbies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hobbies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        acciones()
    }

    private fun acciones() {
        binding.botonSegundaPantalla.setOnClickListener(View.OnClickListener{
            val accionPasar = Intent(applicationContext, SecondActivity::class.java)
            startActivity(accionPasar)
        })
    }
}
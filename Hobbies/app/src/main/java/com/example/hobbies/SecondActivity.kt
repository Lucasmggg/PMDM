package com.example.hobbies

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbies.adapter.AdaptadorJugador
import com.example.hobbies.databinding.ActivityMainBinding
import com.example.hobbies.databinding.ActivitySecondBinding
import com.example.practica_hobbies.model.Jugador

class SecondActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener,AdaptadorJugador.OnRecyclerElementoListener{
    private lateinit var binding: ActivitySecondBinding
    private lateinit var adaptadorJugador: AdaptadorJugador
    private lateinit var arrayDatosSpinner:ArrayList<CharSequence>
    private lateinit var arrayJugadores:ArrayList<Jugador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        instancias()
        asociarDatos()
        acciones()
    }

    private fun acciones() {
        binding.spinnerHobbies.onItemSelectedListener=this
    }



    private fun asociarDatos() {
        binding.listaRecycler.adapter = adaptadorJugador;
        binding.spinnerHobbies.adapter = ArrayAdapter.createFromResource(
            applicationContext,R.array.hobbies,
            android.R.layout.simple_list_item_1);
        binding.listaRecycler.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
    }

    private fun instancias() {
        arrayDatosSpinner = ArrayList();
        arrayJugadores = ArrayList();
        adaptadorJugador= AdaptadorJugador(arrayJugadores,applicationContext)
        arrayJugadores.add(Jugador("Messi", "FC. Barcelona", R.drawable.messi))
        arrayJugadores.add(Jugador("Ronaldo", "Brasil", R.drawable.ronaldo))
        arrayJugadores.add(Jugador("Maradona", "Argentina", R.drawable.maradona))
        arrayJugadores.add(Jugador("Zidane", "Francia", R.drawable.zidane))
        adaptadorJugador = AdaptadorJugador(arrayJugadores,this)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0!!.id) {
            R.id.spinner_hobbies -> {
                var seleccionado: String=binding.spinnerHobbies.getItemAtPosition(p2).toString()
                if (seleccionado=="Futbol"){
                    arrayJugadores.clear()
                    arrayJugadores.add(Jugador("Messi", "FC. Barcelona", R.drawable.messi))
                    arrayJugadores.add(Jugador("Ronaldo", "Brasil", R.drawable.ronaldo))
                    arrayJugadores.add(Jugador("Maradona", "Argentina", R.drawable.maradona))
                    arrayJugadores.add(Jugador("Zidane", "Francia", R.drawable.zidane))
                    adaptadorJugador.notifyDataSetChanged()
                }
                else if(seleccionado=="Juegos"){
                    arrayJugadores.clear()
                    arrayJugadores.add(Jugador("Metal Gear", "Sigilo", R.drawable.metal))
                    arrayJugadores.add(Jugador("Gran Turismo", "Coches", R.drawable.gt))
                    arrayJugadores.add(Jugador("God Of War", "Plataformas", R.drawable.god))
                    arrayJugadores.add(Jugador("Final Fantasy X", "Rol", R.drawable.ffx))
                    adaptadorJugador.notifyDataSetChanged()
                }
                else if(seleccionado=="Series"){
                    arrayJugadores.clear()
                    arrayJugadores.add(Jugador("Stranger Things", "Fantastica", R.drawable.stranger))
                    arrayJugadores.add(Jugador("Juego de tronos", "Histórica", R.drawable.tronos))
                    arrayJugadores.add(Jugador("Lost", "Fantastica", R.drawable.lost))
                    arrayJugadores.add(Jugador("La casa de papel", "Accion", R.drawable.papel))
                    adaptadorJugador.notifyDataSetChanged()
                }
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onElementoSelected(elemento: Jugador) {
        binding.imagenItemSelected.setImageResource(elemento.imagen)
        binding.nombreItemSelected.text=elemento.nombre
        binding.tipoItemSelected.text=elemento.equipo
    }

    override fun onElementoSelected(elemento: Jugador, posicion: Int) {
    }
}

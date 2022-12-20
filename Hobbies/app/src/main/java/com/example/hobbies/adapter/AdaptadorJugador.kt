package com.example.hobbies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbies.R
import com.example.practica_hobbies.model.Jugador

class AdaptadorJugador(var lista: ArrayList<Jugador>, var contexto: Context) :
    RecyclerView.Adapter<AdaptadorJugador.MyHolder>() {

    private lateinit var listener: OnRecyclerElementoListener

    init {
        listener = contexto as OnRecyclerElementoListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var vista: View = LayoutInflater.from(contexto).inflate(
            R.layout.item_recycler, parent,
            false);
        return MyHolder(vista);
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var elementoActual = lista.get(position)
        holder.textoNombre.setText(elementoActual.nombre)
        holder.textoTipo.setText(elementoActual.equipo)
        holder.imagenElemento.setImageResource(elementoActual.imagen)

        holder.constraintLayout.setOnClickListener {
            listener.onElementoSelected(elementoActual)
        }
        holder.textoNombre.setOnClickListener {
            listener.onElementoSelected(elementoActual)
        }
        holder.imagenElemento.setOnClickListener {
            listener.onElementoSelected(elementoActual)
        }
        holder.textoTipo.setOnClickListener {
            listener.onElementoSelected(elementoActual)
        }
    }

    override fun getItemCount(): Int {
        // tamaño de la lista
        return lista.size
    }

    interface OnRecyclerElementoListener{
        fun onElementoSelected(elemento: Jugador)
        fun onElementoSelected(elemento: Jugador, posicion: Int)
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textoNombre: TextView;
        var textoTipo: TextView;
        var imagenElemento: ImageView;
        var constraintLayout: ConstraintLayout;

        init {
            constraintLayout = itemView.findViewById(R.id.constraint_general)
            textoTipo = itemView.findViewById(R.id.tipo_jugador)
            textoNombre = itemView.findViewById(R.id.nombre_jugador)
            imagenElemento = itemView.findViewById(R.id.foto_jugador)
        }

    }
}

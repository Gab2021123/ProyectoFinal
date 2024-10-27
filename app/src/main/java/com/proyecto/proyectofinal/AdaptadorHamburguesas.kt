package com.proyecto.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.proyectofinal.R

class AdaptadorHamburguesas (private val listahamburguesas:List<EntidadHamburguesas>):
    RecyclerView.Adapter<AdaptadorHamburguesas.HamburguesaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamburguesaViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_hamburguesas,parent,false)
        return HamburguesaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listahamburguesas.size
    }

    override fun onBindViewHolder(holder: HamburguesaViewHolder, position: Int) {
        val hamburguesasactuales=listahamburguesas[position]
        holder.img_hamburguesa.setImageResource(hamburguesasactuales.imagenhamburguesa)
        holder.t_hamburguesa.text=hamburguesasactuales.nombrehamburguesa
        holder.t_precio.text=hamburguesasactuales.preciohamburguesa
        holder.t_descripcion.text=hamburguesasactuales.descriphamburguesa
    }
    class HamburguesaViewHolder (itemView:View):RecyclerView.ViewHolder(itemView) {
        val img_hamburguesa: ImageView=itemView.findViewById(R.id.imghamburguesas)
        val t_hamburguesa: TextView=itemView.findViewById(R.id.thamburguesas)
        val t_precio: TextView=itemView.findViewById(R.id.tprecio)
        val t_descripcion: TextView=itemView.findViewById(R.id.tdescripcion)
    }
}
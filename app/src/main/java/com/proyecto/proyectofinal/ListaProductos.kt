package com.proyecto.proyectofinal


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.proyectofinal.R
import com.proyecto.proyectofinal.databinding.FragmentListaproductosBinding

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListaProductos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentListaproductosBinding
    private lateinit var adaptador_hamburguesa: AdaptadorHamburguesas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaproductosBinding.inflate(inflater, container, false)
        configurarRecyclerView()
        llenar_data()

        return binding.root
    }

    private fun configurarRecyclerView() {
        binding.recycleviewlistahamburguesa.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaProductos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun llenar_data() {
        val listaHamburguesas=mutableListOf(
            EntidadHamburguesas(R.drawable.pizza1, nombrehamburguesa = "Veggie burguer clásica", preciohamburguesa = "Precio: S/ 14.90", descriphamburguesa = "La Veggie Burger Clásica: medallón de vegetales frescos, lechuga, tomate y cebolla caramelizada en pan suave."),
            EntidadHamburguesas(R.drawable.pizza2, nombrehamburguesa = "Champiñones y Cebolla Caramelizada", preciohamburguesa = "Precio: S/ 24.90", descriphamburguesa = "Champiñones y Cebolla Caramelizada: una combinación jugosa de champiñones frescos y cebolla caramelizada en cada bocado."),
            EntidadHamburguesas(R.drawable.pizza3, nombrehamburguesa = "Impossible Burger Vegano", preciohamburguesa = "Precio: S/ 29.90", descriphamburguesa = "Impossible Burger Vegano: sabor y textura únicos, 100% vegetal, ideal para los amantes de lo saludable."),
            EntidadHamburguesas(R.drawable.pizza4, nombrehamburguesa = "Garbanzos y Espinacas", preciohamburguesa = "Precio: S/ 18.90", descriphamburguesa = "Garbanzos y Espinacas: combinación nutritiva de garbanzos y espinacas, con un toque de especias para un sabor delicioso."),
            EntidadHamburguesas(R.drawable.pizza5, nombrehamburguesa = "Beyond Soy Meat Burger", preciohamburguesa = "Precio: S/ 27.90", descriphamburguesa = "Beyond Soy Meat Burger: hamburguesa de carne vegetal a base de soya, jugosa y llena de sabor, ideal para los amantes de lo vegano."),
            EntidadHamburguesas(R.drawable.pizza6, nombrehamburguesa = "Burger de Tofu y Espárragos", preciohamburguesa = "Precio: S/ 31.90", descriphamburguesa = "Burger de Tofu y Espárragos: deliciosa hamburguesa de tofu marinado con espárragos frescos, perfecta para una experiencia ligera y nutritiva."),
            EntidadHamburguesas(R.drawable.pizza7, nombrehamburguesa = "Roots & Spice", preciohamburguesa = "Precio: S/ 33.90", descriphamburguesa = "Roots & Spice: hamburguesa sabrosa de vegetales asados y especias aromáticas, ideal para los amantes de sabores intensos y naturales."),
            EntidadHamburguesas(R.drawable.pizza8, nombrehamburguesa = "Bite of Nature", preciohamburguesa = "Precio: S/ 37.90", descriphamburguesa = "Bite of Nature: una hamburguesa fresca y nutritiva, repleta de ingredientes orgánicos que celebran el sabor auténtico de la naturaleza.")
        )
        adaptador_hamburguesa=AdaptadorHamburguesas(listaHamburguesas)
        binding.recycleviewlistahamburguesa.adapter = adaptador_hamburguesa
    }
}
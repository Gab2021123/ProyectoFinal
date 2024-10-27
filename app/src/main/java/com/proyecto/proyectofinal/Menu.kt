package com.proyecto.proyectofinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class Menu: AppCompatActivity()  {
    lateinit var navegation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navegation = findViewById(R.id.navMenu)
        navegation.setOnNavigationItemSelectedListener(mOnNavMenu)

        supportFragmentManager.commit {
            replace<ListaProductos>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("Replacement")
        }
    }

    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.itemMenu -> {
                supportFragmentManager.commit {
                    replace<ListaProductos>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("Replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.itemCarrito -> {
                supportFragmentManager.commit {
                    replace<CarritoCompras>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("Replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.itemUbicacion -> {
                supportFragmentManager.commit {
                    replace<Ubicacion>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("Replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
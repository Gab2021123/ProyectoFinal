package com.proyecto.proyectofinal
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyecto.proyectofinal.R
import com.proyecto.proyectofinal.databinding.ActivityRegistroBinding


class Registro : AppCompatActivity() {

    lateinit var binding: ActivityRegistroBinding
    lateinit var clientesDBhelper: MiSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clientesDBhelper = MiSQLiteHelper(this)

        binding.apply {
            btRegistro.setOnClickListener {
                if (etNombre.text.isNotBlank() && etApellido.text.isNotBlank() && etTelefono.text.isNotBlank() && etEmail.text.isNotBlank() && etDireccion.text.isNotBlank() && etContrasena.text.isNotBlank()) {
                    clientesDBhelper.anadirDatos(
                        etNombre.text.toString(),
                        etApellido.text.toString(),
                        etTelefono.text.toString(),
                        etEmail.text.toString(),
                        etDireccion.text.toString(),
                        etContrasena.text.toString()
                    )
                    etNombre.text.clear()
                    etApellido.text.clear()
                    etTelefono.text.clear()
                    etEmail.text.clear()
                    etDireccion.text.clear()
                    etContrasena.text.clear()
                    Toast.makeText(this@Registro, R.string.successful_register, Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@Registro, Login::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@Registro, R.string.add_information, Toast.LENGTH_SHORT)
                        .show()
                }
            }
            btSalir.setOnClickListener {
                finish()
            }
        }
    }
}
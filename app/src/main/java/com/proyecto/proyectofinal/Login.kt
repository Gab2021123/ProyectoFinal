package com.proyecto.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.proyectofinal.databinding.ActivityLoginBinding

class Login: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLoguear.setOnClickListener {
                println("Hola mundo")
               if (binding.etCorreo.text.isNotEmpty() && binding.etContrasena.text.isNotEmpty()) {

                    Toast.makeText(this@Login, R.string.successful_enter, Toast.LENGTH_SHORT).show()
                   /* val intent = Intent(this@Login, Menu::class.java)
                    startActivity(intent)
             */
                   val intent=Intent(this@Login,Menu::class.java)
                   startActivity(intent)
                } else {
                    Toast.makeText(this@Login, R.string.enter_data, Toast.LENGTH_SHORT).show()
                }
            }
            binding.btRegistro.setOnClickListener {
                val intent = Intent(this@Login, Registro::class.java)
                startActivity(intent)
                finish()
            }

    }
}
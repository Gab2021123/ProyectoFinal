package com.proyecto.proyectofinal

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location

import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.proyecto.proyectofinal.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Ubicacion : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val COD_PERMISO_SEGUNDO_PLANO = 100
    private var isPermisos = false

    private lateinit var txtlatitud: TextView
    private lateinit var txtlongitud: TextView

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private lateinit var btMapa: Button

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
        val view = inflater.inflate(R.layout.fragment_ubicacion, container, false)
        txtlatitud = view.findViewById(R.id.tvLatitud)
        txtlongitud = view.findViewById(R.id.tvLongitud)
        btMapa = view.findViewById(R.id.bt_Mapa)

        btMapa.setOnClickListener{
            val intent = Intent(requireContext(),MapActivity::class.java)
            startActivity(intent)
        }

        verificarpermisos()
        return view
    }

    private fun verificarpermisos() {
        val permisos = arrayListOf(
            //Manifest.permission.ACCESS_COARSE_LOCATION,
            //Manifest.permission.ACCESS_FINE_LOCATION
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"
        )
        if (tienePermisos(permisos)){
            isPermisos = true
            permisosConcedidos()
        }else{
            solicitarPermisos(permisos)
        }
    }

    private fun permisosConcedidos() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        try {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    obtenerUbicacion(it)
                } else {
                    Toast.makeText(requireContext(), "No se puede obtener ubicacion", Toast.LENGTH_SHORT).show()
                }
            }
            val locationRequest = com.google.android.gms.location.LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                30000
            ).apply {
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        obtenerUbicacion(location)
                    }
                }
            }

            // Refresh a proveedor de GPS para que pueda personalizar a mi uso.
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
        }
    }

    private fun obtenerUbicacion(it: Location) {
        Log.i("mensaje","lat: ${it.latitude} lng: ${it.longitude}")
        txtlatitud.text = "${it.latitude}"
        txtlongitud.text = "${it.longitude}"
    }

    private fun solicitarPermisos(permisosArray: ArrayList<String>) {
        Log.i("mensaje","solicitarPermisos")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Log.i("mensaje","mayor")
            ActivityCompat.requestPermissions(
                requireActivity(),
                permisosArray.toTypedArray(),
                COD_PERMISO_SEGUNDO_PLANO
            )
        }else {
            Log.i("mensaje","menor")

            requestPermissions(
                permisosArray.toTypedArray(),
                COD_PERMISO_SEGUNDO_PLANO
            )
        }
    }

    private fun tienePermisos(permisosArray: ArrayList<String>): Boolean {
        return permisosArray.toTypedArray().all{item->
            return ContextCompat.checkSelfPermission(
                requireContext(),
                item
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    // Después que se han solicitado los permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i("mensaje","onRequestPermissionsResult:requestCode: $requestCode")
        if (requestCode == COD_PERMISO_SEGUNDO_PLANO){
            val allPermisosConcedidos = grantResults.all {
                it == PackageManager.PERMISSION_GRANTED
            }
            Log.i("mensaje","allPermisosConcedidos: $allPermisosConcedidos")
            if (grantResults.isNotEmpty() && allPermisosConcedidos){
                permisosConcedidos()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Ubicacion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
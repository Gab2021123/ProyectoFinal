package com.proyecto.proyectofinal

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(),OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    lateinit var mGoogleMap: GoogleMap

    companion object {
        const val REQUEST_CODE_LOCATION=0}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    // Es para inicializar el MAP
    override fun onMapReady(p0: GoogleMap) {
        mGoogleMap=p0
        //mGoogleMap.setOnMyLocationClickListener(this)
        enableLocation()
    }

    private fun enableLocation() {
        if (!::mGoogleMap.isInitialized) return
        if (isLocatedPermissionGranted()){
            if (ActivityCompat.checkSelfPermission(
                    this,
                    "android.permission.ACCESS_FINE_LOCATION"
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    "android.permission.ACCESS_COARSE_LOCATION"
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            mGoogleMap.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    // Ve los permisos de localizacion a AccesFineLocation
    private fun isLocatedPermissionGranted() = ContextCompat.checkSelfPermission(this,"android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                "android.permission.ACCESS_FINE_LOCATION"
            )){
            Toast.makeText(this,"Ve a tus ajustes y acepta los permisos",Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION->if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        "android.permission.ACCESS_FINE_LOCATION"
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        "android.permission.ACCESS_COARSE_LOCATION"
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                mGoogleMap.isMyLocationEnabled = true
            }else{
                Toast.makeText(this,"Ve a tus ajustes y acepta los permisos",Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun agregarMarcador(latLng: LatLng){
        // Limpia todo lo que se ha buscado en el mapa
        mGoogleMap.clear()
        mGoogleMap.addMarker(MarkerOptions().position(latLng).title("Mi ubicacion actual"))
        /*mGoogleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng,18f),
            4000,
            null*/}

    // Muestra las coordenadas.
    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this,"Estas en la coordenada: ${p0.latitude} , ${p0.longitude}",Toast.LENGTH_SHORT).show()
        agregarMarcador(LatLng(p0.latitude,p0.longitude))
    }

    override fun onResume() {
        super.onResume()
        if (!::mGoogleMap.isInitialized) return
        if (!isLocatedPermissionGranted()){
            if (ActivityCompat.checkSelfPermission(
                    this,
                    "android.permission.ACCESS_FINE_LOCATION"
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    "android.permission.ACCESS_COARSE_LOCATION"
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            mGoogleMap.isMyLocationEnabled = false
            Toast.makeText(this,"Ve a tus ajustes y acepta los permisos",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        agregarMarcador(LatLng(mGoogleMap.myLocation.latitude,mGoogleMap.myLocation.longitude))
        return false
    }
}
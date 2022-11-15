package com.example.vistaprincipal.recetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.vistaprincipal.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_recetasargentina.*

class Recetas : AppCompatActivity()  {
    //variable de base de datos
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        //Estas dos variables son necesarias para recibir la información que nos envía la clase anterior
        //En la variable "pais" y en la variable "postres"
        val bundle = intent.extras
        val pais = bundle?.getString("pais")
        val postre = bundle?.getString("postre")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetasargentina)

        //este código es un botón para volver a la pantalla anterior
        val botonVolver = findViewById<ImageView>(R.id.volver)
        botonVolver.setOnClickListener{

            onBackPressed()
        }
        //este bloque de código obtiene la información de la base de datos
        //básicamente es lo que hace posible la visualización de las recetas
        db.collection(pais.toString()).document(postre.toString()).get().addOnSuccessListener {
            textViewDescripcion.setText(it.get("receta") as String?)
            textViewNombreReceta.setText(it.get("nombre") as String?)
            textViewIngredientes.setText(it.get("ingredientes") as String?)

        }
    }
}
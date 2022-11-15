package com.example.vistaprincipal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vistaprincipal.recetas.Recetas
import com.example.vistaprincipal.recyclerview.Post
import com.example.vistaprincipal.recyclerview.PostreAdapter
import com.example.vistaprincipal.recyclerview.OnClickListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_seleccion_postre_arg.*

class Postres : AppCompatActivity() , OnClickListener {
    private lateinit var toogle: ActionBarDrawerToggle
    //variable de base de datos
    private val db = FirebaseFirestore.getInstance()
    //variable de la clase Post, necesaria para el recyclerview
    private var posts = mutableListOf<Post>()

   // private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_postre_arg)

        //Estas dos variables son necesarias para recibir la información que nos envía la clase anterior
        //En la variable "pais"
        val bundle = intent.extras
        val pais = bundle?.getString("pais")

        //Este bloque de código lo que hace es traer los postres de la base de datos
        //Y colocarlos en el recyclerview
        db.collection(pais.toString()).addSnapshotListener { value, error ->

            posts = value!!.toObjects(Post::class.java)

            posts.forEachIndexed{index, post ->
                post.uid = value.documents[index].id
            }

            rv.adapter = PostreAdapter(this@Postres, posts, this)
            rv.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@Postres)


            }

        }

    }

    //Este bloque de código diferencia las posiciones del recyclerview
    //Esto con el fin de poder hacer clicks en las diferentes recetas
    //Aparte envía el nombre del postre utilizando la posicion
    //El nombre se guarda en el string "postre"
    override fun onItemClicked(position: Int) {
        //Estas dos variables son necesarias para recibir la información que nos envía la clase anterior
        //En la variable "pais"
        val bundle = intent.extras
        val pais = bundle?.getString("pais")
        val intentPublic = Intent(this, Recetas::class.java)
        intentPublic.putExtra("postre", posts[position].nombre)
        intentPublic.putExtra("pais", pais)
        startActivity(intentPublic)
    }

}
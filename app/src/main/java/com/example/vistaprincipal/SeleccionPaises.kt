package com.example.vistaprincipal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.vistaprincipal.databinding.ActivityMainBinding


class SeleccionPaises : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(500)
        setTheme(R.style.Apptheme)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val paises = resources.getStringArray(R.array.pais)
        val adapter = ArrayAdapter(
            this,
            R.layout.list_item_menu,
            paises
        )

        with(binding.autoCompleteTextView){
            setAdapter(adapter)
            onItemClickListener = this@SeleccionPaises
        }


    }
    //Este bloque de código tiene la función de diferenciar el país seleccionado
    //Esto sirve para poder mandar información a la siguiente clase en un "string" llamado pais
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        if (item == "Argentina"){
            val lanzar1 = Intent(this, Postres::class.java)
            lanzar1.putExtra("pais","Argentina")
            startActivity(lanzar1)

        }else if (item == "Perú"){
            val lanzar2 = Intent(this, Postres::class.java)
            lanzar2.putExtra("pais","Peru")
            startActivity(lanzar2)

        }else if (item == "Brasil"){
            val lanzar3 = Intent(this, Postres::class.java)
            lanzar3.putExtra("pais","Brasil")
            startActivity(lanzar3)

        }else if (item == "México"){
            val lanzar4 = Intent(this, Postres::class.java)
            lanzar4.putExtra("pais","México")
            startActivity(lanzar4)

        }else{
            val lanzar5 = Intent(this, Postres::class.java)
            lanzar5.putExtra("pais","Colombia")
            startActivity(lanzar5)

        }

    }

    //Este bloque de código sirve para que al tocar el botón "hacía atras" del celular
    //Nos salte un mensaje de advertencia preguntando si realmente queremos salir de la app
    override fun onBackPressed() {
        SweetAlertDialog(this@SeleccionPaises, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Has oprimido el botón salir")
            .setContentText("¿Está seguro qué quiere salir?")
            .setConfirmButton(
                "Si, salir"
            ) { sDialog -> System.exit(0) }
            .setCancelButton(
                "No, cancelar"
            ) { sDialog -> sDialog.dismissWithAnimation() }
            .show()
    }

}
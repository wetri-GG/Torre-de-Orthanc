package com.isengard.fruegas.torredeorthanc

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Idetificadores
        val idUruk = findViewById<EditText>(R.id.Identificador)
        val unidad = findViewById<Spinner>(R.id.Unidad)
        val Grupo = findViewById<RadioGroup>(R.id.Grupo)
        val antorcha = findViewById<CheckBox>(R.id.Antorcha)
        //le pedimos focus
        idUruk.requestFocus()
        //escuchamos el focus y le mandamos error si no pone nombre
        idUruk.setOnFocusChangeListener{ view, hasFocus ->
            if (!hasFocus){
                if (idUruk.text.isEmpty()) idUruk.error = "El ejercito no acepta soldados anonimos"
            }
        }
        //boton de registro
        val registro = findViewById<ImageButton>(R.id.Registro)
        //creamos el registro
        registro.setOnClickListener {
            val id = idUruk.text.toString()
            if (idUruk.text.isEmpty()){
                Toast.makeText(this, "no hay nombre", Toast.LENGTH_LONG).show()
            }else{
                val unity = unidad.selectedItem.toString()
                val grupo = when (Grupo.checkedRadioButtonId) {
                    R.id.Armadura -> "Armadura de Hierro"
                    R.id.Escudo -> "Escudo de Isengard"
                    else -> "Sin elegir"
                }
                val torch = mutableListOf<String>()
                    if (antorcha.isChecked) torch.add("Antorcha")
                val resumen = "ID: $id\nUnidad: $unity\nGrupo: $grupo\n" + torch
                Toast.makeText(this,resumen,Toast.LENGTH_LONG).show()
            }
        }
    }
}
package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityMainBinding
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    val CODIGO_SUBMIT_1 = 1;
    val CODIGO_SUBMIT_2 = 2;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.text1.text = viewModel.text1
        binding.text2.text = viewModel.text2

        var db = LivroDBOpener(this)

        var settings = getSharedPreferences("prefs", MODE_PRIVATE)
        var output = settings.getBoolean("wel", true)

        if(output) {
            Toast.makeText(this, "Bem vindo", Toast.LENGTH_LONG).show()

            var edit = settings.edit()
            edit.putBoolean("wel", false)
            edit.apply()
        }


        binding.button1.setOnClickListener {
            val intent = Intent(this, ActionActivity1::class.java)

            intent.putExtra("teste", 1)
            startActivityForResult(intent, CODIGO_SUBMIT_1)
        }

        binding.button2.setOnClickListener {
            val dialog = SendMessageDialogFragment()
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, "Dialog")
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, ActionActivity2::class.java)
            startActivityForResult(intent, CODIGO_SUBMIT_2)
        }

        binding.button4.setOnClickListener {
            if (db.findAll().isEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_LONG).show()
                finish()
            } else {
                val intent = Intent(this, ActionActivity3::class.java)
                startActivity(intent)

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var resultado = data!!.getStringExtra("resultado")
        var resultado2 = data.getStringExtra("result")
        when(resultCode) {
            RESULT_OK-> {
                when(requestCode) {
                    CODIGO_SUBMIT_1 -> {
                        binding.text1.text = resultado
                        viewModel.text1 = resultado.toString()
                    }
                    CODIGO_SUBMIT_2 -> {
                        binding.text2.text = resultado2
                        viewModel.text2 = resultado2.toString()
                    }
                }
            }
            RESULT_CANCELED-> {
                Snackbar.make(mainLayout, "Atualização Realizada", Snackbar.LENGTH_LONG)
                    .setAction("Cancelar") {
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
                    }
                    .show()
            }
        }

    }
}
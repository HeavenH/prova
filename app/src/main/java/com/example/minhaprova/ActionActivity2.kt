package com.example.minhaprova

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAction2Binding

import android.app.Activity

class ActionActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityAction2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_action2);




        binding.saveButton.setOnClickListener {


            var nome = binding.nome.text
            var autor = binding.autor.text
            var ano = binding.ano.text
            var nota = binding.notaButton.rating

            var livro = Livro(0, nome.toString(), autor.toString(), ano.toString().toInt(), nota)
            val db = LivroDBOpener(this);

            db.insert(livro)

            setResult(RESULT_OK,Intent().putExtra("result","cadastrado"))
            finish()
        }

        binding.cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }
}
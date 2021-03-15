package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAction3Binding

class ActionActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityAction3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action3)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_action3);

        var db = LivroDBOpener(this)
        var cont = 1
        var livros = db.findAll()
        var livro = db.findById(cont)


            binding.apply {
                textNome.text = "Titulo: ${livro.nome}"
                textAutor.text = "Autor: ${livro.autor}"
                textAno.text = "Ano: ${livro.ano}"
                textNota.text = "Nota: ${livro.nota}"

                nextButton.setOnClickListener {
                    var livro = db.findById(++cont)
                    textNome.text = "Titulo: ${livro.nome}"
                    textAutor.text = "Autor: ${livro.autor}"
                    textAno.text = "Ano: ${livro.ano}"
                    textNota.text = "Nota: ${livro.nota}"

                    if (cont > 1) binding.previousButton.isEnabled = true
                    if (cont == livros.size) binding.nextButton.isEnabled = false
                }

                previousButton.setOnClickListener {
                    if (cont > 1) {
                        nextButton.isEnabled = true
                    }

                    var livro = db.findById(--cont)
                    textNome.text = "Titulo: ${livro.nome}"
                    textAutor.text = "Autor: ${livro.autor}"
                    textAno.text = "Ano: ${livro.ano}"
                    textNota.text = "Nota: ${livro.nota}"

                    if (cont == 1) binding.previousButton.isEnabled = false
                }
            }

    }
}
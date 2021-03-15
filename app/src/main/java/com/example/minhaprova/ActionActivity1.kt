package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAction1Binding
import android.content.Intent

class ActionActivity1 : AppCompatActivity() {

    lateinit var binding: ActivityAction1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action1)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_action1);

        binding.cancelButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Cancelar", "Cancelado")
            setResult(RESULT_CANCELED, intent)
            finish()
        }

        binding.submitButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("resultado", binding.editText.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}
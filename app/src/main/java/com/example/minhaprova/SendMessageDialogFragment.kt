package com.example.minhaprova

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SendMessageDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)

        builder.setTitle("Pergunta importante").
        setMessage("Gostaria de uma xícara de café?")
            .setPositiveButton("sim", { dialog, id ->
                Toast.makeText(activity, "Ótimo", Toast.LENGTH_LONG).show()
            })

            .setNegativeButton("Não", { dialog, id ->
                Toast.makeText(activity, "Fica para a próxima", Toast.LENGTH_LONG).show()
            })

        return builder.create()
    }
}
package com.example.minhaprova

import android.provider.BaseColumns

object LivroContrato {
    const val DATABASE_NAME = "livro.sqlite"
    const val DATA_BASE_VERSION = 1

    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroEntry.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
        "${LivroEntry.NOME} TEXT," +
        "${LivroEntry.AUTOR} TEXT," +
        "${LivroEntry.ANO} INTEGER," +
        "${LivroEntry.NOTA} REAL )"

    object LivroEntry: BaseColumns {
        const val TABLE_NAME = "livro"
        const val NOME = "nome"
        const val AUTOR = "autor"
        const val ANO = "ano"
        const val NOTA = "nota"
    }
}
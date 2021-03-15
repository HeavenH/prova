package com.example.minhaprova

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class LivroDBOpener(context : Context) : SQLiteOpenHelper(context, LivroContrato.DATABASE_NAME, null, 1) {

    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroContrato.LivroEntry.TABLE_NAME}" +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${LivroContrato.LivroEntry.NOME} TEXT," +
                " ${LivroContrato.LivroEntry.AUTOR} TEXT," +
                " ${LivroContrato.LivroEntry.NOTA} REAL," +
                " ${LivroContrato.LivroEntry.ANO} INTEGER" +
                ")"
    val SQL_DROP_TABLE =
        "DROP TABLE ${LivroContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insert(livro: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, livro.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, livro.autor)
            values.put(LivroContrato.LivroEntry.NOTA, livro.nota)
            values.put(LivroContrato.LivroEntry.ANO, livro.ano)

            banco.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values)

        } finally {
            banco.close()
        }
    }

    fun findById(id: Int): Livro {
        var db: SQLiteDatabase = readableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = db.query(
                LivroContrato.LivroEntry.TABLE_NAME,
                null,
                selection,
                whereArgs,
                null,
                null,
                null,
                null
            )

            cursor.moveToNext()

            var livro = Livro()
            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))

            return livro

        } finally {
            db.close()
        }
    }

    fun findAll(): ArrayList<Livro> {
        var db: SQLiteDatabase = readableDatabase

        try {
        var livroList = ArrayList<Livro>()
        val cursor: Cursor = db.query(LivroContrato.LivroEntry.TABLE_NAME,null,
            null,
            null,
            null,
            null,
            null,
            null)

        while(cursor.moveToNext()) {

            var livro = Livro()

            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

            livroList.add(livro)
        }

        return livroList
        } finally {
            db.close()
        }
    }

}
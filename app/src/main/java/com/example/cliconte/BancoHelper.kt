package com.example.cliconte

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class BancoHelper(context: Context): SQLiteOpenHelper(context, "dados.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table instituicao(id integer primary key autoincrement not null, " +
                "nome text, contador integer)"

        Log.i("APP_LOG", "Acesso Banco Instituição")
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table instisuicao")
        this.onCreate(db)
    }
}
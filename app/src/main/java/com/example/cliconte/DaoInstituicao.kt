package com.example.cliconte

import android.content.ContentValues
import android.content.Context

class DaoInstituicao {
    var banco: BancoHelper

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    // Fun para adicionar um novo objeto na Lista
    fun add(instituicao: Instituicao) {
        val cv = ContentValues().apply {
            put("nome", instituicao.nome)
            put("contador", instituicao.contador)
        }
        this.banco.writableDatabase.insert("intituicao", null, cv)
    }

    // Fun para contar os Clicks
    fun count(): Int {
        val colunas = arrayOf("id")
        val c = this.banco.readableDatabase.query("instituicao", colunas, null,
            null, null, null, "contador")
        return c.count
    }

    // Consulta de todos objetos da lista
    fun get(): MutableList<Instituicao> {
        val lista = mutableListOf<Instituicao>()
        val colunas = arrayOf("id", "nome", "contador")
        val c = this.banco.readableDatabase.query("instituicao", colunas, null,
            null, null, null, "contador desc")
        c.moveToFirst()
        // Log.i("APP_LOG", lista.toString()+"antes do For")
        for(i in 1 .. c.count) {
            var id = c.getInt(0)
            var nome = c.getString(1)
            var contador = c.getInt(2)
            lista.add(Instituicao(id, nome, contador))
            c.moveToNext()
        }
        // Log.i("APP_LOG", lista.toString()+"depois do For")
        return lista
    }

    // Consulta de um objeto na lista passando o Id
    fun get(id: Int): Instituicao? {
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        val colunas = arrayOf("id", "nome", "contador")
        val c = this.banco.readableDatabase.query("instituicao", colunas, where,
            pWhere, null, null, "contador")
        c.moveToFirst()
        if (c.count == 1) {
            var id = c.getInt(0)
            var nome = c.getString(1)
            var contador = c.getInt(2)
            return Instituicao(id, nome, contador)
        }
        return null
    }

    // Fun para atualizar um objeto na Lista
    fun update(instituicao: Instituicao) {
        val where = "id = ?"
        val pWhere = arrayOf(instituicao.id.toString())
        val cv = ContentValues().apply {put("contador", instituicao.contador+1)}
        this.banco.writableDatabase.update("instituicao", cv, where, pWhere)
    }

    // Fun para deletar um objeto da Lista passando um Id
    fun delete(id: Int) {
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        this.banco.writableDatabase.delete("instituicao", where, pWhere)
    }

    // Fun para deletar um objeto da Lista passando um Objeto
    fun delete(instituicao: Instituicao) {
        val where = "id = ?"
        val pWhere = arrayOf(instituicao.id.toString())
        this.banco.writableDatabase.delete("instituicao", where, pWhere)
    }
}
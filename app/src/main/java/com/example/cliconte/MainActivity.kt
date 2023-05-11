package com.example.cliconte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    // Declaração das Var do Back-End
    private lateinit var btAdd: Button
    private lateinit var lvInstituicoes: ListView
    private lateinit var dao: DaoInstituicao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sincronismo Back-End com Front-End
        this.dao = DaoInstituicao(this)
        this.btAdd = findViewById(R.id.btadd)
        this.lvInstituicoes = findViewById(R.id.lvInstituicao)

        // Adicionar um Objeto na Lista
        this.btAdd.setOnClickListener({add()})

        // Atualização de um Objeto na lista
        this.updateListView()

        // Ação do Click Curto em um Objeto na Tela
        this.lvInstituicoes.setOnItemClickListener(OnItemClick())

        // Ação do Click Longo em um Objeto na Tela
        this.lvInstituicoes.setOnItemLongClickListener(OnItemLongClick())
    }

    // Fun para adicionar um Objeto na Lista
    fun add() {
        val instituicao = Instituicao("IFPB")
        this.dao.add(instituicao)
        //this.lvInstituicoes.adapter = ArrayAdapter<Instituicao>(this, android.R.layout.simple_list_item_1, this.dao.get())
        this.updateListView()
    }

    // Fun para atualizar um Objeto na Lista
    fun updateListView() {
        this.lvInstituicoes.adapter = ArrayAdapter<Instituicao>(this, android.R.layout.simple_list_item_1, this.dao.get())
    }

    // Inner class para a ação do Click Curto no objeto da Tela
    inner class OnItemClick: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val inst = parent?.getItemAtPosition(position) as Instituicao
            this@MainActivity.dao.update(inst)
            this@MainActivity.updateListView()
            //Toast.makeText(this@MainActivity, inst.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    // Inner class para ação Click Longo no Objeto na Tela
    inner class OnItemLongClick: AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long,
        ): Boolean {
            val inst = parent?.getItemAtPosition(position) as Instituicao
            this@MainActivity.dao.delete(inst)
            this@MainActivity.updateListView()
            return true
        }
    }
}
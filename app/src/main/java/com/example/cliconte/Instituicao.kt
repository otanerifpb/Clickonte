package com.example.cliconte

class Instituicao {
    var id: Int
    var nome: String
    var contador: Int

    // Constructor 1 quando for passado apenas o nome da instituição
    constructor(nome: String) {
        this.id = -1
        this.nome = nome
        this.contador = 0
    }

    // Contrutor 2 quando for informado todos os dados do objeto, id, nome e contador
    constructor(id: Int, nome: String, contador: Int) {
        this.id = id
        this.nome = nome
        this.contador = contador
    }

    // ToString, é a formatação de como os dados do objeto Instituição é apresentado na View
    override fun toString(): String {
        return "${id} - ${nome} - ${contador}"
    }
}
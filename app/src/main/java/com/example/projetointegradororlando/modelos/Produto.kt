package com.example.projetointegradororlando.modelos

import java.io.File

data class Produto(
    var id: String? = null,
    val imagem: String,
    var titulo: String,
    var descricao: String,
    var preco: String
)

package com.example.projetointegradororlando.modelos

import java.io.File

data class Produto(
    var id: String = "",
    val imagem: String = "",
    var titulo: String = "",
    var descricao: String = "",
    var preco: String = "",
    var comprado: Boolean = false

)

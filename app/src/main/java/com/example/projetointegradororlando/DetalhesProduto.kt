package com.example.projetointegradororlando

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetointegradororlando.databinding.ActivityDetalhesProdutoBinding

class DetalhesProduto : AppCompatActivity() {
    lateinit var binding: ActivityDetalhesProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTitulo.text = intent.getStringExtra("titulo")
        binding.textDescricao.text = intent.getStringExtra("descricao")
        binding.textPreco.text = intent.getStringExtra("preco")

        binding.button.setOnClickListener {

        }

    }
}
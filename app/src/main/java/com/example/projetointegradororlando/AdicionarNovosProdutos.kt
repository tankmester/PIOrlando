package com.example.projetointegradororlando

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentAdicionarNovosProdutosBinding
import com.example.projetointegradororlando.databinding.FragmentAdicionarProdutosBinding
import com.example.projetointegradororlando.modelos.Produto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdicionarNovosProdutos : Fragment() {

    lateinit var binding: FragmentAdicionarNovosProdutosBinding

    lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdicionarNovosProdutosBinding.inflate(inflater)


        binding.buttonAdicionar.setOnClickListener {
            adicionarNovoProduto()
        }

        return binding.root
    }

    fun adicionarNovoProduto(){
        val produto = Produto(
            imagem = binding.editTextUrlImagem.toString(),
            titulo = binding.editTextTitulo.toString(),
            descricao = binding.editTextTextMultiLineDescricao.toString(),
            preco = binding.editTextNumberDecimalPreco.toString().toDouble())
        val newNode = database.child("produtos").push()
        produto.id = newNode.key
        newNode.setValue(produto)
    }

    fun setupFirebase(){
        val usuario = LoginActivity().getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference.child(usuario.uid)
        }
    }

}
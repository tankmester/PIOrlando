package com.example.projetointegradororlando

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentAdicionarNovosProdutosBinding
import com.example.projetointegradororlando.databinding.FragmentAdicionarProdutosBinding
import com.example.projetointegradororlando.modelos.Produto
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

        setupFirebase()

        binding.buttonAdicionar.setOnClickListener {
            adicionarNovoProduto()
        }

        return binding.root
    }

    fun adicionarNovoProduto(){
        val produto = Produto(
            imagem = binding.editTextUrlImagem.text.toString(),
            titulo = binding.editTextTitulo.text.toString(),
            descricao = binding.editTextTextMultiLineDescricao.text.toString(),
            preco = binding.editTextNumberDecimalPreco.text.toString())
        val newNode = database.child("produtos").push()
        val newNodeGeral = FirebaseDatabase.getInstance().reference.child("TodosProdutos").push()
        produto.id = newNode.key
        newNode.setValue(produto)
        newNodeGeral.setValue(produto)
        val i = Intent(context, MainActivity::class.java)
        startActivity(i)
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun setupFirebase(){
        val usuario = getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference.child(usuario.uid)
        }
        else{
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(providers)
                    .build(), 1
            )
        }
    }

}
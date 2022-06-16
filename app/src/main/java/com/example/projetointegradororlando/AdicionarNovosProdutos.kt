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
import com.google.firebase.database.*

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
            val i = Intent(context, MainActivity::class.java)
            startActivity(i)
        }

        return binding.root
    }
    fun adicionarNovoProduto(){
        val usuario = getCurrentUser()
        val produto = Produto(
            imagem = binding.editTextUrlImagem.text.toString(),
            titulo = binding.editTextTitulo.text.toString(),
            descricao = binding.editTextTextMultiLineDescricao.text.toString(),
            preco = binding.editTextNumberDecimalPreco.text.toString())
        //val newNode = database.child("Produtos").push()
        //val newNodeGeral = FirebaseDatabase.getInstance().reference.child("TodosProdutos").push()
        //produto.id = newNode.key
        val newNode = usuario?.let { database.child("usuarios").child(it.uid).child("produtos").push() }
        newNode?.key.let { produto.id = it }
        newNode?.setValue(produto)
        //newNodeGeral.setValue(produto)
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun setupFirebase(){
        val usuario = getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference//.child(usuario.uid)
            val dataBaseListener = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    dataProcessing(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            database.child("produtos").addValueEventListener(dataBaseListener)
        }
       /* else{
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(providers)
                    .build(), 1
            )
        }*/
    }
    fun dataProcessing(snapshot: DataSnapshot) {
        val produtos = arrayListOf<Produto>()

        snapshot.children.forEach{
            val produto = it.getValue(Produto::class.java)
            produto?.let {
                 produtos.add(produto)
            }
        }
    }
}
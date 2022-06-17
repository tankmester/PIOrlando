package com.example.projetointegradororlando

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentAdicionarProdutosBinding
import com.example.projetointegradororlando.databinding.ProdutoBinding
import com.example.projetointegradororlando.modelos.Produto
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class adicionarProdutos : Fragment() {

    lateinit var binding: FragmentAdicionarProdutosBinding

    lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdicionarProdutosBinding.inflate(inflater)

        binding.floatingActionButton.setOnClickListener {
            container?.let{
                parentFragmentManager.beginTransaction().replace(it.id, AdicionarNovosProdutos()).commit()
            }
        }
        setupFirebase()

        return binding.root

    }
    fun refreshUi(list: List<Produto>){
        binding.containerProduto.removeAllViews()

        list.forEach{
            val cardBinding = ProdutoBinding.inflate(layoutInflater)

            Picasso.get().load(it.imagem).into(cardBinding.imageView)
            cardBinding.textTitulo.text = it.titulo
            cardBinding.textDesc.text = it.descricao

            binding.containerProduto.addView(cardBinding.root)

        }

    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun setupFirebase(){
        val usuario = getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference.child(usuario.uid)

            val dataBaseListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    dataProcessing(snapshot)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("adicionarProdutos", "setupFirebase", error.toException())
                }
            }
            database.child("produtos").addValueEventListener(dataBaseListener)
        }
    }
    fun dataProcessing(snapshot: DataSnapshot) {
        val produtos = arrayListOf<Produto>()

        snapshot.children.forEach{
            val produto = it.getValue(Produto::class.java)
            produto?.let {
                produtos.add(produto)
            }
        }
        refreshUi(produtos)
    }

}

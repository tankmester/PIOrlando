package com.example.projetointegradororlando

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.ActivityMainBinding
import com.example.projetointegradororlando.databinding.FragmentHomeBinding
import com.example.projetointegradororlando.databinding.ProdutoBinding
import com.example.projetointegradororlando.modelos.Produto
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class homeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root

    }


    fun refreshUi(list: List<Produto>){
        binding.containerHome.removeAllViews()

        list.forEach{
            val cardBinding = ProdutoBinding.inflate(layoutInflater)

            cardBinding.textTitulo.text = it.titulo
            cardBinding.textDesc.text = it.descricao

            binding.containerHome.addView(cardBinding.root)

        }

    }

    private fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
    fun setupFirebase(){

    }
/*
    private fun setupFirebase(){
        val usuario = getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference.child("TodosProdutos")
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = arrayListOf<Produto>()

                    snapshot.children.forEach{
                        val map = it.value as HashMap<String, Any>

                        val id = it.key

                        val imagem = map["imagem"] as String
                        val titulo = map["titulo"] as String
                        val descricao = map["descricao"] as String
                        val preco = map["preco"] as String

                        val prod = Produto(id, titulo,descricao, preco, imagem)
                        list.add(prod)
                    }

                    refreshUi(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("homeFragment", "setupFirebase", error.toException())
                }
            }
            database.addValueEventListener(valueEventListener)
        }
    }*/
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
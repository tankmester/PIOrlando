package com.example.projetointegradororlando

import android.content.Intent
import android.net.Uri
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
import com.google.firebase.database.ktx.getValue
import com.squareup.picasso.Picasso


class homeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        setupFirebase()


        return binding.root

    }


    fun refreshUi(list: List<Produto>){
        binding.containerHome.removeAllViews()

        list.forEach{

            if(it.comprado == true){

            }else{
                val cardBinding = ProdutoBinding.inflate(layoutInflater)

                val img = it.imagem

                Picasso.get().load(it.imagem).into(cardBinding.imageView)
                cardBinding.titulo.text = it.titulo
                cardBinding.descricao.text = it.descricao
                val preco = it.preco
                val id = it.id


                binding.containerHome.addView(cardBinding.root)

                cardBinding.root.setOnClickListener {
                    val i = Intent(context, DetalhesProduto::class.java)

                    i.putExtra("imagem", img)
                    i.putExtra("titulo", cardBinding.titulo.text.toString())
                    i.putExtra("descricao", cardBinding.descricao.text.toString())
                    i.putExtra("preco", preco)
                    i.putExtra("id", id)
                    startActivity(i)

                }
            }


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
                produtos.add(it)
            }
        }
        refreshUi(produtos)
    }

}
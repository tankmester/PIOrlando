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


        if(getCurrentUser() == null){
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(providers)
                    .build(), 1
            )
        }
        else {
            setupFirebase()
        }


        return binding.root

    }

    fun refreshUi(list: List<Produto>){
        binding.containerProduto.removeAllViews()

        list.forEach{
            val cardBinding = ProdutoBinding.inflate(layoutInflater)

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

            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = arrayListOf<Produto>()

                    snapshot.child("produtos").children.forEach{
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
    }

}
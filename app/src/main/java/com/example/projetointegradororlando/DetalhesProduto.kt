package com.example.projetointegradororlando

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.projetointegradororlando.databinding.ActivityDetalhesProdutoBinding
import com.example.projetointegradororlando.modelos.Produto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class DetalhesProduto : AppCompatActivity() {
    lateinit var binding: ActivityDetalhesProdutoBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupFirebase()

        val id = intent.getStringExtra("id")
        val img = intent.getStringExtra("imagem")

        if(id != null){

            val itemReference = database?.child("produtos")?.child(id)


            Picasso.get().load(img).into(binding.imagemProduto)

            binding.textTitulo.text = intent.getStringExtra("titulo")
            binding.textDescricao.text = intent.getStringExtra("descricao")
            binding.textPreco.text = intent.getStringExtra("preco")

            binding.button.setOnClickListener {

                alert("Alugar produto","Você deseja realmente alugar este produto?")


                itemReference?.child("comprado")?.setValue(true)

            }

        }

    }

    private fun alert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Alugar") {dialog, button ->
                alert2("Sucesso", "Seu produto foi alugado e estará com você em breve")
            }
            .create().show()
    }

    private fun alert2(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") {dialog, button ->
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            .create().show()
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }


    private fun setupFirebase(){
        val usuario = getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference.child(usuario.uid)
        }
    }



}


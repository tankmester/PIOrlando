package com.example.projetointegradororlando

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.projetointegradororlando.databinding.ActivityCadastroBinding

/*
class CadastroActivity : AppCompatActivity() {
    lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonCadastro.setOnClickListener {
            if(binding.editSenha != null && binding.editEmail != null && binding.editNome != null){

                AlertDialog.Builder(this)
                    .setTitle("Olá, ${binding.editNome.text}")
                    .setMessage("Você foi cadastrado com sucesso!")
                    .create()
                    .show()

                val i = Intent(this, MainActivity::class.java)
                i.putExtra("nome", binding.editNome.text.toString())
                i.putExtra("email", binding.editEmail.text.toString())
                startActivity(i)


            }


        }

    }
}

 */
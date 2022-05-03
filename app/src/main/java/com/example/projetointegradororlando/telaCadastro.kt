package com.example.projetointegradororlando

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentTelaCadastroBinding
import com.example.projetointegradororlando.perfil as comExampleProjetointegradororlandoPerfil

class telaCadastro : Fragment() {

    lateinit var binding: FragmentTelaCadastroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentTelaCadastroBinding.inflate(inflater)


        binding..setOnClickListener {
            if(binding.editSenhaCadastro != binding.editConfirmarSenhaCadastro){

                AlertDialog.Builder(context)
                    .setTitle("Senhas não são iguais")
                    .setMessage("Você não pode efetuar o cadastro :(" +
                            "" +
                            "Confirme novamente sua senha")
                    .create()
                    .show()

                val i = Intent(this, MainActivity::class.java)
                i.putExtra("nome", binding.editNome)
                i.putExtra("email", binding.editEmailCadastro)
                startActivity(i)
            }



            AlertDialog.Builder(context)
                .setTitle("Olá, ${binding.editNome}")
                .setMessage("Você foi cadastrado com sucesso!")
                .create()
                .show()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }


        return binding.root
    }


}
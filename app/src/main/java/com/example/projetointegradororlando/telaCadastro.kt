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


        binding.buttonCadastro.setOnClickListener {
            if(binding.editSenha == null){

                AlertDialog.Builder(context)
                    .setTitle("Senhas vazia")
                    .setMessage("Você não pode efetuar o cadastro :(" +
                            "" +
                            "Coloque sua senha")
                    .create()
                    .show()


            }



            AlertDialog.Builder(context)
                .setTitle("Olá, ${binding.editNome}")
                .setMessage("Você foi cadastrado com sucesso!")
                .create()
                .show()

            val i = Intent(context, MainActivity::class.java)
            i.putExtra("nome", binding.editNome.text.toString())
            i.putExtra("email", binding.editEmail.text.toString())
            startActivity(i)

        }


        return binding.root
    }


}
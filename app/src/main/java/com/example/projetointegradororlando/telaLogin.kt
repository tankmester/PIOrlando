package com.example.projetointegradororlando

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentTelaLoginBinding


class telaLogin : Fragment() {

    lateinit var binding: FragmentTelaLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTelaLoginBinding.inflate(inflater)

        binding.textTelaCadastro.setOnClickListener{
            val i = Intent(context, FragmentTelaLoginBinding::class.java)
            startActivity(i)
        }


        return binding.root
    }


}
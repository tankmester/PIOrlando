package com.example.projetointegradororlando

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentAdicionarProdutosBinding


class adicionarProdutos : Fragment() {

    lateinit var binding: FragmentAdicionarProdutosBinding

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


        return binding.root

    }

}
package com.example.projetointegradororlando

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegradororlando.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPerfilBinding.inflate(inflater)

        binding.btDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }
        binding.btSair.setOnClickListener{
            System.exit(0)
        }

        return binding.root

    }
}
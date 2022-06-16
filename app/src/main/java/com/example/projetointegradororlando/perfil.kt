package com.example.projetointegradororlando


import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.projetointegradororlando.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPerfilBinding.inflate(inflater)

        binding.btDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            alert("DESLOGANDO", "Você deseja sair do app?")

        }


        binding.btSair.setOnClickListener{
            System.exit(0)
        }

        return binding.root

    }

    private fun alert(title: String, message: String) {
        val builder = AlertDialog.Builder(context!!)
        builder
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Deslogar") {dialog, button ->
                finishAffinity()
            }
            .setNegativeButton("Ficar", null)
            .create().show()
    }

    private fun finishAffinity() {
        System.exit(0)
    }


}



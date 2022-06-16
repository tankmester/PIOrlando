package com.example.projetointegradororlando



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.projetointegradororlando.databinding.FragmentPerfilBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.system.exitProcess


class perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPerfilBinding.inflate(inflater)

        binding.btDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            alert("DESLOGANDO", "Você não está mais logado\nDeseja sair do app?")

        }


        binding.btSair.setOnClickListener{
            exitProcess(0)
        }

        binding.btLogin.setOnClickListener(){
            if(getCurrentUser() == null){
                val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build())

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
        }

        binding.textEmailUsuario.text = FirebaseAuth.getInstance().currentUser?.email.toString()
        binding.textNomeUsuario.text = FirebaseAuth.getInstance().currentUser?.displayName.toString()

        binding.textEmailUsuario

        return binding.root

    }

    private fun alert(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Sair") {dialog, button ->
                finishAffinity()
            }
            .setNegativeButton("Ficar") { dialog, button ->
                val i = Intent(context, MainActivity::class.java)
                startActivity(i)
            }
            .create().show()
    }

    private fun finishAffinity() {
        exitProcess(0)
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun setupFirebase(){
        val usuario = getCurrentUser()

        if (usuario != null){
            database = FirebaseDatabase.getInstance().reference.child(usuario.uid)
        }
    }


}



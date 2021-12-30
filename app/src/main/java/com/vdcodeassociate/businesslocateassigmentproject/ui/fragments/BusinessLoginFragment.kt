package com.vdcodeassociate.businesslocateassigmentproject.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vdcodeassociate.businesslocateassigmentproject.R
import com.vdcodeassociate.businesslocateassigmentproject.databinding.FragmentBusinessLoginBinding

class BusinessLoginFragment : Fragment(R.layout.fragment_business_login) {

    // TAG
    private final val TAG = "BusinessLoginFragment"

    // viewBinding
    private lateinit var binding: FragmentBusinessLoginBinding

    // Firebase
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBusinessLoginBinding.bind(view)

        // init Firebase auth
        FirebaseApp.initializeApp(requireContext())
        firebaseAuth = Firebase.auth

        binding.apply {

            // go to sign up fragment
            signUp.setOnClickListener{
                findNavController().navigate(R.id.action_businessLoginFragment_to_businessSignUpFragment)
            }

            // login pressed
            loginButton.setOnClickListener {
                login()
            }

        }

    }

    // user exist firebase
    private fun loginViaFirebase(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")

                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    // proceed login
    private fun login() {
        binding.apply {

            if (!isTextEmpty()) {
                loginViaFirebase(email.text.toString(),password.text.toString())
            }

        }
    }

    // checks for empty texts
    private fun isTextEmpty(): Boolean {

        allTextEmptyListener()

        var isTextEmpty = false

        binding.apply {

            if (email.text.isNullOrEmpty()) {
                isTextEmpty = true
                emailLayout.error = "Registered email required!"
            }

            if (password.text.isNullOrEmpty()) {
                isTextEmpty = true
                passwordLayout.error = "Registered password required!"
            }

        }

        return isTextEmpty
    }

    // all text empty reset
    private fun allTextEmptyListener(){
        binding.apply {

            email.addTextChangedListener{
                if(it.isNullOrEmpty()){
                    emailLayout.error = "Registered email required!"
                } else {
                    emailLayout.error = null
                }
            }

            password.addTextChangedListener{
                if(it.isNullOrEmpty()){
                    passwordLayout.error = "Registered password required!"
                } else {
                    passwordLayout.error = null
                }
            }

        }
    }

}
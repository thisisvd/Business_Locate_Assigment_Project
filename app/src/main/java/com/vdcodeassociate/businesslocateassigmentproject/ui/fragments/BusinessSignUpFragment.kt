package com.vdcodeassociate.businesslocateassigmentproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vdcodeassociate.businesslocateassigmentproject.R
import com.vdcodeassociate.businesslocateassigmentproject.databinding.FragmentBusinessSignUpBinding
import kotlin.math.log

class BusinessSignUpFragment : Fragment(R.layout.fragment_business_sign_up) {

    // TAG
    private val TAG = "BusinessSignUpFragment"

    // viewBinding
    private lateinit var binding: FragmentBusinessSignUpBinding

    // Firebase account
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBusinessSignUpBinding.bind(view)

        // init firebase
        firebaseAuth = Firebase.auth

        binding.apply {

            // go to login
            login.setOnClickListener{
                findNavController().navigate(R.id.action_businessSignUpFragment_to_businessLoginFragment)
            }

            // create an account
            continueButton.setOnClickListener {
                createAccount()
            }

        }

    }

    // Create account in firebase
    private fun createAccountInFirebase(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = firebaseAuth.currentUser

                } else {
                    Log.d(TAG, "createUserWithEmail:failure")
                    Toast.makeText(requireContext(), "Authentication failed - May be account already exists",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    // proceed create account
    private fun createAccount() {
        binding.apply {

            if (!isTextEmpty()) {
                if (isALLayoutNull()) {
                    createAccountInFirebase(email.text.toString(),reTypePassword.text.toString())
                    Toast.makeText(requireContext(), "Successful toast!", Toast.LENGTH_SHORT).show()
                }
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
                emailLayout.error = "Valid email required!"
            }

            if (password.text.isNullOrEmpty()) {
                isTextEmpty = true
                passwordLayout.error = "Password can't be empty!"
            } else if (password.text.toString().length < 6) {
                isTextEmpty = true
                passwordLayout.error = "Password length can't be smaller than 6!"
            }

            if(reTypePassword.text.isNullOrEmpty()){
                isTextEmpty = true
                reTypePasswordLayout.error = "Re-Type Password can't be empty!"
            } else if (reTypePassword.text.toString().length < 6) {
                isTextEmpty = true
                reTypePasswordLayout.error = "Re-Type Password length can't be smaller than 6!"
            }

            if(password.text.toString() != reTypePassword.text.toString()) {
                isTextEmpty = true
                reTypePasswordLayout.error = "Password doesn't match!"
            }

        }

        return isTextEmpty
    }

    // all text empty reset
    private fun allTextEmptyListener() {
        binding.apply {

            email.addTextChangedListener {
                if (it.isNullOrEmpty()) {
                    emailLayout.error = "Email can't be empty!"
                } else {
                    emailLayout.error = null
                }
            }

            password.addTextChangedListener {
                if (it.isNullOrEmpty()) {
                    passwordLayout.error = "Password can't be empty!"
                } else {
                    passwordLayout.error = null
                }

                if (it.toString().length < 6) {
                    passwordLayout.error = "Password length can't be smaller than 6!"
                } else {
                    passwordLayout.error = null
                }

                if (!reTypePassword.text.isNullOrEmpty()) {
                    if (it.toString() != reTypePassword.text.toString()) {
                        reTypePasswordLayout.error = "Password doesn't match!"
                    } else {
                        reTypePasswordLayout.error = null
                    }
                }
            }

            reTypePassword.addTextChangedListener {
                when {
                    it.isNullOrEmpty() -> {
                        reTypePasswordLayout.error = "Re-Type Password can't be empty!"
                    }
                    it.toString() != password.text.toString() -> {
                        reTypePasswordLayout.error = "Password doesn't match!"
                    }
                    else -> {
                        reTypePasswordLayout.error = null
                    }
                }
            }
        }
    }

    // checks all null
    private fun isALLayoutNull(): Boolean {
        binding.apply {
            return when {
                emailLayout.error == null -> {
                    true
                }
                passwordLayout.error == null -> {
                    true
                }
                reTypePasswordLayout.error == null -> {
                    true
                }
                else -> false
            }
        }
    }

}
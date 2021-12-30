package com.vdcodeassociate.businesslocateassigmentproject.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.vdcodeassociate.businesslocateassigmentproject.R
import com.vdcodeassociate.businesslocateassigmentproject.databinding.FragmentBusinessLoginBinding

class BusinessLoginFragment : Fragment(R.layout.fragment_business_login) {

    // viewBinding
    private lateinit var binding: FragmentBusinessLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBusinessLoginBinding.bind(view)

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
    private fun loginViaFirebase(){

    }

    // proceed login
    private fun login(){
        if(!isTextEmpty()){
            Toast.makeText(requireContext(),"Logined",Toast.LENGTH_SHORT).show()
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
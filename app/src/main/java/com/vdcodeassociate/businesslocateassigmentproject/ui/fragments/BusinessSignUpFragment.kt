package com.vdcodeassociate.businesslocateassigmentproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import com.vdcodeassociate.businesslocateassigmentproject.R
import com.vdcodeassociate.businesslocateassigmentproject.databinding.FragmentBusinessSignUpBinding
import kotlin.math.log

class BusinessSignUpFragment : Fragment(R.layout.fragment_business_sign_up) {

    // viewBinding
    private lateinit var binding: FragmentBusinessSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBusinessSignUpBinding.bind(view)

        binding.apply {

            login.setOnClickListener{
                findNavController().navigate(R.id.action_businessSignUpFragment_to_businessLoginFragment)
            }

        }

    }

}
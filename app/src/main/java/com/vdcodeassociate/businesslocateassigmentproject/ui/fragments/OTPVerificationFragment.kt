package com.vdcodeassociate.businesslocateassigmentproject.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.vdcodeassociate.businesslocateassigmentproject.R
import com.vdcodeassociate.businesslocateassigmentproject.databinding.FragmentOTPVerificationBinding

class OTPVerificationFragment : Fragment(R.layout.fragment_o_t_p_verification) {

    // viewBinding
    private lateinit var binding: FragmentOTPVerificationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOTPVerificationBinding.bind(view)

        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                    Log.d("TAGU","Check Hua! $millisUntilFinished")
                if(millisUntilFinished < 5000) {
//                    timer.cancel()
                }
            }

            override fun onFinish() {
                Log.d("TAGU","Khatam tata bye bye!")
            }
        }
        timer.start()


    }

}
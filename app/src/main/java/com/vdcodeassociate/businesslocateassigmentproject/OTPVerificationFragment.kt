package com.vdcodeassociate.businesslocateassigmentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OTPVerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OTPVerificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_o_t_p_verification, container, false)

        view?.findViewById<MaterialButton>(R.id.otp_verify_button)
            ?.setOnClickListener {
                findNavController().navigate(R.id.action_OTPVerificationFragment_to_welcomeScreenFragment)
            }

        view?.findViewById<TextView>(R.id.otp_resend_code)
            ?.setOnClickListener {
                Toast.makeText(activity,"CODE SEND AGAIN!",Toast.LENGTH_SHORT).show()
            }

        return view
    }

}
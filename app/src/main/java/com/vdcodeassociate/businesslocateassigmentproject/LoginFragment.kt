package com.vdcodeassociate.businesslocateassigmentproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var phoneNumber: String = ""
    lateinit var auth : FirebaseAuth
    lateinit var storedVerificationId :String
    lateinit var resendToken : PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view?.findViewById<MaterialButton>(R.id.login_sendotp_button)
            ?.setOnClickListener() {

//                findNavController().navigate(R.id.action_loginFragment_to_OTPVerificationFragment3)

//                sendOTP(view)


            }

        view?.findViewById<TextView>(R.id.login_business)
            ?.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_businessLoginFragment)
            }

//        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                Toast.makeText(activity, "onVerificationCompleted:$credential",Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                // This callback is invoked in an invalid request for verification is made,
//                // for instance if the the phone number format is not valid.
////                Log.w(TAG, "onVerificationFailed", e)
//
//                if (e is FirebaseAuthInvalidCredentialsException) {
//                    // Invalid request
//                } else if (e is FirebaseTooManyRequestsException) {
//                    // The SMS quota for the project has been exceeded
//                }
//
//                // Show a message and update the UI
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//                // The SMS verification code has been sent to the provided phone number, we
//                // now need to ask the user to enter the code and then construct a credential
//                // by combining the code with a verification ID.
////                Log.d(TAG, "onCodeSent:$verificationId")
//
//                // Save verification ID and resending token so we can use them later
//                storedVerificationId = verificationId
//                resendToken = token
//            }
//        }

        return view

    }

    private fun sendOTP(view : View){
        phoneNumber = view.findViewById<TextInputEditText>(R.id.login_phone).toString()

        if(phoneNumber.isNotEmpty()){
            phoneNumber = "+91$phoneNumber"
            sendOTPCode(phoneNumber)
        }else{
            view.findViewById<TextInputLayout>(R.id.login_phone_layout)
                .error = "Phone number can't be empty!"
        }
    }

    private fun sendOTPCode(phoneNumber : String){
        val options = PhoneAuthOptions.newBuilder().setPhoneNumber(phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(requireActivity())
//            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

}
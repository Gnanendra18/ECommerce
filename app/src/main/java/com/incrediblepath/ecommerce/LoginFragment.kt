package com.incrediblepath.ecommerce

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.incrediblepath.ecommerce.databinding.FragmentLoginBinding
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {

    lateinit var auth:FirebaseAuth
    lateinit var storedVerificationId:String
    lateinit var resendToken:PhoneAuthProvider.ForceResendingToken
    private  val TAG = "LoginFragment"

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.

            Log.e(TAG, "onVerificationCompleted:$credential")
            credential.smsCode
            //signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)
            Toast.makeText(activity,"VerificationFailed",Toast.LENGTH_LONG).show()
            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, token)
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()
        binding.sendOtp.setOnClickListener{
            val phone = binding.phone.text.toString()
            if(!TextUtils.isEmpty(phone) && phone.length == 10) {
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber("+91" + phone)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(requireActivity())                 // Activity (for callback binding)
                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
            }
        }

        binding.verify.setOnClickListener{
            val otp = binding.otp.text.toString()
            if(!TextUtils.isEmpty(otp)){
                val credential = PhoneAuthProvider.getCredential(storedVerificationId, otp)
                signInWithFirebase(credential)
            }
        }

        return binding.root
    }

    private fun signInWithFirebase(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(
            OnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(requireContext(),"Login Successful",Toast.LENGTH_LONG).show()
                }
            })
    }
}
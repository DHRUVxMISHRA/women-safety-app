package com.example.womensafetyapp.ui.auth

import android.app.Activity
import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState


    private val _passwordStrength = MutableLiveData<PasswordStrength>()
    val passwordStrength: LiveData<PasswordStrength> = _passwordStrength

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private var verificationId: String? = null

    init {

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: com.google.firebase.auth.PhoneAuthCredential) {
                auth.currentUser?.linkWithCredential(credential)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authState.value = AuthState.Authenticated
                        }
                    }
            }

            override fun onVerificationFailed(e: com.google.firebase.FirebaseException) {
                _authState.value = AuthState.Error(e.message ?: "OTP verification failed")
            }

            override fun onCodeSent(
                id: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                this@AuthViewModel.verificationId = id
                _authState.value = AuthState.OtpSent

            }
        }
    }



    fun login(
        email: String,
        password: String
    ) {

        if (email.isBlank()) {
            _authState.value = AuthState.Error("Email cannot be empty")
            return
        }


        if (password.isBlank()) {
            _authState.value = AuthState.Error("Password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Login Failed")
                }
            }
    }

    fun signup(email: String, password: String) {

        // 1️⃣ Empty check
        if (email.isBlank()) {
            _authState.value = AuthState.Error("Email cannot be empty")
            return
        }

        // 2️⃣ Format check
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            _authState.value = AuthState.Error("Enter a valid email address")
            return
        }


        // 3️⃣ Password length check
        if (password.length < 6) {
            _authState.value = AuthState.Error("Password must be 6+ characters")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.PhoneVerificationRequired
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Signup failed")
                }
            }
    }


    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun onPasswordChanged(password: String) {
        _passwordStrength.value = calculatePasswordStrength(password)
    }

    private fun calculatePasswordStrength(password: String): PasswordStrength {

        if (password.length < 8) return PasswordStrength.WEAK

        var score = 0

        if (password.any { it.isLowerCase() }) score++
        if (password.any { it.isUpperCase() }) score++
        if (password.any { it.isDigit() }) score++
        if (password.any { !it.isLetterOrDigit() }) score++

        return when {
            score <= 1 -> PasswordStrength.WEAK
            score == 2 || score == 3 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.STRONG
        }
    }


    fun googleLogin(email: String, idToken: String) {

        _authState.value = AuthState.Loading


        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { fetchTask ->

                if (fetchTask.isSuccessful) {
                    val method = fetchTask.result?.signInMethods


                    if (method.isNullOrEmpty()) {
                        // ❌ Email not registered
                        auth.signOut()
                        _authState.value = AuthState.Error(
                            "Email not registered. Please sign up first."
                        )
                    } else {
                        // ✅ User exists → now sign in

                        val credential = GoogleAuthProvider.getCredential(idToken, null)

                        auth.signInWithCredential(credential)
                            .addOnCompleteListener { loginTask ->

                                if (loginTask.isSuccessful) {

                                    _authState.value = AuthState.PhoneVerificationRequired

                                } else {
                                    _authState.value = AuthState.Error("Google login failed")
                                }
                            }
                    }
                } else {
                    _authState.value = AuthState.Error("Failed to verify email")
                }
            }
    }

    fun googleSignup(email: String, idToken: String) {

        _authState.value = AuthState.Loading

        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { fetchTask ->

                if (fetchTask.isSuccessful) {
                    val method = fetchTask.result?.signInMethods

                    if (!method.isNullOrEmpty()) {

                        // ❌ Email already registered
                        _authState.value =
                            AuthState.Error("Email already registered. Please login.")

                    } else {

                        // ✅ Email not registered → create account
                        val credential = GoogleAuthProvider.getCredential(idToken, null)

                        auth.signInWithCredential(credential)
                            .addOnCompleteListener { signupTask ->

                                if (signupTask.isSuccessful) {

                                    // After Google account created,
                                    // require phone verification
                                    _authState.value = AuthState.PhoneVerificationRequired

                                } else {

                                    _authState.value = AuthState.Error(
                                        signupTask.exception?.message?:
                                        "Google signup failed"
                                    )
                                }
                            }

                    }
                } else{

                    _authState.value = AuthState.Error(
                        "Failed to verify Email"
                    )
                }
            }
    }

    fun sendOtp(phone: String, activity: Activity) {
        val option = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(option)
    }

    fun verifyOtp(code: String) {

        val id = verificationId ?: run {
            _authState.value = AuthState.Error("Verification ID missing")
            return
        }
        val credential = PhoneAuthProvider.getCredential(id, code)

        auth.currentUser?.linkWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error("Invalid OTP")
                }
            }
    }


}


sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    object PhoneVerificationRequired : AuthState()
    object OtpSent : AuthState()
    data class Error(val message: String) : AuthState()

}


enum class PasswordStrength {
    WEAK,
    MEDIUM,
    STRONG
}
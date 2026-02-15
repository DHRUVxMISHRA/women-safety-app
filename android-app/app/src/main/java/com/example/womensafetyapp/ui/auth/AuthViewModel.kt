package com.example.womensafetyapp.ui.auth

import android.os.Message
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.womensafetyapp.ui.auth.AuthState
import android.util.Patterns.EMAIL_ADDRESS


class AuthViewModel : ViewModel(){

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState


    private val _passwordStrength = MutableLiveData<PasswordStrength>()
    val passwordStrength : LiveData<PasswordStrength> = _passwordStrength

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        } else
        {
            _authState.value = AuthState.Authenticated
        }
    }


    fun login(
        email : String,
        password : String
    ){

        if(email.isBlank()){
            _authState.value = AuthState.Error("Email cannot be empty")
            return
        }


        if (password.isBlank()){
            _authState.value = AuthState.Error("Password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else
                {
                    _authState.value = AuthState.Error(task.exception?.message?: "Login Failed")
                }
            }
    }

    fun signup(email: String, password: String){

        // 1️⃣ Empty check
        if (email.isBlank()){
            _authState.value = AuthState.Error("Email cannot be empty")
            return
        }

        // 2️⃣ Format check
        if (!EMAIL_ADDRESS.matcher(email).matches()){
            _authState.value = AuthState.Error("Enter a valid email address")
            return
        }


        // 3️⃣ Password length check
        if (password.length < 6){
            _authState.value = AuthState.Error("Password must be 6+ characters")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword( email, password)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "Signup failed")
                }
            }
    }


    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun onPasswordChanged(password : String){
        _passwordStrength.value = calculatePasswordStrength(password)
    }

    private fun calculatePasswordStrength(password : String) : PasswordStrength{

        if(password.length < 8) return PasswordStrength.WEAK

        var score = 0

        if(password.any { it.isLowerCase()}) score++
        if(password.any { it.isUpperCase()}) score++
        if(password.any { it.isDigit()}) score++
        if(password.any { !it.isLetterOrDigit()}) score++

        return when{
            score<=1 -> PasswordStrength.WEAK
            score == 2 || score == 3 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.STRONG
        }
    }
}



sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading: AuthState()
    data class Error(val message: String) :  AuthState()
}


enum class PasswordStrength{
    WEAK,
    MEDIUM,
    STRONG
}
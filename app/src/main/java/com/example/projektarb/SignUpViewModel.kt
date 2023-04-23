package com.example.projektarb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun signUp() {
        val nameValue = name.value
        val emailValue = email.value
        val passwordValue = password.value

        // TODO: Add code to submit the form
    }

}
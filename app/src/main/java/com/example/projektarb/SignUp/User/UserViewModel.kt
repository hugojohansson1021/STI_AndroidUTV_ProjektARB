package com.example.projektarb.SignUp.User

import androidx.lifecycle.ViewModel
import com.example.projektarb.SignUp.User.User

class UserViewModel : ViewModel() {
    val users = mutableListOf<User>()

    fun addUser(user: User) {
        users.add(user)
    }

    fun clearUsers() {
        users.clear()
    }
}

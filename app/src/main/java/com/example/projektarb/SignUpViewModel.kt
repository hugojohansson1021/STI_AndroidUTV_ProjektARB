package com.example.projektarb

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val users = mutableListOf<User>()

    fun addUser(user: User) {
        users.add(user)
    }

    fun clearUsers() {
        users.clear()
    }
}

package com.example.projektarb.SignUp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userInputDao: UserInputDao) {
    suspend fun insert(userInput: UserInput) {
        withContext(Dispatchers.IO) {
            userInputDao.insert(userInput)
        }
    }

    suspend fun delete(userInput: UserInput) {
        withContext(Dispatchers.IO) {
            userInputDao.delete(userInput)
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.IO) {
            userInputDao.clearAll()
        }
    }

    suspend fun getAll(): List<UserInput> {
        return withContext(Dispatchers.IO) {
            userInputDao.getAll()
        }
    }
}

package com.example.projektarb.SignUp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserInputDao {
    @Query("SELECT * FROM user_inputs")
    fun getAll(): List<UserInput>

    @Insert
    fun insert(userInput: UserInput)

    @Delete
    fun delete(userInput: UserInput)

    @Query("DELETE FROM user_inputs")
    fun clearAll()
}


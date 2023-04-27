package com.example.projektarb.SignUp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInput::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInputDao(): UserInputDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user-inputs"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

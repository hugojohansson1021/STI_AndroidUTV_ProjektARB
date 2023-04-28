package com.example.projektarb.SignUp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.projektarb.R

class SignUpFragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var userInputDao: UserInputDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "user-inputs"
        ).build()

        userInputDao = db.userInputDao()

        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val submitButton = view.findViewById<Button>(R.id.submitButton)
        val clearButton = view.findViewById<Button>(R.id.clearButton)
        val outputTextView = view.findViewById<TextView>(R.id.outputTextView)
        val backButton = view.findViewById<ImageView>(R.id.back_press112)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val userInput = UserInput(name, email, password)

            Thread {
                userInputDao.insert(userInput)

                val userInputList = userInputDao.getAll()
                val userInputString = userInputList.joinToString("\n") { it.toString() }

                activity?.runOnUiThread {
                    outputTextView.text = userInputString
                }
            }.start()
        }

        clearButton.setOnClickListener {
            Thread {
                userInputDao.clearAll()

                activity?.runOnUiThread {
                    outputTextView.text = ""
                }
            }.start()
        }






        backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        return view
    }
}

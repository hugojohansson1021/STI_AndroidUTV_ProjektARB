package com.example.projektarb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projektarb.databinding.FragmentMovieBinding

import com.example.projektarb.databinding.FragmentSignUpBinding





class SignUpFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //val backButtonSignUpFragment = binding.backPress1
        binding.backPress1.setOnClickListener {
            findNavController().popBackStack()
        }

        val nameEditText = binding.nameEditText
        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val submitButton = binding.submitButton
        val clearButton = binding.clearButton
        val usersTextView = binding.usersTextView

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = User(name, email, password)
            userViewModel.addUser(user)

            val users = userViewModel.users.map { "${it.name}, ${it.email}, ${it.password}" }
            usersTextView.text = users.joinToString("\n")
        }

        clearButton.setOnClickListener {
            userViewModel.clearUsers()
            usersTextView.text = ""
        }
    }
}



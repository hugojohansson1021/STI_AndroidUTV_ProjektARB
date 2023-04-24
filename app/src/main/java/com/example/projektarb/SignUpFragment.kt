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
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        // Restore user input from ViewModel (if it exists)
        signUpViewModel.name = savedInstanceState?.getString("name") ?: ""
        signUpViewModel.email = savedInstanceState?.getString("email") ?: ""
        signUpViewModel.password = savedInstanceState?.getString("password") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameEditText.setText(signUpViewModel.name)
        binding.emailEditText.setText(signUpViewModel.email)
        binding.passwordEditText.setText(signUpViewModel.password)

        binding.submitButton.setOnClickListener {
            signUpViewModel.name = binding.nameEditText.text.toString()
            signUpViewModel.email = binding.emailEditText.text.toString()
            signUpViewModel.password = binding.passwordEditText.text.toString()

            val user = User(signUpViewModel.name, signUpViewModel.email, signUpViewModel.password)
            userViewModel.addUser(user)

            val users = userViewModel.users.map { "${it.name}, ${it.email}, ${it.password}" }
            binding.usersTextView.text = users.joinToString("\n")
        }

        binding.clearButton.setOnClickListener {
            userViewModel.clearUsers()
            binding.usersTextView.text = ""
        }

        binding.backPress1.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save user input to ViewModel
        signUpViewModel.name = binding.nameEditText.text.toString()
        signUpViewModel.email = binding.emailEditText.text.toString()
        signUpViewModel.password = binding.passwordEditText.text.toString()

        // Save user input to Bundle
        outState.putString("name", signUpViewModel.name)
        outState.putString("email", signUpViewModel.email)
        outState.putString("password", signUpViewModel.password)
    }
}




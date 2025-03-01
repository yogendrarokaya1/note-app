package com.example.noteandroidapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteandroidapp.R
import com.example.noteandroidapp.databinding.ActivityLoginBinding
import com.example.noteandroidapp.repository.UserRepositoryImpl
import com.example.noteandroidapp.utils.LoadingUtils
import com.example.noteandroidapp.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
        lateinit var userViewModel: UserViewModel
        lateinit var loadingUtils: LoadingUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        //initializing loading
        loadingUtils = LoadingUtils(this)

        binding.btnLogin.setOnClickListener {
            loadingUtils.show()
            var email :String = binding.editEmail.text.toString()
            var password :String = binding.editPassword.text.toString()

            userViewModel.login(email,password){
                    success,message->
                if(success){
                    Toast.makeText(this@LoginActivity,message, Toast.LENGTH_LONG).show()
                    loadingUtils.dismiss()
                    var intent = Intent(this@LoginActivity,NavigationActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext,message, Toast.LENGTH_LONG).show()
                    loadingUtils.dismiss()

                }
            }
        }

        binding.btnSignupnavigate.setOnClickListener {
            val intent = Intent(this@LoginActivity,
                RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnForget.setOnClickListener {
            val intent = Intent(this@LoginActivity,
                ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
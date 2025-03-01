package com.example.noteandroidapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteandroidapp.R
import com.example.noteandroidapp.databinding.ActivityForgetPasswordBinding
import com.example.noteandroidapp.repository.UserRepositoryImpl
import com.example.noteandroidapp.utils.LoadingUtils
import com.example.noteandroidapp.viewmodel.UserViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)

        //initializing auth viewmodel
        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        //initializing loading
        loadingUtils = LoadingUtils(this)
        forgetPasswordBinding.btnForget.setOnClickListener {
            loadingUtils.show()
            var email: String = forgetPasswordBinding.editEmailForget.text.toString()

            userViewModel.forgetPassword(email) { success, message ->
                if (success) {
                    loadingUtils.dismiss()
                    Toast.makeText(this@ForgetPasswordActivity, message, Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    loadingUtils.dismiss()
                    Toast.makeText(this@ForgetPasswordActivity, message, Toast.LENGTH_LONG).show()

                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
package com.example.noteandroidapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteandroidapp.R
import com.example.noteandroidapp.databinding.ActivityRegisterBinding
import com.example.noteandroidapp.model.UserModel
import com.example.noteandroidapp.repository.UserRepositoryImpl
import com.example.noteandroidapp.utils.LoadingUtils
import com.example.noteandroidapp.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    lateinit var userViewModel: UserViewModel

    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val userRepository = UserRepositoryImpl()

        userViewModel = UserViewModel(userRepository)

        loadingUtils = LoadingUtils(this)

        binding.signUp.setOnClickListener {
            loadingUtils.show()
            var email: String = binding.registerEmail.text.toString()
            var password: String = binding.registerPassword.text.toString()
            var fName: String = binding.registerFname.text.toString()
            var lName: String = binding.registerLName.text.toString()
            var address: String = binding.registerAddress.text.toString()
            var contact: String = binding.registerContact.text.toString()

            userViewModel.signup(email,password){
                success,message,userId ->
                if(success){
                    val userModel = UserModel(
                        userId,
                        email, fName, lName, address, contact
                    )
                    addUser(userModel)
                }else{
                    loadingUtils.dismiss()
                    Toast.makeText(this@RegisterActivity,
                        message,Toast.LENGTH_SHORT).show()
                }


            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun addUser(userModel: UserModel){
        userViewModel.addUserToDatabase(userModel.userId,userModel){
                success,message ->
            if(success){
                Toast.makeText(this@RegisterActivity
                    ,message,Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@RegisterActivity
                    ,message,Toast.LENGTH_SHORT).show()
            }
            loadingUtils.dismiss()
        }
    }
}
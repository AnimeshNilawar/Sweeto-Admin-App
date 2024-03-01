package com.example.adminsweeto

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.adminsweeto.databinding.ActivitySignUpBinding
import com.example.adminsweeto.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var UserName: String
    private lateinit var NameofResturant: String
    private lateinit var Email: String
    private lateinit var Password: String

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Intialization Firebase auth
        auth = Firebase.auth

        //Intialization Firebase Database
        database = Firebase.database.reference

        binding.signUpButton.setOnClickListener {

            //get text from EditText
            UserName = binding.userName.text.toString().trim()
            NameofResturant = binding.restaurantName.text.toString().trim()
            Email = binding.emailEditText.text.toString().trim()
            Password = binding.passwordEditText.text.toString().trim()

            if (UserName.isBlank() ||NameofResturant.isBlank() ||Email.isBlank() ||Password.isBlank() ){
                Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show()
            }else{
                createAccount(Email,Password)
            }

        }
        binding.alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val locationList = arrayOf("Jaipur", "odisha", "rajasthan", "sikkim")

        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, locationList)

        val autoCompleteTextView = binding.listoflocation
        autoCompleteTextView.setAdapter(adapter)

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure",task.exception)
            }
        }
    }

    //Save data into Database
    private fun saveUserData() {
        //get text from EditText
        UserName = binding.userName.text.toString().trim()
        NameofResturant = binding.restaurantName.text.toString().trim()
        Email = binding.emailEditText.text.toString().trim()
        Password = binding.passwordEditText.text.toString().trim()

        val user = UserModel(UserName,NameofResturant,Email,Password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        //save user data in firebase Database
        database.child("user").child(userId).setValue(user)
    }
}
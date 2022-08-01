package com.example.quecklynew

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quecklynew.Model.AuthModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var namaRegis: EditText
    private lateinit var emailRegis: EditText
    private lateinit var passwordRegis: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnSignInMove: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        namaRegis = findViewById(R.id.namaSignUp)
        emailRegis = findViewById(R.id.emailSignUp)
        passwordRegis = findViewById(R.id.passwordSIgnUp)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnSignInMove = findViewById(R.id.btnSignInMove)
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference

        btnSignUp.setOnClickListener {
            val name = namaRegis.text.toString()
            val email = emailRegis.text.toString()
            val password = passwordRegis.text.toString()

            if (name != "" && email != "" && password != "") {

                signUp(email, password)

            } else {
                Toast.makeText(this, "Masih ada yang kosong", Toast.LENGTH_SHORT).show()
            }

        }

        btnSignInMove.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun auth() {
        val name = namaRegis.text.toString()
        val email = emailRegis.text.toString()
        val uid = mAuth.currentUser?.uid
        Log.e("uid", "auth: $uid", )
        mDbRef.child("data").child("user").child("$uid").setValue(AuthModel(name, email, uid))
//        sharedPreferences = getSharedPreferences("uidRegis", Context.MODE_PRIVATE)
//        val getRegis = uid
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        editor.putString("uidSend", getRegis)
//        editor.apply()

    }

    private fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    auth()
                    Toast.makeText(this, "SignUp Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "SignUp Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun clearData() {
        namaRegis.setText("")
        emailRegis.setText("")
        passwordRegis.setText("")
    }
}
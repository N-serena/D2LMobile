package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R
import com.example.d2lmobile.models.User
import com.example.d2lmobile.utils.AuthManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val dbRef = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val firstName = findViewById<EditText>(R.id.first_name)
        val lastName = findViewById<EditText>(R.id.last_name)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password)
        val registerBtn = findViewById<Button>(R.id.btn_register)
        val loginLink = findViewById<TextView>(R.id.btn_back_to_login)

        registerBtn.setOnClickListener {
            val fname = firstName.text.toString().trim()
            val lname = lastName.text.toString().trim()
            val emailInput = email.text.toString().trim()
            val pass = password.text.toString().trim()
            val confirmPass = confirmPassword.text.toString().trim()

            if (fname.isEmpty() || lname.isEmpty() || emailInput.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != confirmPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Register the user with Firebase Authentication
            auth.createUserWithEmailAndPassword(emailInput, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                        val user = User(fname, lname, emailInput, "")

                        // ✅ Save name to Firebase Realtime Database
                        dbRef.child(userId).setValue(user)
                            .addOnSuccessListener {
                                AuthManager.login(this, emailInput)  // Optional local session
                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                                // ✅ Go directly to Dashboard
                                startActivity(Intent(this, DashboardActivity::class.java))
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Error saving user data", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        loginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

package de.kaaaxcreators.kcui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = R.string.error.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        button.setOnClickListener{ signup() }
        signin_btn.setOnClickListener { signin() }

    }
    public override fun onStart() {
        super.onStart()
        /*// Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        Toast.makeText(this, "Please dont make another account", Toast.LENGTH_LONG).show()*/
    }
    fun signup(){
        val email1 = editTextTextEmailAddress.text
        val email = email1.toString()
        val password1 = editTextTextPassword.text
        val password = password1.toString()
        if (email1.length > 3){
            if (password1.length > 5){
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            val intent = Intent(this, MainActivity::class.java).apply {
                            }
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, (R.string.access_denied), Toast.LENGTH_LONG).show()

                        }

                        // ...
                    }
            }
            else {
                editTextTextPassword.error = R.string.password_short.toString()
            }
        }
        else {
            editTextTextEmailAddress.error = R.string.email_short.toString()
        }
    }
    private fun signin(){
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
}


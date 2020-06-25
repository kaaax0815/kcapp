package de.kaaaxcreators.kcui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    private val TAG = R.string.error.toString()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            auth = Firebase.auth
            buttonlogin.setOnClickListener { btnsignin() }
            textView2.setOnClickListener { register() }

    }

    private fun btnsignin(){
        val email1 = email_txt.text
        val password1 = password_txt.text
        val email = email1.toString()
        val password = password1.toString()
        if (email1.length > 3){
            if (password1.length > 5){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            // unused val user = auth.currentUser
                            val intent = Intent(this, Drawer::class.java).apply {
                            }
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(this, (R.string.access_denied), Toast.LENGTH_SHORT).show()
                            // ...
                        }

                        // ...
                    }
            }
            else {
                password_txt.error = resources.getString(R.string.password_short)
            }
        }
        else {
            email_txt.error = resources.getString(R.string.email_short)
        }

    }
    private fun register(){
        val intent = Intent(this, Register::class.java).apply {
        }
        startActivity(intent)
    }




}
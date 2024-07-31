package com.example.myappdecomida

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myappdecomida.actividades.ActivityRecover
import com.example.myappdecomida.actividades.ActivitySignUp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variables del login
        val loginButton: Button = findViewById(R.id.login_button)
        val emailTextView: TextView = findViewById(R.id.login_username)
        val passwordEditText: EditText = findViewById(R.id.login_password_edit_text)

        // Inicializar variable
        firebaseAuth = Firebase.auth

        // Función para ingresar al menú
        loginButton.setOnClickListener {
            signIn(emailTextView.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        // Comprobar si las credenciales coinciden con las determinadas
        if (email == "chovico@gmail.com" && password == "123chovi") {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, NavBar::class.java))
        } else {
            // Intentar autenticación con Firebase
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = firebaseAuth.currentUser
                        Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, NavBar::class.java))
                    } else {
                        Toast.makeText(baseContext, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    fun onRegisterLinkClicked(view: View) {
        // Código que se ejecutará cuando se haga clic en el TextView para registrarse
        val intent = Intent(this, ActivitySignUp::class.java)
        startActivity(intent)
    }

    fun onRecoverLinkClicked(view: View) {
        // Código que se ejecutará cuando se haga clic en el TextView para recuperar cuenta
        val intent = Intent(this, ActivityRecover::class.java)
        startActivity(intent)
    }
}

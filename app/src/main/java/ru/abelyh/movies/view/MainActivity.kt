package ru.abelyh.movies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.abelyh.movies.R
import ru.abelyh.movies.data.User
import ru.abelyh.movies.view_model.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel : MainActivityViewModel = MainActivityViewModel()

    // создали объект авторизации
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { resultCallback ->
        this.onSignInResult(resultCallback) // запуск самого экрана
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
    }

    /**
     * We make a call to firebase auth to show dialog for registration
     */
    private fun openRegistrationScreen() {
        val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)

        // список регистраций, который мы используем
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build() // создали интент для экрана firebase auth
        signInLauncher.launch(signInIntent) // запустили экран firebase auth
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {

        when (result.resultCode) {
            RESULT_OK -> {
                // создаем объект текущего пользователя Firebase auth
                val authUser = FirebaseAuth.getInstance().currentUser

                // если он существует сохраняем его в базу данных
                authUser?.let {
                    val email = it.email.toString()
                    val uid = it.uid
                    val firebaseUser = User(email, uid)

                    mMainActivityViewModel.updateUserData(firebaseUser, uid)

                    val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
                    startActivity(intentToAnotherScreen)
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(
                    this@MainActivity,
                    "Something wrong with registration",
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                // do not anything
            }
        }
    }
}
package com.example.tanuki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tanuki.databinding.ActivityLoginBinding
import com.example.tanuki.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase


class Login : AppCompatActivity() {
    private val RC_SIGN_IN: Int = 1
    private lateinit var auth: FirebaseAuth
    private lateinit var loginElements: ActivityLoginBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions

    companion object {
        private val TAG = "ClassName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginElements = DataBindingUtil.setContentView(this,R.layout.activity_login)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        gso = GoogleSignInOptions.Builder()
            .requestEmail()
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        onStart()
        loginElements.login.setOnClickListener {
            signIn()
    }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    //checking to see if the user is currently signed in
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null)
        {
            updateUI(currentUser)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
//                    updateUI(null)
                    Toast.makeText(this,task.exception.toString(), Toast.LENGTH_LONG).show()
                }

                // ...
            }
    }

    private fun updateUI(currentUser: FirebaseUser?){
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val user = Users(currentUser?.displayName.toString(), currentUser?.getIdToken(true).hashCode(), 0.toFloat(), 0.toFloat())
        ref.push().setValue(user)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}

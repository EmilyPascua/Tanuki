package com.example.tanuki

<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tanuki.databinding.ActivityLoginBinding
import com.example.tanuki.model.User
=======
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tanuki.databinding.ActivityLoginBinding
import com.example.tanuki.databinding.ActivityMainBinding
>>>>>>> firebase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
=======
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserInfo
>>>>>>> firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
<<<<<<< HEAD
=======
import java.util.ArrayList
>>>>>>> firebase


class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
<<<<<<< HEAD
    private lateinit var database: FirebaseDatabase
=======
>>>>>>> firebase
    private lateinit var loginElements: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions

<<<<<<< HEAD
=======

>>>>>>> firebase
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        loginElements = DataBindingUtil.setContentView(this,R.layout.activity_login)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

=======
        loginElements = DataBindingUtil.setContentView(this, R.layout.activity_login)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
>>>>>>> firebase
        //instantiating GoogleSignInOptions object -> Specifies your sign in scope and to crate a google api
        // Configure Google Sign In
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
<<<<<<< HEAD
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        loginElements.login.setOnClickListener{
            signIn()
        }

=======
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        loginElements.login.setOnClickListener {
            signIn()
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
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
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
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
                    val currentUsr = auth.currentUser
                    val personName = acct.displayName
                    val idToken = acct.idToken
                        saveUser(currentUsr, personName!!, idToken!!)
                        updateUI(currentUsr)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
//                    updateUI(null)
                }
                // ...
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveUser(currentUser: FirebaseUser?, name: String, idToken: String) {
            Log.d("saveUser method","checking if this method runs")
            val usersRef = FirebaseDatabase.getInstance().getReference("Users")
            val query = usersRef.orderByKey()

            val uid = currentUser!!.uid

            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val list: ArrayList<String> = ArrayList()

                    // Get Post object and use the values to update the UI
                    dataSnapshot.children.forEach{
                        Log.d("saveUser method","query loop")
                        list.add(it.key!!)
                    }

                    if (!list.contains(uid)) {
                        val userObject = Users(name, uid, idToken, 0.toFloat(), 0.toFloat())
                        usersRef.child(uid).setValue(userObject)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                    // ...
                }
            }
            query.addListenerForSingleValueEvent(listener)
>>>>>>> firebase
    }

    private fun signIn() {
        Log.d("signIn", "Sign in client started")
        val signInIntent = googleSignInClient.signInIntent
        Log.d("signIn", "start Activity Result")
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d("onActivityResult","passed request code check")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
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
                    val user = auth.currentUser!!
                    val name = acct.displayName!!
                    val email = acct.email!!

                    saveUser(user, name, email)

                    updateUI()
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
//                    updateUI(null)
                }
                // ...
            }
    }

    private fun updateUI() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun saveUser(user: FirebaseUser, name: String, email: String) {
        Log.d("saveUser method","checking if this method runs")
        val usersRef = database.getReference("Users")
        val query = usersRef.orderByKey()

        val uid = user.uid


        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: ArrayList<String> = ArrayList()

                // Get Post object and use the values to update the UI
                dataSnapshot.children.forEach{
                    Log.d("saveUser method","query loop")
                    list.add(it.key!!)
                }

                if (!list.contains(uid)) {
                    val userObject = User(
                        uid,
                        name,
                        "m",
                        email,
                        ArrayList(),
                        0.0,
                        0.0
                    )
                    usersRef.child(uid).setValue(userObject)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        query.addListenerForSingleValueEvent(listener)
    }
//
//    private fun getUsers(): Boolean {
//        Log.d("getUsers","should start")
//        val usersRef = database.getReference("Users")
//        val query = usersRef.orderByKey()
//
//        val listener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                dataSnapshot.children.forEach{
//                    Log.d("getUsers","get users")
//                    val list: ArrayList<User> = ArrayList()
//
//                    list.add(it.getValue(User::class.java)!!)
//                }
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        }
//        query.addListenerForSingleValueEvent(listener)
//
//        return true
//    }
}


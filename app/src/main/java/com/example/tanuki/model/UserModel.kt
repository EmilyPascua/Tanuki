//package com.example.tanuki
//
//import android.util.Log
//import com.google.firebase.database.*
//
//import java.util.*
//
//object UserModel : Observable() {
//    private var mValueDataListener: ValueEventListener? = null
//    private var userList: ArrayList<User>? = ArrayList()
//
//    private fun getDatabaseRef(): DatabaseReference? {
//    return FirebaseDatabase.getInstance().reference.child("User")
//}
//
//    init {
//        if (mValueDataListener != null) {
//            getDatabaseRef()?.removeEventListener(mValueDataListener!!)
//        }
//        mValueDataListener = null
//        Log.i("User Model", "data init line 26")
//        mValueDataListener = object: ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                if (p0 != null) {
//                    Log.i("User Model", "Line 51 Data update Canceled")
//                }
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                try {
//                    Log.i("User Model", "Data updated line 29")
//                    val data: ArrayList<User> = ArrayList()
//                    //if p0 != null then that means that there is data
//                    if (p0 != null) {
//                        for (p0: DataSnapshot in p0.children) {
//                            try {
//                                data?.add(User(p0))
//                            }catch(e: Exception) {
//                                e.printStackTrace()
//                            }
//                            userList = data
//                            Log.i("User Model", "Data Updated" + userList!!.size+ " in the cache")
//                            setChanged()
//                            notifyObservers()
//                        }
//                    }
//
//                }catch(e: Exception) {
//                    e.printStackTrace()
//                }
//                getDatabaseRef()?.addValueEventListener(mValueDataListener!!)
//            }
//        }
//    }
//
//    fun getData(): ArrayList<User>? {
//        return userList
//    }
//}

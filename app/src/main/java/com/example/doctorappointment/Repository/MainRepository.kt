package com.example.doctorappointment.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doctorappointment.Model.DoctorsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainRepository {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun load():LiveData<MutableList<DoctorsModel>>{
        val listData=MutableLiveData<MutableList<DoctorsModel>>()
        val ref =firebaseDatabase.getReference("Doctors")

        ref.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<DoctorsModel>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(DoctorsModel::class.java)
                    item?.let { lists.add(it) }
                    Log.d("FirebaseData", "Doctors found: ${lists.size}")
                listData.postValue(lists)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Database error: ${error.message}")
            }
    }
        )
        return listData
    }
}
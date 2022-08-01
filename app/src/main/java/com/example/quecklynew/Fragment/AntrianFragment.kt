package com.example.quecklynew.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quecklynew.Adapter.AdapterAntrian
import com.example.quecklynew.Adapter.AdapterEvent
import com.example.quecklynew.Model.EventModel
import com.example.quecklynew.Model.EventViewModel
import com.example.quecklynew.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.util.*
import kotlin.collections.ArrayList

class AntrianFragment : Fragment() {

    private lateinit var rvAntrian: RecyclerView
    private lateinit var dataAntrianView: ArrayList<EventModel>
    private lateinit var mDbRef: DatabaseReference
    lateinit var preferences: SharedPreferences
    private lateinit var mAuth: FirebaseAuth


    companion object {
        const val EXTRA_UID = "extra_uid"
        const val EXTRA_EVENT = "extra_event"
        const val EXTRA_JMLANTRIAN = "extra_jmlantrian"
        const val EXTRA_TANGGAL = "extra_tanggal"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antrian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()


        dataAntrianView = arrayListOf()
        rvAntrian = view.findViewById(R.id.rvAntrian)
        rvAntrian.layoutManager = LinearLayoutManager(this.context)
        rvAntrian.setHasFixedSize(true)

        rvAntrian.adapter = AdapterAntrian(dataAntrianView)
        val data = activity?.intent?.getStringExtra(EXTRA_UID)

        mDbRef = FirebaseDatabase.getInstance().reference





        getAntrian()
//        getData()

    }

    private fun saveData(namaEV: String, jmlAntrian: String, tanggal: String, uid: String) {
        val uu = UUID.randomUUID().toString()
        val unice = mAuth.currentUser?.uid
        val data = activity?.intent?.getStringExtra(EXTRA_UID)
        mDbRef.child("$data").child("$unice")
            .setValue(EventModel(namaEV, jmlAntrian, tanggal, uid))
    }

    private fun getAntrian() {
        val uid = mAuth.currentUser?.uid
        val data = activity?.intent?.getStringExtra(EXTRA_UID)
        preferences = requireActivity().getSharedPreferences("SHARED", Context.MODE_PRIVATE)
        val uidGet = preferences.getString("uid", "")
        Toast.makeText(requireActivity(), "$data", Toast.LENGTH_SHORT).show()
        mDbRef =
            FirebaseDatabase.getInstance().getReference("data").child("antrian").child("$data")
        mDbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(EventModel::class.java)
                        dataAntrianView.clear()
                        dataAntrianView.add(user!!)
                        val nameEvent = user!!.namaEventView.toString()
                        val jumlahAntrian = user!!.nomorAntrianView.toString()
                        val tanggal = user!!.tanggalView.toString()
                        val uid = user!!.uidEvent.toString()
                        val data = jumlahAntrian.toInt()
                        val ini = data - 1
                        saveData(nameEvent, ini.toString() , tanggal, uid)
                    }
                    rvAntrian.adapter = AdapterAntrian(dataAntrianView)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireActivity(), "Error Get Data", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun getData() {
//        val data = activity?.intent?.getStringExtra(EXTRA_UID)
//        mDbRef = FirebaseDatabase.getInstance().getReference("data").child("antrian").child("$data")
//        mDbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists())
//                    for (userSnapshot in snapshot.children) {
//                        val user = userSnapshot.getValue(EventModel::class.java)
//                        dataAntrianView.clear()
//                        val jumlahAntrian = user!!.nomorAntrianView.toString()
//                        val data = jumlahAntrian.toInt()
//                        val ini = data - 1
//                    }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }


}


//.addOnSuccessListener {
//                mDbRef.child("data").child("antrian").child(uid).child(uidRandom)
//                    .setValue(EventModel(namaEV, jmlAntrian, tanggal, uidRandom)).addOnSuccessListener {
//                    }
//            }
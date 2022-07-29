package com.example.quecklynew.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quecklynew.Adapter.AdapterAntrian
import com.example.quecklynew.Adapter.AdapterEvent
import com.example.quecklynew.Model.EventModel
import com.example.quecklynew.Model.EventViewModel
import com.example.quecklynew.R
import com.google.firebase.database.*

class AntrianFragment : Fragment() {

    private lateinit var rvAntrian: RecyclerView
    private lateinit var dataAntrianView: ArrayList<EventViewModel>
    private lateinit var mDbRef: DatabaseReference

    companion object {
        const val EXTRA_UID = "extra_uid"
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

        dataAntrianView = arrayListOf()
        rvAntrian = view.findViewById(R.id.rvAntrian)
        rvAntrian.layoutManager = LinearLayoutManager(this.context)
        rvAntrian.setHasFixedSize(true)

        rvAntrian.adapter = AdapterAntrian(dataAntrianView)
        val data = activity?.intent?.getStringExtra(EXTRA_UID)
        Toast.makeText(requireActivity(), "$data", Toast.LENGTH_SHORT).show()
        mDbRef = FirebaseDatabase.getInstance().getReference("data").child(data!!)
        getAntrian()
    }

    private fun getAntrian() {
        mDbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(EventViewModel::class.java)
                        dataAntrianView.add(user!!)
                    }
                    rvAntrian.adapter = AdapterAntrian(dataAntrianView)

                }


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireActivity(), "Error Get Data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
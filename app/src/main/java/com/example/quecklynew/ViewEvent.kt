package com.example.quecklynew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quecklynew.Adapter.AdapterEvent
import com.example.quecklynew.Model.EventViewModel
import com.google.firebase.database.*

class ViewEvent : AppCompatActivity() {
    private lateinit var rvEventView: RecyclerView
    private lateinit var dataEventView: ArrayList<EventViewModel>
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_event)

        dataEventView = arrayListOf()
        rvEventView = findViewById(R.id.rvEvent)
        rvEventView.layoutManager = LinearLayoutManager(this)
        rvEventView.setHasFixedSize(true)
        mDbRef = FirebaseDatabase.getInstance().getReference("event")

        getEventData()
    }

    private fun getEventData() {
        mDbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (eventView in snapshot.children) {
                    val event = eventView.getValue(EventViewModel::class.java)
                    dataEventView.add(event!!)
                }
                rvEventView.adapter = AdapterEvent(dataEventView)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewEvent, "Error Get Data", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
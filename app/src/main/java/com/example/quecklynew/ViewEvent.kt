package com.example.quecklynew

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    companion object {
        const val EXTRA_EVENT_VIEW_UID = "extra_event_view_uid"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_event)

        dataEventView = arrayListOf()
        rvEventView = findViewById(R.id.rvEvent)
        rvEventView.layoutManager = LinearLayoutManager(this)
        rvEventView.setHasFixedSize(true)
        val uidGet = intent.getStringExtra(EXTRA_EVENT_VIEW_UID)
        Toast.makeText(this, "$uidGet", Toast.LENGTH_SHORT).show()
        Log.e("TAG", "onCreate: $uidGet")
        mDbRef = FirebaseDatabase.getInstance().getReference("data").child("event")

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
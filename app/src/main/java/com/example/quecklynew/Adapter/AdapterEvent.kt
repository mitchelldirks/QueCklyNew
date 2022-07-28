package com.example.quecklynew.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quecklynew.Model.EventModel
import com.example.quecklynew.Model.EventViewModel
import com.example.quecklynew.R
import com.example.quecklynew.VIewQrCOde

class AdapterEvent(val viewEvent: ArrayList<EventViewModel>) :
    RecyclerView.Adapter<AdapterEvent.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nmEvent = itemView.findViewById<TextView>(R.id.namaEventView)
        val jmlAntrianView = itemView.findViewById<TextView>(R.id.jmlAntrianView)
        val tngglEventView = itemView.findViewById<TextView>(R.id.tanggalEventView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventView = viewEvent[position]
        holder.nmEvent.text = eventView.namaEventView
        holder.jmlAntrianView.text = eventView.nomorAntrianView
        holder.tngglEventView.text = eventView.tanggalView

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, VIewQrCOde::class.java)
            intent.putExtra(VIewQrCOde.EXTRA_QRCODE, eventView.uidEvent)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return viewEvent.size
    }
}
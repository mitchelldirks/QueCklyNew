package com.example.quecklynew.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quecklynew.Model.EventModel
import com.example.quecklynew.R

class AdapterAntrian(var dataAntrian: ArrayList<EventModel>) :
    RecyclerView.Adapter<AdapterAntrian.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaEv = itemView.findViewById<TextView>(R.id.namaEventShow)
        val jmlAntrian = itemView.findViewById<TextView>(R.id.nomorAntriEvent)
        val tanggalScan = itemView.findViewById<TextView>(R.id.tanggalScanEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_antrian, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataView = dataAntrian[position]
        holder.namaEv.text = dataView.namaEvent
        holder.jmlAntrian.text = dataView.nomorAntrian
        holder.tanggalScan.text = dataView.tanggal
    }

    override fun getItemCount(): Int {
        return dataAntrian.size
    }
}
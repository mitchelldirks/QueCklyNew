package com.example.quecklynew.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quecklynew.Fragment.AntrianFragment
import com.example.quecklynew.Model.EventModel
import com.example.quecklynew.Model.EventViewModel
import com.example.quecklynew.R

class AdapterAntrian(var dataAntrian: ArrayList<EventModel>) :
    RecyclerView.Adapter<AdapterAntrian.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaEv = itemView.findViewById<TextView>(R.id.namaEventShow)
        val jmlAntrian = itemView.findViewById<TextView>(R.id.nomorAntriEvent)
        val tanggalScan = itemView.findViewById<TextView>(R.id.tanggalScanEvent)
        val builder: AlertDialog.Builder = AlertDialog.Builder(itemView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_antrian, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataView = dataAntrian[position]
        holder.namaEv.text = dataView.namaEventView
        holder.jmlAntrian.text = dataView.nomorAntrianView
        holder.tanggalScan.text = dataView.tanggalView




//        holder.itemView.setOnClickListener {
//            val alert = AlertDialog.Builder(holder.itemView.context)
//            alert.setTitle(holder.namaEv.text)
//            alert.setMessage("Ini adalah alert dialog")
//            alert.setMessage("Ini adalah alert dialog tes")
//            alert.setNegativeButton("yNo", DialogInterface.OnClickListener { dialoh, which ->
//                Toast.makeText(holder.itemView.context, "NO", Toast.LENGTH_SHORT).show()
//            })
//            alert.setPositiveButton("yes", DialogInterface.OnClickListener { dialoh, which ->
//                Toast.makeText(holder.itemView.context, "Ya", Toast.LENGTH_SHORT).show()
//            })
//            alert.setNeutralButton("Batal", DialogInterface.OnClickListener { dialoh, which ->
//                Toast.makeText(holder.itemView.context, "Ya", Toast.LENGTH_SHORT).show()
//            })
//            alert.show()


//            val dialog = LayoutInflater.from(holder.itemView.context)
//                .inflate(R.layout.my_custome_dialog, null)
//            val myDialog = Dialog(holder.itemView.context)
//            myDialog.setContentView(dialog)
//            myDialog.setCancelable(true)
//            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            myDialog.show()

//        }


    }

    override fun getItemCount(): Int {
        return dataAntrian.size
    }
}
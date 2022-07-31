package com.example.quecklynew

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.quecklynew.Model.EventModel
import com.example.quecklynew.Model.EventViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.text.SimpleDateFormat
import java.util.*

class CreateEvent : AppCompatActivity() {
    private lateinit var qrImage: ImageView
    private lateinit var nmEvent: EditText
    private lateinit var jmlAntrian: EditText
    private lateinit var btnSaveCreate: Button
    private lateinit var btnViewEvent: Button

    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        qrImage = findViewById(R.id.qrCodeView)
        nmEvent = findViewById(R.id.namaEvent)
        jmlAntrian = findViewById(R.id.jumlahAntrian)
        btnSaveCreate = findViewById(R.id.btnSaveEvent)
        mDbRef = FirebaseDatabase.getInstance().reference
        btnViewEvent = findViewById(R.id.btnViewEvent)

        btnViewEvent.setOnClickListener {
            startActivity(Intent(this, ViewEvent::class.java))
        }

        btnSaveCreate.setOnClickListener {
            val namaEv = nmEvent.text.toString().trim()
            val jmlAntrian = jmlAntrian.text.toString().trim()
            val uid = UUID.randomUUID().toString()
            val calendar = Calendar.getInstance()
            val format = SimpleDateFormat(" EEEE d MM yyyy HH:mm:ss")
            val time: String = format.format(calendar.time)

            if (jmlAntrian.isEmpty()) {
                Toast.makeText(this, "Masukkan Data", Toast.LENGTH_SHORT).show()
            } else {
//                val writer = QRCodeWriter()
//                try {
//                    val bitMatrix = writer.encode(uid, BarcodeFormat.QR_CODE, 512, 512)
//                    val width = bitMatrix.width
//                    val height = bitMatrix.height
//                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
//                    for (x in 0 until width) {
//                        for (y in 0 until height) {
//                            bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
//                        }
//                    }
//                    qrImage.setImageBitmap(bmp)
//                    addDataEvent(namaEv, jmlAntrian, time, uid)
//                } catch (e: WriterException) {
//                    e.printStackTrace()
//                }
                addDataEvent(namaEv, jmlAntrian, time, uid)


            }
        }
    }

    private fun addDataEvent(namaEV: String, jmlAntrian: String, tanggal: String, uid: String) {
        mDbRef.child("data").child("event").child(uid)
            .setValue(EventViewModel(namaEV, jmlAntrian, tanggal, uid))
            .addOnSuccessListener {
                mDbRef.child("data").child("antrian").child(uid).child(uid)
                    .setValue(EventModel(namaEV, jmlAntrian, tanggal, uid)).addOnSuccessListener {
                    }
            }
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()
    }
}
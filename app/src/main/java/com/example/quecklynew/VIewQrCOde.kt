package com.example.quecklynew

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class VIewQrCOde : AppCompatActivity() {

    private lateinit var qrImageView: ImageView

    companion object {
        const val EXTRA_QRCODE = "extra_qrcode"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_qr_code)
        qrImageView = findViewById(R.id.qrCodeViewIntent)

        val uid = intent.getStringExtra(EXTRA_QRCODE)

        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(uid, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            qrImageView.setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}

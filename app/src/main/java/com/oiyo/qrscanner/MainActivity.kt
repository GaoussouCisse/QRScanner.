package com.oiyo.qrscanner


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.android.material.button.MaterialButton
import com.google.zxing.qrcode.encoder.QRCode
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var qrCodeIV: ImageView
    private lateinit var dataEdt: EditText
    private lateinit var generateQRBtn: MaterialButton
    var bitmap: Bitmap? = null
    var qrgEncoder: QRGEncoder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrCodeIV = findViewById(R.id.idIVQrcode)
        dataEdt = findViewById(R.id.idEdt)
        generateQRBtn = findViewById(R.id.idBtngenerate)
        generateQRBtn.setOnClickListener {
            if (TextUtils.isEmpty(dataEdt.text.toString())) {
                Toast.makeText(
                    this@MainActivity,
                    "QR Oluşturmak için Birşeyler Yazın",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                val manager = getSystemService(WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4
                qrgEncoder = QRGEncoder(dataEdt.text.toString(),
                null,QRGContents.Type.TEXT,dimen)
                try {
                    bitmap=qrgEncoder!!.encodeAsBitmap()
                    qrCodeIV.setImageBitmap(bitmap)
                }catch (e:Exception){
                    Log.e("Tag",e.toString() )
                }
            }
        }
    }
}
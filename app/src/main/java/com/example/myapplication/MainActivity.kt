package com.example.myapplication

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btBtn = findViewById<Button>(R.id.btBtn)
        val tvBt = findViewById<TextView>(R.id.BtTv)

        val bluetoothManager = applicationContext.getSystemService(
            BLUETOOTH_SERVICE
        ) as BluetoothManager

        btBtn.setOnClickListener {
            if (bluetoothManager.adapter.isEnabled) {
                bluetoothManager.adapter.disable()
                tvBt.text = getString(R.string.BLuetoothOff)
            } else {
                bluetoothManager.adapter.enable()
                tvBt.text = getString(R.string.BLuetoothOn)
            }
        }

        val pdBtn = findViewById<Button>(R.id.pdBtn)
        pdBtn.setOnClickListener {
            val i = Intent(this, PairedDevices::class.java)
            if (bluetoothManager.adapter.isEnabled)
                startActivity(i)
            else
                Toast.makeText(
                    this, "Please Enable Bluetooth", Toast
                        .LENGTH_SHORT
                ).show()
        }

        val sdBtn = findViewById<Button>(R.id.sdBtn)
        sdBtn.setOnClickListener {
            if (!packageManager.hasSystemFeature(PackageManager
                    .FEATURE_BLUETOOTH_LE))
                Toast.makeText(this, "BLE not supported", Toast
                    .LENGTH_SHORT).show()
            else {
                val i = Intent(this, ScannedBLE::class.java)
                startActivity(i)
            }
        }
    }
}
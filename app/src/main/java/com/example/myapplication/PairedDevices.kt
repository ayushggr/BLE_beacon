package com.example.myapplication

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class PairedDevices : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paired_devices)

        val bluetoothManager = applicationContext.getSystemService(
            BLUETOOTH_SERVICE) as BluetoothManager

        val pairedDevices = bluetoothManager.adapter.bondedDevices as
                Set<BluetoothDevice>

        val pairedDevicesList = mutableListOf<String>()
        for (item in pairedDevices)
            pairedDevicesList.add(item.name)

        val lvPd = findViewById<ListView>(R.id.lvPd)
        if (pairedDevicesList.isNotEmpty())
            lvPd.adapter = ArrayAdapter(
                this,
                R.layout.pd_list,
                R.id.tvPd,
                pairedDevicesList
            )
        else
            Toast.makeText(
                this, "No Paired Devices Found!!", Toast
                    .LENGTH_SHORT
            ).show()
    }
}
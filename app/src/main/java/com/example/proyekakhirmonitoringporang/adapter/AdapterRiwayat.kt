package com.example.proyekakhirmonitoringporang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.model.RvRiwayat

class AdapterRiwayat(var data:ArrayList<RvRiwayat>): RecyclerView.Adapter<AdapterRiwayat.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvTanggal = view.findViewById<TextView>(R.id.tv_tanggalMonitoring)
        val tvKelembaban = view.findViewById<TextView>(R.id.tv_kelembabanMonitoring)
        val tvPh = view.findViewById<TextView>(R.id.ph_Monitoring)
        val imgRiwayat = view.findViewById<ImageView>(R.id.img_riwayat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate (R.layout.item_riwayat, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvTanggal.text = data[position].tanggal
        holder.tvKelembaban.text = data[position].kelembaban
        holder.tvPh.text = data[position].ph
        holder.imgRiwayat.setImageResource(data[position].gambar)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}
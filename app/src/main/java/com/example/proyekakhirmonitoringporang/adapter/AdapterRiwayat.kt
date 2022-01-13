package com.example.proyekakhirmonitoringporang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.sensor.Massage

class AdapterRiwayat(var data: ArrayList<Massage>): RecyclerView.Adapter<AdapterRiwayat.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val tvTanggal = view.findViewById<TextView>(R.id.tv_tanggalMonitoring)
        val tvKelembaban = view.findViewById<TextView>(R.id.tv_kelembabanMonitoring)
        val tvPh = view.findViewById<TextView>(R.id.ph_Monitoring)
        val imgRiwayat = view.findViewById<ImageView>(R.id.img_riwayat)
        val infoKelembapan = view.findViewById<TextView>(R.id.info_kelembapan)
        val infoPh = view.findViewById<TextView>(R.id.info_ph)
        val rekomKelembapan = view.findViewById<TextView>(R.id.rekom_kelembapan)
        val rekomPh = view.findViewById<TextView>(R.id.rekom_ph)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate (R.layout.item_riwayat, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvTanggal.text = data[position].lahanId.toString()
        holder.tvKelembaban.text = data[position].kelembapan.toString()
        holder.tvPh.text = data[position].ph.toString()
//        holder.imgRiwayat.setImageResource(data[position].gambar)
        holder.infoKelembapan.text = data[position].infoKelembapan
        holder.infoPh.text = data[position].infoPh
        holder.rekomKelembapan.text = data[position].rekomKelembapan
        holder.rekomPh.text = data[position].rekomPh



    }

    override fun getItemCount(): Int {
        return data.size
    }

}
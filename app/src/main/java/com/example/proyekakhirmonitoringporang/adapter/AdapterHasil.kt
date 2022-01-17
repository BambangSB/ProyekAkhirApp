package com.example.proyekakhirmonitoringporang.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekakhirmonitoringporang.DetailHasilCatat
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.getLahan.Massage
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import com.squareup.picasso.Picasso

class AdapterHasil(var activity: Activity, var dataLahanHasil: ArrayList<Massage>) : RecyclerView.Adapter<AdapterHasil.Holder>() {



    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val hasilNamaLahan = view.findViewById<TextView>(R.id.hasil_namaLahan)
        val hasilAlamatLahan = view.findViewById<TextView>(R.id.tv_item_hasil_alamatLahan)
        val hasilLuasLahan = view.findViewById<TextView>(R.id.tv_item_hasil_luasLahan)
        val hasilimgLahan = view.findViewById<ImageView>(R.id.img_item_hasil)
        val cvHasilLahan = view.findViewById<CardView>(R.id.cd_item_hasil)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hasil, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.hasilNamaLahan.text = dataLahanHasil[position].nama
        holder.hasilAlamatLahan.text = dataLahanHasil[position].alamat
        holder.hasilLuasLahan.text = dataLahanHasil[position].luas


//        val id = sp.getUser()!!.id

        val id = "6"
        val image = "http://porasiteam.com/TA/api/GetLahan/6" + dataLahanHasil[position].foto
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.lahan_image_item)
            .error(R.drawable.lahan_error_item)
            .into(holder.hasilimgLahan)
//        holder.imgLahan.setImageResource(data[1].massage[position])

        holder.cvHasilLahan.setOnClickListener {
            activity.startActivity(Intent(activity, DetailHasilCatat::class.java))
        }



    }



    override fun getItemCount(): Int {
        return dataLahanHasil.size
    }

    fun clear() {
        dataLahanHasil.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<Massage>) {
        dataLahanHasil.addAll(tweetList)
        notifyDataSetChanged()
    }

}
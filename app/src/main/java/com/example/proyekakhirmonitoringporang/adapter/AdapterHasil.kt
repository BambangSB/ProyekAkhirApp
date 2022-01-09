package com.example.proyekakhirmonitoringporang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.getLahan.Massage
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import com.squareup.picasso.Picasso

class AdapterHasil(var dataHasil: ArrayList<Massage>) :
    RecyclerView.Adapter<AdapterHasil.Holder>() {



    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaLahan = view.findViewById<TextView>(R.id.tv_item_hasil_namaLahan)
        val tvAlamatLahan = view.findViewById<TextView>(R.id.tv_item_hasil_alamatLahan)
        val tvLuasLahan = view.findViewById<TextView>(R.id.tv_item_hasil_luasLahan)
        val imgLahan = view.findViewById<ImageView>(R.id.img_item_hasil)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lahan, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNamaLahan.text = dataHasil[position].nama
        holder.tvAlamatLahan.text = dataHasil[position].alamat
        holder.tvLuasLahan.text = dataHasil[position].luas
//        lateinit var sharedPref: SharedPref
//        val sp = sharedPref
//        val id = sp.getUser()!!.id
        val image = "http://porasiteam.com/TA/api/GetLahan/" + dataHasil[position].foto
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.lahan_image_item)
            .error(R.drawable.lahan_error_item)
            .into(holder.imgLahan)
//        holder.imgLahan.setImageResource(data[1].massage[position])

    }



    override fun getItemCount(): Int {
        return dataHasil.size
    }

    fun clear() {
        dataHasil.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<Massage>) {
        dataHasil.addAll(tweetList)
        notifyDataSetChanged()
    }

}
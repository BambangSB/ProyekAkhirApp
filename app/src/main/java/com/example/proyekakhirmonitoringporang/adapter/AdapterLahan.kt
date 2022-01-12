package com.example.proyekakhirmonitoringporang.adapter

import android.app.Activity
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

class AdapterLahan(var dataLahan: ArrayList<Massage>) :
    RecyclerView.Adapter<AdapterLahan.Holder>() {



    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaLahan = view.findViewById<TextView>(R.id.tv_item_namaLahan)
        val tvAlamatLahan = view.findViewById<TextView>(R.id.tv_item_alamatLahan)
        val tvLuasLahan = view.findViewById<TextView>(R.id.tv_item_luasLahan)
        val imgLahan = view.findViewById<ImageView>(R.id.img_item_lahan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lahan, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNamaLahan.text = dataLahan[position].nama
        holder.tvAlamatLahan.text = dataLahan[position].alamat
        holder.tvLuasLahan.text = dataLahan[position].luas


//        val id = sp.getUser()!!.id

        val id = "6"
        val image = "http://porasiteam.com/TA/api/GetLahan/6" + dataLahan[position].foto
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.lahan_image_item)
            .error(R.drawable.lahan_error_item)
            .into(holder.imgLahan)
//        holder.imgLahan.setImageResource(data[1].massage[position])

    }



    override fun getItemCount(): Int {
        return dataLahan.size
    }

    fun clear() {
        dataLahan.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<Massage>) {
        dataLahan.addAll(tweetList)
        notifyDataSetChanged()
    }

}
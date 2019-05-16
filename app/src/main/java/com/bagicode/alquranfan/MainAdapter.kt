package com.bagicode.alquranfan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bagicode.alquranfan.model.Ayah
import kotlinx.android.synthetic.main.row_item_ayat.view.*

class MainAdapter(private val movies: ArrayList<Ayah>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = movies[position].text
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_item_ayat, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View?) {
            Toast.makeText(v!!.context, txtTitle.text, Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener(this)
        }

        val txtTitle = itemView.txtTitle!!

    }
}
package com.example.vistaprincipal.recyclerview

import android.app.Activity
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistaprincipal.R
import kotlinx.android.synthetic.main.postres.view.*

class PostreAdapter(private val activity: Activity, private val dataset: List<Post>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<PostreAdapter.ViewHolder>() {

    class ViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.postres, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = dataset[position]

        holder.layout.postre_tv.text = post.nombre
        holder.layout.dulceSalado_tv.text = post.tipo
        holder.layout.pais_tv.text = post.pais

        holder.layout.setOnClickListener{
            onClickListener.onItemClicked(position)
        }
    }

}
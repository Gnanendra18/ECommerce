package com.incrediblepath.ecommerce.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.incrediblepath.ecommerce.R
import com.incrediblepath.ecommerce.model.Product

class ProductAdapter(var list: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_item_view, parent, false)
        val viewholder = ViewHolder(view)

        return viewholder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = holder.itemView.findViewById<ImageView>(R.id.image1)

        Glide.with(image.context).load(list[position].pd_image).into(image);
        holder.itemView.findViewById<TextView>(R.id.pd_name).setText(list[position].pd_name)
        holder.itemView.findViewById<TextView>(R.id.pd_price).setText(list[position].pd_price.toString())


    }

    override fun getItemCount(): Int {
        return list.size

    }

    fun filterData(list: List<Product>){
        this.list = list

        notifyDataSetChanged()
    }
}





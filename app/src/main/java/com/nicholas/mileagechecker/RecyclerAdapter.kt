package com.nicholas.mileagechecker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    //create three arrays for our items
    private var images = intArrayOf(R.drawable.audi,R.drawable.audi,R.drawable.audi,R.drawable.audi, R.drawable.audi, R.drawable.audi,
        R.drawable.audi,R.drawable.audi )
    private var models = arrayOf("Audi A7", "Audi A7", "Audi A7", "Audi A7", "Audi A7", "Audi A7", "Audi A7", "Audi A7")
    private var prices = arrayOf("$80,250","$80,250","$80,250","$80,250","$80,250","$80,250","$80,250","$80,250")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        //inflating the layout of the cardItem
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        //the method or the function helps in populating data into the cardView
        holder.itemModel.text = models[position]
        holder.itemPrice.text = prices[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return models.size //
    }
    //create an inner class
    inner class  ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView
        var itemModel : TextView
        var itemPrice : TextView

        //initializing items
        init{
            itemImage = itemView.findViewById(R.id.carImg)
            itemModel = itemView.findViewById(R.id.carmodel)
            itemPrice = itemView.findViewById(R.id.carprice)
        }

    }
}
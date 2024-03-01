package com.example.adminsweeto.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminsweeto.databinding.DeliveryItemBinding

class DeliveryAdapter(private val customerName: MutableList<String>,
                      private val statusMoney: MutableList<Boolean>)
    :RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = DeliveryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = customerName.size

    inner class DeliveryViewHolder(private val binding: DeliveryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                CustomerName.text = customerName[position]
                if (statusMoney [position] == true){
                    moneyStatus.text = "Received"
                }else{
                    moneyStatus.text = "NotReceived"
                }
                val colorMap = mapOf(
                    true to Color.GREEN, false to Color.RED
                )
                moneyStatus.setTextColor(colorMap[statusMoney[position]]?:Color.BLACK)
                orderStatus.backgroundTintList = ColorStateList.valueOf(colorMap[statusMoney[position]]?:Color.BLACK)
            }
        }

    }
}
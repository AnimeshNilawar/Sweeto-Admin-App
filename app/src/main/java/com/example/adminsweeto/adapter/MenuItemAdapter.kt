package com.example.adminsweeto.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminsweeto.databinding.ItemItemBinding
import com.example.adminsweeto.model.AllMenu
import com.google.firebase.database.DatabaseReference

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    private val databaseReference: DatabaseReference,
    private val onDeleteClickListener:(position : Int) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(menuList.size) { 1 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size

    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                binding.apply {
                    val quantity = itemQuantities[position]
                    val menuItem = menuList[position]
                    val uriString = menuItem.foodImage
                    val uri = Uri.parse(uriString)

                    FoodName.text = menuItem.foodName
                    FoodPrice.text = menuItem.foodPrice
                    Glide.with(context).load(uri).into(foodImage)
                    foodQuantity.text = quantity.toString()

                    minusButton.setOnClickListener {
                        decreaseQuantity(position)
                    }
                    plusbutton.setOnClickListener {
                        increaseQuantity(position)
                    }
                    deleteButton.setOnClickListener {
                        onDeleteClickListener(position)
                    }

                }
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.foodQuantity.text = itemQuantities[position].toString()
            }
        }

//        private fun deleteItem(position: Int) {
//            val itemToDelete = menuList[position]
//            databaseReference.child(itemToDelete.foodId).removeValue().addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    menuList.removeAt(position)
//                    notifyItemRemoved(position)
//                    notifyItemRangeChanged(position, menuList.size)
//                } else {
//                    // Handle failure
//                    Log.e("Firebase", "Failed to delete item", task.exception)
//                }
//            }
//        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.foodQuantity.text = itemQuantities[position].toString()
            }

        }

    }


}
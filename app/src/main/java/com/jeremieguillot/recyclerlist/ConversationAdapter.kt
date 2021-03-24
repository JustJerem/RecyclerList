package com.jeremieguillot.recyclerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

interface ConversationAdapterListener{
    fun onUserClicked(name: Contact)
}

class ConversationAdapter(private val listener: ConversationAdapterListener) : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {

    private var data: ArrayList<Contact> = ArrayList()

    fun setData(data: ArrayList<Contact>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvPhone: TextView = view.findViewById(R.id.tv_phone)
        val imgUserProfile: ImageView = view.findViewById(R.id.img_user_profile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = data[position]
        holder.tvName.text = contact.name.capitalize(Locale.getDefault())
        holder.tvPhone.text = contact.phoneNumber.chunked(2).joinToString(separator = " ")
        holder.imgUserProfile.load(contact.userPicture)
        holder.itemView.setOnClickListener { listener.onUserClicked(contact) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
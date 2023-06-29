package com.example.radius.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.R

class OptionAdapter (private val facilityOptions: List<String>): RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): OptionAdapter.OptionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.options, parent, false)
        return OptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OptionAdapter.OptionViewHolder, position: Int) {
        val facilityName = facilityOptions[position]
        holder.bind(facilityName)
    }

    override fun getItemCount(): Int {
        return facilityOptions.size
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facilityOptionTextView: TextView = itemView.findViewById(R.id.facilityOptions)

        fun bind(facilityName: String) {
            facilityOptionTextView.text = facilityName

        }
    }
}
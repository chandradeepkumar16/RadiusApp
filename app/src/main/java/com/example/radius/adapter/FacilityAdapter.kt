package com.example.radius.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.R

class FacilityAdapter(private val facilityNames: List<String>) : RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.facilitynames, parent, false)
        return FacilityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
//        holder.facilityNameTextView.text=facilityNames[position]


        val facilityName = facilityNames[position]
        holder.bind(facilityName)
    }

    override fun getItemCount(): Int {
        return facilityNames.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facilityNameTextView: TextView = itemView.findViewById(R.id.facilityNameTextView)
//        val recyclerview :RecyclerView = itemView.findViewById(R.id.RecyclerView)

        fun bind(facilityName: String) {
            facilityNameTextView.text = facilityName

        }
    }
}

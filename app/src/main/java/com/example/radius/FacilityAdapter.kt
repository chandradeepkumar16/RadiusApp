package com.example.radius

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FacilityAdapter(private val facilities: List<Facility>) :
    RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    private val selectedOptions = mutableMapOf<String, FacilityOption>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.facilityName.text = facility.name

        val optionsAdapter = OptionAdapter(facility.options, selectedOptions)
        holder.optionsRecyclerView.adapter = optionsAdapter
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facilityName: TextView = itemView.findViewById(R.id.facilityName)
        val optionsRecyclerView: RecyclerView = itemView.findViewById(R.id.optionsRecyclerView)
    }
}


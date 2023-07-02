package com.example.radius.adapter.facility

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.R
import com.example.radius.adapter.option.FacilityOptionAdapter
import com.example.radius.model.Facility
import com.example.radius.model.Option

class FacilityAdapter(private val facilities: List<Facility>) :
    RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

//    private val selectedOptions = mutableMapOf<String, Option>()
    private val selectedOptions = mutableMapOf<String, String>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.facility_item_layout, parent, false)
        return FacilityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityNameTextView: TextView = itemView.findViewById(R.id.facilityNameTextView)
        private val facilityOptionsRecyclerView: RecyclerView = itemView.findViewById(R.id.facilityOptionsRecyclerView)



        fun bind(facility: Facility) {

            facilityNameTextView.text = facility.name
            facilityOptionsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)

            val optionAdapter = FacilityOptionAdapter(facility.options)
            optionAdapter.setOnOptionClickListener { option ->
                selectedOptions[facility.facility_id] = option.name
            }
            facilityOptionsRecyclerView.adapter = optionAdapter
        }
    }

    fun getSelectedOptions(): List<String> {
        return selectedOptions.values.toList()
    }

}


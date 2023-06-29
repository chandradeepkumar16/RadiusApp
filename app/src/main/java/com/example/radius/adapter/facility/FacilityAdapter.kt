package com.example.radius.adapter.facility

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

    private val facilitieslist: MutableList<Facility> = mutableListOf()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.facility_item_layout, parent, false)
        return FacilityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    fun setFacilities(facilities: List<Facility>) {
        this.facilitieslist.clear()
        this.facilitieslist.addAll(facilities)
        notifyDataSetChanged()
    }

    fun getSelectedOptions(): List<String> {
        val selectedOptions = mutableListOf<String>()
        for (fac in facilitieslist) {
            for(f in fac.options){
                selectedOptions.add(f.name)
            }
        }
        return selectedOptions
    }

//    fun getSelectedOptions(): List<Option> {
//        val selectedOptions = mutableListOf<Option>()
//        for (f in facilitieslist) {
//            val selectedOption = f.options.find { it == f.options }
//            selectedOption?.let {
//                selectedOptions.add(selectedOption)
//            }
//        }
//        return selectedOptions
//    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityNameTextView: TextView = itemView.findViewById(R.id.facilityNameTextView)
        private val facilityOptionsRecyclerView: RecyclerView = itemView.findViewById(R.id.facilityOptionsRecyclerView)
//        private val facilityOptionAdapter: FacilityOptionAdapter = FacilityOptionAdapter()

        fun bind(facility: Facility) {
            facilityNameTextView.text = facility.name

//            facilityOptionAdapter.options = facility.options
//            facilityOptionAdapter.selectedOption = facility.selectedOption
//            facilityOptionAdapter.notifyDataSetChanged()

            facilityOptionsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            facilityOptionsRecyclerView.adapter = FacilityOptionAdapter(facility.options)
        }
    }
}

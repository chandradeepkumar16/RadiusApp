package com.example.radius.adapter.option

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.R
import com.example.radius.model.Option

class FacilityOptionAdapter(var options: List<Option>) :
    RecyclerView.Adapter<FacilityOptionAdapter.FacilityOptionViewHolder>() {

    private var selectedOptionPosition = -1
    val selectedOptions= ArrayList<String>()
    private var onOptionClickListener: ((Option) -> Unit)? = null

    inner class FacilityOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val optionNameTextView: TextView = itemView.findViewById(R.id.optionNameTextView)
        val optionRadioBTN :RadioButton = itemView.findViewById(R.id.optionBtn)


        fun bind(option: Option) {

            optionNameTextView.text = option.name
            optionRadioBTN.isChecked = adapterPosition == selectedOptionPosition
            itemView.setOnClickListener {
                setSelectedOption(adapterPosition)
                onOptionClickListener?.invoke(option)
            }

            optionRadioBTN.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    setSelectedOption(adapterPosition)
                    onOptionClickListener?.invoke(option)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityOptionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.options, parent, false)
        return FacilityOptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FacilityOptionViewHolder, position: Int) {
        val optionName = options[position]
        holder.bind(optionName)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    fun setOnOptionClickListener(listener: (Option) -> Unit) {
        onOptionClickListener = listener
    }


    fun setSelectedOption(position: Int) {
        if (selectedOptionPosition != position) {
            selectedOptionPosition = position
            notifyDataSetChanged()
        }
    }

    fun clearSelectedOptions() {
        selectedOptionPosition = -1
        selectedOptions.clear()
        notifyDataSetChanged()
    }



}

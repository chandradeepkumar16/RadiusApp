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


    inner class FacilityOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val optionNameTextView: TextView = itemView.findViewById(R.id.optionNameTextView)
        val optionRadioBTN :RadioButton = itemView.findViewById(R.id.optionBtn)

        init {
            optionRadioBTN.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    setSelectedOption(position)
                    Log.d("check", "${optionNameTextView.text}")

                }
            }


            optionRadioBTN.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedOptions.add(optionNameTextView.text.toString())
                    Log.d("check", "${selectedOptions.size}")

                } else {
                    selectedOptions.remove(optionNameTextView.text.toString())
                }
            }


        }



        fun bind(option: Option) {

            optionNameTextView.text = option.name
            optionRadioBTN.isChecked = adapterPosition == selectedOptionPosition

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

    fun setSelectedOption(position: Int) {
        if (selectedOptionPosition != position) {
            selectedOptionPosition = position
            notifyDataSetChanged()
        }
    }

    fun getSelectedOption(): Option? {
        if (selectedOptionPosition != -1) {
            return options[selectedOptionPosition]
        }
        return null
    }


    fun getSeletedArray(): List<String> {
        return selectedOptions
        notifyDataSetChanged()
    }



}

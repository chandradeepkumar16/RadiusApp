package com.example.radius

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.model.FacilityOption

class OptionAdapter(
    private val options: List<FacilityOption>,
    private val selectedOptions: MutableMap<String, FacilityOption>
) : RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val option = options[position]
        holder.optionName.text = option.name
        holder.optionIcon.setImageResource(option.icon)
        holder.optionCheckBox.isChecked = selectedOptions.containsValue(option)

        holder.optionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Store the selected option
                selectedOptions[option.name] = option
                // Remove any conflicting options
                removeConflictingOptions(option)
            } else {
                // Remove the deselected option
                selectedOptions.remove(option.name)
            }
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionName: TextView = itemView.findViewById(R.id.optionName)
        val optionIcon: ImageView = itemView.findViewById(R.id.optionIcon)
        val optionCheckBox: CheckBox = itemView.findViewById(R.id.optionCheckBox)
    }

    private fun removeConflictingOptions(selectedOption: FacilityOption) {
        // Check for exclusion combinations and remove conflicting options
        if (selectedOption.name == "Land") {
            selectedOptions.remove("1 to 3 rooms")
        } else if (selectedOption.name == "Boat House") {
            selectedOptions.remove("Garage")
        }
        // Notify adapter of changes if necessary
        notifyDataSetChanged()
    }
}

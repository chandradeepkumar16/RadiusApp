package com.example.radius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.radius.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val mylist = ArrayList<Facility>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)



        val adapter=FacilityAdapter(mylist)
        binding.optionsRecyclerView.adapter=adapter
        adapter.notifyDataSetChanged()

//        binding.RecyclerViewBeat.layoutManager = LinearLayoutManager(context)

    }
}
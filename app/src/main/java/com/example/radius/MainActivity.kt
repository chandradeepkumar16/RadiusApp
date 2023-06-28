package com.example.radius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radius.databinding.ActivityMainBinding
import com.example.radius.model.Facility
import com.example.radius.model.FacilityOption

class MainActivity : AppCompatActivity() {

    val mylist = ArrayList<Facility>()
    var newlist = ArrayList<FacilityOption>()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)


        var facilityOption1= FacilityOption("name",1)
        var facilityOption2= FacilityOption("name2",2)
        var facilityOption3= FacilityOption("nam3",3)
        var facilityOption4= FacilityOption("name4",4)
        newlist.add(facilityOption1)
        newlist.add(facilityOption2)
        newlist.add(facilityOption3)
        newlist.add(facilityOption4)

        val facility = Facility("ayush",newlist)
        mylist.add(1,facility)

        val adapter=FacilityAdapter(mylist)
        binding.optionsRecyclerView.adapter=adapter
        adapter.notifyDataSetChanged()

//        binding.RecyclerViewBeat.layoutManager = LinearLayoutManager(context)

    }
}
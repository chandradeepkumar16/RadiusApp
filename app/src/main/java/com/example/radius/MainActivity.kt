package com.example.radius

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.radius.adapter.FacilityAdapter
import com.example.radius.databinding.ActivityMainBinding
import com.example.radius.services.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

class MainActivity : AppCompatActivity() {

//    val exclusions = List<List<Exclusion>>()
//    var facilities = List<Facility>()

  val list = ArrayList<String>()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        recyclerView = findViewById(R.id.recyclerView)
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://my-json-server.typicode.com/iranjith4/ad-assignment/")
            // .baseUrl("http://192.168.137.1:3001/v1/") //home
            .build()
            .create(ApiInterface::class.java)

        val call = retrofitBuilder.getFacilities()



// Make the API call to fetch the FacilityModel data




        call.enqueue(object : Callback<FacilityModel> {
            override fun onResponse(call: Call<FacilityModel>, response: Response<FacilityModel>) {
                if (response.isSuccessful) {
                    val facilityModel = response.body()
                    facilityModel?.let {
                        for (facility in facilityModel.facilities) {
                            val facilityName = facility.name
                            list.add(facilityName)
                        }
                    }

                    Toast.makeText(this@MainActivity, "${list.size}",Toast.LENGTH_LONG).show()
                    val adapter = FacilityAdapter(list)
                    binding.RecyclerView.adapter=adapter
                } else {
                    println("API call failed: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<FacilityModel>, t: Throwable) {
                println("Network error: ${t.message}")
            }
        })

    }
}



//        setContentView(R.layout.activity_main)

//        val facilityModel = FacilityModel(exclusions, facilities)
//        val facilityAdapter = FacilityAdapter(facilityModel)

//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        binding.optionsRecyclerView.adapter = facilityAdapter




//        var facilityOption1= FacilityOption("name",1)
//        var facilityOption2= FacilityOption("name2",2)
//        var facilityOption3= FacilityOption("nam3",3)
//        var facilityOption4= FacilityOption("name4",4)
//        newlist.add(facilityOption1)
//        newlist.add(facilityOption2)
//        newlist.add(facilityOption3)
//        newlist.add(facilityOption4)
//
//        val facility = Facility("ayush",newlist)
//        mylist.add(1,facility)
//
//        val adapter=FacilityAdapter(mylist)
//        binding.optionsRecyclerView.adapter=adapter
//        adapter.notifyDataSetChanged()

//        binding.RecyclerViewBeat.layoutManager = LinearLayoutManager(context)

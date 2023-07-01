package com.example.radius

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.radius.adapter.facility.FacilityAdapter
import com.example.radius.adapter.option.FacilityOptionAdapter
import com.example.radius.databinding.ActivityMainBinding
import com.example.radius.model.FacilityModel
import com.example.radius.services.ApiInterface
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

//    val exclusions = List<List<Exclusion>>()
//    var facilities = List<Facility>()

    val list = ArrayList<String>()
    val optionslist = ArrayList<String>()
    lateinit var binding: ActivityMainBinding
    private lateinit var facilityAdapter: FacilityAdapter
     var facilityOptionAdapter: FacilityOptionAdapter ?=null


    val database = FirebaseDatabase.getInstance()
    val reference = FirebaseDatabase.getInstance().getReference("users")

//    lateinit var binding: FacilityItemLayoutBinding


    override fun onBackPressed() {
        startActivity(Intent(this , MainActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://my-json-server.typicode.com/iranjith4/ad-assignment/")
            .build()
            .create(ApiInterface::class.java)

        val call = retrofitBuilder.getFacilities()

        call.enqueue(object : Callback<FacilityModel> {
            override fun onResponse(call: Call<FacilityModel>, response: Response<FacilityModel>) {
                if (response.isSuccessful) {
                    val facilityModel = response.body()
                    facilityModel?.let {
                        for (facility in facilityModel.facilities) {

                            facilityAdapter = FacilityAdapter(it.facilities)
                            binding.recyclerView.adapter = facilityAdapter

                            facilityOptionAdapter = FacilityOptionAdapter(it.facilities.first().options)
                            binding.recyclerView.adapter = facilityAdapter

                        }
                    }
                } else {
                    println("API call failed: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<FacilityModel>, t: Throwable) {
                println("Network error: ${t.message}")
            }
        })


        binding.submitBtn.setOnClickListener {


            val selectedOptions = facilityAdapter.getSelectedOptions()
            Log.d("check", "$selectedOptions")

            val reference = database.getReference("users")
            val newUserReference = reference.push()
            newUserReference.setValue(selectedOptions)

//            facilityAdapter?.clearOptions()
            startActivity(Intent(this , MainActivity::class.java))

        }


        binding.previousDataBtn.setOnClickListener {
            startActivity(Intent(this,PreviousSubmittedDataActivtiy::class.java))
        }





    }
}



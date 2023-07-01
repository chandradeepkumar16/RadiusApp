package com.example.radius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.radius.adapter.previousData.PreviousDataSelectedAdapter
import com.example.radius.databinding.ActivityMainBinding
import com.example.radius.databinding.ActivityPreviousSubmittedDataActivtiyBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PreviousSubmittedDataActivtiy : AppCompatActivity() {
    lateinit var binding: ActivityPreviousSubmittedDataActivtiyBinding

    private lateinit var adapter: PreviousDataSelectedAdapter
    private val dataList = ArrayList<String>()

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviousSubmittedDataActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.prevrecyclerView.layoutManager = LinearLayoutManager(this)


        reference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (childSnapshot in snapshot.children) {
                    dataList.add(childSnapshot.value.toString())

                        adapter=PreviousDataSelectedAdapter(dataList)
                        binding.prevrecyclerView.adapter=adapter
                        adapter.notifyDataSetChanged()
                 }

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })



    }



}
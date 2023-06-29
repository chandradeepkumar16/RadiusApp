package com.example.radius.model

import com.example.radius.model.Exclusion
import com.example.radius.model.Facility

data class FacilityModel(
    val exclusions: List<List<Exclusion>>,
    val facilities: List<Facility>
)
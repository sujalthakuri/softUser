package com.example.week6assignment1.models

data class User(
    var id: Int?,
    val name: String?,
    val age: Int?,
    val gender: Char?,
    val address: String?,
    val image_url: String? = null)
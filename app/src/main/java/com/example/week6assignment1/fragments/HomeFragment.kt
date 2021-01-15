package com.example.week6assignment1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week6assignment1.R
import com.example.week6assignment1.Storage
import com.example.week6assignment1.adapters.UserAdapter
import com.example.week6assignment1.models.User

class HomeFragment : Fragment() {
    private val storage = Storage()
    private lateinit var rvHome: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        rvHome = view.findViewById(R.id.rvHome)
        loadUserAdapter()
        return view
    }
    private fun loadUserAdapter() {
        val arr = storage.getUsers().values.toTypedArray().reversedArray().toList()
        val adapter = this.context?.let { UserAdapter(arr as ArrayList<User>, it) }
        rvHome.layoutManager = LinearLayoutManager(this.context)
        rvHome.adapter = adapter
    }
}
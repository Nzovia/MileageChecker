package com.nicholas.mileagechecker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.mileagechecker.R
import com.nicholas.mileagechecker.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_cars.*

class CarsFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cars, container, false)
        //initializing
        layoutManager = LinearLayoutManager (activity)
        cargallery.layoutManager = layoutManager
        adapter = RecyclerAdapter()
//        cargallery.adapter = adapter

        return view


    }

}
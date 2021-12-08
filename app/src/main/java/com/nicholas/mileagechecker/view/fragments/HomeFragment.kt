package com.nicholas.mileagechecker.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nicholas.mileagechecker.R
import com.nicholas.mileagechecker.databinding.FragmentHomeBinding
import com.nicholas.mileagechecker.view.activities.AddCar
import com.nicholas.mileagechecker.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // override onCreate method sets options menu to appear at the top(in the actions bar)
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
  //defines how the options menu gonna look like once created
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inside the onCreate Options Menu we inflate out menu xml
        inflater.inflate(R.menu.add_cars, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_new_car->{
                startActivity(Intent(requireActivity(), AddCar::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
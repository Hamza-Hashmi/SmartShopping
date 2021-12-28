package com.example.smartshoppingapp.ui.fragment.dashboardFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartshoppingapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        var layoutManager: GridLayoutManager = GridLayoutManager(requireContext(), 2)

        binding.recyclerviewItems.layoutManager = layoutManager
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
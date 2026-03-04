package com.abdallahyasser.digi_azkar.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abdallahyasser.digi_azkar.R
import com.abdallahyasser.digi_azkar.data.PrayerRepoImpl
import com.abdallahyasser.digi_azkar.databinding.FragmentHomeBinding
import com.abdallahyasser.digi_azkar.domain.prayer.GetPrayerTimesUseCase


class HomeFragment : Fragment() {

    private val adapter: PrayerAdapter by lazy { PrayerAdapter(emptyList()) }

    private lateinit var binding: FragmentHomeBinding


    val viewModel: HomeViewModel by viewModels() {
        val repo = PrayerRepoImpl()
        val useCase = GetPrayerTimesUseCase(repo)
        HomeVMFactory(useCase)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.prayerRecyclerView.adapter = adapter


        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressIndicator.visibility = View.VISIBLE
            } else {
                binding.progressIndicator.visibility = View.GONE
            }
        }

        viewModel.prayerTimes.observe(viewLifecycleOwner) {

            val adapter = PrayerAdapter(it)
            binding.prayerRecyclerView.adapter = adapter

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}
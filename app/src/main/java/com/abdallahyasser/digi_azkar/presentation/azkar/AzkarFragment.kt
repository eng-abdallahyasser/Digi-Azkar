package com.abdallahyasser.digi_azkar.presentation.azkar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdallahyasser.digi_azkar.data.azkar.AzkarRepoImpl
import com.abdallahyasser.digi_azkar.databinding.FragmentAzkarBinding
import com.abdallahyasser.digi_azkar.domain.azkar.GetAzkarUseCase

class AzkarFragment : Fragment() {

    private var _binding: FragmentAzkarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AzkarViewModel by viewModels {
        val repo = AzkarRepoImpl()
        val useCase = GetAzkarUseCase(repo)
        ViewModelFactory(useCase)
    }

    private lateinit var categoryAdapter: ZekrCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAzkarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupRecyclerViews() {
        categoryAdapter = ZekrCategoryAdapter()
        binding.rvAzkarCategories.adapter = categoryAdapter
    }

    private fun observeViewModel() {
        viewModel.azkarCategories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.setData(categories)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

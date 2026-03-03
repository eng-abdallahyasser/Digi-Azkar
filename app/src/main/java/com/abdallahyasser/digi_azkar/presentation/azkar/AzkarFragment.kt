package com.abdallahyasser.digi_azkar.presentation.azkar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdallahyasser.digi_azkar.data.AzkarRepoImpl
import com.abdallahyasser.digi_azkar.databinding.FragmentAzkarBinding
import com.abdallahyasser.digi_azkar.domain.GetAzkarUseCase
import com.abdallahyasser.digi_azkar.domain.ZekrCategory
import com.abdallahyasser.digi_azkar.presentation.azkar.ViewModelFactory

class AzkarFragment : Fragment() {

    private var _binding: FragmentAzkarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AzkarViewModel by viewModels {
        val repo = AzkarRepoImpl(requireContext())
        val useCase = GetAzkarUseCase(repo)
        ViewModelFactory(useCase)
    }

    private lateinit var adapter: ZekrCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAzkarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = ZekrCategoryAdapter()
        binding.rvAzkarCategories.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.azkarCategories.observe(viewLifecycleOwner) { categories ->
            adapter.setData(categories)
        }
    }

//    override fun onDestroyView() {
//        super.onViewCreated(view, savedInstanceState)
//        _binding = null
//    }
}

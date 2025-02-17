package kldemo.chargestations.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kldemo.chargestations.R
import kldemo.chargestations.databinding.FragmentCitiesBinding

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private val citiesViewModel: CitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                citiesViewModel.citySelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        citiesViewModel.citiesViewState.observe(viewLifecycleOwner) { citiesViewState ->
            with(binding)
            {
                cities.isVisible = !citiesViewState.isLoading
                progressBar.isVisible = citiesViewState.isLoading
                if (!citiesViewState.isLoading &&
                    cities.adapter == null
                ) {
                    cities.adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.spinner_item,
                        citiesViewState.cities
                    )
                }
                cities.setSelection(citiesViewState.selectedPosition)
                showCityChargersBtn.isVisible = !citiesViewState.isLoading
            }
        }
        binding.showCityChargersBtn.setOnClickListener {
            citiesViewModel.showCityChargersClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CitiesFragment()
    }
}
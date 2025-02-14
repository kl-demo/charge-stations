package kldemo.chargestations.presentation.city_chargers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kldemo.chargestations.R
import kldemo.chargestations.databinding.FragmentCityChargersBinding

private const val ARG_CITY = "city"

@AndroidEntryPoint
class CityChargersFragment : Fragment() {

    private var _binding: FragmentCityChargersBinding? = null
    private val binding get() = _binding!!

    private val cityChargersViewModel: CityChargersViewModel by viewModels()
    private lateinit var chargersAdapter: ChargersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getString(ARG_CITY)?.let { city ->
                cityChargersViewModel.setCity(city)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityChargersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding)
        {
            chargersAdapter = ChargersAdapter()
            cityChargers.adapter = chargersAdapter
            cityChargers.layoutManager = LinearLayoutManager(context)
        }
        cityChargersViewModel.citiesViewState.observe(viewLifecycleOwner) { cityChargersViewState ->
            with(binding)
            {
                title.text = getString(R.string.city_chargers, cityChargersViewState.city)
                progressBar.isVisible = cityChargersViewState.isLoading
                cityChargers.isVisible = !cityChargersViewState.isLoading
                if (!cityChargersViewState.isLoading &&
                    chargersAdapter.itemCount == 0
                ) {
                    chargersAdapter.addAll(cityChargersViewState.chargers)
                    chargersAdapter.notifyDataSetChanged()
                }
            }
        }
        binding.backToCitiesBtn.setOnClickListener {
            cityChargersViewModel.backToCitiesClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        fun newInstance(city: String) =
            CityChargersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CITY, city)
                }
            }
    }
}
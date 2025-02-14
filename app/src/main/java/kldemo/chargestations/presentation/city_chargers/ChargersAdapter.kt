package kldemo.chargestations.presentation.city_chargers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kldemo.chargestations.R
import kldemo.chargestations.data.chargestation.model.Charger
import kldemo.chargestations.databinding.ChargerItemBinding

class ChargersAdapter :
    RecyclerView.Adapter<ChargersAdapter.ViewHolder>() {

    private val chargers: MutableList<Charger> = mutableListOf()

    class ViewHolder(val binding: ChargerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChargerItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val charger = chargers[position]

        with(holder.binding)
        {
            chargerContainer.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    if (charger.busy) R.color.red else R.color.green
                )
            )
            name.text = charger.name
            address.text = charger.address
        }

    }

    override fun getItemCount(): Int = chargers.size

    fun addAll(chargers: List<Charger>) {
        this.chargers.addAll(chargers)
    }
}
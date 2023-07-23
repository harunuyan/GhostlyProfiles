package com.volie.ghostlyprofiles.ui.fragment.generate_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.volie.ghostlyprofiles.data.model.Country
import com.volie.ghostlyprofiles.databinding.AdapterItemCheckboxBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseRVAdapter

class CheckboxCountryRVAdapter(
    val countryList: List<Country>,
    val onItemCheck: (country: Country) -> Unit
) :
    BaseRVAdapter<Country>(CountryDiffUtilCallBack()) {

    inner class CountryViewHolder(
        private val binding: AdapterItemCheckboxBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) {
            val country = countryList[position]

            binding.checkbox.text = country.name

            binding.checkbox.setOnClickListener {
                country.isSelected = binding.checkbox.isChecked
                onItemCheck(country)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            AdapterItemCheckboxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CountryViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Country, position: Int) {
        if (holder is CountryViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItem(position: Int): Country {
        return countryList.getOrNull(position) ?: Country("", "")
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}

class CountryDiffUtilCallBack : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}

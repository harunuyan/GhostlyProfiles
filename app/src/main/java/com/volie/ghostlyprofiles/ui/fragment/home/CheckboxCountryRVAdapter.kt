package com.volie.ghostlyprofiles.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.volie.ghostlyprofiles.data.model.Country
import com.volie.ghostlyprofiles.databinding.AdapterItemCheckboxBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseRVAdapter

class CheckboxCountryRVAdapter(
    val onItemCheck: (country: Country) -> Unit
) :
    BaseRVAdapter<Country>(CountryDiffUtilCallBack()) {

    inner class CountryViewHolder(
        private val binding: AdapterItemCheckboxBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) {
            val country = currentList[position]

            binding.checkbox.text = country.name
            binding.checkbox.isChecked = country.isSelected

            binding.checkbox.setOnClickListener {
                //country.isSelected = binding.checkbox.isChecked
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
        return currentList.getOrNull(position) ?: Country("", "")
    }

    override fun getItemCount(): Int {
        return currentList.size
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

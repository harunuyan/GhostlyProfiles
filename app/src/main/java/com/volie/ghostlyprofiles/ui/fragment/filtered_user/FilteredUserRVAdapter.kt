package com.volie.ghostlyprofiles.ui.fragment.filtered_user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.databinding.ItemRandomUserBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseRVAdapter

class FilteredUserRVAdapter : BaseRVAdapter<User>(FilteredUserDiffCallback()) {

    inner class FilteredUserViewHolder(private val binding: ItemRandomUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val user = currentList[position]

            if (user.picture.large.isNullOrEmpty()) {
                binding.ivPictureItem.visibility = View.GONE
            } else {
                binding.ivPictureItem.load(user.picture.large)
            }

            if (user.name.first.isNullOrEmpty() || user.name.last.isNullOrEmpty()) {
                binding.tvNameItem.visibility = View.GONE
            } else {
                binding.tvNameItem.text = "${user.name.first} ${user.name.last}"
            }

            if (user.email.isNullOrEmpty()) {
                binding.tvEmailItem.visibility = View.GONE
                binding.ivMailIcon.visibility = View.GONE
            } else {
                binding.tvEmailItem.text = user.email
            }

            if (user.phone.isNullOrEmpty()) {
                binding.tvPhoneItem.visibility = View.GONE
                binding.ivPhoneIcon.visibility = View.GONE
            } else {
                binding.tvPhoneItem.text = user.phone
            }

            if (user.nat.isNullOrEmpty()) {
                binding.ivFlagItem.visibility = View.GONE
            } else {
                with(binding.ivFlagItem) {
                    when (user.nat) {
                        "AU" -> load("https://flagpedia.net/data/flags/w702/au.webp")
                        "BR" -> load("https://flagpedia.net/data/flags/w702/br.webp")
                        "CA" -> load("https://flagpedia.net/data/flags/w702/ca.webp")
                        "CH" -> load("https://flagpedia.net/data/flags/w702/ch.webp")
                        "DE" -> load("https://flagpedia.net/data/flags/w702/de.webp")
                        "DK" -> load("https://flagpedia.net/data/flags/w702/dk.webp")
                        "ES" -> load("https://flagpedia.net/data/flags/w702/es.webp")
                        "FI" -> load("https://flagpedia.net/data/flags/w702/fi.webp")
                        "FR" -> load("https://flagpedia.net/data/flags/w702/fr.webp")
                        "GB" -> load("https://flagpedia.net/data/flags/w702/gb.webp")
                        "IE" -> load("https://flagpedia.net/data/flags/w702/ie.webp")
                        "IN" -> load("https://flagpedia.net/data/flags/w702/in.webp")
                        "IR" -> load("https://flagpedia.net/data/flags/w702/ir.webp")
                        "MX" -> load("https://flagpedia.net/data/flags/w702/mx.webp")
                        "NL" -> load("https://flagpedia.net/data/flags/w702/nl.webp")
                        "NO" -> load("https://flagpedia.net/data/flags/w702/no.webp")
                        "NZ" -> load("https://flagpedia.net/data/flags/w702/nz.webp")
                        "RS" -> load("https://flagpedia.net/data/flags/w702/rs.webp")
                        "TR" -> load("https://flagpedia.net/data/flags/w702/tr.webp")
                        "UA" -> load("https://flagpedia.net/data/flags/w702/ua.webp")
                        "US" -> load("https://flagpedia.net/data/flags/w702/us.webp")
                        else -> visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemRandomUserBinding.inflate(inflater, parent, false)
        return FilteredUserViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: User, position: Int) {
        if (holder is FilteredUserViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class FilteredUserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

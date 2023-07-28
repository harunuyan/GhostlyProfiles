package com.volie.ghostlyprofiles.ui.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.volie.ghostlyprofiles.R
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.databinding.AdapterItemRandomUserBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseRVAdapter

class HomeRVAdapter(
    val onItemClick: (User) -> Unit,
    val onFavClick: (user: User, position: Int) -> Unit
) : BaseRVAdapter<User>(UserDetailsItemCallback()) {

    inner class UserDetailsViewHolder(private val binding: AdapterItemRandomUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(currentList[adapterPosition])
            }

            binding.ivLikeItem.setOnClickListener {
                onFavClick(currentList[adapterPosition], adapterPosition)
            }
        }

        fun bind(position: Int) {
            val user = currentList[position]
            binding.ivPictureItem.load(user.picture.large)
            binding.tvNameItem.text = "${user.name.first} ${user.name.last}"
            binding.tvEmailItem.text = user.email
            binding.tvPhoneItem.text = user.phone

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

            if (user.isLiked) {
                binding.ivLikeItem.setImageResource(R.drawable.ic_liked)
            } else {
                binding.ivLikeItem.setImageResource(R.drawable.ic_like)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemRandomUserBinding.inflate(inflater, parent, false)
        return UserDetailsViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: User, position: Int) {
        if (holder is UserDetailsViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class UserDetailsItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

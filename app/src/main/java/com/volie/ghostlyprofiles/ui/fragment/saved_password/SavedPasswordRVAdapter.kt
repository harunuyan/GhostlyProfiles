package com.volie.ghostlyprofiles.ui.fragment.saved_password

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.databinding.AdapterItemSavedPasswordBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseRVAdapter

class SavedPasswordRVAdapter :
    BaseRVAdapter<Login>(SavedPasswordCallback()) {

    inner class SavedPasswordViewHolder(private val binding: AdapterItemSavedPasswordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val password = currentList[position]

            binding.tvPassword.text = password.password
            binding.tvPasswordName.text = password.name
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemSavedPasswordBinding.inflate(inflater, parent, false)
        return SavedPasswordViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Login, position: Int) {
        if (holder is SavedPasswordViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class SavedPasswordCallback : DiffUtil.ItemCallback<Login>() {
    override fun areItemsTheSame(oldItem: Login, newItem: Login): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Login, newItem: Login): Boolean {
        return oldItem == newItem
    }
}

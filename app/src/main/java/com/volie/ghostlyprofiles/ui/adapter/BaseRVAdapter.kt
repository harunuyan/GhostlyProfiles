package com.volie.ghostlyprofiles.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRVAdapter<T>(
    diffCallBack: DiffUtil.ItemCallback<T>
) : ListAdapter<T, RecyclerView.ViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return createViewHolder(parent, inflater, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        bindViewHolder(holder, item, position)
    }

    protected abstract fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder

    protected abstract fun bindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: T,
        position: Int
    )
}
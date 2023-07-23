package com.volie.ghostlyprofiles.ui.fragment.generate_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.volie.ghostlyprofiles.data.model.Field
import com.volie.ghostlyprofiles.databinding.AdapterItemCheckboxBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseRVAdapter

class CheckboxFieldRVAdapter(
    val fieldList: List<Field>,
    val onItemCheck: (field: Field) -> Unit
) :
    BaseRVAdapter<Field>(FieldDiffUtilCallBack()) {

    inner class FieldViewHolder(private val binding: AdapterItemCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val field = fieldList[position]

            binding.checkbox.text = field.fieldName

            binding.checkbox.setOnClickListener {
                field.isSelected = binding.checkbox.isChecked
                onItemCheck(field)
            }
        }
    }

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = AdapterItemCheckboxBinding.inflate(inflater, parent, false)
        return FieldViewHolder(binding)
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Field, position: Int) {
        if (holder is FieldViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItem(position: Int): Field {
        return fieldList.getOrNull(position) ?: Field("")
    }

    override fun getItemCount(): Int {
        return fieldList.size
    }
}

class FieldDiffUtilCallBack : DiffUtil.ItemCallback<Field>() {
    override fun areItemsTheSame(oldItem: Field, newItem: Field): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Field, newItem: Field): Boolean {
        return oldItem == newItem
    }
}

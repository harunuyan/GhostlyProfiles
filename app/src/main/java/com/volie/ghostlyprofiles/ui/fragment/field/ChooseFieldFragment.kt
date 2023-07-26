package com.volie.ghostlyprofiles.ui.fragment.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.data.model.Field
import com.volie.ghostlyprofiles.databinding.FragmentChooseFieldBinding

class ChooseFieldFragment : Fragment() {
    private var _mBinding: FragmentChooseFieldBinding? = null
    private val mBinding get() = _mBinding!!
    private val mAdapter: CheckboxFieldRVAdapter by lazy {
        CheckboxFieldRVAdapter(fieldList) { selectedField ->
            if (selectedField.isSelected) {
                selectedFields.add(selectedField)
            } else {
                selectedFields.remove(selectedField)
            }

        }
    }

    private val fieldList = ArrayList<Field>()
    private val selectedFields = ArrayList<Field>(emptyList())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentChooseFieldBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvFields.adapter = mAdapter

        addFieldsToList()

        mBinding.switchAllFields.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mBinding.rvFields.visibility = View.GONE
                selectedFields.add(Field(fieldName = ""))
            } else {
                mBinding.rvFields.visibility = View.VISIBLE
            }
        }

        mBinding.ivSelectedFields.setOnClickListener {

        }
    }

    private fun addFieldsToList() {
        with(fieldList) {
            add(Field(fieldName = "gender"))
            add(Field(fieldName = "name"))
            add(Field(fieldName = "location"))
            add(Field(fieldName = "email"))
            add(Field(fieldName = "login"))
            add(Field(fieldName = "registered"))
            add(Field(fieldName = "dob"))
            add(Field(fieldName = "phone"))
            add(Field(fieldName = "cell"))
            add(Field(fieldName = "id"))
            add(Field(fieldName = "picture"))
            add(Field(fieldName = "nat"))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
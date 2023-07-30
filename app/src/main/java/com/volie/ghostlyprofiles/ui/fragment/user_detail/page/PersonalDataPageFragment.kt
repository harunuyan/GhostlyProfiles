package com.volie.ghostlyprofiles.ui.fragment.user_detail.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.databinding.FragmentPersonelDataBinding
import com.volie.ghostlyprofiles.util.DateManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalDataPageFragment(private val personal: User) : Fragment() {
    private var _mBinding: FragmentPersonelDataBinding? = null
    private val mBinding get() = _mBinding!!
    private val dateFormat = DateManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPersonelDataBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            tvFullName.text = "${personal.name.first} ${personal.name.last}"
            tvName.text = personal.name.first
            tvSurname.text = personal.name.last
            tvAge.text = personal.dob.age.toString()
            tvDob.text = dateFormat.formatDate(personal.dob.date)
            tvGender.text = personal.gender
            tvNationality.text = personal.nat
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
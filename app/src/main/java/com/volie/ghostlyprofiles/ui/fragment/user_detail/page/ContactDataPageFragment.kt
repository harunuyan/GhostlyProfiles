package com.volie.ghostlyprofiles.ui.fragment.user_detail.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.databinding.FragmentContactDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDataPageFragment(private val contact: User) : Fragment() {
    private var _mBinding: FragmentContactDataBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentContactDataBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            tvUsername.text = contact.login.username
            tvEmail.text = contact.email
            tvPhone.text = contact.phone
            tvCell.text = contact.cell
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
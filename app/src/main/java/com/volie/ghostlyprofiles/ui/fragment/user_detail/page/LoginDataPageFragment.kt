package com.volie.ghostlyprofiles.ui.fragment.user_detail.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.databinding.FragmentLoginDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginDataPageFragment : Fragment() {
    private var _mBinding: FragmentLoginDataBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentLoginDataBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
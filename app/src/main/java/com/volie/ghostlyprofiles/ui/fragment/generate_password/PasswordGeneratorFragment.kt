package com.volie.ghostlyprofiles.ui.fragment.generate_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.databinding.FragmentPasswordGeneratorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordGeneratorFragment : Fragment() {
    private var _mBinding: FragmentPasswordGeneratorBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPasswordGeneratorBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
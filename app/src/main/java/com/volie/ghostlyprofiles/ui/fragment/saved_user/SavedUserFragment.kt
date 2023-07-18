package com.volie.ghostlyprofiles.ui.fragment.saved_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.databinding.FragmentSavedUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedUserFragment : Fragment() {
    private var _mBinding: FragmentSavedUserBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSavedUserBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
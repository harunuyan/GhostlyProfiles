package com.volie.ghostlyprofiles.ui.fragment.generate_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.databinding.FragmentCreateUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUserFragment : Fragment() {
    private var _mBinding: FragmentCreateUserBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentCreateUserBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.switchAllFields.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mBinding.gridFields.visibility = View.GONE
            } else {
                mBinding.gridFields.visibility = View.VISIBLE
            }
        }

        mBinding.switchAllNationalities.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mBinding.gridNationalities.visibility = View.GONE
            } else {
                mBinding.gridNationalities.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
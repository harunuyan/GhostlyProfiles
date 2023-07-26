package com.volie.ghostlyprofiles.ui.fragment.user_detail.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.databinding.FragmentLoginDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginDataPageFragment(private val login: User) : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            tvUsername.text = login.login.username
            tvEmail.text = login.email
            tvPassword.text = login.login.password
            tvUuid.text = login.login.uuid
            tvMd5.text = login.login.md5
            tvSha1.text = login.login.sha1
            tvSha256.text = login.login.sha256
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
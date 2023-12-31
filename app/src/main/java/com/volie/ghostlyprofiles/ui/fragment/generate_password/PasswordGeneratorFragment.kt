package com.volie.ghostlyprofiles.ui.fragment.generate_password

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.ghostlyprofiles.R
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.databinding.FragmentPasswordGeneratorBinding
import com.volie.ghostlyprofiles.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordGeneratorFragment : Fragment() {
    private var _mBinding: FragmentPasswordGeneratorBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: PasswordGeneratorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPasswordGeneratorBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(mBinding) {

            ivSavedPassword.setOnClickListener {
                val action =
                    PasswordGeneratorFragmentDirections.actionPasswordGeneratorFragmentToSavedPasswordFragment()
                findNavController().navigate(action)
            }

            btnGenerate.setOnClickListener {

                val isUpperCase = cbUpperCase.isChecked
                val isLowerCase = cbLowerCase.isChecked
                val isNumeric = cbNumeric.isChecked
                val isSpecial = cbSpecial.isChecked
                val passwordLength = sliderPasswordLength.value.toInt()

                mViewModel.generatePassword(
                    isUppercase = isUpperCase,
                    isLowerCase = isLowerCase,
                    isNumeric = isNumeric,
                    isSpecial = isSpecial,
                    passwordLength = passwordLength
                )

                observeLiveData()
            }

            btnRegenerate.setOnClickListener {

                val isUpperCase = cbUpperCase.isChecked
                val isLowerCase = cbLowerCase.isChecked
                val isNumeric = cbNumeric.isChecked
                val isSpecial = cbSpecial.isChecked
                val passwordLength = sliderPasswordLength.value.toInt()

                mViewModel.generatePassword(
                    isUppercase = isUpperCase,
                    isLowerCase = isLowerCase,
                    isNumeric = isNumeric,
                    isSpecial = isSpecial,
                    passwordLength = passwordLength
                )

                observeLiveData()
            }

            ivBack.setOnClickListener {
                showPasswordOptions()
            }

            btnSavePassword.setOnClickListener {
                if (etMyPassword.text.isNullOrEmpty()) {
                    val redColor = ContextCompat.getColor(requireContext(), R.color.red)
                    etMyPassword.backgroundTintList = ColorStateList.valueOf(redColor)
                    tvPasswordName.visibility = View.VISIBLE
                    etMyPassword.setHintTextColor(ColorStateList.valueOf(redColor))
                } else {
                    val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                    etMyPassword.backgroundTintList = ColorStateList.valueOf(whiteColor)
                    tvPasswordName.visibility = View.GONE
                    etMyPassword.setHintTextColor(ColorStateList.valueOf(whiteColor))
                    savePassword()
                }
            }
        }
    }

    private fun savePassword() {
        val password = mBinding.tvPasswordGenerated.text.toString()
        val passwordName = mBinding.etMyPassword.text.toString()

        val loginData = Login(
            name = passwordName,
            password = password
        )

        mViewModel.savePassword(loginData)
    }

    private fun showPasswordOptions() {
        mBinding.btnGenerate.visibility = View.VISIBLE
        mBinding.sliderPasswordLength.visibility = View.VISIBLE
        mBinding.tvPasswordLength.visibility = View.VISIBLE
        mBinding.llPasswordOptions.visibility = View.VISIBLE
        mBinding.llPasswordGenerated.visibility = View.GONE
    }

    private fun showPasswordGenerated() {
        mBinding.btnGenerate.visibility = View.GONE
        mBinding.sliderPasswordLength.visibility = View.GONE
        mBinding.tvPasswordLength.visibility = View.GONE
        mBinding.llPasswordOptions.visibility = View.GONE
        mBinding.llPasswordGenerated.visibility = View.VISIBLE
    }

    private fun observeLiveData() {
        mViewModel.password.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    showPasswordGenerated()
                    mBinding.tvPasswordGenerated.text =
                        it.data?.results?.get(0)?.login?.password ?: ""
                }

                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
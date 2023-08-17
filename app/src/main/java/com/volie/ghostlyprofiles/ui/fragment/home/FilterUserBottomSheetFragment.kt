package com.volie.ghostlyprofiles.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.volie.ghostlyprofiles.R
import com.volie.ghostlyprofiles.data.model.Country
import com.volie.ghostlyprofiles.data.model.Field
import com.volie.ghostlyprofiles.databinding.FragmentBottomSheetFilterUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterUserBottomSheetFragment : BottomSheetDialogFragment() {
    private var _mBinding: FragmentBottomSheetFilterUserBinding? = null
    private val mBinding get() = _mBinding!!
    private val mArgs: FilterUserBottomSheetFragmentArgs by navArgs()
    private val mAdapter: CheckboxCountryRVAdapter by lazy {
        CheckboxCountryRVAdapter(
            onItemCheck = { selectedCountry ->
                selectedCountry.isSelected = !selectedCountry.isSelected
                natString = mAdapter.currentList
                    .filter { it.isSelected }
                    .map { it.countryCode }
                    .joinToString(separator = ",")
                setFragmentResult(
                    "userNat", bundleOf(
                        "userNat" to natString
                    )
                )
            }
        )
    }

    private var gender = Field(selectedGender = "")
    private val nat = Country(countryCode = "")

    private var natString: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentBottomSheetFilterUserBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val countries = mArgs.country
            val selectedNatsStrings = mArgs.selectedNats.orEmpty().split(",")
            selectedNatsStrings.forEach { selectedNat ->
                countries.find { it.countryCode == selectedNat }?.let {
                    it.isSelected = true
                }
            }
            mAdapter.submitList(countries.toList())
            gender = Field(mArgs.selectedGender.orEmpty())
            natString = mArgs.selectedNats.orEmpty()
        }

        mBinding.radioGroupGenders.check(
            when (gender.selectedGender) {
                "male" -> R.id.rb_male
                "female" -> R.id.rb_female
                else -> R.id.rb_both
            }
        )

        mBinding.rvNationalities.adapter = mAdapter

        mBinding.radioGroupGenders.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.rb_male -> {
                    setFragmentResult(
                        "userGender", bundleOf(
                            "userGender" to "male"
                        )
                    )
                    gender.selectedGender = "male"
                    gender.isSelected = true
                    mBinding.rbMale.isChecked = true
                    mBinding.rbFemale.isChecked = false
                    mBinding.rbBoth.isChecked = false
                }

                R.id.rb_female -> {
                    setFragmentResult(
                        "userGender", bundleOf(
                            "userGender" to "female"
                        )
                    )
                    gender.selectedGender = "female"
                    gender.isSelected = true
                    mBinding.rbMale.isChecked = false
                    mBinding.rbFemale.isChecked = true
                    mBinding.rbBoth.isChecked = false
                }

                R.id.rb_both -> {
                    setFragmentResult(
                        "userGender", bundleOf(
                            "userGender" to ""
                        )
                    )
                    gender.selectedGender = ""
                    gender.isSelected = true
                    mBinding.rbMale.isChecked = false
                    mBinding.rbFemale.isChecked = false
                    mBinding.rbBoth.isChecked = true
                }
            }
        }

        mBinding.switchAllNationalities.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mBinding.rvNationalities.visibility = View.GONE
                natString = ""
                setFragmentResult(
                    "userNat", bundleOf(
                        "userNat" to ""
                    )
                )
            } else {
                mBinding.rvNationalities.visibility = View.VISIBLE
                nat.countryCode = natString
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
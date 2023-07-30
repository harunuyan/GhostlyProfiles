package com.volie.ghostlyprofiles.ui.fragment.generate_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.R
import com.volie.ghostlyprofiles.data.model.Country
import com.volie.ghostlyprofiles.databinding.FragmentCreateUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUserFragment : Fragment() {
    private var _mBinding: FragmentCreateUserBinding? = null
    private val mBinding get() = _mBinding!!
    private val mAdapter: CheckboxCountryRVAdapter by lazy {
        CheckboxCountryRVAdapter(countryList) { selectedCountry ->
            if (selectedCountry.isSelected) {
                selectedCountries.add(selectedCountry)
            } else {
                selectedCountries.remove(selectedCountry)
            }
        }
    }
    private var selectedGender = ""
    private var selectedCountries = ArrayList<Country>()
    private val countryList = ArrayList<Country>()

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

        mBinding.rvNationalities.adapter = mAdapter

        addCountriesToList()

        mBinding.radioGroupGenders.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_male -> {
                    selectedGender = ""
                    selectedGender = "male"
                }

                R.id.rb_female -> {
                    selectedGender = ""
                    selectedGender = "female"
                }

                R.id.rb_both -> selectedGender = ""
            }
        }

        mBinding.switchAllNationalities.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mBinding.rvNationalities.visibility = View.GONE
                selectedCountries.add(Country(name = "", countryCode = ""))
            } else {
                mBinding.rvNationalities.visibility = View.VISIBLE
            }
        }
    }

    private fun addCountriesToList() {
        with(countryList) {
            add(Country(name = "Australia", countryCode = "AU"))
            add(Country(name = "Brazil", countryCode = "BR"))
            add(Country(name = "Canada", countryCode = "CA"))
            add(Country(name = "Switzerland", countryCode = "CH"))
            add(Country(name = "Germany", countryCode = "DE"))
            add(Country(name = "Denmark", countryCode = "DK"))
            add(Country(name = "Spain", countryCode = "ES"))
            add(Country(name = "Finland", countryCode = "FI"))
            add(Country(name = "France", countryCode = "FR"))
            add(Country(name = "United Kingdom", countryCode = "GB"))
            add(Country(name = "Ireland", countryCode = "IE"))
            add(Country(name = "India", countryCode = "IN"))
            add(Country(name = "Iran", countryCode = "IR"))
            add(Country(name = "Mexico", countryCode = "MX"))
            add(Country(name = "Netherlands", countryCode = "NL"))
            add(Country(name = "Norway", countryCode = "NO"))
            add(Country(name = "New Zealand", countryCode = "NZ"))
            add(Country(name = "Serbia", countryCode = "RS"))
            add(Country(name = "Turkey", countryCode = "TR"))
            add(Country(name = "Ukraine", countryCode = "UA"))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
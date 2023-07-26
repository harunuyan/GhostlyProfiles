package com.volie.ghostlyprofiles.ui.fragment.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.ghostlyprofiles.R
import com.volie.ghostlyprofiles.databinding.FragmentUserDetailsBinding
import com.volie.ghostlyprofiles.ui.adapter.BaseViewPagerAdapter
import com.volie.ghostlyprofiles.ui.fragment.user_detail.page.ContactDataPageFragment
import com.volie.ghostlyprofiles.ui.fragment.user_detail.page.LocationDataPageFragment
import com.volie.ghostlyprofiles.ui.fragment.user_detail.page.LoginDataPageFragment
import com.volie.ghostlyprofiles.ui.fragment.user_detail.page.PersonalDataPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private var _mBinding: FragmentUserDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val pages = ArrayList<Fragment>()
    private val mArgs: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.tvUserTitle.text = "${mArgs.user.name.title} ${mArgs.user.name.last}"
        mBinding.ivUserPicture.load(mArgs.user.picture.large)

        mBinding.ivBack.setOnClickListener { findNavController().navigateUp() }

        setupViewPager()
    }

    private fun setupViewPager() {

        with(pages) {
            with(mArgs.user) {
                add(PersonalDataPageFragment(this))
                add(ContactDataPageFragment(this))
                add(LocationDataPageFragment(this.location))
                add(LoginDataPageFragment(this))
            }
        }

        val adapter = BaseViewPagerAdapter(pages, requireActivity())
        mBinding.viewPagerDetails.adapter = adapter

        TabLayoutMediator(
            mBinding.tabLayoutDetails,
            mBinding.viewPagerDetails
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.personal)
                1 -> tab.text = getString(R.string.contact)
                2 -> tab.text = getString(R.string.location)
                3 -> tab.text = getString(R.string.login)
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
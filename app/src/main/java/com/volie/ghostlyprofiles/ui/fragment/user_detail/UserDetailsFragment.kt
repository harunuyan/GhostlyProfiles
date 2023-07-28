package com.volie.ghostlyprofiles.ui.fragment.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    private val mViewModel: UserDetailsViewModel by viewModels()

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

        mBinding.ivBack.setOnClickListener { findNavController().navigateUp() }

        mBinding.ivLike.setOnClickListener {
            if (!mArgs.user.isLiked) {
                mArgs.user.isLiked = true
                mBinding.ivLike.setImageResource(R.drawable.ic_liked)
            } else {
                mArgs.user.isLiked = false
                mBinding.ivLike.setImageResource(R.drawable.ic_like)
            }
        }


        setupViewPager()
        getUserDetails()
    }

    private fun getUserDetails() {
        with(mBinding) {
            tvUserTitle.text = "${mArgs.user.name.title} ${mArgs.user.name.last}"
            ivUserPicture.load(mArgs.user.picture.large)

            if (mArgs.user.isLiked) {
                ivLike.setImageResource(R.drawable.ic_liked)
                mViewModel.insertUser(mArgs.user)
            } else {
                ivLike.setImageResource(R.drawable.ic_like)
                mViewModel.deleteUser(mArgs.user)
            }
        }
    }

    private fun setupViewPager() {

        val tabIcons = listOf(
            R.drawable.ic_person,
            R.drawable.ic_phone,
            R.drawable.ic_location_marker,
            R.drawable.ic_password_tab
        )

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
            tab.icon = ContextCompat.getDrawable(requireContext(), tabIcons[position])
        }.attach()
    }


    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
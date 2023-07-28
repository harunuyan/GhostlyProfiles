package com.volie.ghostlyprofiles.ui.fragment.saved_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.volie.ghostlyprofiles.databinding.FragmentSavedUserBinding
import com.volie.ghostlyprofiles.ui.fragment.home.HomeRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedUserFragment : Fragment() {
    private var _mBinding: FragmentSavedUserBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: SavedUserViewModel by viewModels()
    private val mAdapter: HomeRVAdapter by lazy {
        HomeRVAdapter(
            onItemClick = {
                val action =
                    SavedUserFragmentDirections.actionSavedUserFragmentToUserDetailsFragment(it)
                findNavController().navigate(action)
            },
            onFavClick = { user, position ->
                if (!user.isLiked) {
                    user.isLiked = true
                    mViewModel.insertUser(user)
                } else {
                    user.isLiked = false
                    mViewModel.deleteUser(user)
                }
                mAdapter.notifyItemChanged(position)
                observeLiveData()
                mViewModel.getSavedUsers()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSavedUserBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvSavedUsers.adapter = mAdapter
        observeLiveData()
        mViewModel.getSavedUsers()
    }

    private fun observeLiveData() {
        mViewModel.savedUsers.observe(viewLifecycleOwner) {
            if (mViewModel.savedUsers.value.isNullOrEmpty()) {
                mBinding.rvSavedUsers.visibility = View.GONE
                mBinding.ivEmptyStorage.visibility = View.VISIBLE
            } else {
                mBinding.rvSavedUsers.visibility = View.VISIBLE
                mBinding.ivEmptyStorage.visibility = View.GONE
            }
            mAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
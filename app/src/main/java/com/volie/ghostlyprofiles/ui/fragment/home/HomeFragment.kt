package com.volie.ghostlyprofiles.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.volie.ghostlyprofiles.R
import com.volie.ghostlyprofiles.databinding.FragmentHomeBinding
import com.volie.ghostlyprofiles.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _mBinding: FragmentHomeBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: HomeViewModel by viewModels()
    private val mAdapter: HomeRVAdapter by lazy {
        HomeRVAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvFeed.adapter = mAdapter
        mViewModel.getRandomUsers()
        observeLiveData()
        pullToRefresh()
    }

    private fun pullToRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener {
            mBinding.swipeRefresh.isRefreshing = false
            mViewModel.getRandomUsers()
        }
    }

    private fun observeLiveData() {
        mViewModel.users.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.rvFeed.visibility = View.VISIBLE
                    mBinding.tvTitle.text = getString(R.string.users)
                    resource.data?.let {
                        mAdapter.submitList(it.results)
                    }
                }

                Status.ERROR -> {
                    mBinding.rvFeed.visibility = View.GONE
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.tvTitle.text =
                        getString(R.string.please_check_your_internet_connection)
                }

                Status.LOADING -> {
                    mBinding.rvFeed.visibility = View.GONE
                    mBinding.tvTitle.text = getString(R.string.users)
                    mBinding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
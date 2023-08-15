package com.volie.ghostlyprofiles.ui.fragment.saved_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.volie.ghostlyprofiles.databinding.FragmentSavedPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedPasswordFragment : Fragment() {
    private var _mBinding: FragmentSavedPasswordBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: SavedPasswordViewModel by viewModels()
    private val mAdapter: SavedPasswordRVAdapter by lazy {
        SavedPasswordRVAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSavedPasswordBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvSavedPasswords.adapter = mAdapter

        swipeToDelete()

        mBinding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        observeLiveData()
        mViewModel.getSavedPasswords()
    }

    private fun swipeToDelete() {
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val password = mAdapter.currentList[position]
                    mViewModel.deletePassword(password)
                    observeLiveData()
                    mViewModel.getSavedPasswords()
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(mBinding.rvSavedPasswords)
    }

    private fun observeLiveData() {
        mViewModel.savedPassword.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
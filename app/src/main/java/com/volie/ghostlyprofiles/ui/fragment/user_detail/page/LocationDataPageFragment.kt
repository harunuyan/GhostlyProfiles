package com.volie.ghostlyprofiles.ui.fragment.user_detail.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.ghostlyprofiles.data.model.Location
import com.volie.ghostlyprofiles.databinding.FragmentLocationDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDataPageFragment(private val location: Location) : Fragment() {
    private var _mBinding: FragmentLocationDataBinding? = null
    private val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentLocationDataBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            tvCountry.text = location.country
            tvRegion.text = location.city
            tvPostalCode.text = location.postcode
            tvStreetAddress.text = "${location.street.name} ${location.street.number}"
            tvLatitudeLongitude.text =
                "${location.coordinates.latitude} / ${location.coordinates.longitude}"
            tvTimeZone.text = "${location.timezone.offset} ${location.timezone.description}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}
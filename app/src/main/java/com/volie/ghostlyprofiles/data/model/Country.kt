package com.volie.ghostlyprofiles.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String? = null,
    var countryCode: String,
    var isSelected: Boolean = false
) : Parcelable

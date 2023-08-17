package com.volie.ghostlyprofiles.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Field(
    var selectedGender: String,
    var isSelected: Boolean = false
) : Parcelable
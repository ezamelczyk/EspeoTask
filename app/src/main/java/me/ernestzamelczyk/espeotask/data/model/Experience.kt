package me.ernestzamelczyk.espeotask.data.model

import android.support.annotation.DrawableRes

data class Experience(
        val companyName: String,
        val startDate: String,
        val endDate: String,
        val description: String,
        @DrawableRes val logo: Int
)
package me.ernestzamelczyk.espeotask.data.model

import android.support.annotation.DrawableRes

data class Education(
        val schoolName: String,
        val degree: String,
        val field: String,
        val startDate: String,
        val endDate: String,
        @DrawableRes val logo: Int? = null
): AbstractEducationItem()
package me.ernestzamelczyk.espeotask.data.model

import android.support.annotation.DrawableRes

data class ShowCaseApp(
        val name: String,
        val description: String,
        val playStoreUrl: String,
        @DrawableRes val image: Int
)
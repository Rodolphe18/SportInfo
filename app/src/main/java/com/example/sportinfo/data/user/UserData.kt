package com.example.sportinfo.data.user

import com.example.sportinfo.data.user.DarkThemeConfig
import com.example.sportinfo.data.user.ThemeBrand

/**
 * Class summarizing user interest data
 */
data class UserData(
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
)
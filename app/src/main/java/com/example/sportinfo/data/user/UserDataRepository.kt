package com.example.sportinfo.data.user

import com.example.sportinfo.data.user.DarkThemeConfig
import com.example.sportinfo.data.user.ThemeBrand
import com.example.sportinfo.data.user.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    /**
     * Sets the desired theme brand.
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

}
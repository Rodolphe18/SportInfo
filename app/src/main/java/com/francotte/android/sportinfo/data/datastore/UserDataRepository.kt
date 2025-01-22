package com.francotte.android.sportinfo.data.datastore

import com.francotte.android.sportinfo.data.datastore.DarkThemeConfig
import com.francotte.android.sportinfo.data.datastore.ThemeBrand
import com.francotte.android.sportinfo.data.datastore.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

}
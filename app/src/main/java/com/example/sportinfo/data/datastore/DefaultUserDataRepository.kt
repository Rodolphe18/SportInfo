package com.example.sportinfo.data.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultUserDataRepository @Inject constructor(private val preferencesDataSource: PreferencesDataSource): UserDataRepository {

   override val userData: Flow<UserData> = preferencesDataSource.userData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        preferencesDataSource.setThemeBrand(themeBrand)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
       preferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

}
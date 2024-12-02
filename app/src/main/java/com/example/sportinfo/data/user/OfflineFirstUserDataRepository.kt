/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sportinfo.data.user

import com.example.sportinfo.data.datastore.SportsPreferencesDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class OfflineFirstUserDataRepository @Inject constructor(
    private val sportsPreferencesDataSource: SportsPreferencesDataSource
) : UserDataRepository {

    override val userData: Flow<UserData> =
        sportsPreferencesDataSource.userData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) =
       sportsPreferencesDataSource.setThemeBrand(themeBrand)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) =
        sportsPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)


}

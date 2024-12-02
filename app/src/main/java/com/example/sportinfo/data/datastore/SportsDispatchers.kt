package com.example.sportinfo.data.datastore

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val sportsDispatcher: SportsDispatchers)

enum class SportsDispatchers {
    IO
}

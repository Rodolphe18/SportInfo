package com.example.sportinfo.util

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: SportInfoDispatchers)

enum class SportInfoDispatchers {
    Default,
    IO,
}
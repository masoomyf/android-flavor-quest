package com.celaenoapps.flavorquest.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

interface FqDispatchers {
    val io: CoroutineDispatcher
    val main: MainCoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

class DefaultFqDispatchers @Inject constructor() : FqDispatchers {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: MainCoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
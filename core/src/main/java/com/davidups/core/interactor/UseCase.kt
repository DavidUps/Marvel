package com.davidups.core.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<out Type, in Params> where Type : Any? {
    abstract fun run(params: Params? = null): Flow<Type>

    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ): Flow<Type> {
        return run(params).flowOn(Dispatchers.IO)
    }

    class None
}

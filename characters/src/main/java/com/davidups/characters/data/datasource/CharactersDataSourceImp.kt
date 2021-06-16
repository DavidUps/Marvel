package com.davidups.characters.data.datasource

import com.davidups.characters.data.local.CharacterLocal
import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.data.service.CharacterService
import com.davidups.core.exception.Failure
import com.davidups.core.extensions.orEmpty
import com.davidups.core.functional.Error
import com.davidups.core.functional.State
import com.davidups.core.functional.Success
import com.davidups.core.platform.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharactersDataSourceImp(
    private val networkHandler: NetworkHandler,
    private val service: CharacterService,
    private val local: CharacterLocal
) : CharactersDataSource {

    override fun getCharacters(fromPagination: Boolean) = flow {
        val local = local.getCharacters()
        if (local != null && !fromPagination)
            emit(Success(local.toCharacters().toCharactersView()))
        else
            emit(getCharactersFromService())
    }
        .catch { emit(Error(Failure.Throwable(it))) }
        .flowOn(Dispatchers.IO)

    private suspend fun getCharactersFromService(): State<CharactersView> {
        return if (networkHandler.isConnected.orEmpty()) {
            service.getCharacters(10, calculateOffset()).run {
                if (isSuccessful && body() != null) {
                    val data = body()!!.data
                    saveLocal(data)
                    Success(data.toCharacters().toCharactersView())
                } else {
                    Error(Failure.ServerError(code()))
                }
            }
        } else
            Error(Failure.NetworkConnection)
    }

    private fun calculateOffset() = local.getOffset()?.let { it + 10 }.orEmpty()

    private fun saveLocal(characters: CharactersEntity) {
        val localCache = local.getCharacters()
        if (localCache != null) {
            localCache.offset = localCache.offset?.plus(10)
            localCache.results?.addAll(characters.results.orEmpty())
            local.updateCharacters(localCache)
        } else {
            local.putCharacters(characters)
        }
    }
}

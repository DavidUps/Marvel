package com.davidups.marvel.domain.datasource

import com.davidups.marvel.data.local.CharacterLocal
import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.data.service.CharacterService
import com.davidups.marvel.exception.Failure
import com.davidups.marvel.extensions.orEmpty
import com.davidups.marvel.functional.Error
import com.davidups.marvel.functional.State
import com.davidups.marvel.functional.Success
import com.davidups.marvel.platform.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharactersDataSourceImp(
    private val networkHandler: NetworkHandler,
    private val service: CharacterService,
    private val local: CharacterLocal
) : CharactersDataSource {

    override fun getCharacters(offset: Int?, fromPagination: Boolean) = flow {
        val local = local.getCharacters()
        if (local != null && !fromPagination)
            emit(Success(local.toCharacters().toCharactersView()))
        else
            emit(getCharactersFromService(offset))
    }
        .catch { emit(Error(Failure.Throwable(it))) }
        .flowOn(Dispatchers.IO)

    private suspend fun getCharactersFromService(offset: Int?): State<CharactersView> {
        return if (networkHandler.isConnected.orEmpty()) {
            service.getCharacters(10, offset).run {
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

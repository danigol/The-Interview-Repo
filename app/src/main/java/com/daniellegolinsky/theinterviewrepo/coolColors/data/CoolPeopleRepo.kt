package com.daniellegolinsky.theinterviewrepo.coolColors.data

import android.util.Log
import com.daniellegolinsky.theinterviewrepo.coolColors.api.CoolTestApi
import com.daniellegolinsky.theinterviewrepo.coolColors.models.CoolPersonResponse
import com.daniellegolinsky.theinterviewrepo.di.ApplicationModule
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Named

class CoolPeopleRepo @Inject constructor(
    @Named(ApplicationModule.COOL_TEST_API) private val coolService: CoolTestApi
){

    private val coolListMutex = Mutex()
    private var cachedCoolList: List<CoolPersonResponse> = emptyList()

    suspend fun getCoolPeople(refreshList: Boolean = true): List<CoolPersonResponse> {
        if (refreshList || cachedCoolList.isEmpty()) {
            try { // TODO: This isn't very good error handling
                //       A much better (but more complex and longer venture I'll have to research a bit)
                //       would be creating a custom GSON Converter Factory that can take a custom Response<Data>
                //       This is what I'd do in a professional setting,
                //       so we could get response.data or response is Error -> throw response.error
                val responseData = coolService.getCoolData()
                coolListMutex.withLock {
                    cachedCoolList = responseData
                }
            } catch (e: Exception) {
                Log.e("ERROR in CoolPeopleRepo.getCoolPeople()", e.message ?: "")
                throw e
            }
        }
        return coolListMutex.withLock { cachedCoolList }
    }
}
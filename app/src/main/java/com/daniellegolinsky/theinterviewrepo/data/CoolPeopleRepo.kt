package com.daniellegolinsky.theinterviewrepo.data

import com.daniellegolinsky.theinterviewrepo.api.CoolTestApi
import com.daniellegolinsky.theinterviewrepo.api.models.CoolPersonResponse
import com.daniellegolinsky.theinterviewrepo.di.ApplicationModule
import javax.inject.Inject
import javax.inject.Named

class CoolPeopleRepo @Inject constructor(
    @Named(ApplicationModule.COOL_TEST_API) private val coolService: CoolTestApi
){

    suspend fun getCoolPeople(): List<CoolPersonResponse> {
        return coolService.getCoolData()
    }
}
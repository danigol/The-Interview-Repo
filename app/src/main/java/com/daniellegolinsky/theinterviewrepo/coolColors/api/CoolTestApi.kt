package com.daniellegolinsky.theinterviewrepo.coolColors.api

import com.daniellegolinsky.theinterviewrepo.coolColors.models.CoolPersonResponse
import retrofit2.http.GET

interface CoolTestApi {

    // Some mock data whipped up in a jiffy by https://mockaroo.com/
    @GET("danigol/cc35404a2e89cf2883e7cfc62a31c582/raw/bdff970a0884588318a23323f97784dd726557f2/coolTestData.json")
    suspend fun getCoolData(): List<CoolPersonResponse>
}
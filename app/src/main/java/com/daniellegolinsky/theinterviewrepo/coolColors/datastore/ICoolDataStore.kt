package com.daniellegolinsky.theinterviewrepo.coolColors.datastore

interface ICoolDataStore {
    suspend fun getLastSearchTerm(): String
    suspend fun setLastSearchTerm(term: String)
}
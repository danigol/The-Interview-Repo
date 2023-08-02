package com.daniellegolinsky.theinterviewrepo.datastore

interface ICoolDataStore {
    suspend fun getLastSearchTerm(): String
    suspend fun setLastSearchTerm(term: String)
}
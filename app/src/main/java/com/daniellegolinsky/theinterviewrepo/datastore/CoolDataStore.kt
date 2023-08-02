package com.daniellegolinsky.theinterviewrepo.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daniellegolinsky.theinterviewrepo.di.ApplicationModule
import kotlinx.coroutines.flow.catch
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Named

// It's really just a wrapper for the datastore with accessors
class CoolDataStore @Inject constructor (
    @Named(ApplicationModule.COOL_DATA_STORE) private val dataStore: DataStore<Preferences>
) : ICoolDataStore {

    val LAST_SEARCH_TERM = stringPreferencesKey("last_search_term_string")

    private val preferencesFlow = dataStore.data.catch {
        if (it is IOException) {
            Log.e("DATASTORE_ERROR", "Error retrieving settings datastore.", it)
        } else {
            throw it
        }
    }

    override suspend fun getLastSearchTerm(): String {
        return "todo"
    }

    override suspend fun setLastSearchTerm(term: String) {

    }

}
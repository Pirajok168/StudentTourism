package ru.android.studenttourism

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.shared.IAuthSettingsRepository
import ru.shared.MobileSdk
import ru.shared.core.datastore.IAuthSettingsRepository
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(
    application: Application
): AndroidViewModel(application) {
    private val dataStore: IAuthSettingsRepository = MobileSdk.IAuthSettingsRepository

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.token.collect{
                val shared = application.applicationContext.getSharedPreferences("tokenautn", MODE_PRIVATE)

                with(shared.edit()){
                    putString("token", it?.token.orEmpty())
                    apply()
                }
            }
        }

    }
}
package ru.shared.core.datastore

import android.content.Context
import model.PlatformConfiguration



class AndroidSharedFileSystem(private val context: Context): SharedFileSystem{

    override val path: String
        get() = context.filesDir.resolve(dataStoreFileName).absolutePath

}

actual fun fileSystem(platformConfiguration: PlatformConfiguration): SharedFileSystem = AndroidSharedFileSystem(platformConfiguration.androidContext)
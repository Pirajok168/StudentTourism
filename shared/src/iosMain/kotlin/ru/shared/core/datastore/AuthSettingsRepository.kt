package ru.shared.core.datastore

import model.PlatformConfiguration
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

class IosSharedFileSystem: SharedFileSystem{
    override val path: String
    get() {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }
}

actual fun fileSystem(platformConfiguration: PlatformConfiguration): SharedFileSystem = IosSharedFileSystem()


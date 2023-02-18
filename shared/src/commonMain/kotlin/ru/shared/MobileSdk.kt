package ru.shared

import model.PlatformConfiguration
import org.kodein.di.*
import ru.commons.feature.search.data.database.dormitoriesDatabasesModule
import ru.shared.core.datastore.IAuthSettingsRepository
import ru.shared.core.datastore.dataStoreModule
import ru.shared.core.ktor.domainApi
import ru.shared.feature.auth.data.IAuthRepository
import ru.shared.feature.auth.data.authModule
import ru.shared.feature.booking.data.IRepoBooking
import ru.shared.feature.booking.data.bookingModule
import ru.shared.feature.dormitories.data.dormitoriesModule
import ru.shared.feature.event.data.IRepoEvent
import ru.shared.feature.event.data.searchEventsModule
import ru.shared.feature.filters.data.IRepoFilters
import ru.shared.feature.filters.data.moduleFilters
import ru.shared.feature.home.data.IRepositoryHome
import ru.shared.feature.home.data.homeDormitoriesModule
import ru.shared.feature.labs.data.searchLabsModule
import ru.shared.feature.profile.data.IRepoProfile
import ru.shared.feature.profile.data.profileModule
import ru.shared.feature.seeInfoDormitories.data.IRepoGetInfo
import ru.shared.feature.seeInfoDormitories.data.infoModule
import ru.shared.feature.userReccomendations.data.IRepoUserRecommendation
import ru.shared.feature.userReccomendations.data.userRecommendationsModule
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object MobileSdk {
    private var _di: DirectDI? = null

    val di: DirectDI
        get() = requireNotNull(_di)

    fun init(configuration: PlatformConfiguration) {
        val platformConf = DI.Module("platformConf"){
            bind<PlatformConfiguration>() with singleton {
                configuration
            }
        }


        val direct = DI {
            importAll(
                platformConf,
                dormitoriesDatabasesModule,
                dormitoriesModule,
                moduleFilters,
                profileModule,
                userRecommendationsModule,
                dataStoreModule,
                domainApi,
                authModule,
                searchEventsModule,
                searchLabsModule,
                homeDormitoriesModule,
                infoModule,
                bookingModule,
            )
        }.direct

        _di = direct
    }
}

val MobileSdk.IAuthRepository: IAuthRepository
    get() = MobileSdk.di.instance()

val MobileSdk.ISearchDormitories: IRepositoryHome
    get() = MobileSdk.di.instance()

val MobileSdk.IRepoProfile: IRepoProfile
    get() = MobileSdk.di.instance()

val MobileSdk.IInfoDormitories: IRepoGetInfo
    get() = MobileSdk.di.instance()

val MobileSdk.IEventRepo: IRepoEvent
    get() = MobileSdk.di.instance()

val MobileSdk.IAuthSettingsRepository: IAuthSettingsRepository
    get() = MobileSdk.di.instance()


val MobileSdk.IRepoBooking: IRepoBooking
    get() = MobileSdk.di.instance()

val MobileSdk.IRepoFilters: IRepoFilters
    get() = MobileSdk.di.instance()

val MobileSdk.IRepoUserRecommendation: IRepoUserRecommendation
    get() = MobileSdk.di.instance()


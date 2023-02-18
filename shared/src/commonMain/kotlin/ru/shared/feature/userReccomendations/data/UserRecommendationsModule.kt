package ru.shared.feature.userReccomendations.data

import app.cash.sqldelight.ColumnAdapter
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.commons.feature.userrecommendation.data.database.*

internal val userRecommendationsModule = DI.Module("UserRecommendationsModule") {

    bind<IUserRecommendations>() with singleton {
        UserRecommendationsImpl(
            database = RecommendationTable(
                createDriver(instance()).sqlDriver,

                UserRecomendationsAdapter = UserRecomendations.Adapter(object :
                    ColumnAdapter<List<String>, String> {
                    override fun decode(databaseValue: String) =
                        if (databaseValue.isEmpty()) {
                            listOf()
                        } else {
                            databaseValue.split(",")
                        }

                    override fun encode(value: List<String>) = value.joinToString(separator = ",")
                })
            )
        )
    }

    bind<IRepoUserRecommendation>() with singleton {
        RepoUserRecommendation(
            dao = instance()
        )
    }
}
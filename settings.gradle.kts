pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StudentTourism"
include(":androidApp")
include(":shared")
include(":commons:core")
include(":commons:feature:search:sql")
include(":commons:feature:profile:sql")
include("commons:feature:userrecommendation:sql")

include(":feature:auth:presentation")
include(":feature:home:presentation")
include(":feature:viewinghousing:presentation")
include(":feature:umbrellanavigation")
include(":feature:events:presentation")
include(":feature:news:presentation")
include(":feature:profile:presentation")
include(":feature:booking:presentation")
include(":feature:filters:presentation")
include(":feature:notification:api")
include(":feature:notification:datasource")
include(":feature:maps:presentation")
include(":feature:quiz:presentation")

include(":core:navigation")
include(":core:theme")
include(":sync:work")
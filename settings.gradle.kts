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


include(":feature:auth:presentation")
include(":feature:home:presentation")
include(":feature:viewinghousing:presentation")
include(":feature:umbrellanavigation")
include(":feature:events:presentation")
include(":feature:news:presentation")
include(":feature:profile:presentation")

include(":core:navigation")
include(":core:theme")
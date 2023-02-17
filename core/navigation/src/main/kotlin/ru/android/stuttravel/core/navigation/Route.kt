package ru.android.stuttravel.core.navigation

import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

abstract class Route<T>(route: String? = null, private val navArgs: Map<T, NavType<*>> = emptyMap()) {
    companion object {
        const val DEEP_LINK_SCHEME = "android"
        const val DEEP_LINK_HOST = "studtravel.ru"
    }

    fun passDeepLinkRoute(): List<NavDeepLink> = listOf(navDeepLink {
        uriPattern = "$DEEP_LINK_SCHEME://$DEEP_LINK_HOST/$provideRoute${navArgs.map { "{${it.key}}" }.joinToString(separator = "/", prefix = if (navArgs.any()) "/" else "")}"
    })

    fun navigateDeepLink(args: (key: T) -> Any? = { null }):String = "$DEEP_LINK_SCHEME://$DEEP_LINK_HOST/$provideRoute${navArgs.mapNotNull { args(it.key) }.joinToString(separator = "/", prefix = if (navArgs.any()) "/" else "")}"

    private val provideRoute: String = route ?: this::class.java.name

    fun passRoute(): String = provideRoute + navArgs.map { "${it.key}={${it.key}}" }.joinToString(separator = "&", prefix = if (navArgs.any()) "?" else "")
    fun passArguments() = navArgs.map {
        navArgument(it.key.toString()) {
            type = it.value; nullable = true;
            defaultValue = null }
    }

    fun navigate(args: (key: T) -> Any? = { null }): String = provideRoute + navArgs.mapNotNull { args(it.key)?.let { arg -> "${it.key}=$arg" } }.let { it.joinToString(separator = "&", prefix = if (it.any()) "?" else "") }
    fun popTo() = provideRoute
    fun startDest() = provideRoute
}
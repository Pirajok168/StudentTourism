package ru.android.studenttourism

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
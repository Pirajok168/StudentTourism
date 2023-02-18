package ru.shared.feature.seeInfoDormitories.api

interface ApiInfoDormitories {
    suspend fun getInfoById(id: String)
}
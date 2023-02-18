package ru.shared.feature.profile.data

import ru.shared.feature.profile.data.model.PresentationProfile

interface IRepoProfile {
    suspend fun getProfile(): PresentationProfile
}
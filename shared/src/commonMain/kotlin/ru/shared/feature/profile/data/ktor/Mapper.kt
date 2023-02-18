package ru.shared.feature.profile.data.ktor

import ru.shared.feature.profile.api.response.ProfileResponse
import ru.shared.feature.profile.data.model.PresentationProfile

fun ProfileResponse.toPresent(): PresentationProfile = PresentationProfile(
    email = email.orEmpty(),
    id = id.orEmpty(),
    lastName = lastName.orEmpty(),
    name = name.orEmpty(),
    phone = phone.orEmpty(),
    studentRoleType = studentRoleType.orEmpty(),
    userRole = userRole.orEmpty(),
    username = username.orEmpty()
)
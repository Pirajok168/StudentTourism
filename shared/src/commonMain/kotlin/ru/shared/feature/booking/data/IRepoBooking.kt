package ru.shared.feature.booking.data

import ru.shared.feature.home.data.model.dormitories.Dormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories

interface IRepoBooking {
    suspend fun getInfoDormitoriesById(idDormitories: String): PresentationDetailDormitories
}
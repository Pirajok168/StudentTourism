package ru.shared.feature.booking.data

import ru.shared.feature.booking.data.sql.IBookingDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.sql.toPresent

class RepoBookingImpl(
    val dao: IBookingDormitories
):IRepoBooking {
    override suspend fun getInfoDormitoriesById(idDormitories: String): PresentationDetailDormitories {
        return dao.getInfoById(idDormitories).toPresent()
    }
}
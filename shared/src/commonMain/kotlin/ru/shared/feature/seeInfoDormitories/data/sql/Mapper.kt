package ru.shared.feature.seeInfoDormitories.data.sql

import ru.commons.feature.search.data.database.ReviewDormitoriesTable
import ru.commons.feature.search.data.database.RoomsTable
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail
import ru.shared.feature.seeInfoDormitories.data.model.PresentationServices
import ru.shared.feature.seeInfoDormitories.data.model.ReviewsDetailPresentation
import ru.shared.feature.seeInfoDormitories.data.sql.model.InfoEntity
import kotlin.math.ln

fun InfoEntity.toPresent(): PresentationDetailDormitories = PresentationDetailDormitories(
    regionUniversity = dormitories.regionUniversity,
    nameUniversity = dormitories.nameUniversity,
    idDormitories = dormitories.idDormitories.orEmpty(),
    name = dormitories.name.orEmpty(),
    city = dormitories.city.orEmpty(),
    street = dormitories.street.orEmpty(),
    houseNumber = dormitories.houseNumber.orEmpty(),
    mealPlan = dormitories.mealPlan.orEmpty(),
    minDays = dormitories.minDays.orEmpty(),
    maxDays = dormitories.maxDays.orEmpty(),
    photos = dormitories.photos.orEmpty(),
    requiredUniversityDocuments = dormitories.requiredUniversityDocuments.orEmpty(),
    requiredStudentsDocuments = dormitories.requiredStudentsDocuments.orEmpty(),
    lat = dormitories.lat,
    lng = dormitories.lng,
    phone = dormitories.phone.orEmpty(),
    email = dormitories.email.orEmpty(),
    nameCommittee = dormitories.nameCommittee.orEmpty(),
    rooms = rooms.map { it.toPresent() },
    services = dormitories.services?.map { PresentationServices(
        it.description, it.id, it.isFree, it.name, it.price
    ) }.orEmpty(),
    reviews = reviews.map { it.toReviewsDetailPresentation() }
)
fun ReviewDormitoriesTable.toReviewsDetailPresentation(): ReviewsDetailPresentation = ReviewsDetailPresentation(
    idReview, createdTimestamp, idDormitories, photos, rating, text, timestamp, topic, userId
)

fun RoomsTable.toPresent(): PresentationRoomsDetail = PresentationRoomsDetail(
    idRooms, idDormitories, amountRooms, descriptionRooms, isFreeRooms, photosRooms, priceRooms, typeRooms
)
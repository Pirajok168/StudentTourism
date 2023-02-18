package ru.shared.feature.home.data.ktor

import ru.shared.core.model.DetailRoom
import ru.shared.feature.home.api.response.dormitories.DetailsResponseDormitories
import ru.shared.feature.home.api.response.dormitories.ResponseDormitories
import ru.shared.feature.home.api.response.dormitories.RoomResponse
import ru.shared.feature.home.api.response.reviews.ReviewResponse
import ru.shared.feature.home.api.response.university.UniversityResponse
import ru.shared.feature.home.data.model.dormitories.*
import ru.shared.feature.home.data.model.univiersity.DetailsUniversity
import ru.shared.feature.home.data.model.univiersity.University


fun DetailsResponseDormitories.toPreviewDetail() : Details {
    return Details(
        name = mainInfo?.name.orEmpty(),
        city = mainInfo?.city.orEmpty(),
        street = mainInfo?.street.orEmpty(),
        houseNumber = mainInfo?.houseNumber.orEmpty(),
        coordinates = Coordinates(lat = mainInfo?.coordinates?.lat, lng = mainInfo?.coordinates?.lng),
        mealPlan = mainInfo?.mealPlan.orEmpty(),
        minDays = mainInfo?.minDays.orEmpty(),
        maxDays = mainInfo?.maxDays.orEmpty(),
        photos = mainInfo?.photos.orEmpty(),
        requiredUniversityDocuments = rules?.requiredUniDocuments.orEmpty(),
        requiredStudentsDocuments = rules?.requiredStudentsDocuments.orEmpty(),
        committee = Committee(phone = rules?.committee?.phone.orEmpty(), email = rules?.committee?.phone.orEmpty(), name = rules?.committee?.phone.orEmpty())
    )
}

fun RoomResponse.toPreviewRoom(): Rooms {
    return Rooms(
        details = DetailRoom(
            amount = details?.amount.orEmpty(),
            description = details?.description.orEmpty(),
            isFree = details?.isFree ?: false,
            photos = details?.photos.orEmpty(),
            price = details?.price.orEmpty(),
            type = details?.type.orEmpty(),
        ),
        id = id.orEmpty(),
        universityId.orEmpty()
    )
}

fun UniversityResponse.toPreviewUniversity(): University = University(
    id = id.orEmpty(),
    detail = DetailsUniversity(
        adminContacts = details?.adminContacts.orEmpty(),
        city = details?.city.orEmpty(),
        committee = details?.committee.orEmpty(),
        district = details?.district.orEmpty(),
        founderName = details?.founderName.orEmpty(),
        name = details?.name.orEmpty(),
        photo = details?.photo.orEmpty(),
        region = details?.region.orEmpty(),
        shortName = details?.shortName.orEmpty(),
        site = details?.site.orEmpty()
    )
)
fun ReviewResponse.toPreviewReviewDormitories(): ReviewDormitories = ReviewDormitories(
    id = id.orEmpty(),
    createdTimestamp = createdTimestamp!!,
    dormitoryId = dormitoryId.orEmpty(),
    rating = rating!!,
    photos = photos.orEmpty(),
    text = text.orEmpty(),
    timestamp = timestamp!!,
    topic = topic.orEmpty(),
    userId = userId.orEmpty()
)
 fun ResponseDormitories.toPreview(
     universityResponse: List<UniversityResponse>,
     reviews: List<ReviewResponse>,

     ): Dormitories {
    return Dormitories(
        details = details!!.toPreviewDetail(),
        rooms = rooms.map { it.toPreviewRoom() },
        ownerUniversity = universityResponse.first{ it.id == universityId && it.details != null}.toPreviewUniversity(),
        reviews = reviews.filter { it.dormitoryId == id }.map { it.toPreviewReviewDormitories()},
        idDormitories = id.orEmpty()
    )
}
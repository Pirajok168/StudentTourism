package ru.shared.feature.home.data.sql


import ru.commons.feature.search.data.database.*
import ru.commons.feature.search.data.database.entity.DormitoriesEntity2
import ru.commons.feature.search.data.database.entity.RelationUniversityEntity
import ru.commons.feature.search.data.database.entity.ServicesEntity
import ru.shared.feature.home.api.response.dormitories.DetailsResponseDormitories
import ru.shared.feature.home.api.response.dormitories.ResponseDormitories
import ru.shared.feature.home.api.response.dormitories.RoomResponse
import ru.shared.feature.home.api.response.dormitories.ServiceResponseDormitories
import ru.shared.feature.home.api.response.reviews.ReviewResponse
import ru.shared.feature.home.api.response.university.DetailsUniversityResponse
import ru.shared.feature.home.api.response.university.UniversityResponse
import ru.shared.feature.home.data.model.MostPopular


fun DetailsUniversityResponse.toCache(idUniversity: String): DetailsUniversityTable =
    DetailsUniversityTable(
        idUniversity = idUniversity,
        adminContactsUniversity = adminContacts.orEmpty(),
        cityUniversity = city.orEmpty(),
        committeeUniversity = committee.orEmpty(),
        districtUniversity = district.orEmpty(),
        founderNameUniversity = founderName.orEmpty(),
        nameUniversity = name.orEmpty(),
        photoUniversity = photo.orEmpty(),
        regionUniversity = region.orEmpty(),
        shortNameUniversity = shortName.orEmpty(),
        siteUniversity = site.orEmpty()
    )

fun DetailsResponseDormitories.toCache(idDormitories: String): DetailsTable = DetailsTable(
    idDormitories,
    name = mainInfo?.name.orEmpty(),
    city = mainInfo?.city.orEmpty(),
    street = mainInfo?.street.orEmpty(),
    houseNumber = mainInfo?.houseNumber.orEmpty(),
    mealPlan = mainInfo?.mealPlan.orEmpty(),
    minDays = mainInfo?.minDays.orEmpty(),
    maxDays = mainInfo?.maxDays.orEmpty(),
    photos = mainInfo?.photos.orEmpty(),
    requiredUniversityDocuments = rules?.requiredUniDocuments.orEmpty(),
    requiredStudentsDocuments = rules?.requiredStudentsDocuments.orEmpty(),
    lat = mainInfo?.coordinates?.lat!!,
    lng = mainInfo.coordinates.lng!!,
    phone = rules?.committee?.phone.orEmpty(),
    email = rules?.committee?.email.orEmpty(),
    nameCommittee = rules?.committee?.name.orEmpty(),
)

fun RoomResponse.toCache(idDormitories: String?): RoomsTable = RoomsTable(
    idRooms = id.orEmpty(),
    idDormitories = idDormitories.orEmpty(),
    amountRooms = details?.amount.orEmpty(),
    descriptionRooms = details?.description.orEmpty(),
    isFreeRooms = if (details?.isFree == true) 1 else 0,
    photosRooms = details?.photos.orEmpty(),
    priceRooms = details?.price.orEmpty(),
    typeRooms = details?.type.orEmpty()
)


fun ReviewResponse.toCache(idDormitories: String?): ReviewDormitoriesTable = ReviewDormitoriesTable(
    idReview = id.orEmpty(),
    createdTimestamp = createdTimestamp!!,
    idDormitories = idDormitories.orEmpty(),
    photos = photos?.firstOrNull() ?: "",
    rating = rating?.toDouble()!!,
    text = text.orEmpty(),
    timestamp = timestamp!!,
    topic = topic.orEmpty(),
    userId = userId.orEmpty()
)
fun ResponseDormitories.toCache(
    idDormitories: String?,
    idUniversity: String,
    reviews: List<ReviewResponse>
): DormitoriesEntity2 = DormitoriesEntity2(
    idDormitories.orEmpty(),
    idUniversity = idUniversity,
    details = details?.toCache(idDormitories.orEmpty())!!,
    rooms = rooms.map {
        it.toCache(idDormitories) },
    reviews = reviews.map {

        it.toCache(idDormitories)
    },
    services = details.services?.map { it.toCache() }.orEmpty()
)

fun ServiceResponseDormitories.toCache(): ServicesEntity = ServicesEntity(
    description.orEmpty(),
    id.orEmpty(),
    isFree == true,
    name.orEmpty(),
    price.orEmpty()
)

fun UniversityResponse.toCache(
    responseSearchDormitories: List<ResponseDormitories>,
    responseReviews: List<ReviewResponse>
): RelationUniversityEntity {
    val idUniversity = id.orEmpty()
    val idsDormitories = responseSearchDormitories.filter { it.universityId == idUniversity && it.onModeration == false }
    if (idsDormitories.isEmpty()){
         throw Exception("Ошибка")
    }

    return RelationUniversityEntity(
        university = details!!.toCache(idUniversity),
        listDormitories = idsDormitories.map {
            dormitoies ->
            dormitoies.toCache(
                dormitoies.id,
                idUniversity,
                responseReviews.filter { it.dormitoryId == dormitoies.id })
        }
    )
}

fun GetMostPopular.toPreview(): MostPopular = MostPopular(
    rating = rating,
    image = photos?.first() ?:"",
    title = name.orEmpty(),
    city = city.orEmpty(),
    idDormitories = idDormitories
)
//
//fun Rooms.toEntity(idDormitories: String): RoomsTable = RoomsTable(
//    idRooms = id,
//    idDormitories = idDormitories,
//    amountRooms = details.amount,
//    descriptionRooms = details.description,
//    isFreeRooms = if (details.isFree) 1 else 0,
//    photosRooms = details.photos.firstOrNull() ?: "",
//    priceRooms = details.price,
//    typeRooms = details.type
//)
//
//
//fun Dormitories.toEntity(): DormitoriesEntity = DormitoriesEntity(
//    idUniversity = ownerUniversity.id,
//    idDormitories = idDormitories,
//    details = details.toEntity(idDormitories),
//    rooms = rooms.map { it.toEntity(idDormitories) },
//    ownerUniversity = ownerUniversity.toEntity(),
//    reviews = reviews.map { it.toEntity(idDormitories) }
//)
//
//fun ReviewDormitories.toEntity(idDormitories: String): ReviewDormitoriesTable = ReviewDormitoriesTable(
//    idReview = id,
//    createdTimestamp,
//    idDormitories = idDormitories,
//    photos.firstOrNull() ?: "",
//    rating.toDouble(),
//    text, timestamp, topic, userId
//)
//
//fun Details.toEntity(idDormitories: String): DetailsTable = DetailsTable(
//
//    idDormitories = idDormitories,
//    name = name,
//    city,
//    street,
//    houseNumber,
//    mealPlan,
//    minDays,
//    maxDays,
//    photos.firstOrNull().orEmpty(),
//    requiredUniversityDocuments,
//    requiredStudentsDocuments,
//    coordinates.lat!!,
//    coordinates.lng,
//    committee.phone,
//    committee.email,
//    committee.name
//)
//
//
//fun University.toEntity(): DetailsUniversityTable = DetailsUniversityTable(
//    idUniversity = id.orEmpty(),
//    adminContactsUniversity = detail.adminContacts.orEmpty(),
//    cityUniversity = detail.city.orEmpty(),
//    committeeUniversity = detail.committee.orEmpty(),
//    districtUniversity = detail.district.orEmpty(),
//    founderNameUniversity = detail.founderName.orEmpty(),
//    nameUniversity = detail.name.orEmpty(),
//    photoUniversity = detail.photo.orEmpty(),
//    regionUniversity = detail.region.orEmpty(),
//    shortNameUniversity = detail.shortName.orEmpty(),
//    siteUniversity = detail.site.orEmpty()
//)
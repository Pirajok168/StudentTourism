package ru.shared.feature.event.data.model

sealed class TypeEvent(
    val title: String,
    val desc: String
    ){
    object Culture: TypeEvent(
        title = "Культурно-позновательные",
        desc = "cultural"
    )

    object LabPopulation: TypeEvent(
        title = "Научно-популярные",
        desc = "scientific"
    )

    object Proforientation: TypeEvent(
        title = "Профориентационные",
        desc = "proforientation"
    )
}

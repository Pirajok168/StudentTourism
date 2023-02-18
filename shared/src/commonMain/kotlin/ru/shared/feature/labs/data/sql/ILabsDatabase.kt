package ru.shared.feature.labs.data.sql

import ru.commons.feature.search.data.database.Lab

interface ILabsDatabase {
    suspend fun setLab(labs: List<Lab>)
}
package ru.android.stutravel.feature.filters.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.commons.feature.search.data.database.GetAllFilters
import ru.shared.IRepoFilters
import ru.shared.ISearchDormitories
import ru.shared.MobileSdk
import ru.shared.feature.filters.data.IRepoFilters
import ru.shared.feature.home.data.IRepositoryHome


enum class TypeFood(val title: String, val description: String) {
    NoFood("Без питания", "nothing"),
    BreakfastAndDinner("Завтрак и ужин", "breakfastAndDinner"),
    FullFood("Полный пансион", "full"),
    OnlyBreakfast("Только завтрак", "breakfastOnly")
}


data class FilterState(


    val selectedRegion: String = "",
    val listAvailableRegion: List<String> = listOf(),

    val selectedCity: String = "",
    val listAvailableCities: List<String> = listOf(),


    val searchValueRegion: String = "",
    val searchValueCities: String = "",


    val listAvailableTypeFood: List<TypeFood> = listOf(),
    val selectedTypeFood: TypeFood = TypeFood.NoFood,

    val listAvailableNameUniversity: List<String> = emptyList(),
    val searchNameUniversity: String = "",
    val selectedNameUniversity: String = "",

    val listAvailableTypeRoom: List<String> = emptyList(),
    val selectedTypeRoom: String = "",
)


sealed class Event {
    object LoadAllShortNameUniversities : Event()
    data class SelectRegion(val selected: String) : Event()
    data class SelectCities(val selected: String) : Event()

    data class SearchRegions(val input: String) : Event()
    data class SearchCities(val input: String) : Event()

    data class SelectTypeFood(val input: TypeFood) : Event()

    data class SelectUniversity(val input: String) : Event()
    data class SearchUniversity(val input: String) : Event()

    data class SelectRoom(val input: String) : Event()

    object ClearCiti : Event()

    object SaveFilter : Event()
}

class FilterSearchViewModel(
    private val repo: IRepositoryHome = MobileSdk.ISearchDormitories,
    private val filters: IRepoFilters = MobileSdk.IRepoFilters,
) : ViewModel() {
    var uiState by mutableStateOf(FilterState())
    private var threeFilter: Map<String, Map<String, Map<String?, List<GetAllFilters>>>> =
        mapOf()

    private var allAvailableCities: List<String> = listOf()
    private var allAvailableRegion: List<String> = listOf()
    private var allAvailableTypeFood: List<TypeFood> = listOf()
    private var allAvailableNameUniversity: List<String> = listOf()
    private var allAvailableTypeRoom: List<String> = listOf()
    fun event(event: Event) {
        when (event) {
            Event.LoadAllShortNameUniversities -> {
                loadAllShortNameUniversities()
            }
            is Event.SelectRegion -> selectRegion(event.selected)
            is Event.SelectCities -> selectCities(event.selected)
            Event.ClearCiti -> {
                uiState = uiState.copy(
                    listAvailableCities = allAvailableCities,
                    listAvailableRegion = allAvailableRegion,
                    selectedRegion = "",
                    selectedCity = ""
                )
            }
            is Event.SearchRegions -> {
                uiState = uiState.copy(
                    listAvailableRegion = allAvailableRegion.filter {
                        it.lowercase().contains(event.input.lowercase())
                    },
                    searchValueRegion = event.input
                )
            }
            is Event.SearchCities -> {
                uiState = uiState.copy(
                    listAvailableCities = allAvailableCities.filter {
                        it.lowercase().contains(event.input.lowercase())
                    },
                    searchValueCities = event.input
                )
            }
            is Event.SelectTypeFood -> selectTypeFood(event.input)
            is Event.SelectUniversity -> selectUniversity(event.input)
            is Event.SearchUniversity -> uiState = uiState.copy(
                listAvailableNameUniversity = allAvailableNameUniversity.filter {
                    it.lowercase().contains(event.input.lowercase())
                },
                searchNameUniversity = event.input
            )
            is Event.SelectRoom -> {
                selectTypeRoom(event.input)
            }
            Event.SaveFilter -> {
                viewModelScope.launch {
                    val list = hashSetOf<GetAllFilters>()
                    threeFilter[uiState.selectedRegion]?.let {
                        it[uiState.selectedCity]?.let {
                            it.forEach { (typeFood, value) ->
                                value.filter {
                                    uiState.listAvailableNameUniversity.contains(it.shortNameUniversity)
                                }.forEach {
                                    if (it.typeRooms == uiState.selectedTypeRoom
                                        || uiState.selectedTypeRoom.isEmpty())
                                        list.add(it)
                                }
                            }
                        } ?: it.forEach { (cities, value) ->
                            value.forEach { (typeFood, value) ->
                                value.forEach {
                                    list.add(it)
                                }
                            }
                        }
                    } ?: threeFilter.forEach { (region, value) ->
                        value.filter { (citi, value) ->
                            uiState.listAvailableCities.contains(citi)
                        }.forEach { (city, value) ->
                            value.forEach { (typeFood, value) ->
                                value
                                    .forEach {
                                        if (it.typeRooms == uiState.selectedTypeRoom || uiState.selectedTypeRoom.isEmpty())
                                            list.add(it)
                                    }
                            }
                        }
                    }


                    filters.setFilteredData(list.toList().distinctBy { it.idDormitories })
                }

            }
        }
    }

    private fun selectTypeRoom(input: String) {
        uiState = uiState.copy(selectedTypeRoom = input)
    }

    private fun selectUniversity(input: String) {
        threeFilter
            .forEach { (region, value) ->
                value.forEach { (cities, value) ->
                    value.forEach { (typeFood, value) ->

                        if (value.firstOrNull { it.shortNameUniversity == input } != null) {
                            val rooms = hashSetOf<String>()
                            value.forEach {
                                rooms.add(it.typeRooms.orEmpty())
                            }
                            uiState = uiState.copy(
                                selectedNameUniversity = input,
                                selectedRegion = region,
                                selectedCity = cities,
                                listAvailableTypeRoom = rooms.filter { it.isNotEmpty() },
                                selectedTypeRoom = "",
                                listAvailableNameUniversity = listOf(input)
                            )
                            return
                        }


                    }
                }
            }

    }

    private fun selectTypeFood(input: TypeFood) {

        val regions = hashSetOf<String>()
        val cities = hashSetOf<String>()
        val uni = hashSetOf<String>()
        val typeRoom = hashSetOf<String>()
        threeFilter.forEach { (region, value) ->
            value.forEach { (city, value) ->

                value.forEach { (typeFood, value) ->
                    if (typeFood == input.description) {
                        regions.add(region)
                        cities.add(city)
                        value.forEach {
                            uni.add(it.shortNameUniversity)
                            typeRoom.add(it.typeRooms.orEmpty())
                        }
                    }
                }
//                val a = value.keys
//                val b = a.map { stringToTypeFood(it.orEmpty()) }
//                if (b.contains(input)) {
//                    regions.add(region)
//                    cities.add(city)
//                }
            }
        }
        uiState = uiState.copy(
            selectedTypeFood = input,
            listAvailableRegion = regions.toList().sortedBy { it },
            listAvailableCities = cities.toList().sortedBy { it },
            listAvailableNameUniversity = uni.toList(),
            selectedCity = "",
            selectedRegion = "",
            selectedNameUniversity = "",
            selectedTypeRoom = "",
            listAvailableTypeRoom = typeRoom.filter { it.isNotEmpty() }.sortedBy { it }
        )
    }

    private fun selectCities(selected: String) {
        var selectedRegion = ""
        val availableUni = hashSetOf<String>()
        val availableRoom = hashSetOf<String>()
        threeFilter.forEach { (region, value) ->
            value
                .filter { (cities, value) -> cities == selected }
                .forEach { (cities, value) ->
                    selectedRegion = region
                    value.forEach { (type, value) ->
                        value.forEach {
                            availableUni.add(it.shortNameUniversity)
                            availableRoom.add(it.typeRooms.orEmpty())
                        }
                    }
                }

        }


        uiState = uiState
            .copy(
                selectedCity = selected,
                selectedRegion = selectedRegion,
                searchValueCities = "",
                listAvailableNameUniversity = availableUni.toList(),
                listAvailableTypeRoom = availableRoom.filter { it.isNotEmpty() }.sortedBy { it },
                selectedTypeRoom = ""
            )
    }

    private fun selectRegion(selected: String) {
        val filterThree = threeFilter
        val availableCities = mutableListOf<String>()
        val availableTypeFood = hashSetOf<TypeFood>()
        val availableUni = hashSetOf<String>()
        val availableRoom = hashSetOf<String>()
        filterThree.filter { (key, value) ->
            key == selected
        }.map { (key, value) ->
            value.forEach { (city, value) ->
                availableCities.add(city)
                value.forEach { (typeFood, value) ->
                    availableTypeFood.add(stringToTypeFood(typeFood.orEmpty()))
                    value.forEach {
                        availableUni.add(it.shortNameUniversity)
                        availableRoom.add(it.typeRooms.orEmpty())
                    }
                }
            }
        }
        uiState = uiState
            .copy(
                selectedRegion = selected,
                listAvailableCities = availableCities,
                searchValueRegion = "",
                listAvailableNameUniversity = availableUni.toList(),
                listAvailableTypeRoom = availableRoom.filter { it.isNotEmpty() }.sortedBy { it },
                selectedTypeRoom = "",
                selectedCity = "",
            )
    }

    private fun loadAllShortNameUniversities() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataList = filters.getAllFilters()
            val data = dataList.groupBy { it.regionUniversity }
            val a = mutableMapOf<String, MutableMap<String, Map<String?, List<GetAllFilters>>>>()
            data.forEach { (key, value) ->
                val citiesGroup =
                    value.filter { it.city!!.isNotEmpty() }.groupBy { it.city!! }
                val b = mutableMapOf<String, Map<String?, List<GetAllFilters>>>()
                citiesGroup.forEach { (key, value) ->
                    val mealPlanGroup = value.groupBy { it.mealPlan }
                    b[key] = mealPlanGroup
                }
                a.put(key, b)
            }
            threeFilter = a.toMap()
            allAvailableRegion = a.keys.toList().sortedBy { it }.distinct()
            allAvailableCities = dataList.filter { it.city!!.isNotEmpty() }
                .map { it.city!! }.sortedBy { it }.distinct()

            allAvailableNameUniversity =
                dataList.map { it.shortNameUniversity }.distinct().sortedBy { it }
            allAvailableTypeFood = dataList.map {
                it.mealPlan!!
            }.distinct().map {
                stringToTypeFood(it)
            }

            allAvailableTypeRoom =
                dataList.map {
                    it.typeRooms.orEmpty()
                }.filter { it.isNotEmpty() }.distinct().sortedByDescending { it }
            uiState = uiState.copy(
                listAvailableRegion = allAvailableRegion,
                listAvailableCities = allAvailableCities,
                listAvailableTypeFood = allAvailableTypeFood,
                listAvailableNameUniversity = allAvailableNameUniversity,
                listAvailableTypeRoom = allAvailableTypeRoom
            )
        }
    }

    private fun stringToTypeFood(it: String): TypeFood {
        return when (it) {
            "nothing" -> TypeFood.NoFood
            "breakfastAndDinner" -> TypeFood.BreakfastAndDinner
            "full" -> TypeFood.FullFood
            "breakfastOnly" -> TypeFood.OnlyBreakfast
            else -> {
                throw Exception("")
            }
        }
    }
}
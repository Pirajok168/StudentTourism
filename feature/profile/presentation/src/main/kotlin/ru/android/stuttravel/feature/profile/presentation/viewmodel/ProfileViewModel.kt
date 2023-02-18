package ru.android.stuttravel.feature.profile.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.shared.IRepoProfile
import ru.shared.MobileSdk
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.feature.profile.data.IRepoProfile
import ru.shared.feature.profile.data.model.PresentationProfile


enum class LevelNotice(){
    Important,
    Usually,
}

// TODO: Доделать для каждого поля на сайте в профиле такую менюшку.
sealed class NoticeEvent(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val levelNotice: LevelNotice
){
    object Birthday: NoticeEvent(
        title = "Заполните дату рождения",
        subtitle = "", icon = Icons.Outlined.Cake,
        levelNotice = LevelNotice.Important
    )

    object Email: NoticeEvent(
        title = "Заполните Email",
        subtitle = "", icon = Icons.Outlined.Mail,
        levelNotice = LevelNotice.Usually
    )

    object Specialization: NoticeEvent(
        title = "Выберите специализацию",
        subtitle = "", icon = Icons.Outlined.Construction,
        levelNotice = LevelNotice.Important
    )

    object Sex: NoticeEvent(
        title = "Выберите свой пол",
        subtitle = "", icon = Icons.Outlined.Face6,
        levelNotice = LevelNotice.Important
    )

    object SurName: NoticeEvent(
        title = "Заполните фамилию",
        subtitle = "", icon = Icons.Outlined.Badge,
        levelNotice = LevelNotice.Usually
    )

    object Name: NoticeEvent(
        title = "Заполните имя",
        subtitle = "", icon = Icons.Outlined.Badge,
        levelNotice = LevelNotice.Usually
    )

    object LastName: NoticeEvent(
        title = "Заполните отчество",
        subtitle = "", icon = Icons.Outlined.Badge,
        levelNotice = LevelNotice.Usually
    )

    object Role: NoticeEvent(
        title = "Выберите роль",
        subtitle = "", icon = Icons.Outlined.PostAdd,
        levelNotice = LevelNotice.Usually
    )

    object City: NoticeEvent(
        title = "Выберите город отправления",
        subtitle = "", icon = Icons.Outlined.Apartment,
        levelNotice = LevelNotice.Usually
    )

    object Phone: NoticeEvent(
        title = "Введите номер телефона",
        subtitle = "", icon = Icons.Outlined.Smartphone,
        levelNotice = LevelNotice.Usually
    )

    object Link: NoticeEvent(
        title = "Введите ссылку на соцсеть",
        subtitle = "", icon = Icons.Outlined.Link,
        levelNotice = LevelNotice.Usually
    )

    object University: NoticeEvent(
        title = "Выберите ВУЗ",
        subtitle = "", icon = Icons.Outlined.School,
        levelNotice = LevelNotice.Usually
    )

    object Photo: NoticeEvent(
        title = "Загрузите фотографию",
        subtitle = "", icon = Icons.Outlined.PhotoCamera,
        levelNotice = LevelNotice.Usually
    )
}

data class ProfileState(
    val profile: PresentationProfile? = null,
    val isLoading: Boolean = false,
    val isError: ResponseError? = null,
    val listWarning: List<NoticeEvent>  = emptyList()
)


sealed class Event{
    object LoadData: Event()
}

class ProfileViewModel(
    private val repo: IRepoProfile = MobileSdk.IRepoProfile
): ViewModel() {
    var uiState by mutableStateOf(ProfileState())
    val mutex = Mutex()
    fun event(event: Event){
        when(event){
            Event.LoadData -> {
                viewModelScope.launch {
                    repo.getProfile()
                        .collectLatest {
                            mutex.withLock(Dispatchers.Main) {
                                uiState = when(it){
                                    is FlowResponse.Error -> uiState.copy(isError = it.error)
                                    is FlowResponse.Loading -> uiState.copy(isLoading = it.isLoading)
                                    is FlowResponse.Success -> {

                                        val listWarning = mutableListOf<NoticeEvent>()

                                        if (it.data.firstName.isEmpty()){
                                            listWarning.add(NoticeEvent.Name)
                                        }
                                        if (it.data.lastName.isEmpty()){
                                            listWarning.add(NoticeEvent.SurName)
                                        }
                                        if (it.data.middleName.isEmpty()){
                                            listWarning.add(NoticeEvent.LastName)
                                        }
                                        if (it.data.universityName.isEmpty()){
                                            listWarning.add(NoticeEvent.University)
                                        }
                                        if (it.data.phone.isEmpty()){
                                            listWarning.add(NoticeEvent.Phone)
                                        }
                                        if (it.data.departureCity.isEmpty()){
                                            listWarning.add(NoticeEvent.City)
                                        }
                                        if (it.data.studentRoleType.isEmpty()){
                                            listWarning.add(NoticeEvent.Role)
                                        }
                                        if (it.data.email.isEmpty()){
                                            listWarning.add(NoticeEvent.Email)
                                        }
                                        if (it.data.gender.isEmpty()){
                                            listWarning.add(NoticeEvent.Sex)
                                        }
                                        if (it.data.WoS.isEmpty()){
                                            listWarning.add(NoticeEvent.Specialization)
                                        }
                                        if (it.data.birthday.isEmpty()){
                                            listWarning.add(NoticeEvent.Birthday)
                                        }
                                        uiState.copy(profile = it.data, listWarning = listWarning.toList())
                                    }
                                }
                            }

                        }
                }
            }
        }
    }
}

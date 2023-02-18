package ru.android.stuttravel.feature.viewinghousing.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories


@Composable
fun ConditionsViewer(
    modifier: Modifier,
    detail: PresentationDetailDormitories
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Перечень необходимых документов и требований для путешественников," +
                    " оплачивающих услуги самостоятельно",
            style = MaterialTheme.typography.titleMedium
                .copy(textAlign = TextAlign.Start),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        Text(
            text = detail.requiredStudentsDocuments,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Перечень необходимых документов и требований " +
                    "для направляющей образовательной организации",
            style = MaterialTheme.typography.titleMedium
                .copy(textAlign = TextAlign.Start),
            modifier = Modifier
                .padding(bottom = 8.dp, top = 16.dp)
        )

        Text(
            text = detail.requiredUniversityDocuments,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
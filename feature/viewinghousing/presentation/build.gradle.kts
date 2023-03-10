plugins{
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.android.studenttourism.feature.viewinghousing.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies{
    implementation(project(":core:navigation"))
    implementation(project(":core:theme"))
    implementation(project(":shared"))
    val compose_version = "1.3.3"
    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.ui:ui:$compose_version")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation ("androidx.compose.material3:material3:1.1.0-alpha05")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.compose.material:material-icons-extended:1.3.1")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$compose_version")

    //compose nav
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0")
}
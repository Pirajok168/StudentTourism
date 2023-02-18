plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.android.studenttourism.feature.umbrellanavigation"
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

dependencies {
    //  api(project(":feature:profile:presentation"))
    api(project(":feature:home:presentation"))
    api(project(":feature:news:presentation"))
    api(project(":core:navigation"))
    api(project(":feature:profile:presentation"))

    val compose_version = "1.3.3"
    implementation("androidx.compose.material3:material3:1.1.0-alpha05")
    implementation("androidx.compose.ui:ui:$compose_version")

    //compose nav
    implementation("androidx.navigation:navigation-compose:2.5.3")




    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
}
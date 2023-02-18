plugins {
    id("com.android.application")
    kotlin("android")

    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ru.android.stuttravel"
    compileSdk = 33
    defaultConfig {
        applicationId = "ru.android.stuttravel"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":core:theme"))
    implementation(project(":feature:viewinghousing:presentation"))
    implementation(project(":feature:events:presentation"))
    implementation(project(":feature:booking:presentation"))
    implementation(project(":feature:filters:presentation"))
    implementation(project(":feature:quiz:presentation"))
    api(project(":feature:umbrellanavigation"))
    implementation(project(":feature:auth:presentation"))
    api(project(":sync:work"))

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.ui:ui:1.3.3")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation ("androidx.compose.material3:material3:1.1.0-alpha05")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.appcompat:appcompat:1.6.0")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.compose.animation:animation-graphics:1.1.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.3.3")

    //compose nav
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    //Insets
    implementation ("androidx.compose.foundation:foundation:1.3.1")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation ("com.google.dagger:hilt-android:2.44.2")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    implementation ("androidx.hilt:hilt-work:1.0.0")
    implementation ("androidx.work:work-runtime-ktx:2.8.0")

    implementation ("androidx.startup:startup-runtime:1.1.1")
}
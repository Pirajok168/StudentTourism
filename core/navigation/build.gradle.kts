plugins{
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.android.studenttourism.core.navigation"
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



    //compose nav
    implementation ("androidx.navigation:navigation-compose:2.5.3")


}
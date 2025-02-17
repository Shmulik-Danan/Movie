plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)

}

android {
    compileSdk = 34
    namespace = "com.shmulik.data"


    defaultConfig {
        minSdk = 29
        consumerProguardFiles("proguard-rules.pro")
        buildConfigField(
            "String",
            "BASE_URL", "\"https://omdbapi.com/\""
        )
        buildConfigField(
            "String",
            "API_KEY_omdbapi", "\"a72e5744\""
        )

    }
    buildTypes {
        release {
            isMinifyEnabled = true
            consumerProguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true

    }
}

dependencies {
    api(project(":domain"))
    // Compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))

    //Paging
    implementation(libs.paging.common.ktx)

    // Network
    implementation(libs.bundles.network)
    //Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.paging)
    implementation(libs.room.ktx)
    // Hilt
    implementation(libs.hilt.dagger.android)
    ksp(libs.hilt.dagger.compiler)
    ksp(libs.hilt.compiler)


}
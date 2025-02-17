plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = 34
    namespace = "com.shmulik.domain"
    defaultConfig {
        minSdk = 29
        consumerProguardFiles("proguard-rules.pro")
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
    }
}

dependencies {
    // Compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))
    //Paging
    implementation(libs.paging.common.ktx)
}
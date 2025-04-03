plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services) // Adicionando o plugin do Google Services
}

android {
    namespace = "br.com.redeAncora.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.redeAncora.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        viewBinding = true;
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation(libs.firebase.database.v2030)
}
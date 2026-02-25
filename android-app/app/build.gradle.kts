plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)

    //    migration from kapt to ksp
    id("com.google.devtools.ksp")

//    hilt library dependency
    id("com.google.dagger.hilt.android")

//    serialization dependency
    alias(libs.plugins.kotlin.serialization)

    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.womensafetyapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.womensafetyapp"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
    }

}

kotlin {
    jvmToolchain(17)

    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.play.services.location)
//    implementation(libs.androidx.compose.foundation.layout)
//    implementation(libs.androidx.compose.ui.text)
//    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.androidx.compose.runtime.livedata)
//    implementation(libs.androidx.compose.runtime.livedata)
//    implementation(libs.androidx.compose.runtime)
//    implementation(libs.androidx.ui.graphics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")


    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation(libs.firebase.auth)
    implementation(platform("com.google.firebase:firebase-bom:34.9.0"))

    implementation("com.google.android.gms:play-services-auth:21.5.1")

    implementation("com.google.android.libraries.navigation:navigation:5.0.1")



//    serialization dependency
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")

//    hilt dependency injection dependency
    implementation("com.google.dagger:hilt-android:2.56.2")
    ksp("com.google.dagger:hilt-compiler:2.56.2")

//    ksp("androidx.hilt:hilt-compiler:1.3.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("com.google.android.gms:play-services-location:21.0.1")

    implementation("com.google.maps.android:maps-compose:4.3.0")
//    implementation("com.google.android.gms:play-services-maps:18.2.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.3")

}
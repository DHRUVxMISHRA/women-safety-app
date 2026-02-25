// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
//    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    //    migration from kapt to ksp
    id("com.google.devtools.ksp") version "2.2.10-2.0.2" apply false


    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10" apply false

//    hilt dependency injection dependency
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
    
}
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.weatherapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherapplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    //implementation("org.jetbrains:kotlinx:kotlinx-coroutines:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    //implementation("com.github.bumptech.glide:glide2.15.1")

    implementation("com.github.bumptech.glide:glide:4.16.0")
//
//    implementation("com.google.dagger:hilt-android:2:46")
//    kapt "com.google.dagger:hilt-compiler:2.46"

    implementation("com.google.dagger:hilt-android:2.46")
    kapt( "com.google.dagger:hilt-compiler:2.46")


}
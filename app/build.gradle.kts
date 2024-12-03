plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")

}

android {
    namespace = "com.example.quizapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quizapp"
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
    // Room dependencies
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2") // Kotlin extensions
    implementation("androidx.room:room-paging:2.5.2") // Optional: Paging support

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Gson (for JSON serialization/deserialization)
    implementation("com.google.code.gson:gson:2.10.1")

    // Coroutines for async operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Compose
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material3:material3:1.2.0-alpha04")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation("androidx.compose.runtime:runtime:1.4.0") // State management
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.compose.ui:ui:1.5.1")
    implementation ("androidx.compose.material3:material3:1.2.0-alpha04")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation ("androidx.activity:activity-compose:1.8.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
    implementation ("androidx.compose.runtime:runtime:1.4.0")

    // Lifecycle and ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Core KTX
    implementation("androidx.core:core-ktx:1.12.0")

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose Testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.1")
}

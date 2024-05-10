plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.comGoogledDaggerHiltPlugin)
}
kapt {
    correctErrorTypes  = true
}
android {
    namespace = "com.randrez.countriesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.randrez.countriesapp"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            type = "String",
            name = "URL",
            value = "\"https://restcountries.com/\""
        )
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
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.appcompanist.coil)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.compose.animation)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)

    //network
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.gsonConverter)
    implementation(libs.squareup.okhttp.logging)
    implementation(libs.grpc.okHttp)

    //lifecycle viewmodel
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    //coroutines
    implementation(libs.jetbrains.kotlin.coroutines.core)
    implementation(libs.jetbrains.kotlin.coroutines.android)
    implementation(project(":network"))
    implementation(project(":database"))
    testImplementation(libs.jetbrains.kotlin.coroutines.test)

    //dagger hilt
    implementation(libs.google.dagger.hilt.android)
    testImplementation("junit:junit:4.12")
    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    kapt(libs.google.dagger.hilt.android.compiler)
    kapt(libs.google.dagger.hilt.android.procesor)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    kaptAndroidTest(libs.google.dagger.hilt.compiler)

    //room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    //mockk
    testImplementation(libs.io.mockk)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
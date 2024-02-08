plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.funnumberfacts"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.funnumberfacts"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "REST_URL", "\"http://numbersapi.com/\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.room)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.ktx)
    implementation(libs.androidx.core.ktx)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)

    implementation(libs.okhttp.logging.interceptor)

    kapt(libs.room.compiler)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.junit.ktx)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
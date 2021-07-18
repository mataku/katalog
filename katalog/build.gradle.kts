plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}

android {
    compileSdk = Constants.compileSdk
    buildToolsVersion = Constants.buildToolsVersion

    defaultConfig {
        minSdk = Constants.minSdk
        targetSdk = Constants.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xexplicit-api=strict",
            "-Xopt-in=kotlin.RequiresOptIn"
        )
    }
    composeOptions {
        kotlinCompilerVersion = Versions.composeKotlinCompiler
        kotlinCompilerExtensionVersion = Versions.Androidx.compose
    }
    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
        getByName("test").java.srcDir("src/test/kotlin")
        getByName("androidTest").java.srcDir("src/androidTest/kotlin")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Deps.KotlinX.Coroutines.android)

    implementation(Deps.Androidx.Core.ktx)
    implementation(Deps.Androidx.Activity.ktx)
    implementation(Deps.Androidx.Activity.compose)

    implementation(Deps.Androidx.Compose.ui)
    implementation(Deps.Androidx.Compose.uiTooling)
    implementation(Deps.Androidx.Compose.foundation)
    implementation(Deps.Androidx.Compose.material)
    implementation(Deps.Androidx.Compose.materialIconsCore)
    implementation(Deps.Androidx.Compose.materialIconExtended)

    implementation(Deps.Androidx.Lifecycle.viewModel)
    implementation(Deps.Androidx.Lifecycle.compose)

    implementation(Deps.Androidx.annotation)

    implementation(Deps.material)

    testImplementation(Deps.Androidx.Test.core)
    testImplementation(Deps.Androidx.Test.runner)
    testImplementation(Deps.Androidx.Test.rules)
    testImplementation(Deps.Androidx.Test.junit)
    testImplementation(Deps.Androidx.Test.truth)
}

ext {
    set("releaseArtifact", "katalog-android")
}

apply(from = "$rootDir/config/publish.gradle")

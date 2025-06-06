import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    // Ktor
    alias(libs.plugins.kotlin.serialization)

    // Room
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)

    // Unit testing
    alias(libs.plugins.mockative)

    // ktlint
    alias(libs.plugins.ktlint)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // DI
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            // Ktor and Coil
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // General
            implementation(libs.kotlinx.serialization.json)

            // ViewModel
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.kotlinx.coroutines.core)

            // Navigation
            implementation(libs.navigation.compose)

            // DI
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            // Ktor
            implementation(libs.bundles.ktor)

            // Room
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            // Compottie (Lottie KMP lib) --> https://github.com/alexzhirkevich/compottie/tree/standalone-main
            implementation(libs.compottie)
        }
        commonTest.dependencies {
            // Unit testing
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.assertk)
            implementation(libs.mockative)
            implementation(libs.turbine)

            // UI testing
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
        nativeMain.dependencies {
            // Ktor and Coil
            implementation(libs.ktor.client.darwin)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            // Ktor and Coil
            implementation(libs.ktor.client.okhttp)
        }
        // Room-2
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata")
        }
    }
    // Unit testing
    mockative {
        sourceSets {
            commonMain {
            }
        }
    }
}

android {
    namespace = "com.murosar.kmp.completemoviesapp"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "com.murosar.kmp.completemoviesapp"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

    // Room
    ksp(libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspDesktop", libs.room.compiler)
}

compose.desktop {
    application {
        mainClass = "com.murosar.kmp.completemoviesapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.murosar.kmp.completemoviesapp"
            packageVersion = "1.0.0"
        }
    }
}

// Room
room {
    schemaDirectory("$projectDir/schemas")
}

ktlint {
    version.set("1.5.0") // ktlint (Pinterest) core version
    android.set(true) // Android-specific rules
    outputToConsole.set(true) // Shows errors in console
    ignoreFailures.set(false) // The build fails if issues are found

    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }

    filter {
        exclude("**/build/**") // Exclude build folder
    }

    // To execute ktlint you can run:
    // ./gradlew ktlintCheck
    // ./gradlew ktlintFormat
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("jacoco")
    id("kotlin-kapt")
    alias(libs.plugins.hilt.android)
}
val jacocoTestReport = tasks.create("jacocoTestReport")

android {
    namespace = "com.celaenoapps.flavorquest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.celaenoapps.flavorquest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled =  true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    androidComponents {
        val coverageExclusion = listOf(
            // Android
            "**/R.class",
            "**/R\$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*"
        )

        onVariants(selector = selector().all()) { variant ->
            val testTaskName = "test${variant.name.capitalize()}UnitTest"
            val reportTask = tasks.register(
                name = "jacoco${testTaskName.capitalize()}Report",
                type = JacocoReport::class,
            ) {
                dependsOn(testTaskName)
                reports {
                    html.required.set(true)
                }

                classDirectories.setFrom(
                    fileTree(baseDir = "$buildDir/tmp/kotlin-classes/${variant.name}") {
                        exclude(coverageExclusion)
                    }
                )

                sourceDirectories.setFrom(
                    files("$projectDir/src/main/java", "$projectDir/src/main/kotlin")
                )
                executionData.setFrom(file("$buildDir/jacoco/${testTaskName}.exec"))
            }
            jacocoTestReport.dependsOn(reportTask)
        }
    }
}

dependencies {
    coreLibraryDesugaring( libs.android.desugar.jdk.libs)

    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.navigation.compose)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

tasks.withType(Test::class.java) {
    jacoco {
        setExcludes(
            listOf("jdk.internal.*")
        )
    }
}
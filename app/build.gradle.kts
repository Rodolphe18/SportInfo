@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.detekt)
    id("com.google.protobuf") version "0.9.1"
}

val appVersionName: String
inline get() = libs.versions.sport.asProvider().get()

val appBetaVersion: Int
inline get() = libs.versions.sport.beta.map(String::toInt).get()

val appVersionCode: Int
inline get() = libs.versions.sport.code.map(String::toInt).get()

@Suppress("StringLiteralDuplication", "UnderscoresInNumericLiterals")
android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.francotte.android.sportinfo"
        namespace = "com.francotte.android"
        minSdk = 21
        versionName = appVersionName
        versionCode = appVersionCode
        targetSdk = 34
        testInstrumentationRunner = "com.francotte.android.test"
        multiDexEnabled = true

    }

    bundle {
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha02"
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packagingOptions {
        resources.merges.addAll(
            listOf(
                        "META-INF/AL2.0",
                        "LICENSE-EPL-1.0.txt",
                        "LICENSE-EDL-1.0.txt"
            )
        )
    }
}


@Suppress("StringLiteralDuplication")
dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.0")
    val roomVersion = "2.5.2"
    implementation ("androidx.room:room-runtime:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    kapt ("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation(libs.hilt.android)
    val androidHiltVersion = "1.0.0"
    implementation("androidx.hilt:hilt-work:$androidHiltVersion")
    implementation("io.coil-kt:coil-bom:2.2.2")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-base")
    implementation("io.coil-kt:coil-compose-base")
    implementation("io.coil-kt:coil-gif")
    implementation("io.coil-kt:coil-video")
    implementation("io.coil-kt:coil-svg")
    kapt("androidx.hilt:hilt-compiler:$androidHiltVersion")
    kapt(libs.hilt.compiler)
    implementation("androidx.browser:browser:1.5.0-beta01")
    implementation("androidx.security:security-crypto:1.1.0-alpha04")
    implementation("androidx.startup:startup-runtime:1.1.1")
    val workVersion = "2.8.0-beta02"
    implementation("androidx.work:work-runtime:$workVersion")
    implementation("androidx.work:work-runtime-ktx:$workVersion")
    val activityVersion = "1.6.1"
    implementation("androidx.activity:activity-ktx:$activityVersion")
    implementation("androidx.activity:activity-compose:$activityVersion")
    val navVersion = "2.5.3"
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.androidx.dataStore.core)
    val emoji2Version = "1.2.0-rc01"
    implementation("androidx.emoji2:emoji2:$emoji2Version")
    implementation("androidx.emoji2:emoji2-views:$emoji2Version")
    implementation("androidx.emoji2:emoji2-views-helper:$emoji2Version")
    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-process:$lifecycleVersion")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-text-google-fonts")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.tracing:tracing-ktx:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.webkit:webkit:1.6.0-rc01")
    implementation("com.google.android.material:material:1.8.0-rc01")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-dynamic-links-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.android.gms:play-services-safetynet:18.0.1")
    implementation("com.google.android.gms:play-services-auth:20.4.0")
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation("com.google.android.gms:play-services-appindex:16.1.0")
    implementation("com.google.android.play:review:2.0.1")
    implementation("com.google.android.play:review-ktx:2.0.1")
    implementation("com.google.android.play:app-update:2.0.1")
    implementation("com.google.android.play:app-update-ktx:2.0.1")
    implementation("com.android.billingclient:billing:4.1.0")
    implementation("com.android.billingclient:billing-ktx:4.1.0")
    implementation("com.android.installreferrer:installreferrer:2.2")

    val coroutinesVersion = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:$coroutinesVersion")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    val okhttpVersion = "5.0.0-alpha.11"
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("io.coil-kt:coil-bom:2.2.2")
    implementation("io.coil-kt:coil-base")
    implementation("io.coil-kt:coil-compose-base")
    implementation("io.coil-kt:coil-gif")
    implementation("io.coil-kt:coil-video")
    implementation("io.coil-kt:coil-svg")
    implementation(platform("org.jetbrains.kotlinx:kotlinx-serialization-bom:1.4.1"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-okio")
    implementation("com.google.guava:guava:31.1-android")
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("org.eclipse.collections:eclipse-collections:11.1.0")
    implementation("com.batch.android:batch-sdk:1.19.3")
    implementation("com.adjust.sdk:adjust-android:4.33.2")

    val androidTestVersion = "1.5.0"
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation("org.robolectric:robolectric:4.9.2")
    testImplementation(libs.hilt.android.test)
    testImplementation("com.squareup.okhttp3:mockwebserver3:$okhttpVersion")
    testImplementation("androidx.work:work-testing:$workVersion")
    testImplementation("app.cash.turbine:turbine:0.12.1")
    testImplementation(libs.kotlin.test)
    testImplementation("org.apache.commons:commons-csv:1.9.0")
    val testParameterInjectorVersion = "1.10"
    testImplementation("com.google.testparameterinjector:test-parameter-injector:$testParameterInjectorVersion")
    testImplementation("androidx.test:rules:$androidTestVersion")
    testImplementation("org.apache.commons:commons-text:1.10.0")
    testImplementation("androidx.room:room-testing:$roomVersion")
    kaptTest(libs.hilt.compiler)
    androidTestImplementation("androidx.test:core:$androidTestVersion")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:$androidTestVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.ext:truth:1.5.0")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    androidTestImplementation(libs.hilt.android.test)
    androidTestImplementation("androidx.window:window")
    androidTestImplementation("com.google.testparameterinjector:test-parameter-injector:$testParameterInjectorVersion")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.work:work-testing:$workVersion")
    androidTestUtil("androidx.test:orchestrator:1.4.2")
    kaptAndroidTest(libs.hilt.compiler)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering {
                    option("lite")
                }
                val kotlin by registering {
                    option("lite")
                }
            }
        }
    }
}
buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.android.plugin)
        classpath(libs.kotlin.plugin)
        classpath("com.google.gms:google-services:4.3.14")
        classpath("com.google.firebase:perf-plugin:1.4.2")
        classpath("com.google.firebase:firebase-appdistribution-gradle:3.1.1")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
        classpath("com.spotify.ruler:ruler-gradle-plugin:1.0.0")
        classpath(libs.hilt.plugin)
        classpath("com.vdurmont:semver4j:3.1.0")
        classpath("com.squareup:javapoet:1.13.0")
        classpath("com.squareup.okio:okio:3.2.0")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
    kotlin("multiplatform")
}

kotlin { // Extension for easy setup
    jvmToolchain(libs.jvm)

    jvm().mainRun {
        mainClass.set("MainKt")
    }
    js {
        nodejs()
        binaries.executable()
    }
    wasmJs {
        nodejs()
        binaries.executable()
    }
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        hostOs == "Linux" && isArm64 -> linuxArm64("native")
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    
    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
    
}

repositories {
    mavenCentral()
}


rootProject.the<NodeJsRootExtension>().apply {
    // this version was able to work with a "hello world" wasm target
//    nodeVersion = "21.0.0-v8-canary202309143a48826a08"
    nodeVersion = "22.0.0-v8-canary202311294a3904c9c5"
    nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
}

rootProject.tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}

// TODO create runAll Task
tasks.register("runAll") {
    dependsOn("jvmRun")
    dependsOn("jsNodeRun")
    dependsOn("runReleaseExecutableNative")
    dependsOn("wasmJsNodeRun")
}

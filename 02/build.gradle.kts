plugins {
    id("aoc-2023-kotlin-conventions")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared"))
        }
    }
}
plugins {
//    alias(libs.plugins.android.library)
//    id("maven-publish")
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.sissi.lib.xhook"
    compileSdk = 34

    ndkVersion = "23.1.7779620" // koom使用的版本，它的agp7.1.0
//    ndkVersion = "26.1.10909125"

    defaultConfig {
        minSdk = 18

        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags("")
                arguments("-DANDROID_STL=c++_shared")
            }
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
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    buildFeatures {
        prefabPublishing = true
    }

    prefab {
        create("xhook"){
            headers="src/main/cpp/"
        }
    }
}

dependencies {

}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.sissi.lab"
            artifactId = "xhook"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        mavenLocal()

//        maven {
//            name="myrepo"
////            url = uri("${rootProject.projectDir}/build/repository")
//            url = uri("E:\\_tmp")
//        }
    }
}

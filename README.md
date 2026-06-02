# Launcher-For-TV

A modern, high-performance Android TV launcher application built entirely with Jetpack Compose. This project serves as a Leanback launcher replacement, optimized for D-Pad navigation, fast application discovery, and a seamless television user experience.

## Architecture & Technology Stack

The application leverages modern Android development practices and libraries:

*   **UI Framework**: Jetpack Compose (with Material 3) optimized for Android TV screens.
*   **Language**: Kotlin and C++ (via JNI).
*   **Asynchronous Programming**: Kotlin Coroutines and Flows.
*   **Networking**: Retrofit and OkHttp.
*   **Data Persistence**: Room Database and Moshi for JSON parsing.
*   **Performance Optimization**: A custom JNI bridge (`app/src/main/cpp/native-utils.cpp` and `NativeStringMatcher.kt`) is utilized for high-speed, case-insensitive string matching during application filtering. It includes a Kotlin fallback mechanism if native libraries fail to load.
*   **Testing**: Comprehensive testing suite using JUnit, Robolectric, and Roborazzi for automated UI verification.

## Core Features

*   **Leanback Launcher Entrypoint**: Fully implements the `android.intent.category.LEANBACK_LAUNCHER` specification.
*   **D-Pad Optimized Navigation**: Custom focus handling to ensure smooth directional pad interaction across all UI elements.
*   **Application Discovery**: Rapid search and filtering capabilities for installed applications, powered by the C++ native bridge.
*   **Favorites Management**: Users can pin and organize their favorite applications for quick access.
*   **Automated Deployments**: Continuous Integration and Continuous Deployment (CI/CD) pipelines configured via GitHub Actions for automated release management.

## Development Prerequisites

Ensure your development environment meets the following requirements before proceeding:

*   Android Studio (latest stable release recommended)
*   Java Development Kit (JDK) 11
*   Android SDK (Minimum API 24, Target API 36)
*   Android NDK (Required for building the JNI C++ performance bridge)

## Local Setup Instructions

1.  **Clone and Open**: Clone the repository and open the project directory in Android Studio.
2.  **Environment Variables**: Create a `.env` file in the project root directory (based on `.env.example` if available) and configure necessary variables, such as `GEMINI_API_KEY`.
3.  **Build and Test**: Use the Gradle wrapper to compile the project and run tests:
    *   Run Unit Tests: `./gradlew testDebugUnitTest`
    *   Build Release APK: `./gradlew assembleRelease`

## Continuous Integration / Continuous Deployment (CI/CD)

The project utilizes GitHub Actions for automated workflows (`.github/workflows/android-release.yml`). 

Upon every push to the repository, the workflow executes the following pipeline:
1.  Executes the automated unit testing suite.
2.  Compiles and signs the release APK.
3.  Uploads the generated APK as a workflow artifact.
4.  Creates a new GitHub Release and attaches the signed APK as a downloadable asset.

### GitHub Actions Configuration

To enable automated signing in the CI/CD pipeline, the following secrets must be configured in a GitHub Actions environment named `KEYSTORE`:

*   `KEYSTORE_PASSWORD`: The password for the keystore file.
*   `KEY_ALIAS`: The alias associated with the signing key.
*   `KEY_PASSWORD`: The password for the specific key alias.
*   `SIGNING_KEY`: The base64-encoded representation of your `.jks` keystore file.

The workflow script handles decoding the keystore and injecting the necessary parameters (`KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`, `KEYSTORE_PATH`) into the Gradle build process.

SpeechMatcherLib 🎤➡️📝

A lightweight Android library for real-time speech recognition and text matching. Easily compare spoken words with target text and get similarity scores.

Features
🎤 Real-time Speech Recognition - Continuous speech-to-text conversion

📊 Similarity Scoring - Compare spoken text with target using advanced algorithms

🚀 Easy Integration - Simple API with Kotlin Coroutines support

🎯 Customizable Thresholds - Configurable matching sensitivity

📱 Jetpack Compose Ready - Built with modern Android development in mind

🔌 Installation
Use JitPack (recommended)

Add JitPack repository to your project-level settings.gradle:
```
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your app-level build.gradle:
```
dependencies {
    implementation("com.github.nakulsudhakar035:SpeechMatcherLib:1.0.0")
}
```

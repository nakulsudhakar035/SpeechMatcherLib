# SpeechMatcherLib 🎤➡️📝

A lightweight Android library for **real-time speech recognition + text matching**.  
Compare spoken words with target text and compute similarity scores easily.

[![Maven Central](https://img.shields.io/maven-central/v/com.yourorg/SpeechMatcherLib)](https://search.maven.org/artifact/com.yourorg/SpeechMatcherLib)  
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8-blue.svg)](https://kotlinlang.org/)  
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)

---

## 🚀 Features

- 🎤 **Real-time Speech Recognition** — Continuously transcribe spoken words  
- 📊 **Similarity Scoring** — Compute how close spoken text is to expected text  
- 🧩 **Easy Integration** — Simple Kotlin API with coroutine support  
- ⚙️ **Configurable Thresholds** — Adjust sensitivity / matching logic  
- 🧪 **Jetpack Compose Friendly** — Ready to plug in modern Android apps  

---

## 🛠️ Installation

### Option A: Use JitPack (recommended)

1. Add JitPack to your project-level `settings.gradle`:

   ```kotlin
   dependencyResolutionManagement {
     repositories {
       google()
       mavenCentral()
       maven { url = uri("https://jitpack.io") }
     }
   }
Add the library to your app-level build.gradle:

```kotlin
Copy code
dependencies {
  implementation("com.github.nakulsudhakar035:SpeechMatcherLib:<version>")
}
```
Replace <version> with the tag (e.g. 1.0.0) you release on GitHub.

Option B: Include as Module (during development)
Clone alongside your app project.

In your app’s settings.gradle:

```kotlin
Copy code
include(":SpeechMatcherLib")
project(":SpeechMatcherLib").projectDir = file("../SpeechMatcherLib")
```
Add dependency in build.gradle:

```kotlin
Copy code
dependencies {
  implementation(project(":SpeechMatcherLib"))
}
```

🧩 Usage Example
```kotlin
Copy code
val speechMatcher = SpeechMatcher(context)

// Start listening & match to a target phrase
speechMatcher.startListening("hello world") { result ->
    // result.spokenText: recognized speech
    // result.similarityScore: Float between 0.0 and 1.0
    Log.d("SM", "Spoken: ${result.spokenText}, Score: ${result.similarityScore}")
}

// Later, stop listening
speechMatcher.stopListening()
```

🔧 API / Configuration
Setting	Default	Description
threshold	0.75	Minimum score to consider as match
maxLength	100	Max characters to compare

```kotlin
Copy code
speechMatcher.setThreshold(0.8f)
speechMatcher.setMaxLength(200)
```

📌 Roadmap & Future Work
Add support for alternative matching algorithms (Levenshtein, Cosine)

Add offline speech recognition fallback

Provide detailed logs / debug mode

Publish on Maven Central

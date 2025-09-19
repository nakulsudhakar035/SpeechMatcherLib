package com.nakuls.speechmatchlib.model

data class SpeechMatchResult(
    val spokenText: String,
    val targetText: String,
    val similarity: Float,
) {
    val isMatch: Boolean
        get() = similarity >= 0.8f
}

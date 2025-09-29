package com.nakuls.speechmatchlib.api

import android.app.Application
import com.nakuls.speechmatchlib.core.SpeechRecognizerManager
import com.nakuls.speechmatchlib.core.TextMatcher
import com.nakuls.speechmatchlib.model.SpeechMatchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SpeechMatch(
    app: Application,
    private val targetText: String
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val recognizer = SpeechRecognizerManager(app)

    val spokenText: StateFlow<String> = recognizer.spokenText

    val results: StateFlow<SpeechMatchResult> =
        recognizer.spokenText
            .filterNotNull()
            .map { spoken ->
                val similarity = TextMatcher.similarityRatio(spoken, targetText)
                SpeechMatchResult(spoken, targetText, similarity)
            }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SpeechMatchResult("", "", 0.0F)
            )

    fun start() = recognizer.startListening()
    fun stop() = recognizer.release()

    fun SpeechMatch.getResultsStateFlow(): StateFlow<SpeechMatchResult> = results
}
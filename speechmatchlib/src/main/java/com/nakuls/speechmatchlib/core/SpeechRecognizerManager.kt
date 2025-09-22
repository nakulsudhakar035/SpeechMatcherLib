package com.nakuls.speechmatchlib.core

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class SpeechRecognizerManager(
    private val application: Application
) {

    private var recognizer: SpeechRecognizer? = null
    private val _spokenText = MutableStateFlow("Idle")
    val spokenText: StateFlow<String> = _spokenText

    fun startListening() {
        if (!SpeechRecognizer.isRecognitionAvailable(application)) {
            _spokenText.value = "Speech recognition not available"
            return
        }

        if (recognizer == null) {
            recognizer = SpeechRecognizer.createSpeechRecognizer(application)
        }

        recognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                _spokenText.value = "Listening..."
            }

            override fun onBeginningOfSpeech() {
                _spokenText.value = "Speak now..."
            }

            override fun onEndOfSpeech() {
                // User stopped speaking → recognizer will deliver results in onResults
                _spokenText.value = "Processing..."
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val text = matches?.firstOrNull().orEmpty()
                _spokenText.value = "Heard: $text"
            }

            override fun onError(error: Int) {
                _spokenText.value = "Error code: $error"
            }

            override fun onPartialResults(partialResults: Bundle?) {
                val partial = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!partial.isNullOrEmpty()) {
                    _spokenText.value = "Partial: ${partial.first()}"
                }
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }
        recognizer?.startListening(intent)
    }

    fun release() {
        recognizer?.destroy()
        recognizer = null
    }
}
package com.nakuls.speechmatchlib.core

import kotlin.math.max

object TextMatcher {

    fun similarityRatio(s1: String, s2: String): Float {
        if (s1.isBlank() || s2.isBlank()) return 0f
        val distance = levenshtein(s1.lowercase(), s2.lowercase())
        val maxLen = max(s1.length, s2.length).toFloat()
        return 1f - (distance / maxLen)
    }

    private fun levenshtein(lhs: CharSequence, rhs: CharSequence): Int {
        val lhsLength = lhs.length + 1
        val rhsLength = rhs.length + 1

        val cost = Array(lhsLength) { IntArray(rhsLength) }

        for (i in 0 until lhsLength) cost[i][0] = i
        for (j in 0 until rhsLength) cost[0][j] = j

        for (i in 1 until lhsLength) {
            for (j in 1 until rhsLength) {
                val substitutionCost = if (lhs[i - 1] == rhs[j - 1]) 0 else 1
                cost[i][j] = listOf(
                    cost[i - 1][j] + 1,             // deletion
                    cost[i][j - 1] + 1,             // insertion
                    cost[i - 1][j - 1] + substitutionCost // substitution
                ).minOrNull()!!
            }
        }
        return cost[lhsLength - 1][rhsLength - 1]
    }
}
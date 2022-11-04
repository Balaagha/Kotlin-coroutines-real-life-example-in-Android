package com.example.androidimpltemplate.data.database.utils

import java.text.DecimalFormat
import java.util.*


// End region

// Movie list region
fun Float.convertToFiveStarScale(): Float {
    val decimalFormat = DecimalFormat("#.#")
    return this
}
fun Float.splitToWholeAndFraction(): Pair<Float, Float> {
    val fraction = this % 1
    val whole = this - fraction
    return Pair(whole, fraction)
}
fun String.toLanguageName(): String {
    val locale = Locale(this)
    return locale.displayName
}
fun String.toYear(): String {
    return this.split("-").first()
}
// End region

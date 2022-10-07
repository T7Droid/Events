package co.thyagoneves.eventnews.utils

import java.text.NumberFormat
import java.util.*

fun formatCurrency(input: Double): String {
    if (input != null) {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("BRL")
        return format.format(input.toInt())
    } else {
        return "Evento gratuito"
    }
}
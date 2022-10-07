package co.thyagoneves.eventnews.model

import java.io.Serializable

data class EventsListItem(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double?,
    val longitude: Double?,
    val people: List<String>,
    val price: Double,
    val title: String
): Serializable
package co.thyagoneves.eventnews.repositories

import co.thyagoneves.eventnews.model.EventsList
import co.thyagoneves.eventnews.rest.RetrofitService

class EventsRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllEvents() = retrofitService.getAllEvents()
}
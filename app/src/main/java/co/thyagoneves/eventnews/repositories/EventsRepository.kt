package co.thyagoneves.eventnews.repositories

import co.thyagoneves.eventnews.model.EventsList
import co.thyagoneves.eventnews.model.Person
import co.thyagoneves.eventnews.rest.RetrofitService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class EventsRepository @Inject constructor(private val retrofitService: RetrofitService) {

    fun getAllEvents() = retrofitService.getAllEvents()

    fun doCheckIn(person: Person) = retrofitService.doCheckIn(person)

}
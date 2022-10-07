package co.thyagoneves.eventnews.repositories

import co.thyagoneves.eventnews.model.EventsList
import co.thyagoneves.eventnews.model.Person
import okhttp3.ResponseBody
import retrofit2.Call

interface EventsRepositoryImpl {

    fun getAllEvents(): Call<EventsList>

    fun doCheckin(person: Person): Call<ResponseBody>
}
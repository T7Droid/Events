package co.thyagoneves.eventnews.repositories

import co.thyagoneves.eventnews.model.Person
import co.thyagoneves.eventnews.rest.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class EventsRepository @Inject constructor(private val retrofitService: RetrofitService) : EventsRepositoryImpl {

   override fun getAllEvents() = retrofitService.getAllEvents()

   override fun doCheckin(person: Person): Call<ResponseBody>  = retrofitService.doCheckIn(person)

}
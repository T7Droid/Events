package co.thyagoneves.eventnews.rest

import co.thyagoneves.eventnews.model.EventsList
import co.thyagoneves.eventnews.model.Person
import dagger.Provides
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("events")
    fun getAllEvents(): Call<EventsList>

    @POST("checkin")
    fun doCheckIn(@Body person: Person): Call<ResponseBody>

}
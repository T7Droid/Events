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

    companion object {

        private val retrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }
        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}
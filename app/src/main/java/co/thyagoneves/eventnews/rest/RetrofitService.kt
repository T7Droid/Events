package co.thyagoneves.eventnews.rest

import co.thyagoneves.eventnews.model.EventsList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("events")
    fun getAllEvents(): Call<EventsList>

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
package co.thyagoneves.eventnews.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.thyagoneves.eventnews.model.EventsList
import co.thyagoneves.eventnews.repositories.EventsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class EventsViewModel constructor(private val repository: EventsRepository) : ViewModel(){
    val status = MutableLiveData<Boolean>()
    val eventsList = MutableLiveData<EventsList>()

    fun getAllEvents() {

        val request = repository.getAllEvents()
        request.enqueue(
            object : Callback<EventsList>{
                override fun onResponse(call: Call<EventsList>, response: Response<EventsList>) {
                    if(response.code() == HttpURLConnection.HTTP_OK) {
                        status.postValue(true)
                        eventsList.postValue(response.body())
                    } else {
                        status.postValue(false)
                    }
                }

                override fun onFailure(call: Call<EventsList>, t: Throwable) {
                  status.postValue(false)

                }
            }
        )
    }
}
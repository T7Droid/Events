package co.thyagoneves.eventnews.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.thyagoneves.eventnews.model.EventsList
import co.thyagoneves.eventnews.model.Person
import co.thyagoneves.eventnews.repositories.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: EventsRepository) : ViewModel(){
    val checkInSucessful = MutableLiveData<Boolean>()
    val eventsList = MutableLiveData<EventsList>()

    fun getAllEvents() {

        val getEventsRequest = repository.getAllEvents()
        getEventsRequest.enqueue(
            object : Callback<EventsList>{
                override fun onResponse(call: Call<EventsList>, response: Response<EventsList>) {
                    if(response.code() == HttpURLConnection.HTTP_OK) {
                        eventsList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<EventsList>, t: Throwable) {

                }
            }
        )
    }

    fun doCheckIn(id: String, name: String, email: String) {
        val person = Person(id, name, email)
        val doCheckInRequest = repository.doCheckIn(person)
        doCheckInRequest.enqueue(
            object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        checkInSucessful.postValue(true)
                    } else {
                        checkInSucessful.postValue(false)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    checkInSucessful.postValue(false)
                }
            }
        )
    }
}